package project.five.pos.device;

import java.util.ArrayList;

import project.five.pos.sale.SaleDAO;
import project.five.pos.sale.SaleDTO;

public class SearchDB {

	SaleDAO dao;
	ArrayList<SaleDTO> search_data;

	public ArrayList<SaleDTO> outToTable(String btn_text) {
		
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
	
	
}
