package project.five.pos.device.table;

import java.util.ArrayList;

import project.five.pos.sale.SaleDTO;

public class TableList {

	// 조회할 데이터 값
	int row_length;
	int column_length;
	Object[][] inquiry_list;
	int j;

	// 조회할 컬럼 값
	ArrayList<String> head_data;
	String[] header;
	int head_length;

	// 어느 DB 사용할지 구분
	String btn_text;
	public TableList(String btn_text) {
		this.btn_text = btn_text;
	}

	// DTM에 넣기위한 리스트 -> 배열 변환작업
	private String[] changeArr(int head_length) {

		header = new String[head_length];
		for (int i = 0; i < head_length; i++) {
			header[i] = head_data.get(i);
		}

		return header;
	}

	public String[] header() {
		head_data = new ArrayList<>();
		// 에러방지
		head_length = 5;

		if (btn_text.equals("판매 내역 조회")) {
			head_data.add("카트 번호");
			head_data.add("주문 번호");
			head_data.add("상품 이름");
			head_data.add("수량");
			head_data.add("가격");

			head_length = head_data.size();
			header = changeArr(head_length);

		} else if (btn_text.equals("결제 내역 조회")) {
			head_data.add("결제 번호");
			head_data.add("쿠폰 번호");
			head_data.add("결제 수단");
			head_data.add("결제 날짜");

			head_length = head_data.size();
			header = changeArr(head_length);
			System.err.println("미구현 : PAYMENT 파트 결합");

		} else if (btn_text.equals("회원 정보 조회")) {
			head_data.add("고객 번호");
			head_data.add("회원 번호");
			head_data.add("회원 이름");
			head_data.add("전화 번호");
			head_data.add("회원 등급");

			head_length = head_data.size();
			header = changeArr(head_length);
			System.err.println("미구현 : CUSTOMER 파트 결합");

		}
		return header;
	}

	public Object[][] data(ArrayList<SaleDTO> searchlist, String[] header) {
		row_length = searchlist.size();
		column_length = header.length;		

		inquiry_list = new Object[row_length][column_length];
		
		if (btn_text.equals("판매 내역 조회")) {
			for (int i = 0; i < row_length; i++) {
				j = 0;
			inquiry_list[i][j] = searchlist.get(i).getCart_no();
			inquiry_list[i][++j] = searchlist.get(i).getOrder_no();
			inquiry_list[i][++j] = searchlist.get(i).getProduct_name();
			inquiry_list[i][++j] = searchlist.get(i).getSelected_item();
			inquiry_list[i][++j] = searchlist.get(i).getTotal_price();
			};

		} else if (btn_text.equals("결제 내역 조회")) { // Payment table 조회
			for (int i = 0; i < row_length; i++) {
				j = 0;
				inquiry_list[i][j] = "Payment_no_test_" + i;//searchlist.get(i).getPayment_no();
				inquiry_list[i][++j] = "Coupon_no_test_" + i;//searchlist.get(i).getCoupon_no();
				inquiry_list[i][++j] = "Payment_type_test_" + i;//searchlist.get(i).getPayment_type();
				inquiry_list[i][++j] = "Payment_date_test_" + i;//searchlist.get(i).getPayment_date ();
			};
			
		} else if (btn_text.equals("회원 정보 조회")) { // customer table 조회
			for (int i = 0; i < row_length; i++) {
				j = 0;
				inquiry_list[i][j] = "Customer_no_test_" + i;//searchlist.get(i).getCustomer_no();
				inquiry_list[i][++j] = "Member_id_test_" + i;//searchlist.get(i).getMember_id();
				inquiry_list[i][++j] = "Name_test_" + i;//searchlist.get(i).getLast_name() + getFirst_name();
				inquiry_list[i][++j] = "Tel_test_" + i;//searchlist.get(i).getContact_no();
				inquiry_list[i][++j] = "Grade_test_" + i;//searchlist.get(i).getMembership();
			};
			
		}
			
		return inquiry_list;
	}
}
