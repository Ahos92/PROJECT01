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
	String[] header= {"메뉴", "옵션", "수량", "▲", "▼", "가격", "취소"};
	Object[][] select_list;
	int cell_btn_size;
	
	ArrayList<PosVO> cart_list;
	int order_cnt, order_num;
	CartDAO dao;
	
	Font font;
						// 패널로 보낸다 생각하면 
						// 메인프레임에 전달 받은 값으로 값을 받을 수있음
	public CartDisplay(String device_id) {	
		// 더미 데이터
		// 	 버튼으로 상품의 정보 전달 받음 (한번의 주문량)
		// 새로운 주문번호 들어올때 1증가
		dao = new CartDAO();
		cart_list = new ArrayList<>();
		order_num = dao.MaxOrderNumber();
		order_num++;
//		cart_list.add(dao.testOrder("아메리카노", "HOT", 2));
		cart_list.add(dao.testOrder("아메리카노", "ICE", 1));
		cart_list.add(dao.testOrder("홍차", "HOT", 1));
		cart_list.add(dao.testOrder("케이크", null, 1));
//		cart_list.add(dao.testOrder("빙수", null, 1));
		order_cnt = cart_list.size();	
	
		setLayout(new BorderLayout());
		
		south_p = new JPanel();
		center_p = new JPanel();
		
		// 주문내역 라벨
		info_lab = new JLabel("주문 내역");
			
		// 주문 내역 테이블
		//	- 상품객체 정보 배열에 받아서 출력
		select_list = new Object[order_cnt][6];
		for (int i = 0; i < order_cnt; i++) {
			select_list[i][0] = cart_list.get(i).getProduct_name();
			select_list[i][1] = cart_list.get(i).getTermsofcondition();
			select_list[i][2] = cart_list.get(i).getSelected_item();
			select_list[i][5] = cart_list.get(i).getProduct_price() * cart_list.get(i).getSelected_item();
		};
		dtm = new DefaultTableModel(select_list, header);
		cart_table = new JTable(dtm);
		scroll = new JScrollPane(cart_table);
		scroll.setPreferredSize(new Dimension(480, 100));
		
		cell_btn_size = 40;	
		cart_table.getColumn("취소").setCellRenderer(new DeleteBtnRender());
		cart_table.getColumn("취소").setCellEditor(new DeleteAction(new JCheckBox(), cart_table, dtm));
		cart_table.getColumn("취소").setPreferredWidth(cell_btn_size);
		
		cart_table.getColumn("▲").setCellRenderer(new UpDonwBtnRender("▲"));
		cart_table.getColumn("▲").setCellEditor(new UpDownAction(new JCheckBox(), cart_table, "▲"));
		cart_table.getColumn("▲").setPreferredWidth(cell_btn_size);
		
		cart_table.getColumn("▼").setCellRenderer(new UpDonwBtnRender("▼"));
		cart_table.getColumn("▼").setCellEditor(new UpDownAction(new JCheckBox(), cart_table, "▼"));
		cart_table.getColumn("▼").setPreferredWidth(cell_btn_size);

		// 결제 버튼 -> cartTable에 데이터 저장(commit X) 및 현재Frame false , 다음프레임 true
		//					패널용 생성자도 있음
		pay_btn = new CartBtn("결제", new PaymentPageAction(this, dtm, order_num, device_id));

		// 취소 버튼 -> (예전화면으로 돌아가고) 장바구니 초기화
		cancle_btn = new CartBtn("취소", new CancleAction(this, dtm));
	
		center_p.add(scroll);
		south_p.add(pay_btn);
		south_p.add(cancle_btn);
		
		add(info_lab, BorderLayout.NORTH);
		add(south_p, BorderLayout.SOUTH);
		add(center_p, BorderLayout.CENTER);
		
		TestSwingTools.initTestFrame(this, "장바구니 화면", true);
	}

	public static void main(String[] args) {
		new CartDisplay("1234");
	}
}

