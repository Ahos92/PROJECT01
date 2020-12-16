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
	
	// 정렬을 위한 오버라이드
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
	 	선택된 컬럼 기준으로 잡아주기
	 		- 정렬 기준 추가 가능 	
	 */
	private String selectedCategory (String selectedItem) {
			if (selectedItem.equals("주문 번호")) {
				return "order_no";
				
			} else if (selectedItem.equals("상품 이름")) {
				return "product_name";
	
			} else if (selectedItem.equals("이름")) {
				return "last_name||first_name";
				
			} else if (selectedItem.equals("전화번호")) {
				return "contact_no";
				
			} else if (selectedItem.equals("등급")) {
				return "membership";
				
			} else if (selectedItem.equals("결제일")) {
				return "payment_date";
				
			} else if (selectedItem.equals("결제 금액")) {
				return "Amount_of_money";
				
			} else if (selectedItem.equals("결제 수단")) {
				return "payment_type";
			}

		return null;
	}

	/*
	 	조회 데이터 바꿔서 출력
	 */
	public void changeData(DefaultTableModel old_dtm, DefaultTableModel new_dtm) {
		old_dtm.setNumRows(0);
		old_dtm.setNumRows(lookUp_list.length);
		old_dtm.setDataVector(lookUp_list, table_header);	
	}
	

	/*
	  	조회하고 싶은 데이터 전체 조회
	 */
	public LookUpTableModel(String btn_text) {
		search_data = new SearchDB(btn_text).allData();

		list = new TableList(btn_text);
		table_header = list.header();
		lookUp_list = list.data(search_data, table_header);

		super.setDataVector(lookUp_list, table_header);
	}


	/*
	 	전체조회에서 원하는 데이터만 검색 조회
	 		- TableRepaintAction에 사용
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
