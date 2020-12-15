package project.five.pos.device.table;

import java.util.ArrayList;

import project.five.pos.sale.SaleDAO;
import project.five.pos.db.PosVO;

public class SearchDB {

	String btn_text;
	public SearchDB(String btn_text) {
		this.btn_text = btn_text;
	}
	
	SaleDAO dao;
	ArrayList<PosVO> search_data;

	public ArrayList<PosVO> allData() {
		
		dao = new SaleDAO();
		if (btn_text.equals("판매 내역 조회")) {
			search_data = dao.searchAllCart();
			
		} else if (btn_text.equals("결제 내역 조회")) {
			 // payment 조회 쿼리 메서드 대입하기
			search_data = new ArrayList<>(10);
			
		} else if (btn_text.equals("회원 정보 조회")) {
			// customer 조회 쿼리 메서드 대입하기
			search_data = new ArrayList<>(10); 
			
		}
		
		return search_data;
	}
	
	/*
	 	dto 합쳐서 각 테이블 가져오기
	 */
	public ArrayList<PosVO> searchData(String category, String data) {
		
		dao = new SaleDAO();
		if (btn_text.equals("판매 내역 조회")) {
			search_data = dao.searchCart(category, data);
			
		} else if (btn_text.equals("결제 내역 조회")) {
			 // payment 조회 쿼리 메서드 대입하기
			search_data = new ArrayList<>(10);
			
		} else if (btn_text.equals("회원 정보 조회")) {
			// customer 조회 쿼리 메서드 대입하기
			search_data = new ArrayList<>(10); 
			
		}
		
		return search_data;
	}
	
}
