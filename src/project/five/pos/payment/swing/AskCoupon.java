package project.five.pos.payment.swing;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class AskCoupon extends JFrame {
	
	int price;
	int spend = 10000;
	int discount = 500;
	
	public AskCoupon(int price) {
		this.price = price;
		Container c = this.getContentPane();

		 
		
		setTitle("쿠폰");
		
        // 쿠폰 입력 패널 4개 1. 사용유무 2. 쿠폰입력 3. 쿠폰 이미지, 할인 가격 표시, 4. 결제중 (로딩표시)
		JPanel panel04 = new JPanel(new CardLayout(15, 15));
		
		// 1. 사용유무
		JPanel yes_or_no = new JPanel(new GridLayout(2,1,0,0));
		// 2. 쿠폰 입력
		JPanel input_coupon = new JPanel(new GridLayout(3,1,0,0));
		// 3. 쿠폰 이미지 + 원래가격, 할인 가격 표시
		JPanel display_price = new JPanel(new GridLayout(1,2,0,0));
		// 4. 결제중 (로딩 표시) 자동 꺼짐
		JPanel loading = new JPanel();
		
		JLabel ask = new JLabel("쿠폰을 사용하시겠습니까?", SwingConstants.CENTER);
	
		JPanel yesnobtn = new JPanel(new FlowLayout());
		JButton yes = new JButton("예");
		JButton no = new JButton("아니오");
		
		// 다음 결제 프로세스 창
		yes.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(e.getButton() == MouseEvent.BUTTON1) {
					((CardLayout)panel04.getLayout()).next(panel04);
				}
			}
		});
		
		// 결제 취소 버튼
		no.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(e.getButton() == MouseEvent.BUTTON1) {
					dispose();
				}
			}
		});
		
		JLabel input_label = new JLabel("쿠폰번호를 입력해주세요", SwingConstants.CENTER);
		
		JPanel input_field = new JPanel(null);
		JTextField input_text = new JTextField("입력란");
		input_text.setBounds(175,0,100,25);
		
		JPanel input_btn = new JPanel(new FlowLayout());
		JButton check = new JButton("확인");
		JButton cancel = new JButton("취소");
		
		// 다음 결제 프로세스 창
		check.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					((CardLayout)panel04.getLayout()).next(panel04);
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
		JPanel left_coupon = new JPanel(new GridLayout(2,1,0,0));
		JPanel coupon_name = new JPanel();
		JPanel coupon_img = new JPanel();
		
		JLabel coupon_label = new JLabel("사용하신 쿠폰은 : (쿠폰이름) 입니다.");
		JLabel input_img = new JLabel("쿠폰 이미지");
		
		JPanel right_coupon = new JPanel(new GridLayout(2,1,0,0));
		JPanel total_calc = new JPanel();
		JPanel lcheck_panel = new JPanel(); 
		
		JLabel calc_label = new JLabel("총 결제액 : " + (spend - price + discount) + "입니다");
		JButton check2 = new JButton("확인");
		JButton cancel2 = new JButton("취소");
		
		// 메인에 4개의 패널들 추가
		panel04.add(yes_or_no);
		panel04.add(input_coupon);
		panel04.add(display_price);
		panel04.add(loading);
		
		// 라벨에 버튼 넣을수 없음
		// 패널에 넣으면 너무 커짐
		
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
		
		// - 세번째 패널
		
		coupon_name.add(coupon_label);
		coupon_img.add(input_img);
		left_coupon.add(coupon_name);
		left_coupon.add(coupon_img);
		
		total_calc.add(calc_label);
		lcheck_panel.add(check2);
		lcheck_panel.add(cancel2);
		right_coupon.add(total_calc);
		right_coupon.add(lcheck_panel);
		
		display_price.add(left_coupon);
		display_price.add(right_coupon);
		
		// - 네번째 패널
		
		

		
		setVisible(true);
		//setLayout(null);
		setSize(500, 170);
		setLocation(1180, 500);
		
		this.add(panel04, BorderLayout.CENTER);
	}

}
