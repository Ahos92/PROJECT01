package project.five.pos.sale.btn.action;

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

	JFrame present_frame;

	ArrayList<SaleDTO> cart_list, order_list, update_cart; 
	SaleDTO updateDTO;
	int order_num, order_cnt, price;

	DefaultTableModel dtm;
	String device_id;

	ArrayList<String> lists;

	SaleDAO dao = new SaleDAO(); 

	public PaymentPageAction(JFrame present_frame, 
			DefaultTableModel dtm, int order_num, int order_cnt, String device_id) {
		this.present_frame = present_frame;
		this.dtm = dtm;
		this.order_num = order_num;	
		this.order_cnt = order_cnt;
		this.device_id = device_id;
	}

	/*
	 	업데이트된 정보 전달 받아서
	 	 - savecart_list() 이용 
	 	 - 데이터 저장 (DB cartTABLE에 저장)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		dao.saveCartlist(getUpdateDTO(), order_num, device_id);
		
		try {
			// 결제화면에 넘겨줄 데이터 주문번호, 총가격, 상품 이름
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
			// 생성자 매개변수로 넘겨줄 예정
			new PayPanel();

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		present_frame.dispose();
	}

	/*
	    주문내역 추가, 수정, 삭제 전달받아서 
	     - 실시간 업데이트 하기 
	     - cart TABLE에 최종적으로 저장시킬 데이터 만드는 메서드
	 */
	private ArrayList<SaleDTO> getUpdateDTO() {
		update_cart = new ArrayList<>();
		updateDTO = new SaleDTO();
		if (dtm.getRowCount() == 0) {		
			System.err.println("결제할 품목이 없습니다.");		

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
