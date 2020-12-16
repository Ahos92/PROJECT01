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
		
		if (btn_text.equals("�Ǹ� ���� ��ȸ")) {
			search_data = sale.searchAllCart();
			
		} else if (btn_text.equals("���� ���� ��ȸ")) {
			 // payment ��ȸ ���� �޼��� �����ϱ�
			search_data = new ArrayList<>(10);
			
		} else if (btn_text.equals("ȸ�� ���� ��ȸ")) {	
			search_data = member.findByAll();
			
		}
		
		return search_data;
	}
	
	/*
	 	dto ���ļ� �� ���̺� ��������
	 */
	public ArrayList<PosVO> searchData(String category, String data) {
		
		sale = new SaleDAO();
		member = new MemberDao();
		
		if (btn_text.equals("�Ǹ� ���� ��ȸ")) {
			search_data = sale.searchCart(category, data);
			
		} else if (btn_text.equals("���� ���� ��ȸ")) {
			 // payment ��ȸ ���� �޼��� �����ϱ�
			search_data = new ArrayList<>(10);
			
		} else if (btn_text.equals("ȸ�� ���� ��ȸ")) {
			// customer ��ȸ ���� �޼��� �����ϱ�
			search_data = new ArrayList<>(10); 
			
		}
		
		return search_data;
	}
	
}
