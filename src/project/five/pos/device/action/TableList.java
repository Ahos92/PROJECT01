package project.five.pos.device.action;

import java.util.ArrayList;

public class TableList {

	ArrayList<String> head_data;
	String[] header;
	int head_length;
	
	String btn_text;
	public TableList(String btn_text) {
		this.btn_text = btn_text;
	}
	
	public String[] header() {
		head_data = new ArrayList<>();
		// 에러방지
		head_length = 5;
		
		if (this.btn_text.equals("판매 내역 조회")) {
			head_data.add("카트 번호");
			head_data.add("주문 번호");
			head_data.add("상품 이름");
			head_data.add("수량");
			head_data.add("가격");

			head_length = head_data.size();
			header = new String[head_length];
			for (int i = 0; i < head_length; i++) {
				header[i] = head_data.get(i);
			}
			
		} else if (this.btn_text.equals("결제 내역 조회")) {
			header = new String[head_length];
			System.err.println("미구현 : PAYMENT 파트 결합");
			
		} else if (this.btn_text.equals("회원 정보 조회")) {
			header = new String[head_length];
			System.err.println("미구현 : CUSTOMER 파트 결합");
			
		}
		return header;
	}
}
