package project.five.pos.device.subdisplay;

import java.awt.*;

import java.util.*;

import javax.swing.*;
import javax.swing.table.*;

import project.five.pos.device.SearchDB;
import project.five.pos.device.TableList;
import project.five.pos.device.actionbtn.ChangeFrameBtn;
import project.five.pos.device.comp.IntegerComp;
import project.five.pos.device.comp.SortTable;
import project.five.pos.sale.*;

public class InquiryDisplay extends JFrame {

	JLabel head;
	
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
	SortTable sort;
	
	public InquiryDisplay(String btn_text) {
		setLayout(new BorderLayout());

		head = new JLabel(btn_text);
		north_p = new JPanel(new FlowLayout(FlowLayout.LEFT));
		north_p.add(head);

		center_p = new JPanel();
		south_p = new JPanel();


		config_data =  new TableList(btn_text);
		// ��ȸ�� �÷� ����
		header = config_data.header();

		// ��ȸ�� DB �˻�
		search_data = new SearchDB().outToTable(btn_text);

		// ����� �÷��� �´� ������ ��

		inquiry_list = config_data.data(search_data, header);

		dtm = new DefaultTableModel(inquiry_list, header);
		table = new JTable(dtm);
		
		// ��������, �������� �޼���
		sort = new SortTable(table);
		
		scroll = new JScrollPane(table);

		scroll.setPreferredSize(new Dimension(480, 500));
		center_p.add(scroll);

		back_to_manager = new JButton("������ �޴��� ���ư���");
		back_to_manager.addActionListener(new ChangeFrameBtn(this));
		south_p.add(back_to_manager);

		add(north_p, BorderLayout.NORTH);
		add(center_p, BorderLayout.CENTER);
		add(south_p, BorderLayout.SOUTH);

		TestSwingTools.initTestFrame(this, "INQ TEST", false);
	}

	public static void main(String[] args) {
		new InquiryDisplay("�Ǹ� ���� ��ȸ").setVisible(true);
	}
}
