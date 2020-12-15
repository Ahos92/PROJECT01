package project.five.pos.device.subdisplay;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

import javax.swing.*;
import javax.swing.event.RowSorterListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;

import project.five.pos.TestSwingTools;
import project.five.pos.device.*;
import project.five.pos.device.actionbtn.ChangeFrameBtn;
import project.five.pos.device.actionbtn.TableRepaintAction;
import project.five.pos.sale.*;

public class InquiryDisplay extends JFrame {

	JComboBox<String> select_column;
	JTextField select_data;
	JLabel headLb, categoryLb, selectNameLb;

	JPanel northP, centerP, southP;

	JButton backBtn, searchBtn, allInqBtn;

	JTable table;
	JScrollPane scroll;
	DefaultTableModel dtm;
	public InquiryDisplay(String btn_text) {
		setLayout(new BorderLayout());

		northP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		centerP = new JPanel();
		southP = new JPanel();	
		
		// 헤드
		headLb = new JLabel(btn_text);

		// 조회 테이블
		dtm = new InqTableModel(btn_text);
		table = new JTable(dtm);
		table.setAutoCreateRowSorter(true); // 테이블 역순/정순 변환
		table.getTableHeader().setReorderingAllowed(false); // 테이블 수정불가
		scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(480, 500));
		// 검색 
		String[] list = {"주문 번호", "상품 이름"};
		select_column = new JComboBox<String>(list);
		select_column.setPreferredSize(new Dimension(100, 20));
		categoryLb = new JLabel("카테고리");
		select_data = new JTextField(8);
		selectNameLb = new JLabel("검색 명");
		searchBtn = new JButton("검색");
		searchBtn.addActionListener(new TableRepaintAction(btn_text, dtm,
															select_column, select_data)); 
		allInqBtn = new JButton("전체보기");
		allInqBtn.addActionListener(new TableRepaintAction(btn_text, dtm,
															select_column, select_data)); 	
				
		// 뒤로가기
		backBtn = new JButton("관리자 메뉴로 돌아가기");
		backBtn.addActionListener(new ChangeFrameBtn(this));
		
		southP.add(backBtn);
		centerP.add(scroll);
		centerP.add(categoryLb);
		centerP.add(select_column);
		centerP.add(selectNameLb);
		centerP.add(select_data);
		centerP.add(searchBtn);
		centerP.add(allInqBtn);
		northP.add(headLb);
		
		add(northP, BorderLayout.NORTH);
		add(centerP, BorderLayout.CENTER);
		add(southP, BorderLayout.SOUTH);

		TestSwingTools.initTestFrame(this, "INQ TEST", false);
	}

	public static void main(String[] args) {
		new InquiryDisplay("판매 내역 조회").setVisible(true);
	}
}
