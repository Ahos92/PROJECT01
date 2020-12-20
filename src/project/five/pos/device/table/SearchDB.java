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
		
		if (btn_text.equals("�Ǹ� ���� ��ȸ")) {
			search_data = device.searchAllCart();
			
		} else if (btn_text.equals("���� ���� ��ȸ")) {
			search_data = device.searchAllPayment();
			
		} else if (btn_text.equals("ȸ�� ���� ��ȸ")) {	
			search_data = device.findByAll();
			
		}
		
		return search_data;
	}
	
	/*
	 	dto ���ļ� �� ���̺� ��������
	 */
	public ArrayList<PosVO> searchData(String category, String data) {
		
		device = new DeviceDAO();
		
		if (btn_text.equals("�Ǹ� ���� ��ȸ")) {
			search_data = device.searchCart(category, data);
		
		} else if (btn_text.equals("���� ���� ��ȸ")) {
			search_data = device.searchPayment(category, data);

		} else if (btn_text.equals("ȸ�� ���� ��ȸ")) {
			search_data = device.searchMember(category, data); 
		
		}
		
		return search_data;
	}
	
}
