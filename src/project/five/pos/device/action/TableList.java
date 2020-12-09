package project.five.pos.device.action;

import java.util.ArrayList;

public class TableList {

	ArrayList<String> head_data;
	String[] header;
	int head_length;
	
	String btn_text;
	public TableList(String btn_text) {
		this.btn_text = btn_text;
	}
	
	public String[] header() {
		head_data = new ArrayList<>();
		// ��������
		head_length = 5;
		
		if (this.btn_text.equals("�Ǹ� ���� ��ȸ")) {
			head_data.add("īƮ ��ȣ");
			head_data.add("�ֹ� ��ȣ");
			head_data.add("��ǰ �̸�");
			head_data.add("����");
			head_data.add("����");

			head_length = head_data.size();
			header = new String[head_length];
			for (int i = 0; i < head_length; i++) {
				header[i] = head_data.get(i);
			}
			
		} else if (this.btn_text.equals("���� ���� ��ȸ")) {
			header = new String[head_length];
			System.err.println("�̱��� : PAYMENT ��Ʈ ����");
			
		} else if (this.btn_text.equals("ȸ�� ���� ��ȸ")) {
			header = new String[head_length];
			System.err.println("�̱��� : CUSTOMER ��Ʈ ����");
			
		}
		return header;
	}
}
