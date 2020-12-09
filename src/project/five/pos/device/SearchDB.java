package project.five.pos.device;

import java.util.ArrayList;

import project.five.pos.sale.SaleDAO;
import project.five.pos.sale.SaleDTO;

public class SearchDB {

	SaleDAO dao;
	ArrayList<SaleDTO> search_data;

	public ArrayList<SaleDTO> outToTable(String btn_text) {
		
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
	
	
}
