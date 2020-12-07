package project.five.pos.payment.swing;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import project.five.pos.payment.swing.btn.action.BtnAction;
import project.five.pos.payment.swing.btn.action.ClickedBtnAction;



public class PayPanel extends JFrame {
	
	ImageIcon bg = new ImageIcon(ImageIO.read(new File("assets/images/background.jpg")).getScaledInstance(1000, 1000, Image.SCALE_SMOOTH));
	
	//코로나 고정 이미지
	final static String IMG_COVID = "C:\\자바 취업반 과정\\covid19.png";

	static int price = 5000;
	static String lists = "아이스 아메리카노";
	
	
	
	public PayPanel() throws IOException {
		
		
		
		JTextField register = new JTextField("멤버쉽 입력");
		JTextField input_cp = new JTextField("쿠폰 입력");
		
		// 프레임 설정 함수		
		
		
		
		//동서남북 패널 지정
		JPanel south_panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JPanel center_panel = new JPanel(new GridLayout(5, 5, 5, 5));
		JPanel north_panel = new JPanel(new FlowLayout(FlowLayout.CENTER,5, 5));
		JPanel west_panel = new JPanel(new CardLayout(15, 15));
		JPanel east_panel = new JPanel(new CardLayout(15, 15));
		
		south_panel.setOpaque(false);
		center_panel.setOpaque(false);
		north_panel.setOpaque(false);
		west_panel.setOpaque(false);
		east_panel.setOpaque(false);
		
		//주니어 패널 지정
		JPanel junior_panel = new JPanel(new GridLayout(1, 2, 5, 5));	// 카드, 현금
		
		JPanel junior_panel2 = new JPanel(new GridLayout(1, 2, 5, 5));	// 멤버쉽 입력 / 등록
		
		
		//라벨 모음
		JLabel branch_name = new JLabel("Fancy a cuppa?");
		branch_name.setForeground(Color.WHITE);
		
		
		JLabel speicality = new JLabel("추천 메뉴");
		
		
		JLabel item_list = new JLabel("주문하신 상품 :" + lists + "(이미지로 교체)");
		JLabel total_price = new JLabel("총 결제할 금액 : " + price, SwingConstants.CENTER);
		
		
		item_list.setForeground(Color.WHITE);
		total_price.setForeground(Color.WHITE);
		
		
		//버튼 모음
		JButton ckmem_btn = new JButton("멤버쉽 입력");
		
		
		JButton register_btn = new JButton("멤버쉽 등록");
		
		register_btn.addActionListener(new BtnAction(register_btn));
		
		
		
		JButton card_btn = new JButton("카드");
		JButton cash_btn = new JButton("현금");
		
		//버튼 테두리 지정
		card_btn.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		cash_btn.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		
		JButton cancel_btn = new JButton("취소하기");
		JButton payment_btn = new JButton("결제하기");
		
		//버튼 기능(함수 ClickedBtnAction)
		card_btn.addActionListener(new ClickedBtnAction(junior_panel, "카드"));
		cash_btn.addActionListener(new ClickedBtnAction(junior_panel,"현금"));
		
		//패널 추가
		
		
		// 이름 변경 고안
		north_panel.add(branch_name);
		
		// 추천메뉴 추가
		for(ImageEnum imgname : ImageEnum.values()) {
			west_panel.add(imgname.getLname(), new ImageLabel(imgname));
		}
		// 메뉴 로테이트(클릭) -> 시간 지남에 따라 자동으로 바꿔보기
		west_panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(e.getButton() == MouseEvent.BUTTON1) {
					((CardLayout)west_panel.getLayout()).next(west_panel);
				}
			}
		});
		
		// 코로나 마스크 포스터 추가
		try {
			east_panel.add(new JLabel(new ImageIcon(ImageIO.read(new File(IMG_COVID)).getScaledInstance(150, 400, Image.SCALE_SMOOTH))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//장바구니 패널
		center_panel.add(item_list);
		
		//멤버쉽 패널
		center_panel.add(junior_panel2);
		junior_panel2.add(ckmem_btn);
		junior_panel2.add(register_btn);
		
		
		//쿠폰 패널
		center_panel.add(input_cp);
		
		//결제 타입 패널
		center_panel.add(junior_panel);
		junior_panel.add(card_btn);
		junior_panel.add(cash_btn);
		
		
		center_panel.add(total_price);
		
		//결제 관리 패널
		south_panel.add(cancel_btn);
		south_panel.add(payment_btn);
		
		//매직 소스
		setVisible(true);
		
		ProSwingTools.initTestFrame(this);
		JLabel label = new JLabel(bg);
		label.setBounds(0, 0, 1000, 900);
		JPanel back = new JPanel(new BorderLayout());
		back.setBounds(0, 0, 1000, 900);
		back.add(center_panel, BorderLayout.CENTER);
		back.add(north_panel, BorderLayout.NORTH);
		back.add(west_panel, BorderLayout.WEST);
		back.add(east_panel, BorderLayout.EAST);
		back.add(south_panel, BorderLayout.SOUTH);
		back.setOpaque(false);
		add(back);
		add(label);
		
		
	}
	
	public static void main(String[] args) {
		try {
			new PayPanel();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
