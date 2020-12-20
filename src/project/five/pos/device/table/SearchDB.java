package project.five.pos.device.table;

import java.util.ArrayList;

import project.five.pos.db.PosVO;
import project.five.pos.device.DeviceDAO;

public class SearchDB {

	String btn_text;
	public SearchDB(String btn_text) {
		this.btn_text = btn_text;
	}
	
	DeviceDAO device;
	ArrayList<PosVO> search_data;

	public ArrayList<PosVO> allData() {
		
		device = new DeviceDAO();
		
		if (btn_text.equals("판매 내역 조회")) {
			search_data = device.searchAllCart();
			
		} else if (btn_text.equals("결제 내역 조회")) {
			search_data = device.searchAllPayment();
			
		} else if (btn_text.equals("회원 정보 조회")) {	
			search_data = device.findByAll();
			
		}
		
		return search_data;
	}
	
	/*
	 	dto 합쳐서 각 테이블 가져오기
	 */
	public ArrayList<PosVO> searchData(String category, String data) {
		
		device = new DeviceDAO();
		
		if (btn_text.equals("판매 내역 조회")) {
			search_data = device.searchCart(category, data);
		
		} else if (btn_text.equals("결제 내역 조회")) {
			search_data = device.searchPayment(category, data);

		} else if (btn_text.equals("회원 정보 조회")) {
			search_data = device.searchMember(category, data); 
		
		}
		
		return search_data;
	}
	
}
