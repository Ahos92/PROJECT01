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
	 	업데이트된 정보 전달 받아서
	 	 - saveCartlist() 이용 
	 	 - 데이터 저장 (정산에 활용)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		dao.saveCartlist(getUpdateDTO(), orderNumber);
		try {
			// 화면전환 생성자 받았을 때 적용
			presentFrame.setVisible(false);
			nextFrame.setVisible(true);

		} catch (NullPointerException npe) {

			// 테이블변수 생성자 받았을 때 적용
			System.err.println("결제창 넘어감!!");
		}

	}

	/*
	    주문내역 추가, 수정, 삭제 전달받아서 
	     업데이트 하기 
	 */
	private ArrayList<SaleDTO> getUpdateDTO() {
		ArrayList<SaleDTO> updateCart = new ArrayList<>();
		SaleDTO updateDTO = new SaleDTO();
		if (dtm.getRowCount() == 0) {
			// 알림창으로 구현 예정
			System.err.println("결제할 품목이 없습니다. (알림창 구현)");		

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
