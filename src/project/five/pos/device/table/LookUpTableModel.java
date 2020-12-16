package project.five.pos.device.table;

import java.util.ArrayList;

import javax.swing.JComboBox;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import project.five.pos.db.PosVO;

public class LookUpTableModel extends DefaultTableModel {
	
	TableList list;
	String[] table_header;
	Object[][] lookUp_list; 
	ArrayList<PosVO> search_data;
	String category, selected_item, data;

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
	// ������ ���� �������̵�
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		try {
			return getValueAt(0, columnIndex).getClass();

		} catch (ArrayIndexOutOfBoundsException ae) {
			return Object.class;

		} catch (NullPointerException npe) {
			return Object.class;

		}
	}
	
	
	/*
	 	���õ� �÷� �������� ����ֱ�
	 		- ���� ���� �߰� ���� 	
	 */
	private String selectedCategory (String selectedItem) {
			if (selectedItem.equals("�ֹ� ��ȣ")) {
				return "order_no";
				
			} else if (selectedItem.equals("��ǰ �̸�")) {
				return "product_name";
	
			} else if (selectedItem.equals("�̸�")) {
				return "last_name||first_name";
				
			} else if (selectedItem.equals("��ȭ��ȣ")) {
				return "contact_no";
				
			} else if (selectedItem.equals("���")) {
				return "membership";
				
			} else if (selectedItem.equals("������")) {
				return "payment_date";
				
			} else if (selectedItem.equals("���� �ݾ�")) {
				return "Amount_of_money";
				
			} else if (selectedItem.equals("���� ����")) {
				return "payment_type";
			}

		return null;
	}

	/*
	 	��ȸ ������ �ٲ㼭 ���
	 */
	public void changeData(DefaultTableModel old_dtm, DefaultTableModel new_dtm) {
		old_dtm.setNumRows(0);
		old_dtm.setNumRows(lookUp_list.length);
		old_dtm.setDataVector(lookUp_list, table_header);	
	}
	

	/*
	  	��ȸ�ϰ� ���� ������ ��ü ��ȸ
	 */
	public LookUpTableModel(String btn_text) {
		search_data = new SearchDB(btn_text).allData();

		list = new TableList(btn_text);
		table_header = list.header();
		lookUp_list = list.data(search_data, table_header);

		super.setDataVector(lookUp_list, table_header);
	}


	/*
	 	��ü��ȸ���� ���ϴ� �����͸� �˻� ��ȸ
	 		- TableRepaintAction�� ���
	 */
	public LookUpTableModel(String btn_text, JComboBox<String> select_column, JTextField select_data) {	
		selected_item = select_column.getSelectedItem().toString();
		
		category = selectedCategory(selected_item);
		data = "\'" + select_data.getText() + "\'";

		search_data = new SearchDB(btn_text).searchData(category, data);

		list = new TableList(btn_text);
		table_header = list.header();
		lookUp_list = list.data(search_data, table_header);

		super.setDataVector(lookUp_list, table_header);

	}

}
