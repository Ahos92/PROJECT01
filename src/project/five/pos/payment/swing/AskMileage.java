package project.five.pos.payment.swing;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AskMileage extends JFrame{
	
	static Connection conn;
	static PreparedStatement ps;
	static ResultSet rs;

	LocalDate today;
	Timestamp tstp2;
	int price;
	int actual_expenditure;
	String couponNo;
	int device_id;
	
	static int ml_as_you_wish = 0;
	static int expenditure_ml = 0;
	
	public AskMileage(Timestamp tstp2, int price, int actual_expenditure, String couponNo, int device_id) {
		this.tstp2 = tstp2;
		this.price = price;
		this.actual_expenditure = actual_expenditure;
		this.couponNo = couponNo;
		this.device_id = device_id;
		
		setTitle("마일리지 사용");
		
		// 메인 마일리지 패널
		JPanel panel_ml = new JPanel(new CardLayout(15, 15));
		
		// 첫번째 마일리지 패널
		JPanel use_or_keep = new JPanel(new GridLayout(3,1,0,0));
		
		// 두번째 마일리지 패널
		JPanel input_mileage = new JPanel(new GridLayout(3,1,0,0));
		
		// 세번째 마일리지 패널
		JPanel show_total = new JPanel(new GridLayout(2,1,0,0));
		
		JLabel ask_mileage = new JLabel("가지고 계신 마일리지를 사용하시겠습니까?");
		JLabel show_ml = new JLabel("마일리지 금액 : " + CheckMem.memberMileage);
		JPanel flw_btn = new JPanel(new FlowLayout());
		
		JButton sure = new JButton("확인");
		
		sure.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(e.getButton() == MouseEvent.BUTTON1) {
					((CardLayout)panel_ml.getLayout()).next(panel_ml);
				}
			}
		});
		
		JButton nah = new JButton("아니요");
		
		nah.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				JButton btn = (JButton) e.getSource();
				if(btn.getText().equals("아니요")) {
										
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
				
			}
			
		});
		
		
		JLabel input_ml_label = new JLabel("사용하실 마일리지 금액을 입력해주세요", SwingConstants.CENTER);
		JPanel input_ml_field = new JPanel(null);
		JTextField input_ml_text = new JTextField(6);
		input_ml_text.setBounds(175,0,100,25);
		
		input_ml_text.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				
				char c = e.getKeyChar();
				 
				  JTextField src = (JTextField) e.getSource();
				  
				  if (!Character.isDigit(c)) {
					  e.consume();
					  return;
				  }
				  
				  else if(src.getText().length() >= 6) {
					  e.consume();
					  return;
				  }	  
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
								
			}

			@Override
			public void keyReleased(KeyEvent e) {
					
			}
			
		});
		
		JPanel input_ml_btn = new JPanel(new FlowLayout());
		JButton check_ml = new JButton("확인");
		
		
		JPanel flw_btn2 = new JPanel(new FlowLayout());
		
		// 마지막 확인 버튼
		JButton okay = new JButton("확인");
		
		okay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				JButton btn = (JButton) e.getSource();
				if(btn.getText().equals("확인")) {
																
					// 결제 정보 DB 전송 (마일리지 적용값 전송)
					new PaymentQuery(tstp2, price, expenditure_ml, couponNo, device_id);
					
					// 적립 + 등급업 추가 (마일리지 적용 금액)						
					new MembershipQuery(expenditure_ml, ml_as_you_wish);
					
					//마일리지 입력값 초기화
					ml_as_you_wish = 0;
					
					// 메인 패널 초기화
					new ResetMain();
					
					// 결제 성공
					new SuccessPayment();
					
					dispose();	
					
				}
			}
			
		});
		
		//마지막 취소
		JButton no = new JButton("취소");
		
		no.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				new CancelPayment();
				dispose();
			}
		});
		
		
		// 마일리지 체크 확인 버튼
		check_ml.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				if(e.getButton() == MouseEvent.BUTTON1) {
					
					//마일리지 입력값
					ml_as_you_wish = Integer.parseInt(input_ml_text.getText());
					
					//실지출액 + 마일리지 입력 적용
					expenditure_ml = actual_expenditure - ml_as_you_wish;
								
					if(ml_as_you_wish % 100 != 0) {
						new HundredsMl();
					}
					else if(ml_as_you_wish > CheckMem.memberMileage) {
						new GreaterThanMl();						
					}
					else {
						JLabel calc_ml = new JLabel("<html>결제 금액"+ actual_expenditure +
								"- 마일리지" + ml_as_you_wish + "<br/>"
								+ "=" + expenditure_ml +"</html>", SwingConstants.CENTER);
					
						// 세번째 마일리지 패널	
						show_total.add(calc_ml);
						flw_btn2.add(okay);
						flw_btn2.add(no);
						show_total.add(flw_btn2);
						
						panel_ml.add(show_total);
						
						((CardLayout)panel_ml.getLayout()).next(panel_ml);
						
					}
																									
				}
				
			}
		});
		
		// 중도 취소 버튼
		JButton cancel_ml = new JButton("취소");
		
		cancel_ml.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				new CancelPayment();
				dispose();
			}
		});
		
				
		// 첫번째 마일리지 패널
		use_or_keep.add(ask_mileage);
		use_or_keep.add(show_ml);
		
		flw_btn.add(sure);
		flw_btn.add(nah);
		
		use_or_keep.add(flw_btn);
		
		// 두번째 마일리지 패널	
		input_ml_field.add(input_ml_text);
		
		input_ml_btn.add(check_ml);
		input_ml_btn.add(cancel_ml);
		
		input_mileage.add(input_ml_label);
		input_mileage.add(input_ml_field);
		input_mileage.add(input_ml_btn);
		
		
		panel_ml.add(use_or_keep);
		panel_ml.add(input_mileage);
		
		
		setVisible(true);
		//setLayout(null);
		setSize(500, 185);
		setLocation(1180, 500);
		
		this.add(panel_ml, BorderLayout.CENTER);
	}
}
