package project.five.pos.device;

import java.awt.*;

import java.util.*;

import javax.swing.*;
import javax.swing.table.*;

import project.five.pos.device.action.ChangeFrameBtn;
import project.five.pos.device.comp.IntegerComp;
import project.five.pos.sale.*;

public class InquiryDisplay extends JFrame {

	JPanel north_p, center_p, south_p;

	JButton back_to_manager, change_sort;

	TableList config_data;

	ArrayList<String> head_data;
	String[] header;

	ArrayList<SaleDTO> search_data;
	Object[][] inquiry_list;

	JTable table;
	JScrollPane scroll;
	DefaultTableModel dtm;
	Comparator<Integer> comparator;

	public InquiryDisplay(String btn_text) {
		setLayout(new BorderLayout());

		north_p = new JPanel(new FlowLayout(FlowLayout.LEFT));
		center_p = new JPanel();
		south_p = new JPanel();


		config_data =  new TableList(btn_text);
		// 조회할 컬럼 값들
		header = config_data.header();

		// 조회할 DB 검색
		search_data = new SearchDB().outToTable(btn_text);

		// 출력할 컬럼에 맞는 데이터 값

		inquiry_list = config_data.data(search_data, header);

		dtm = new DefaultTableModel(inquiry_list, header);
		table = new JTable(dtm);
		// 오름차순, 내림차순 메서드
		// 클래스 쪼개기
		TableRowSorter sorter = new TableRowSorter<>();
		comparator = new IntegerComp();
		sorter.setModel(table.getModel());
		for (int i = 0; i < table.getColumnCount(); i++) {		
			char ch = table.getValueAt(1, i).toString().charAt(0);
			if (ch >= '0' && ch <= '9') {
			  sorter.setComparator(i, comparator);
			}
		};
		table.setRowSorter(sorter);
		
		scroll = new JScrollPane(table);

		scroll.setPreferredSize(new Dimension(480, 500));
		center_p.add(scroll);
		add(center_p, BorderLayout.CENTER);

		back_to_manager = new JButton("관리자 메뉴로 돌아가기");
		back_to_manager.addActionListener(new ChangeFrameBtn(this));
		south_p.add(back_to_manager);
		add(south_p, BorderLayout.SOUTH);

		TestSwingTools.initTestFrame(this, "INQ TEST", false);
	}

	public static void main(String[] args) {
		new InquiryDisplay("판매 내역 조회").setVisible(true);
	}
}
