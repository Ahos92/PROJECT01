package project.five.pos.cart;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import project.five.pos.TestSwingTools;
import project.five.pos.cart.btn.CartBtn;
import project.five.pos.cart.btn.action.*;
import project.five.pos.cart.btn.render.*;
import project.five.pos.db.PosVO;
import project.five.pos.device.comp.btn.DeviceBtn;

public class CartPopUpDisplay extends JDialog {

	JPanel south_p, center_p, north_p;

	JButton cancle_btn, check_btn;

	JLabel info_lab;

	JTable cart_table;
	DefaultTableModel dtm;
	JScrollPane scroll;
	String[] header= {"�޴�", "�ɼ�", "����", "����", "��", "��", "���"};
	int cell_btn_size;

	int order_num;
	CartDAO cart;
	
	String[] images_path = {
			"assets/images/device/credit-card.png",
			"assets/images/device/money.png",
			"assets/images/device/close.png"
	};

	int width;
	int height;
	
	public CartPopUpDisplay(JFrame frame, String title, int device_id, Object[][] select_list) {	
		super(frame, title);
		width = 400;
		height = 500;
		setLayout(new BorderLayout());
		setSize(width, height);
		setResizable(false);// ������ ���� �Ұ�
		setLocation(810, 200);
		setModal(true);

		cart = new CartDAO();

		setLayout(new BorderLayout());

		north_p = new JPanel(new BorderLayout());
		south_p = new JPanel();
		center_p = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 40));

		info_lab = new JLabel("�ֹ� ���� Ȯ��");
		info_lab.setFont(new Font("����", Font.BOLD, 20));
		info_lab.setPreferredSize(new Dimension(width, 30));

		// �ֹ� ��ȣ
		order_num = cart.MaxOrderNumber();
		order_num++;

		// ���õ� ��ǰ ���̺�
		dtm = new DefaultTableModel(select_list, header);

		// null�� ���� 
		int delete = 0;
		for (int i = 0; i < select_list.length; i++) {
			if (select_list[i][0] == null) {
				dtm.removeRow(delete);
				delete--;
			} 
			delete++;
		}

		cart_table = new JTable(dtm);
		scroll = new JScrollPane(cart_table);
		scroll.setPreferredSize(new Dimension(width - 15, height - 350));

		cellWitdths(cart_table);

		// ���� ���� ��ư
		cell_btn_size = 40;	
		createcellBtn(cart_table, "���", cell_btn_size);

		createcellBtn(cart_table, "��", cell_btn_size);

		createcellBtn(cart_table, "��", cell_btn_size);
		
		// ����
		check_btn = new DeviceBtn("Ȯ��", images_path[1], 120,  
				new PaymentPageAction(this, frame, dtm, order_num, device_id)
		);


		// ���
		cancle_btn = new DeviceBtn(50, images_path[2],new CancleAction(this, dtm));

		north_p.add(info_lab, BorderLayout.WEST);
		north_p.add(cancle_btn, BorderLayout.EAST);
		center_p.add(scroll);
		center_p.add(check_btn);

		add(north_p, BorderLayout.NORTH);
		add(south_p, BorderLayout.SOUTH);
		add(center_p, BorderLayout.CENTER);

		setVisible(true);
	}

	
	private void cellWitdths(JTable cart_table) {
		cart_table.getColumn("�޴�").setPreferredWidth(110);
		cart_table.getColumn("�ɼ�").setPreferredWidth(35);
		cart_table.getColumn("����").setPreferredWidth(25);
		cart_table.getColumn("����").setPreferredWidth(50);
		cart_table.getColumn("��").setPreferredWidth(75);
		cart_table.getColumn("��").setPreferredWidth(75);
		cart_table.getColumn("���").setPreferredWidth(75);

	}

	/*
	 *	���̺� ������ ��ư ����� �޼��� 		
	 */
	private void createcellBtn(JTable cart_table, String btn_txt, int cell_btn_size) {
		if (btn_txt.equals("���")) {
			cart_table.getColumn(btn_txt).setCellRenderer(new DeleteBtnRender());
			cart_table.getColumn(btn_txt).setCellEditor(new DeleteAction(new JCheckBox(), cart_table, dtm, this));
			cart_table.getColumn(btn_txt).setPreferredWidth(cell_btn_size);
		} else {
			cart_table.getColumn(btn_txt).setCellRenderer(new UpDonwBtnRender(btn_txt));
			cart_table.getColumn(btn_txt).setCellEditor(new UpDownAction(new JCheckBox(), cart_table, btn_txt));
			cart_table.getColumn(btn_txt).setPreferredWidth(cell_btn_size);
		}
	}

}

