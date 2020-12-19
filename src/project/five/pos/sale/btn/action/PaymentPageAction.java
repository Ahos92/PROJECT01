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

	ArrayList<PosVO> update_cart; 
	PosVO updateVO;
	int order_num, price;

	DefaultTableModel dtm;
	String device_id;

	ArrayList<String> lists;

	CartDAO dao = new CartDAO(); 

	public PaymentPageAction(JFrame present_frame, 
			DefaultTableModel dtm, int order_num, String device_id) {
		this.present_frame = present_frame;
		this.dtm = dtm;
		this.order_num = order_num;	
		this.device_id = device_id;
	}

	/*
	 	업데이트된 정보 전달 받아서
	 	 - savecart_list() 이용 
	 	 - 데이터 저장 (DB cartTABLE에 저장)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
			// AutoCommit 해제상태 , 데이터 만들어서 넘겨줄 역할만 하는 메서드
			// 총 결제가 완료 되는 시점에 새로운 쿼리문 짜서 결제, 판매 내역 동시에 저장
			 dao.saveUpdateCartlist(getUpdateDTO(), order_num, device_id);
		try {
			
			update_cart = getUpdateDTO();
			lists = new ArrayList<>();
			System.out.println("<결제 창으로 넘긴 목록 >");
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
				System.out.println("상품 이름 : " + lists.get(i));
				System.out.println("상품 선택 : " + update_cart.get(i).getSelected_item() + " 개");
				System.out.println("각 상품 가격 : " + update_cart.get(i).getProduct_price() + " 원");
			}	
			System.out.println("주문 번호 : "+ order_num);
			System.out.println("총 가격 : " + price);
			
			// 결제화면에 넘겨줄 데이터 주문번호(order_num), 총가격(price), List<상품 이름>
			new PayPanel(order_num, price, lists, update_cart);

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
	private ArrayList<PosVO> getUpdateDTO() {
		update_cart = new ArrayList<>();
		
		if (dtm.getRowCount() == 0) {		
			System.err.println("결제할 품목이 없습니다.");		

		} else {
			for (int i = 0; i < dtm.getRowCount(); i++) {
				updateVO = new PosVO();
				updateVO.setProduct_name((String)dtm.getValueAt(i, 0));
				updateVO.setTermsofcondition((String)dtm.getValueAt(i, 1));
				updateVO.setSelected_item((Integer)dtm.getValueAt(i, 2));
				updateVO.setTotal_price((Integer)dtm.getValueAt(i, 3));
				updateVO.setProduct_price((Integer)dtm.getValueAt(i, 3) / (Integer)dtm.getValueAt(i, 2));
				update_cart.add(updateVO);
			}
		}
		return update_cart;
	}
}
