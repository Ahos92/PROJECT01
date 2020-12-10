package project.five.pos.device;

import java.util.ArrayList;

import javax.swing.JComboBox;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import project.five.pos.sale.SaleDTO;

public class InqTableModel extends DefaultTableModel {
	// 전체조회 모델 , 조건조회 모델
	TableList list;
	Object[][] inquiry_list; 
	String[] header;
	ArrayList<SaleDTO> search_data;

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	public InqTableModel(String btn_text) {
		list = new TableList(btn_text);
		search_data = new SearchDB(btn_text).allData();
		header = list.header();
		inquiry_list = list.data(search_data, header);

		super.setDataVector(inquiry_list, header);
	}

	public InqTableModel(String btn_text, JComboBox<String> select_column, JTextField select_data) {	
		String category = null;
		if (select_column.getSelectedItem().toString().equals("주문 번호")) {
			category = "order_no";
		} else if (select_column.getSelectedItem().toString().equals("상품 이름")) {
			category = "product_name";
		}
		String data = "\'" + select_data.getText() + "\'";

		list = new TableList(btn_text);
		search_data = new SearchDB(btn_text).searchData(category, data);
		header = list.header();
		inquiry_list = list.data(search_data, header);

		super.setDataVector(inquiry_list, header);

	}

	public void changeData(DefaultTableModel old_dtm, DefaultTableModel new_dtm) {
		old_dtm.setNumRows(0);
		old_dtm.setNumRows(inquiry_list.length);
		old_dtm.setDataVector(inquiry_list, header);	
	}

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
	
}
