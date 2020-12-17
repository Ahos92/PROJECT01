package project.five.pos.device;

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
import project.five.pos.device.comp.btn.action.ChangeFrameAction;
import project.five.pos.device.comp.btn.action.TableRepaintAction;
import project.five.pos.device.table.ComboBoxList;
import project.five.pos.device.table.LookUpTableModel;
import project.five.pos.sale.*;

public class LookUpDisplay extends JFrame {

	JComboBox<String> selectColumn_box;
	JTextField selectData_tf;
	JLabel head_lab, category_lab, selectName_lab;

	JPanel north_p, center_p, south_p;

	JButton back_btn, search_btn, allInq_btn;

	JTable lookUp_table;
	JScrollPane scroll;
	DefaultTableModel dtm;
	
	public LookUpDisplay(String btn_text) {
		setLayout(new BorderLayout());

		north_p = new JPanel(new FlowLayout(FlowLayout.LEFT));
		center_p = new JPanel();
		south_p = new JPanel();	
		
		// 헤드
		head_lab = new JLabel(btn_text);

		// 조회 테이블
		dtm = new LookUpTableModel(btn_text);
		lookUp_table = new JTable(dtm);
		lookUp_table.setAutoCreateRowSorter(true); // 테이블 역순/정순 변환
		lookUp_table.getTableHeader().setReorderingAllowed(false); // 테이블 수정불가
		scroll = new JScrollPane(lookUp_table);
		scroll.setPreferredSize(new Dimension(480, 500));
		// 검색 
		String[] list = new ComboBoxList(btn_text).getArr();
		selectColumn_box = new JComboBox<String>(list);
		selectColumn_box.setPreferredSize(new Dimension(100, 20));
		category_lab = new JLabel("카테고리");
		selectData_tf = new JTextField(8);
		selectName_lab = new JLabel("검색 명");
		search_btn = new JButton("검색");
		search_btn.addActionListener(new TableRepaintAction(btn_text, dtm,
															selectColumn_box, selectData_tf)); 
		allInq_btn = new JButton("전체보기");
		allInq_btn.addActionListener(new TableRepaintAction(btn_text, dtm,
															selectColumn_box, selectData_tf)); 	
		
		// 뒤로가기
		back_btn = new JButton("관리자 메뉴로 돌아가기");
		back_btn.addActionListener(new ChangeFrameAction(this));
		
		south_p.add(back_btn);
		center_p.add(scroll);
		center_p.add(category_lab);
		center_p.add(selectColumn_box);
		center_p.add(selectName_lab);
		center_p.add(selectData_tf);
		center_p.add(search_btn);
		center_p.add(allInq_btn);
		north_p.add(head_lab);
		
		JLabel info;
		if (btn_text.equals("결제 내역 조회")) {
			 info = new JLabel("결제일로 검색 하실 경우 결제'일' 만 입력해야 정보를 얻으 실수 있습니다.");
			 center_p.add(info);
		}
		
		add(north_p, BorderLayout.NORTH);
		add(center_p, BorderLayout.CENTER);
		add(south_p, BorderLayout.SOUTH);

		TestSwingTools.initTestFrame(this, btn_text, true);
	}

	public static void main(String[] args) {
		new LookUpDisplay("판매 내역 조회");
	}
}
