package project.five.pos.cart.btn.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;

import project.five.pos.payment.swing.PayPanel;
import project.five.pos.db.PosVO;
import project.five.pos.menu.MenuDisplay;

public class PaymentPageAction implements ActionListener{

	JDialog cart;
	JFrame menu;

	ArrayList<PosVO> update_cart; 
	PosVO updateVO;
	int order_num, price;

	DefaultTableModel dtm;
	String device_id;

	ArrayList<String> lists;

	
	public PaymentPageAction(JDialog cart, JFrame menu,
							DefaultTableModel dtm, int order_num, 
							String device_id) {
		this.cart = cart;
		this.menu = menu;
		this.dtm = dtm;
		this.order_num = order_num;	
		this.device_id = device_id;
	}

	/*
	 	업데이트된 정보 전달 받아서 결제 창에 넘겨주기
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		update_cart = getUpdateVO();

		if (update_cart.size() == 0) {
			JOptionPane.showMessageDialog(cart, "결제할 품목이 없습니다!!", "오류", JOptionPane.ERROR_MESSAGE);
			cart.dispose();
	
		} else {
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
				System.out.println("---------------------------------");
				System.out.println("상품 이름 : " + lists.get(i));
				System.out.println("상품 선택 : " + update_cart.get(i).getSelected_item() + " 개");
				System.out.println("각 상품 가격 : " + update_cart.get(i).getProduct_price() + " 원");
			}	
			System.out.println("---------------------------------");
			System.out.println("주문 번호 : "+ order_num);
			System.out.println("총 가격 : " + price);
			System.out.println("디바이스 아이디 : " + device_id);
			try {
				// 결제화면에 넘겨줄 데이터 
				// 주문번호(order_num), 총가격(price), 상품 이름(lists), 
				// VO객채(update_cart), 결제 타입(e.getactionCommand), 디바이스 아이디(device_id)
				new PayPanel(order_num, price, lists, update_cart, device_id);
				menu.dispose();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			cart.dispose();
		}
	}

	/*
	    주문내역 추가, 수정, 삭제 전달받아서 
	     - 실시간 업데이트 하기 
	     - cart TABLE에 최종적으로 저장시킬 데이터 만드는 메서드
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
