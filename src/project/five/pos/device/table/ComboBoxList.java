package project.five.pos.device.table;

import java.util.ArrayList;

public class ComboBoxList {

	String btn_txt;
	
	public ComboBoxList(String btn_txt) {
		this.btn_txt = btn_txt;
	}

	public String[] getArr() {
		if (btn_txt.equals("�Ǹ� ���� ��ȸ")) {
			String[] list = {"�ֹ� ��ȣ", "��ǰ �̸�"};
			return list;
		} else if (btn_txt.equals("���� ���� ��ȸ")){
			String[] list = {"�����ð�", "��� �ݾס�", "���� ����"};
			return list;
			
		} else if (btn_txt.equals("ȸ�� ���� ��ȸ")){
			String[] list = {"�̸�", "��ȭ��ȣ", "���"};
			return list;
		}
	
		return null;
	}
	
}