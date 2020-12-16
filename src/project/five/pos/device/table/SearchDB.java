package project.five.pos.device.table;

import java.util.ArrayList;

import project.five.pos.sale.SaleDAO;
import project.five.pos.db.PosVO;
import project.five.pos.membership.dao.MemberDao;

public class SearchDB {

	String btn_text;
	public SearchDB(String btn_text) {
		this.btn_text = btn_text;
	}
	
	SaleDAO sale;
	MemberDao member;
	ArrayList<PosVO> search_data;

	public ArrayList<PosVO> allData() {
		
		sale = new SaleDAO();
		member = new MemberDao();
		
		if (btn_text.equals("판매 내역 조회")) {
			search_data = sale.searchAllCart();
			
		} else if (btn_text.equals("결제 내역 조회")) {
			 // payment 조회 쿼리 메서드 대입하기
			search_data = new ArrayList<>(10);
			
		} else if (btn_text.equals("회원 정보 조회")) {	
			search_data = member.findByAll();
			
		}
		
		return search_data;
	}
	
	/*
	 	dto 합쳐서 각 테이블 가져오기
	 */
	public ArrayList<PosVO> searchData(String category, String data) {
		
		sale = new SaleDAO();
		member = new MemberDao();
		
		if (btn_text.equals("판매 내역 조회")) {
			search_data = sale.searchCart(category, data);
			
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
