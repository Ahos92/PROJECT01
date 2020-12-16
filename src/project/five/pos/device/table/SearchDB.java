package project.five.pos.device.table;

import java.util.ArrayList;

import project.five.pos.sale.CartDAO;
import project.five.pos.db.PosDAO;
import project.five.pos.db.PosVO;
import project.five.pos.membership.dao.MemberDao;

public class SearchDB {

	String btn_text;
	public SearchDB(String btn_text) {
		this.btn_text = btn_text;
	}
	
	CartDAO cart;
	MemberDao member;
	PosDAO pos;
	ArrayList<PosVO> search_data;

	public ArrayList<PosVO> allData() {
		
		cart = new CartDAO();
		member = new MemberDao();
		pos = new PosDAO();
		
		if (btn_text.equals("�Ǹ� ���� ��ȸ")) {
			search_data = cart.searchAllCart();
			
		} else if (btn_text.equals("���� ���� ��ȸ")) {
			search_data = pos.searchAllPayment();
			
		} else if (btn_text.equals("ȸ�� ���� ��ȸ")) {	
			search_data = member.findByAll();
			
		}
		
		return search_data;
	}
	
	/*
	 	dto ���ļ� �� ���̺� ��������
	 */
	public ArrayList<PosVO> searchData(String category, String data) {
		
		cart = new CartDAO();
		member = new MemberDao();
		pos = new PosDAO();
		
		if (btn_text.equals("�Ǹ� ���� ��ȸ")) {
			search_data = cart.searchCart(category, data);
		
		} else if (btn_text.equals("���� ���� ��ȸ")) {
			search_data = pos.searchPayment(category, data);

		} else if (btn_text.equals("ȸ�� ���� ��ȸ")) {
			search_data = member.searchMember(category, data); 
		
		}
		
		return search_data;
	}
	
}
