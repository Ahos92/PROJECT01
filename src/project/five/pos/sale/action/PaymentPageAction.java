package project.five.pos.sale.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import project.five.pos.sale.SaleDAO;
import project.five.pos.sale.SaleDTO;

public class PaymentPageAction implements ActionListener{

	JFrame nextFrame;
	JFrame presentFrame;

	ArrayList<SaleDTO> cartlist; 
	int orderNumber;
	DefaultTableModel dtm;

	SaleDAO dao = new SaleDAO(); 

	public PaymentPageAction(JFrame presentFrame, JFrame nextFrame, 
			DefaultTableModel dtm, int orderNumber) {

		this.nextFrame = nextFrame;
		this.presentFrame = presentFrame;
		this.dtm = dtm;
		this.orderNumber = orderNumber;	
	}



	public PaymentPageAction(DefaultTableModel dtm, int orderNumber) {
		this.dtm = dtm;
		this.orderNumber = orderNumber;

	}



	/*
	 	������Ʈ�� ���� ���� �޾Ƽ�
	 	 - saveCartlist() �̿� 
	 	 - ������ ���� (���꿡 Ȱ��)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		dao.saveCartlist(getUpdateDTO(), orderNumber);
		try {
			// ȭ����ȯ ������ �޾��� �� ����
			presentFrame.setVisible(false);
			nextFrame.setVisible(true);

		} catch (NullPointerException npe) {

			// ���̺��� ������ �޾��� �� ����
			System.err.println("����â �Ѿ!!");
		}

	}

	/*
	    �ֹ����� �߰�, ����, ���� ���޹޾Ƽ� 
	     ������Ʈ �ϱ� 
	 */
	private ArrayList<SaleDTO> getUpdateDTO() {
		ArrayList<SaleDTO> updateCart = new ArrayList<>();
		SaleDTO updateDTO = new SaleDTO();
		if (dtm.getRowCount() == 0) {
			// �˸�â���� ���� ����
			System.err.println("������ ǰ���� �����ϴ�. (�˸�â ����)");		

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
