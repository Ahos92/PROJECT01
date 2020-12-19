package project.five.pos.payment.swing;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import project.five.pos.db.DBManager;
import project.five.pos.payment.swing.btn.action.ClickedBtnAction;
import project.five.pos.payment.swing.btn.action.NumberField;
import project.five.pos.sale.SaleDisplay;

// 주의할점
//라벨에 버튼 넣을수 없음 = 둘 다 컴포넌트 이기 때문에
//패널에 넣으면 너무 커짐

public class AskCoupon extends JFrame {
	
	static Connection conn;
	static PreparedStatement ps;
	static ResultSet rs;
			
	int price;
	JFrame frame;
	int order_num;
	
	static int change;
	int actual_expenditure;
	
	static int couponPrice;
	static String couponNo;
	static String couponName;

	static String startDate;
	static String expiredDate;

	static LocalDate today;
	static LocalDateTime today2;
	static Timestamp tstp2;
	
	static int device_id = 1234;
	
	JLabel order_cart;
	
	public AskCoupon(int price, JFrame frame, int order_num, ArrayList<String> lists2) {
		this.price = price;
		this.frame = frame;
		this.order_num = order_num;
		
		// 쿠폰 날짜 비교
		today = LocalDate.now();
		
		// 결제 시간 출력용
		today2 =LocalDateTime.now(ZoneId.of("Asia/Seoul"));
		
		tstp2 = Timestamp.valueOf(today2);
		
		setTitle("쿠폰");
		
        // 쿠폰 입력 패널 3개 1. 사용유무 2. 쿠폰입력 3. 쿠폰 이미지, 할인 가격 표시
		JPanel panel04 = new JPanel(new CardLayout(15, 15));
		
		// 1. 사용유무
		JPanel yes_or_no = new JPanel(new GridLayout(2,1,0,0));
		// 2. 쿠폰 입력
		JPanel input_coupon = new JPanel(new GridLayout(3,1,0,0));
		// 3. 쿠폰 이미지 + 원래가격, 할인 가격 표시
		JPanel display_price = new JPanel(new GridLayout(1,2,0,0));
		
		JLabel ask = new JLabel("쿠폰을 사용하시겠습니까?", SwingConstants.CENTER);
	
		JPanel yesnobtn = new JPanel(new FlowLayout());
		
		JButton yes = new JButton("예");
		
		// 다음 결제 프로세스 창
		yes.addMouseListener(new MouseAdapter() {
					
			@Override
			public void mouseClicked(MouseEvent e) {
						
				if(e.getButton() == MouseEvent.BUTTON1) {
					((CardLayout)panel04.getLayout()).next(panel04);
				}
			}
		});
		
		JPanel result_panel = new JPanel(new GridLayout(3,1,0,0));
		result_panel.setOpaque(false);
		
		JLabel result_explain = new JLabel("주문하신 상품 리스트", SwingConstants.CENTER);
		result_explain.setForeground(Color.WHITE);
		result_explain.setFont(new Font("Serif",Font.BOLD, 36));
		
		JPanel order_list = new JPanel(new FlowLayout());
		order_list.setOpaque(false);
		
		
		
		for(int i = 0; i < lists2.size(); i++) {
			order_list.add(new SetLabel(lists2.get(i), 36));
		}
				
		JLabel order_no = new JLabel("주문 번호 : " + order_num, SwingConstants.CENTER);
		order_no.setForeground(Color.WHITE);
		order_no.setFont(new Font("Serif",Font.BOLD, 36));
		
		result_panel.add(result_explain);
		result_panel.add(order_list);
		result_panel.add(order_no);
		
		PayPanel.main_center_panel.add("결제후", result_panel);
				
		JButton no = new JButton("아니요");
		
		no.addActionListener(new ActionListener() {
						
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				JButton btn = (JButton) e.getSource();
				if(btn.getText().equals("아니요")) {
					actual_expenditure = price;
					
					if(CheckMem.memberOn == true && CheckMem.memberMileage >= 1000) {
						new AskMileage(tstp2, price, actual_expenditure, couponNo, device_id);
						
						dispose();	
						
					}else if(CheckMem.memberOn == true){
						
						// 결제 정보 DB 전송
						new PaymentQuery(tstp2, price, actual_expenditure, couponNo, device_id);						
						
						// 적립 + 등급업 추가						
						new MembershipQuery(actual_expenditure);
										
						// 메인 패널 초기화
						new ResetMain();
						
						// 결제 성공
						new SuccessPayment();
						
						dispose();	
					}
					
					else {
						
						// 결제 정보 DB 전송
						new PaymentQuery(tstp2, price, actual_expenditure, couponNo, device_id);						
																	
						// 메인 패널 초기화
						new ResetMain();
						
						// 결제 성공
						new SuccessPayment();
						
						dispose();	
					}
				}
				
			}
			
		});
				
		JLabel input_label = new JLabel("쿠폰번호를 입력해주세요", SwingConstants.CENTER);
		
		JPanel input_field = new JPanel(null);
		JTextField input_text = new JTextField(6);
		input_text.setBounds(175,0,100,25);
		
		input_text.addKeyListener(new NumberField(6));
					
		JPanel input_btn = new JPanel(new FlowLayout());
		JButton check = new JButton("확인");
		JButton cancel = new JButton("취소");
		
		
		JPanel left_coupon = new JPanel(null);
		JPanel coupon_name = new JPanel();
		coupon_name.setBounds(0, 0, 200, 25);
		
		JPanel coupon_img = new JPanel(new CardLayout(15, 15));
		coupon_img.setBounds(0, 25, 220, 90);
					
		JPanel right_coupon = new JPanel(new GridLayout(2,1,0,0));
		JPanel total_calc = new JPanel();
		JPanel lcheck_panel = new JPanel(); 
		
		for(CouponEnum cpname : CouponEnum.values()) {
			coupon_img.add(cpname.getCname(), new CouponLabel(cpname));
		}
		
	
		JButton check2 = new JButton("확인");
		
		check2.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				// 결제 정보 DB 전송
				new PaymentQuery(tstp2, price, actual_expenditure, couponNo, device_id);
				
				// 멤버 일때 적립
				if(CheckMem.memberOn == true) {
					new MembershipQuery(actual_expenditure);
				}
				
				//쿠폰번호 초기화
				couponNo = "";
								
				// 메인 패널 초기화
				new ResetMain();	
				
				// 결제 성공
				new SuccessPayment();
				dispose();
			}
		});
		
		JButton cancel2 = new JButton("취소");
		
		// 결제 취소 버튼
		cancel2.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
												
				new CancelPayment();
				dispose();								
			}
		});
		
		PayPanel.main_center_panel.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					
					//버튼 기능(함수 ClickedBtnAction)
					PayPanel.main_card.show(PayPanel.main_center_panel, "결제전");	
					new SaleDisplay();
					
					frame.dispose();
				}
				
			}
		});
		
		// 다음 결제 프로세스 창
		check.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseClicked(MouseEvent e) {
					
					
					
					if(e.getButton() == MouseEvent.BUTTON1) {
							couponNo = input_text.getText();
							
							try {
								conn = DBManager.getConnection();

								ps = conn.prepareStatement("SELECT * FROM coupon "
										+ "WHERE coupon_no = ?");
								
								ps.setString(1, couponNo);
								
								rs = ps.executeQuery();
								
								couponName = "";
								couponPrice = 0;
								startDate ="";
								expiredDate = "";
								if(rs.next()) {
									
									do {
										AskCoupon.couponNo = rs.getString("coupon_no");
										AskCoupon.couponName = rs.getString("coupon_name");
										AskCoupon.couponPrice = rs.getInt("coupon_price");
										AskCoupon.startDate = rs.getString("start_date").substring(0, 10);
										AskCoupon.expiredDate = rs.getString("expired_date").substring(0, 10);
																																															
										LocalDate stDate = LocalDate.parse(AskCoupon.startDate);
										LocalDate exDate = LocalDate.parse(AskCoupon.expiredDate);
										
										// 기한이 지난 쿠폰
										if(today.isBefore(stDate) || today.isAfter(exDate)) {
											new ExpiredCoupon();
										}
										else {
										JLabel coupon_label = new JLabel("사용하신 쿠폰은 : " + AskCoupon.couponName + "입니다.");
										
										((CardLayout)coupon_img.getLayout()).show(coupon_img, AskCoupon.couponName);
										
										//실 지출액
										actual_expenditure = (price - AskCoupon.couponPrice);
										// 거스름돈 계산
										change = PaidByCash.i_money - actual_expenditure;
										
										if(ClickedBtnAction.getPaymentType().toString().contains("CARD")) {
											JLabel calc_label2 = new JLabel("<html>카드 결제 입니다<br/>결제 금액 : " + price + "-" + AskCoupon.couponPrice + "=" + actual_expenditure + " (원)<html>", SwingConstants.CENTER);
											total_calc.add(calc_label2);
											}
										else {
											JLabel calc_label = new JLabel("<html>결제 금액 : " + price + "-" + AskCoupon.couponPrice + "=" + actual_expenditure + " (원)<br/>입금 금액 : " + PaidByCash.i_money + "(원)"
													+ "<br/>거스름돈 : "+  change + "(원)</html>", SwingConstants.CENTER);
											total_calc.add(calc_label);
										}
																																																												
										// - 세번째 패널
										coupon_name.add(coupon_label);
										left_coupon.add(coupon_name);
										left_coupon.add(coupon_img);
										
										
										lcheck_panel.add(check2);
										lcheck_panel.add(cancel2);
										right_coupon.add(total_calc);
										right_coupon.add(lcheck_panel);
										
										display_price.add(left_coupon);
										display_price.add(right_coupon);
											
										// 메인에 3의 패널 추가
										panel04.add(display_price);

										((CardLayout)panel04.getLayout()).next(panel04);
										}
										
									}while (rs.next()); 
										
								}
								else {
									// 존재하지 않는 쿠폰 입니다
									new NoneCoupon();	
								}
								
								rs.close();
								ps.close();
								conn.close();
								
							} catch (SQLException ex) {
								ex.printStackTrace();
							}
							
					}
						
				}
			});
			
		// 뒤로가기 버튼
		cancel.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					((CardLayout)panel04.getLayout()).previous(panel04);
				}
				
			}
		});
		
		// - 첫번째 패널
		yesnobtn.add(yes);
		yesnobtn.add(no);
		yes_or_no.add(ask);
		yes_or_no.add(yesnobtn);
		
		// - 두번째 패널
		input_field.add(input_text);
		input_btn.add(check);
		input_btn.add(cancel);
		input_coupon.add(input_label);
		input_coupon.add(input_field);
		input_coupon.add(input_btn);
		
		// 메인에 1, 2의 패널들 추가
		panel04.add(yes_or_no);
		panel04.add(input_coupon);
		
		setVisible(true);
		//setLayout(null);
		setSize(500, 185);
		setLocation(1180, 500);
		
		this.add(panel04, BorderLayout.CENTER);
	}

}
