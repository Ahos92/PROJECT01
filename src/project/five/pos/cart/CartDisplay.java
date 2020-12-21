package project.five.pos.cart;

import java.awt.*;
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

public class CartDisplay extends JFrame {

	JPanel south_p, center_p;

	JButton pay_btn, cancle_btn;

	JLabel info_lab;
	
	JTable cart_table;
	DefaultTableModel dtm;
	JScrollPane scroll;
	String[] header= {"�޴�", "�ɼ�", "����", "����", "��", "��", "���"};
	int cell_btn_size;
	
	int order_num;
	CartDAO cart;
	
	public CartDisplay(String device_id, Object[][] select_list) {	
		cart = new CartDAO();

		setLayout(new BorderLayout());
		
		south_p = new JPanel();
		center_p = new JPanel();

		info_lab = new JLabel("�ֹ� ����");
		
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
		scroll.setPreferredSize(new Dimension(480, 200));
		
		// ���� ���� ��ư
		cell_btn_size = 40;	
		createcellBtn(cart_table, "���", cell_btn_size);
		
		createcellBtn(cart_table, "��", cell_btn_size);
		
		createcellBtn(cart_table, "��", cell_btn_size);

		// ����
		pay_btn = new CartBtn("����", new PaymentPageAction(this, dtm, order_num, device_id));
		// ���
		cancle_btn = new CartBtn("���", new CancleAction(this, dtm));
	
		center_p.add(scroll);
		south_p.add(pay_btn);
		south_p.add(cancle_btn);
		
		add(info_lab, BorderLayout.NORTH);
		add(south_p, BorderLayout.SOUTH);
		add(center_p, BorderLayout.CENTER);
		
		TestSwingTools.initTestFrame(this, "��ٱ��� ȭ��", true);
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

