package project.five.pos.payment.swing;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import project.five.pos.payment.swing.btn.action.NumberField;

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
	
	final static String BG_IMG = "assets/images/miniback7.png";
	
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
		
		JLabel ask_mileage = new JLabel("가지고 계신 마일리지를 사용하시겠습니까?", SwingConstants.CENTER);
		ask_mileage.setForeground(Color.WHITE);
		ask_mileage.setFont(new Font("카페24 숑숑 보통",Font.BOLD, 13));
		
		JLabel show_ml = new JLabel("마일리지 금액 : " + CheckMem.memberMileage, SwingConstants.CENTER);
		show_ml.setForeground(Color.WHITE);
		show_ml.setFont(new Font("카페24 숑숑 보통",Font.BOLD, 13));
		
		JPanel flw_btn = new JPanel(new FlowLayout());
		
		JButton sure = new MiniButton("예");
		
		sure.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(e.getButton() == MouseEvent.BUTTON1) {
					((CardLayout)panel_ml.getLayout()).next(panel_ml);
				}
			}
		});
		
		JButton nah = new MiniButton("아니요");
		
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
		input_ml_label.setForeground(Color.WHITE);
		input_ml_label.setFont(new Font("카페24 숑숑 보통",Font.BOLD, 13));
		
		JPanel input_ml_field = new JPanel(null);
		JTextField input_ml_text = new JTextField(6);
		input_ml_text.setBounds(175,0,115,25);
		
		input_ml_text.addKeyListener(new NumberField(6));
		
		JPanel input_ml_btn = new JPanel(new FlowLayout());
		JButton check_ml = new MiniButton("확인");
		
		
		JPanel flw_btn2 = new JPanel(new FlowLayout());
		
		// 마지막 확인 버튼
		JButton okay = new MiniButton("확인");
		
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
		JButton no = new MiniButton("취소");
		
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
						JLabel calc_ml = new JLabel("<html>결제 금액은"+ actual_expenditure +
								"(원)- 마일리지 차감:" + ml_as_you_wish + "(원)<br/>"
								+ "= 실지출액 :" + expenditure_ml +"(원)입니다.</html>", SwingConstants.CENTER);
						
						calc_ml.setForeground(Color.WHITE);
						calc_ml.setFont(new Font("카페24 숑숑 보통",Font.BOLD, 13));
					
						// 세번째 마일리지 패널	
						show_total.add(calc_ml);
						flw_btn2.add(okay);
						flw_btn2.add(no);
						
						flw_btn2.setOpaque(false);
						
						show_total.add(flw_btn2);
						
						show_total.setOpaque(false);
						
						panel_ml.add(show_total);
						
						((CardLayout)panel_ml.getLayout()).next(panel_ml);
						
					}
																									
				}
				
			}
		});
		
		// 중도 취소 버튼
		JButton cancel_ml = new MiniButton("취소");
		
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
		
		show_ml.setOpaque(false);
		flw_btn.setOpaque(false);
		
		use_or_keep.add(flw_btn);
		
		// 두번째 마일리지 패널	
		input_ml_field.add(input_ml_text);
		
		input_ml_btn.add(check_ml);
		input_ml_btn.add(cancel_ml);
		
		input_ml_field.setOpaque(false);
		input_ml_btn.setOpaque(false);
		
		input_mileage.add(input_ml_label);
		input_mileage.add(input_ml_field);
		input_mileage.add(input_ml_btn);
		
		use_or_keep.setOpaque(false);
		input_mileage.setOpaque(false);
		
		panel_ml.add(use_or_keep);
		panel_ml.add(input_mileage);
		
		panel_ml.setOpaque(false);
		
		setVisible(true);
		//setLayout(null);
		setSize(500, 210);
		setLocationRelativeTo(null);
		
		JLabel label = null;
		try {
			BufferedImage bgImage = ImageIO.read(new File(BG_IMG));
			int bgx = bgImage.getWidth();
			int bgy = bgImage.getHeight();
			label = new JLabel(new ImageIcon(ImageIO.read(new File(BG_IMG)).getScaledInstance(bgx, bgy, Image.SCALE_SMOOTH)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		label.setBounds(0, 0, 500, 170);
		JPanel back = new JPanel(new BorderLayout());
		back.setBounds(0, 0, 500, 170);
		back.add(panel_ml, BorderLayout.CENTER);
		
		back.setOpaque(false);
		add(back);
		add(label);
		
		//this.add(panel_ml, BorderLayout.CENTER);
	}
}
