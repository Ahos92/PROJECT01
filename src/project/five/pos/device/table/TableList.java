package project.five.pos.device.table;

import java.util.ArrayList;

import project.five.pos.db.PosVO;

public class TableList {

	// ��ȸ�� ������ ��
	int row_length;
	int column_length;
	Object[][] lookUp_list;
	int column;

	// ��ȸ�� �÷� ��
	ArrayList<String> head_data;
	String[] header;
	int head_length;

	// ��� DB ������� ����
	String btn_text;
	public TableList(String btn_text) {
		this.btn_text = btn_text;
	}

	// DTM�� �ֱ����� ����Ʈ -> �迭 ��ȯ�۾�
	private String[] changeArr(int head_length) {

		header = new String[head_length];
		for (int i = 0; i < head_length; i++) {
			header[i] = head_data.get(i);
		}

		return header;
	}

	public String[] header() {
		head_data = new ArrayList<>();
		// ��������
		head_length = 5;

		if (btn_text.equals("�Ǹ� ���� ��ȸ")) {
			head_data.add("īƮ ��ȣ");
			head_data.add("�ֹ� ��ȣ");
			head_data.add("��ǰ �̸�");
			head_data.add("����");
			head_data.add("����");

			head_length = head_data.size();
			header = changeArr(head_length);

		} else if (btn_text.equals("���� ���� ��ȸ")) {
			head_data.add("���� ��ȣ");
			head_data.add("���� ��ȣ");
			head_data.add("���� ����");
			head_data.add("���� ��¥");

			head_length = head_data.size();
			header = changeArr(head_length);
			System.err.println("�̱��� : PAYMENT ��Ʈ ����");

		} else if (btn_text.equals("ȸ�� ���� ��ȸ")) {
			head_data.add("�� ��ȣ");
			head_data.add("ȸ�� ��ȣ");
			head_data.add("ȸ�� �̸�");
			head_data.add("��ȭ ��ȣ");
			head_data.add("ȸ�� ���");

			head_length = head_data.size();
			header = changeArr(head_length);
			System.err.println("�̱��� : CUSTOMER ��Ʈ ����");

		}
		return header;
	}

	public Object[][] data(ArrayList<PosVO> searchlist, String[] header) {
		row_length = searchlist.size();
		column_length = header.length;		

		lookUp_list = new Object[row_length][column_length];
		
		if (btn_text.equals("�Ǹ� ���� ��ȸ")) {
			for (int i = 0; i < row_length; i++) {
				column = 0;
			lookUp_list[i][column] = searchlist.get(i).getCart_no();
			lookUp_list[i][++column] = searchlist.get(i).getOrder_no();
			lookUp_list[i][++column] = searchlist.get(i).getProduct_name();
			lookUp_list[i][++column] = searchlist.get(i).getSelected_item();
			lookUp_list[i][++column] = searchlist.get(i).getTotal_price();
			};

		} else if (btn_text.equals("���� ���� ��ȸ")) { // Payment table ��ȸ
			for (int i = 0; i < row_length; i++) {
				column = 0;
				lookUp_list[i][column] = "Payment_no_test_" + i;//searchlist.get(i).getPayment_no();
				lookUp_list[i][++column] = "Coupon_no_test_" + i;//searchlist.get(i).getCoupon_no();
				lookUp_list[i][++column] = "Payment_type_test_" + i;//searchlist.get(i).getPayment_type();
				lookUp_list[i][++column] = "Payment_date_test_" + i;//searchlist.get(i).getPayment_date ();
			};
			
		} else if (btn_text.equals("ȸ�� ���� ��ȸ")) { // customer table ��ȸ
			for (int i = 0; i < row_length; i++) {
				column = 0;
				lookUp_list[i][column] = "Customer_no_test_" + i;//searchlist.get(i).getCustomer_no();
				lookUp_list[i][++column] = "Member_id_test_" + i;//searchlist.get(i).getMember_id();
				lookUp_list[i][++column] = "Name_test_" + i;//searchlist.get(i).getLast_name() + getFirst_name();
				lookUp_list[i][++column] = "Tel_test_" + i;//searchlist.get(i).getContact_no();
				lookUp_list[i][++column] = "Grade_test_" + i;//searchlist.get(i).getMembership();
			};
			
		}
			
		return lookUp_list;
	}
}
