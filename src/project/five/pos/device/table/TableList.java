package project.five.pos.device.table;

import java.util.ArrayList;

import project.five.pos.db.PosVO;

public class TableList {

	// 조회할 데이터 값
	int row_length;
	int column_length;
	Object[][] lookUp_list;
	int column;

	// 어느 DB 사용할지 구분
	String btn_text;
	public TableList(String btn_text) {
		this.btn_text = btn_text;
	}

	public String[] header() {
		
		if (btn_text.equals("판매 내역 조회")) {
			String[] header = {"카트 번호", "주문 번호", "상품 이름", "수량", "가격"};	
			return header;

		} else if (btn_text.equals("결제 내역 조회")) {
			String[] header = {"결제 번호", "결제 수단", "결제일", "은행 이름", "카드 번호", "결제 금액", "사용 금액", "쿠폰 사용"};	
			return header;

		} else if (btn_text.equals("회원 정보 조회")) {
			String[] header = {"회원 번호", "이름", "전화 번호", "등급", "총 사용금액", "적립률", "마일리지"};
			return header;
		}
		return null;
	}

	public Object[][] data(ArrayList<PosVO> searchlist, String[] header) {
		row_length = searchlist.size();
		column_length = header.length;		

		lookUp_list = new Object[row_length][column_length];
		
		if (btn_text.equals("판매 내역 조회")) {
			for (int i = 0; i < row_length; i++) {
				column = 0;
			lookUp_list[i][column] = searchlist.get(i).getCart_no();
			lookUp_list[i][++column] = searchlist.get(i).getOrder_no();
			lookUp_list[i][++column] = searchlist.get(i).getProduct_name();
			lookUp_list[i][++column] = searchlist.get(i).getSelected_item();
			lookUp_list[i][++column] = searchlist.get(i).getTotal_price();
			};

		} else if (btn_text.equals("결제 내역 조회")) { // Payment table 조회
			for (int i = 0; i < row_length; i++) {
				column = 0;
				lookUp_list[i][column] = searchlist.get(i).getPayment_no();
				lookUp_list[i][++column] = searchlist.get(i).getPayment_type();
				lookUp_list[i][++column] = searchlist.get(i).getPayment_date();
				lookUp_list[i][++column] = searchlist.get(i).getBank_id();
				lookUp_list[i][++column] = searchlist.get(i).getCard_num();
				lookUp_list[i][++column] = searchlist.get(i).getAmount_of_money();
				lookUp_list[i][++column] = searchlist.get(i).getActual_expenditure();
				lookUp_list[i][++column] = searchlist.get(i).getCoupon_no();
			};
			
		} else if (btn_text.equals("회원 정보 조회")) { // customer table 조회
			for (int i = 0; i < row_length; i++) {
				column = 0;
				lookUp_list[i][column] = searchlist.get(i).getCustomer_no();
				lookUp_list[i][++column] = searchlist.get(i).getM_last_name() + searchlist.get(i).getM_first_name();
				lookUp_list[i][++column] = searchlist.get(i).getM_contact_no();
				lookUp_list[i][++column] = searchlist.get(i).getMembership();
				lookUp_list[i][++column] = searchlist.get(i).getAmount_price();
				lookUp_list[i][++column] = searchlist.get(i).getAccumulation_pct();
				lookUp_list[i][++column] = searchlist.get(i).getMileage();
			};
			
		}
			
		return lookUp_list;
	}
}
