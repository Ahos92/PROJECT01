package project.five.pos.sale;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import project.five.pos.TestSwingTools;
import project.five.pos.db.PosVO;
import project.five.pos.sale.btn.CartBtn;
import project.five.pos.sale.btn.action.*;
import project.five.pos.sale.btn.render.*;

public class CartDisplay extends JFrame {

	JPanel south_p, center_p;

	JButton pay_btn, cancle_btn;

	JLabel info_lab;
	
	JTable cart_table;
	DefaultTableModel dtm;
	JScrollPane scroll;
	String[] header= {"메뉴", "옵션", "수량", "가격", "▲", "▼", "취소"};
	Object[][] select_list;
	int cell_btn_size;
	
	ArrayList<PosVO> cart_list;
	int order_cnt, order_num;
	CartDAO dao;
	
	Font font;
						// 패널로 보낸다 생각하면 
						// 메인프레임에 전달 받은 값으로 값을 받을 수있음
	public CartDisplay(String device_id, Object[][] list) {	
		
//		cart_list = new ArrayList<>();
//		cart_list.add(dao.testOrder("아메리카노", "HOT", 2));
//		cart_list.add(dao.testOrder("아메리카노", "ICE", 1));
//		cart_list.add(dao.testOrder("홍차", "HOT", 1));
//		cart_list.add(dao.testOrder("케이크", null, 1));
//		cart_list.add(dao.testOrder("빙수", null, 1));
//		order_cnt = cart_list.size();	
// 주문 내역 테이블
//	- 상품객체 정보 배열에 받아서 출력
//		select_list = new Object[order_cnt][6];
//		for (int i = 0; i < order_cnt; i++) {
//			select_list[i][0] = cart_list.get(i).getProduct_name();
//			select_list[i][1] = cart_list.get(i).getTermsofcondition();
//			select_list[i][2] = cart_list.get(i).getSelected_item();
//			select_list[i][5] = cart_list.get(i).getProduct_price() * cart_list.get(i).getSelected_item();
//		};
	
		setLayout(new BorderLayout());
		
		dao = new CartDAO();
		
		south_p = new JPanel();
		center_p = new JPanel();

		info_lab = new JLabel("주문 내역");
			
		order_num = dao.MaxOrderNumber();
		order_num++;
		select_list = list;
		dtm = new DefaultTableModel(select_list, header);
		cart_table = new JTable(dtm);
		scroll = new JScrollPane(cart_table);
		scroll.setPreferredSize(new Dimension(480, 100));
		
		cell_btn_size = 40;	
		
		createcellBtn(cart_table, "취소", cell_btn_size);
		
		createcellBtn(cart_table, "▲", cell_btn_size);
		
		createcellBtn(cart_table, "▼", cell_btn_size);

		pay_btn = new CartBtn("결제", new PaymentPageAction(this, dtm, order_num, device_id));
		
		cancle_btn = new CartBtn("취소", new CancleAction(this, dtm));
	
		center_p.add(scroll);
		south_p.add(pay_btn);
		south_p.add(cancle_btn);
		
		add(info_lab, BorderLayout.NORTH);
		add(south_p, BorderLayout.SOUTH);
		add(center_p, BorderLayout.CENTER);
		
		TestSwingTools.initTestFrame(this, "장바구니 화면", true);
	}
	
	private void createcellBtn(JTable cart_table, String btn_txt, int cell_btn_size) {
		if (btn_txt.equals("취소")) {
			cart_table.getColumn(btn_txt).setCellRenderer(new DeleteBtnRender());
			cart_table.getColumn(btn_txt).setCellEditor(new DeleteAction(new JCheckBox(), cart_table, dtm));
			cart_table.getColumn(btn_txt).setPreferredWidth(cell_btn_size);
		} else {
			cart_table.getColumn(btn_txt).setCellRenderer(new UpDonwBtnRender(btn_txt));
			cart_table.getColumn(btn_txt).setCellEditor(new UpDownAction(new JCheckBox(), cart_table, btn_txt));
			cart_table.getColumn(btn_txt).setPreferredWidth(cell_btn_size);
		}
	}

}

