package project.five.pos.device;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import project.five.pos.device.action.ChangeFrameBtn;
import project.five.pos.device.action.TableList;
import project.five.pos.sale.SaleDAO;
import project.five.pos.sale.SaleDTO;
import project.five.pos.sale.TestSwingTools;

public class InquiryDisplay extends JFrame {

	JPanel center_p;
	JPanel south_p;
	
	ArrayList<String> head_data;
	String[] header;
	int head_length;
	
	SaleDAO dao;
	ArrayList<SaleDTO> cartlist;
	Object[][] inquiry_list;
	int inq_length;
	
	JTable table;
	JScrollPane scroll;
	DefaultTableModel dtm;

	JButton back_to_manager;
	
	public InquiryDisplay(String btn_text) {
		setLayout(new BorderLayout());
		
		center_p = new JPanel();
		south_p = new JPanel();
		
		header = new TableList(btn_text).header();
		head_length = header.length;
		
		dao = new SaleDAO();
		cartlist = dao.searchAllCart("order_no", "asc");
		inq_length = cartlist.size();
		
		inquiry_list = new Object[inq_length][head_length];
		for (int i = 0; i < inq_length; i++) {
			inquiry_list[i][0] = cartlist.get(i).getCart_no();
			inquiry_list[i][1] = cartlist.get(i).getOrder_no();
			inquiry_list[i][2] = cartlist.get(i).getProduct_name();
			inquiry_list[i][3] = cartlist.get(i).getSelected_item();
			inquiry_list[i][4] = cartlist.get(i).getTotal_price();
		};
		
		dtm = new DefaultTableModel(inquiry_list, header);
		table = new JTable(dtm);
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
