package project.five.pos.sale;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import project.five.pos.sale.action.*;

public class OrderDisplay extends JFrame {
	
	JPanel southPanel;
	JPanel centerPanel;

	// 결제, 취소 버튼	
	JButton payBtn;
	JButton cancleBtn;
	
	// 주문내역라벨
	JTable table;
	JScrollPane scroll;
	static String header[]	= {"메뉴", "옵션", "수량", "총가격"};
	Object selectlist[][];
	static SaleDAO dao;
	static {
		dao = new SaleDAO();
	}
	static ArrayList<SaleDTO> cartlist = new ArrayList<>();
	int orderCount;

	public OrderDisplay() {	
		setLayout(new BorderLayout());
		southPanel = new JPanel();
		centerPanel = new JPanel();
	
		// 더미 데이터
		// 	 버튼으로 상품의 정보 전달 받음 (한번의 주문량)
		// 새로운 주문번호 들어올때 1증가
		int orderNumber = dao.MaxOrderNumber();
		orderNumber++;
		cartlist.add(dao.testOrder("아메리카노", "HOT", 2));
		cartlist.add(dao.testOrder("아메리카노", "ICE", 1));
		cartlist.add(dao.testOrder("홍차", "HOT", 1));
		cartlist.add(dao.testOrder("케이크", null, 1));
		cartlist.add(dao.testOrder("빙수", null, 1));
		orderCount = cartlist.size();
		
		// 주문번호, 주문내역 라벨 표시
		
		// 주문 내역 화면
		selectlist = new Object[orderCount][4];
		for (int i = 0; i < orderCount; i++) {
			selectlist[i][0] = cartlist.get(i).getProduct_name();
			selectlist[i][1] = cartlist.get(i).getTermsofcondition();
			selectlist[i][2] = cartlist.get(i).getOrder_count();
			selectlist[i][3] = cartlist.get(i).getProduct_price() * cartlist.get(i).getOrder_count();
		};
	
		table = new JTable(selectlist, header);
		scroll = new JScrollPane(table);
		add(scroll);
	
		payBtn = new SaleBtn("결제", 50);
		cancleBtn = new SaleBtn("취소", 50);
		
		// 결제화면 테스트 Frame으로 설정
		JFrame f = new JFrame("결제 화면 test");
		JLabel l = new JLabel("TEST 결제화면 입니다.");
		Font font;
		font = new Font("바탕", Font.BOLD, 30);
		l.setFont(font);
		l.setHorizontalAlignment(l.CENTER);
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
		f.setSize(500, 300);
		f.add(l);
		f.setLocation(1300, 100);
		f.setVisible(false);
		
		// 결제 버튼 -> cartTable에 데이터 저장(commit X) 및 현재Frame false , 다음프레임 true
		payBtn.addActionListener(new PaymentPageAction(this, f, cartlist, orderNumber));
		
		// 취소 버튼 -> (예전화면으로 돌아가고) 장바구니 초기화
		cancleBtn.addActionListener(new CancleAction());
		
		southPanel.add(payBtn);
		southPanel.add(cancleBtn);
		add(southPanel, BorderLayout.SOUTH);
		
		setTitle("장바구니 화면");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 300);
		setLocation(1300, 100);
		setVisible(true);
	}

	public static void main(String[] args) {		
		new OrderDisplay();
	}

}

