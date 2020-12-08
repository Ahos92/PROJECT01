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
		head_length = 0;
		
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
			
		} else {
			System.out.println("미구현");
		}
		return header;
	}
}
