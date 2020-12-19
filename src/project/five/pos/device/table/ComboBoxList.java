package project.five.pos.device.table;

import java.util.ArrayList;

public class ComboBoxList {

	String btn_txt;
	
	public ComboBoxList(String btn_txt) {
		this.btn_txt = btn_txt;
	}

	public String[] getArr() {
		if (btn_txt.equals("판매 내역 조회")) {
			String[] list = {"주문 번호", "상품 이름"};
			return list;
		} else if (btn_txt.equals("결제 내역 조회")){
			String[] list = {"결제시간", "사용 금액▲", "결제 수단"};
			return list;
			
		} else if (btn_txt.equals("회원 정보 조회")){
			String[] list = {"이름", "전화번호", "등급"};
			return list;
		}
	
		return null;
	}
	
}