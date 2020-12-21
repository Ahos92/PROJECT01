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

public class CartDisplay extends JDialog {

	JPanel south_p, center_p;

	JButton pay_btn, cancle_btn, card_btn, cash_btn;

	JLabel info_lab;

	JTable cart_table;
	DefaultTableModel dtm;
	JScrollPane scroll;
	String[] header= {"�޴�", "�ɼ�", "����", "����", "��", "��", "���"};
	int cell_btn_size;

	int order_num;
	CartDAO cart;

	int payment_type;
	JRadioButton check_rbtn;

	public CartDisplay(JFrame frame, String title, int device_id, Object[][] select_list) {	
		super(frame, title);
		setLayout(new BorderLayout());
		setSize(400, 750);
		setResizable(false);// ������ ���� �Ұ�
		setLocation(810, 150);
		setModal(true);

		cart = new CartDAO();

		setLayout(new BorderLayout());

		south_p = new JPanel();
		center_p = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 70));

		info_lab = new JLabel("�ֹ� ���� Ȯ��");
		info_lab.setFont(new Font("����", Font.BOLD, 20));
		info_lab.setPreferredSize(new Dimension(500, 30));

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
		scroll.setPreferredSize(new Dimension(380, 150));

		cellWitdths(cart_table);

		// ���� ���� ��ư
		cell_btn_size = 40;	
		createcellBtn(cart_table, "���", cell_btn_size);

		createcellBtn(cart_table, "��", cell_btn_size);

		createcellBtn(cart_table, "��", cell_btn_size);

//
//		card_btn = new CartBtn("ī��", 120);
//		check_rbtn = new JRadioButton();
//		card_btn.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				card_btn.setEnabled(true);
//				cash_btn.setEnabled(false);
//				check_rbtn.setSelected(true);
//			}
//		});
//
//		cash_btn = new CartBtn("����", 120);
//		cash_btn.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				cash_btn.setEnabled(true);
//				card_btn.setEnabled(false);
//				check_rbtn.setSelected(false);
//			}
//		});
		
		// ����
		pay_btn = new CartBtn("����", new PaymentPageAction(this, frame, dtm, order_num, device_id));
		// ���
		cancle_btn = new CartBtn("���", new CancleAction(this, dtm));

		center_p.add(scroll);
//		center_p.add(card_btn);
//		center_p.add(cash_btn);
//		center_p.add(check_rbtn);
		south_p.add(cancle_btn);
		south_p.add(pay_btn);

		add(info_lab, BorderLayout.NORTH);
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

