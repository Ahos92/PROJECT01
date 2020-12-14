package project.five.pos.sale.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

import project.five.pos.payment.swing.PayPanel;
import project.five.pos.sale.SaleDAO;
import project.five.pos.sale.SaleDTO;

public class PaymentPageAction implements ActionListener{

	JFrame presentFrame;

	ArrayList<SaleDTO> cartlist, orderList, updateCart; 
	SaleDTO updateDTO;
	int orderNumber, orderCount, price;

	DefaultTableModel dtm;
	String device_id;
	
	ArrayList<String> lists;
	
	SaleDAO dao = new SaleDAO(); 

	public PaymentPageAction(JFrame presentFrame, 
			DefaultTableModel dtm, int orderNumber, int orderCount, String device_id) {
		this.presentFrame = presentFrame;
		this.dtm = dtm;
		this.orderNumber = orderNumber;	
		this.orderCount = orderCount;
		this.device_id = device_id;
	}

	/*
	 	������Ʈ�� ���� ���� �޾Ƽ�
	 	 - saveCartlist() �̿� 
	 	 - ������ ���� (���꿡 Ȱ��)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
	
		dao.saveCartlist(getUpdateDTO(), orderNumber, device_id);
		
		// ������ ������ ���� �ڵ�
		try {
			// ȭ����ȯ �����ڱ��� �޾��� �� ����
			presentFrame.setVisible(false);
			
			try {
				// ����ȭ�鿡 �Ѱ��� ������ �ֹ���ȣ, �Ѱ���, ��ǰ �̸�
				orderList = dao.searchCart("order_no", String.valueOf(orderNumber));
				price = dao.SumByOrderNum(orderNumber);
				lists = new ArrayList<>();
				for (int i = 0; i < orderCount; i++) {
					String format = String.format("%s (%s)",  
														  orderList.get(i).getProduct_name(),
														  orderList.get(i).getTermsofcondition());
					if (format.contains("null")) {
						lists.add(orderList.get(i).getProduct_name());
					} else {
						lists.add(format);
					}
				}	
				
				new PayPanel();
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		} catch (NullPointerException npe) {
			// ���̺�𵨸� �޾��� �� ����
			presentFrame.setVisible(false);
			System.err.println("����â �Ѿ!!");
		}

	}

	/*
	    �ֹ����� �߰�, ����, ���� ���޹޾Ƽ� 
	     - ������Ʈ �ϱ� 
	     - cart TABLE�� ���������� �����ų ������ ����� �޼���
	 */
	private ArrayList<SaleDTO> getUpdateDTO() {
		updateCart = new ArrayList<>();
		updateDTO = new SaleDTO();
		if (dtm.getRowCount() == 0) {		
			System.err.println("������ ǰ���� �����ϴ�.");		

		} else {
			for (int i = 0; i < dtm.getRowCount(); i++) {
				updateCart.add(dao.testOrder((String)dtm.getValueAt(i, 0), 
						(String)dtm.getValueAt(i, 1), 
						(Integer)dtm.getValueAt(i, 2)));
			}
		}
		return updateCart;
	}
}
