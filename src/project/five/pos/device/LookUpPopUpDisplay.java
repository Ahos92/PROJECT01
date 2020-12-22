package project.five.pos.device;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

import javax.swing.*;
import javax.swing.event.RowSorterListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;

import project.five.pos.TestSwingTools;
import project.five.pos.cart.*;
import project.five.pos.device.comp.DevicePanel;
import project.five.pos.device.comp.btn.DeviceBtn;
import project.five.pos.device.comp.btn.action.ChangeFrameAction;
import project.five.pos.device.comp.btn.action.TableRepaintAction;
import project.five.pos.device.table.ComboBoxList;
import project.five.pos.device.table.LookUpTableModel;

public class LookUpPopUpDisplay extends JDialog {

	JComboBox<String> selectColumn_box;
	JTextField selectData_tf;
	JLabel head_lab, category_lab, selectName_lab;

	JPanel north_p, center_p, south_p;

	JButton back_btn, search_btn, allInq_btn;

	JTable lookUp_table;
	JScrollPane scroll;
	DefaultTableModel dtm;
	
	Font lookUp_font = new Font("Serif", Font.BOLD, 20);
	Dimension lookUp_dms = new Dimension(80, 35);
	
	String[] images_path = {
			"assets/images/device/14.png",
	};
	
	int width;
	int height;
	
	public LookUpPopUpDisplay(JFrame frame, String btn_text) {
		super(frame, btn_text);
		width = 550;
		height = 700;
		setLayout(new BorderLayout());
		setSize(width, height);
		setLocationRelativeTo(null);
		setResizable(false);
		setModal(true);

		north_p = new DevicePanel(images_path[0], width, height, new FlowLayout(FlowLayout.LEFT));
		
		center_p = new DevicePanel(images_path[0], width, height, new FlowLayout(FlowLayout.CENTER, 10, 40));
		
		south_p = new DevicePanel(images_path[0], width, height);
		
		// 헤드
		head_lab = new JLabel(btn_text);
		head_lab.setFont(lookUp_font);
		
		// 조회 테이블
		dtm = new LookUpTableModel(btn_text);
		lookUp_table = new JTable(dtm);
		lookUp_table.setAutoCreateRowSorter(true); // 테이블 역순/정순 변환
		lookUp_table.getTableHeader().setReorderingAllowed(false); // 테이블 수정불가
		scroll = new JScrollPane(lookUp_table);
		scroll.setPreferredSize(new Dimension(width - 15, height - 400));
		
		// 검색 
		String[] list = new ComboBoxList(btn_text).getArr();
		selectColumn_box = new JComboBox<String>(list);
		selectColumn_box.setPreferredSize(lookUp_dms);
	
		selectData_tf = new JTextField(8);
		selectData_tf.setPreferredSize(lookUp_dms);
		
		selectName_lab = new JLabel("검색 명");
		selectName_lab.setPreferredSize(lookUp_dms);
		selectName_lab.setFont(lookUp_font);
		
		search_btn = new DeviceBtn("검색", 60, 35, new TableRepaintAction(btn_text, dtm,
											selectColumn_box, selectData_tf));

		allInq_btn = new DeviceBtn("전체", 60, 35, new TableRepaintAction(btn_text, dtm,
				selectColumn_box, selectData_tf));

		
		// 뒤로가기
		back_btn = new DeviceBtn("관리자 메뉴로 돌아가기");
		back_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();			
			}
		});
		
		south_p.add(back_btn);
		center_p.add(scroll);
		center_p.add(selectColumn_box);
		center_p.add(selectName_lab);
		center_p.add(selectData_tf);
		center_p.add(search_btn);
		center_p.add(allInq_btn);
		north_p.add(head_lab);
		
		JLabel info;
		if (btn_text.equals("결제 내역 조회")) {
			 info = new JLabel("결제시간은 '시간'만 입력해 주세요.");
			 info.setFont(lookUp_font);
			 center_p.add(info);
		}
		
		add(north_p, BorderLayout.NORTH);
		add(center_p, BorderLayout.CENTER);
		add(south_p, BorderLayout.SOUTH);

		setVisible(true);
	}

}
