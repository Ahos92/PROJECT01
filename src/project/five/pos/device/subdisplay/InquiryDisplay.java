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

import project.five.pos.device.*;
import project.five.pos.device.actionbtn.ChangeFrameBtn;
import project.five.pos.device.actionbtn.RepaintAction;
import project.five.pos.sale.*;

public class InquiryDisplay extends JFrame {

	JComboBox<String> select_column;
	JTextField select_data;
	JLabel head01, category, select_name;

	JPanel north_p, center_p, south_p;

	JButton back_to_manager, change_sort, search_btn, all_btn;

	JTable table;
	JScrollPane scroll;
	DefaultTableModel dtm;
	public InquiryDisplay(String btn_text) {
		setLayout(new BorderLayout());

		head01 = new JLabel(btn_text);
		north_p = new JPanel(new FlowLayout(FlowLayout.LEFT));

		center_p = new JPanel();
		south_p = new JPanel();

		
		category = new JLabel("ī�װ�");
		//
		String[] list = {"�ֹ� ��ȣ", "��ǰ �̸�"};
		select_column = new JComboBox<String>(list);
		select_name = new JLabel("�˻� ��");
		select_data = new JTextField(8);
		search_btn = new JButton("�˻�");
		all_btn = new JButton("��ü����");

		back_to_manager = new JButton("������ �޴��� ���ư���");

		dtm = new InqTableModel(btn_text);

		table = new JTable(dtm);
		table.setAutoCreateRowSorter(true); // ���̺� ����/���� ��ȯ
		table.getTableHeader().setReorderingAllowed(false); // ���̺� �����Ұ�

		scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(480, 500));
		
		select_column.setPreferredSize(new Dimension(100, 20));

		back_to_manager.addActionListener(new ChangeFrameBtn(this));
		south_p.add(back_to_manager);

		center_p.add(scroll);
		center_p.add(category);
		center_p.add(select_column);
		center_p.add(select_name);
		center_p.add(select_data);
		center_p.add(search_btn);
		search_btn.addActionListener(new RepaintAction(btn_text, dtm,
													   select_column, select_data)); 
		center_p.add(all_btn);
		all_btn.addActionListener(new RepaintAction(btn_text, dtm,
				   									select_column, select_data)); 	
		north_p.add(head01);
		
		add(north_p, BorderLayout.NORTH);
		add(center_p, BorderLayout.CENTER);
		add(south_p, BorderLayout.SOUTH);

		TestSwingTools.initTestFrame(this, "INQ TEST", false);
	}

	public static void main(String[] args) {
		new InquiryDisplay("�Ǹ� ���� ��ȸ").setVisible(true);
	}
}
