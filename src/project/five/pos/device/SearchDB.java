package project.five.pos.device;

import java.util.ArrayList;

import project.five.pos.sale.SaleDAO;
import project.five.pos.sale.SaleDTO;

public class SearchDB {

	String btn_text;
	public SearchDB(String btn_text) {
		this.btn_text = btn_text;
	}
	
	SaleDAO dao;
	ArrayList<SaleDTO> search_data;

	public ArrayList<SaleDTO> allData() {
		
		dao = new SaleDAO();
		if (btn_text.equals("�Ǹ� ���� ��ȸ")) {
			search_data = dao.searchAllCart();
			
		} else if (btn_text.equals("���� ���� ��ȸ")) {
			 // payment ��ȸ ���� �޼��� �����ϱ�
			search_data = new ArrayList<>(10);
			
		} else if (btn_text.equals("ȸ�� ���� ��ȸ")) {
			// customer ��ȸ ���� �޼��� �����ϱ�
			search_data = new ArrayList<>(10); 
			
		}
		
		return search_data;
	}
	
	
	public ArrayList<SaleDTO> searchData(String category, String data) {
		
		dao = new SaleDAO();
		if (btn_text.equals("�Ǹ� ���� ��ȸ")) {
			search_data = dao.searchCart(category, data);
			
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
