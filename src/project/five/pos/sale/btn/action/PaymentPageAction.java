package project.five.pos.sale.btn.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

import project.five.pos.payment.swing.PayPanel;
import project.five.pos.sale.CartDAO;
import project.five.pos.db.PosVO;

public class PaymentPageAction implements ActionListener{

	JFrame present_frame;

	ArrayList<PosVO> cart_list, order_list, update_cart; 
	PosVO updateDTO;
	int order_num, order_cnt, price;

	DefaultTableModel dtm;
	String device_id;

	ArrayList<String> lists;

	CartDAO dao = new CartDAO(); 

	public PaymentPageAction(JFrame present_frame, 
			DefaultTableModel dtm, int order_num, int order_cnt, String device_id) {
		this.present_frame = present_frame;
		this.dtm = dtm;
		this.order_num = order_num;	
		this.order_cnt = order_cnt;
		this.device_id = device_id;
	}

	/*
	 	������Ʈ�� ���� ���� �޾Ƽ�
	 	 - savecart_list() �̿� 
	 	 - ������ ���� (DB cartTABLE�� ����)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		dao.saveCartlist(getUpdateDTO(), order_num, device_id);
		
		try {
			// ����ȭ�鿡 �Ѱ��� ������ �ֹ���ȣ, �Ѱ���, ��ǰ �̸�
			order_list = dao.searchCart("order_no", String.valueOf(order_num));
			price = dao.SumByOrderNum(order_num);
			lists = new ArrayList<>();
			for (int i = 0; i < order_cnt; i++) {
				String format = String.format("%s (%s)",  
						order_list.get(i).getProduct_name(),
						order_list.get(i).getTermsofcondition());
				if (format.contains("null")) {
					lists.add(order_list.get(i).getProduct_name());
				} else {
					lists.add(format);
				}
			}	
			// ������ �Ű������� �Ѱ��� ����
			new PayPanel();

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		present_frame.dispose();
	}

	/*
	    �ֹ����� �߰�, ����, ���� ���޹޾Ƽ� 
	     - �ǽð� ������Ʈ �ϱ� 
	     - cart TABLE�� ���������� �����ų ������ ����� �޼���
	 */
	private ArrayList<PosVO> getUpdateDTO() {
		update_cart = new ArrayList<>();
		updateDTO = new PosVO();
		if (dtm.getRowCount() == 0) {		
			System.err.println("������ ǰ���� �����ϴ�.");		

		} else {
			for (int i = 0; i < dtm.getRowCount(); i++) {
				update_cart.add(dao.testOrder((String)dtm.getValueAt(i, 0), 
						(String)dtm.getValueAt(i, 1), 
						(Integer)dtm.getValueAt(i, 2)));
			}
		}
		return update_cart;
	}
}
