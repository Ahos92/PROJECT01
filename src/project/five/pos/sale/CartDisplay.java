package project.five.pos.sale;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import project.five.pos.TestSwingTools;
import project.five.pos.db.PosVO;
import project.five.pos.sale.btn.CartBtn;
import project.five.pos.sale.btn.action.*;
import project.five.pos.sale.btn.render.*;

public class CartDisplay extends JFrame {

	JPanel south_p, center_p;

	JButton pay_btn, cancle_btn;

	JLabel info_lab;
	
	JTable cart_table;
	DefaultTableModel dtm;
	JScrollPane scroll;
	String[] header= {"�޴�", "�ɼ�", "����", "��", "��", "����", "���"};
	Object[][] select_list;
	int cell_btn_size;
	
	ArrayList<PosVO> cart_list;
	int order_cnt, order_num;
	CartDAO dao;
	
	Font font;
						// �гη� ������ �����ϸ� 
						// ���������ӿ� ���� ���� ������ ���� ���� ������
	public CartDisplay(String device_id) {	
		// ���� ������
		// 	 ��ư���� ��ǰ�� ���� ���� ���� (�ѹ��� �ֹ���)
		// ���ο� �ֹ���ȣ ���ö� 1����
		dao = new CartDAO();
		cart_list = new ArrayList<>();
		order_num = dao.MaxOrderNumber();
		order_num++;
//		cart_list.add(dao.testOrder("�Ƹ޸�ī��", "HOT", 2));
		cart_list.add(dao.testOrder("�Ƹ޸�ī��", "ICE", 1));
		cart_list.add(dao.testOrder("ȫ��", "HOT", 1));
		cart_list.add(dao.testOrder("����ũ", null, 1));
//		cart_list.add(dao.testOrder("����", null, 1));
		order_cnt = cart_list.size();	
	
		setLayout(new BorderLayout());
		
		south_p = new JPanel();
		center_p = new JPanel();
		
		// �ֹ����� ��
		info_lab = new JLabel("�ֹ� ����");
			
		// �ֹ� ���� ���̺�
		//	- ��ǰ��ü ���� �迭�� �޾Ƽ� ���
		select_list = new Object[order_cnt][6];
		for (int i = 0; i < order_cnt; i++) {
			select_list[i][0] = cart_list.get(i).getProduct_name();
			select_list[i][1] = cart_list.get(i).getTermsofcondition();
			select_list[i][2] = cart_list.get(i).getSelected_item();
			select_list[i][5] = cart_list.get(i).getProduct_price() * cart_list.get(i).getSelected_item();
		};
		dtm = new DefaultTableModel(select_list, header);
		cart_table = new JTable(dtm);
		scroll = new JScrollPane(cart_table);
		scroll.setPreferredSize(new Dimension(480, 100));
		
		cell_btn_size = 40;	
		cart_table.getColumn("���").setCellRenderer(new DeleteBtnRender());
		cart_table.getColumn("���").setCellEditor(new DeleteAction(new JCheckBox(), cart_table, dtm));
		cart_table.getColumn("���").setPreferredWidth(cell_btn_size);
		
		cart_table.getColumn("��").setCellRenderer(new UpDonwBtnRender("��"));
		cart_table.getColumn("��").setCellEditor(new UpDownAction(new JCheckBox(), cart_table, "��"));
		cart_table.getColumn("��").setPreferredWidth(cell_btn_size);
		
		cart_table.getColumn("��").setCellRenderer(new UpDonwBtnRender("��"));
		cart_table.getColumn("��").setCellEditor(new UpDownAction(new JCheckBox(), cart_table, "��"));
		cart_table.getColumn("��").setPreferredWidth(cell_btn_size);

		// ���� ��ư -> cartTable�� ������ ����(commit X) �� ����Frame false , ���������� true
		//					�гο� �����ڵ� ����
		pay_btn = new CartBtn("����", new PaymentPageAction(this, dtm, order_num, device_id));

		// ��� ��ư -> (����ȭ������ ���ư���) ��ٱ��� �ʱ�ȭ
		cancle_btn = new CartBtn("���", new CancleAction(this, dtm));
	
		center_p.add(scroll);
		south_p.add(pay_btn);
		south_p.add(cancle_btn);
		
		add(info_lab, BorderLayout.NORTH);
		add(south_p, BorderLayout.SOUTH);
		add(center_p, BorderLayout.CENTER);
		
		TestSwingTools.initTestFrame(this, "��ٱ��� ȭ��", true);
	}

	public static void main(String[] args) {
		new CartDisplay("1234");
	}
}

