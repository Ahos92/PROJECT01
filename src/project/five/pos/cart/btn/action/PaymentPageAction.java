package project.five.pos.cart.btn.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import project.five.pos.payment.swing.PayPanel;
import project.five.pos.db.PosVO;
import project.five.pos.menu.MenuDisplay;

public class PaymentPageAction implements ActionListener{

	JFrame present_frame;

	ArrayList<PosVO> update_cart; 
	PosVO updateVO;
	int order_num, price;

	DefaultTableModel dtm;
	String device_id;

	ArrayList<String> lists;


	public PaymentPageAction(JFrame present_frame, 
			DefaultTableModel dtm, int order_num, String device_id) {
		this.present_frame = present_frame;
		this.dtm = dtm;
		this.order_num = order_num;	
		this.device_id = device_id;
	}

	/*
	 	������Ʈ�� ���� ���� �޾Ƽ�
	 	 - savecart_list() �̿� 
	 	 - ������ ���� (DB cartTABLE�� ����)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		update_cart = getUpdateVO();

		if (update_cart.size() == 0) {
			JOptionPane.showMessageDialog(present_frame, "������ ǰ���� �����ϴ�!!", "����", JOptionPane.ERROR_MESSAGE);
			present_frame.dispose();
			new MenuDisplay();
			
		} else {
			lists = new ArrayList<>();
			System.out.println("<���� â���� �ѱ� ��� >");
			for (int i = 0; i < update_cart.size(); i++) {
				String format = String.format("%s (%s)",  
						update_cart.get(i).getProduct_name(),
						update_cart.get(i).getTermsofcondition());
				if (format.contains("null")) {
					lists.add(update_cart.get(i).getProduct_name());
				} else {
					lists.add(format);
				}
				price += update_cart.get(i).getTotal_price();
				System.out.println("---------------------------------");
				System.out.println("��ǰ �̸� : " + lists.get(i));
				System.out.println("��ǰ ���� : " + update_cart.get(i).getSelected_item() + " ��");
				System.out.println("�� ��ǰ ���� : " + update_cart.get(i).getProduct_price() + " ��");
			}	
			System.out.println("---------------------------------");
			System.out.println("�ֹ� ��ȣ : "+ order_num);
			System.out.println("�� ���� : " + price);

			try {
				// ����ȭ�鿡 �Ѱ��� ������ �ֹ���ȣ(order_num), �Ѱ���(price), List<��ǰ �̸�>
				new PayPanel(order_num, price, lists, update_cart);

			} catch (IOException e1) {
				e1.printStackTrace();
			}
			present_frame.dispose();
		}
	}

	/*
	    �ֹ����� �߰�, ����, ���� ���޹޾Ƽ� 
	     - �ǽð� ������Ʈ �ϱ� 
	     - cart TABLE�� ���������� �����ų ������ ����� �޼���
	 */
	private ArrayList<PosVO> getUpdateVO() {
		update_cart = new ArrayList<>();

		for (int i = 0; i < dtm.getRowCount(); i++) {
			updateVO = new PosVO(); 

			updateVO.setProduct_name((String)dtm.getValueAt(i, 0));
			updateVO.setTermsofcondition((String)dtm.getValueAt(i, 1));
			updateVO.setSelected_item((Integer)dtm.getValueAt(i, 2));
			updateVO.setTotal_price((Integer)dtm.getValueAt(i, 3));
			updateVO.setProduct_price((Integer)dtm.getValueAt(i, 3) / (Integer)dtm.getValueAt(i, 2));

			update_cart.add(updateVO);
		}

		return update_cart;
	}
}