package project.five.pos.payment.swing;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

import project.five.pos.db.PosVO;
import project.five.pos.device.comp.btn.action.ChangeFrameAction;
import project.five.pos.payment.swing.btn.action.BtnAction;
import project.five.pos.payment.swing.btn.action.ClickedBtnAction;

public class PayPanel extends JFrame {
	
	JFrame frame;
		
	// 뒷 배경 이미지
	final static String BG_IMG = "assets/images/backimg5.jpg";
	
	//코로나 고정 이미지
	final static String IMG_COVID = "assets/images/covid19.png";
	
	static JPanel main_center_panel;
	
	public static JPanel center_panel;
	public static CardLayout main_card;
	
	static JPanel junior_panel;
	static JPanel junior_panel2;
	
	static JButton ckmem_btn;
	static JButton register_btn;
	
	public static JPanel card_panel;
	public static CardLayout card;
	
	static JButton card_btn;
	static JButton cash_btn;
							
	public PayPanel(int order_num, int price, ArrayList<String> lists2, ArrayList<PosVO> update_cart) throws IOException {
				
		//동서남북 패널 지정
		JPanel south_panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		main_center_panel = new JPanel();
		main_center_panel.setOpaque(false);
		main_card = new CardLayout();
		main_center_panel.setLayout(main_card);
		
		center_panel = new JPanel(new GridLayout(4, 4, 5, 5));
		
		
		JPanel north_panel = new JPanel(new FlowLayout(FlowLayout.CENTER,5, 5));
		JPanel west_panel = new JPanel(new CardLayout(15, 15));
		JPanel east_panel = new JPanel(new CardLayout(15, 15));
				
		south_panel.setOpaque(false);
		center_panel.setOpaque(false);
		north_panel.setOpaque(false);
		west_panel.setOpaque(false);
		east_panel.setOpaque(false);
		
		main_center_panel.add("결제전", center_panel);
		
		JPanel productPanel = new JPanel(new GridLayout(2, 1, 0, 0));
		productPanel.setOpaque(false);
		
		JLabel stnsProduct = new JLabel("주문 하신 상품 리스트", SwingConstants.CENTER);
		stnsProduct.setForeground(Color.WHITE);
		stnsProduct.setFont(new Font("카페24 숑숑 보통",Font.BOLD, 16));
		
		JPanel productList = new JPanel(new FlowLayout());
		productList.setOpaque(false);
		
		//주니어 패널 지정
		junior_panel = new JPanel(new GridLayout(1, 2, 5, 5));	// 카드, 현금
		
		// 멤버십 패널 지정
		card_panel = new JPanel();
		card_panel.setOpaque(false);
		card = new CardLayout();
		 
		card_panel.setLayout(card);
		
		junior_panel2 = new JPanel(new GridLayout(1, 2, 5, 5));	// 멤버쉽 입력 / 등록
				
		card_panel.add("버튼", junior_panel2);
	
		
		junior_panel.setOpaque(false);
		junior_panel2.setOpaque(false);
		
		
		//카페 이름
		JLabel branch_name = new JLabel("Fancy a cuppa?");
		branch_name.setForeground(Color.WHITE);
		branch_name.setFont(new Font("카페24 숑숑 보통",Font.BOLD, 36));
				
		JLabel total_price = new JLabel("총 결제 금액 : " + price + "(원)", SwingConstants.CENTER);
		total_price.setForeground(Color.WHITE);
		total_price.setFont(new Font("카페24 숑숑 보통",Font.BOLD, 16));
				
		//버튼 모음
		ckmem_btn = new RoundedButton("멤버쉽 입력");
		ckmem_btn.setFont(new Font("카페24 숑숑 보통",Font.BOLD, 16));
		
		register_btn = new RoundedButton("멤버쉽 등록");
		register_btn.setFont(new Font("카페24 숑숑 보통",Font.BOLD, 16));
		
		register_btn.addActionListener(new BtnAction(register_btn));
		ckmem_btn.addActionListener(new BtnAction(ckmem_btn));

		card_btn = new RoundedButton("카드");
		card_btn.setFont(new Font("카페24 숑숑 보통",Font.BOLD, 16));
		cash_btn = new RoundedButton("현금");
		cash_btn.setFont(new Font("카페24 숑숑 보통",Font.BOLD, 16));
		
		
		//버튼 테두리 지정
		card_btn.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		cash_btn.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		
		JButton cancel_btn = new RoundedButton("취소하기");
		cancel_btn.addActionListener(new ChangeFrameAction(this));
		
		JButton payment_btn = new RoundedButton("결제하기");
		payment_btn.setEnabled(false);
		
		payment_btn.addActionListener(new BtnAction(payment_btn, price, this, order_num, lists2, update_cart));
		
		
		//버튼 기능(함수 ClickedBtnAction)
		card_btn.addActionListener(new ClickedBtnAction(junior_panel, "카드", card_btn, cash_btn, payment_btn, price));
		cash_btn.addActionListener(new ClickedBtnAction(junior_panel,"현금", card_btn, cash_btn, payment_btn, price));
		
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
			BufferedImage coImage = ImageIO.read(new File(IMG_COVID));
			int cox = coImage.getWidth();
			int coy = coImage.getHeight();
			east_panel.add(new JLabel(new ImageIcon(ImageIO.read(new File(IMG_COVID)).getScaledInstance(cox/5, coy/3, Image.SCALE_SMOOTH))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < lists2.size(); i++) {
			productList.add(new SetLabel(lists2.get(i), update_cart.get(i).getSelected_item(), 14));
		}
				
		//장바구니 리스트 패널들
		productPanel.add(stnsProduct);
		productPanel.add(productList);
		
		//장바구니 패널
		center_panel.add(productPanel);
		
		//멤버쉽 패널
		center_panel.add(card_panel);
		junior_panel2.add(ckmem_btn);
		junior_panel2.add(register_btn);
	
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
		JLabel label = null;
		try {
			BufferedImage bgImage = ImageIO.read(new File(BG_IMG));
			int bgx = bgImage.getWidth();
			int bgy = bgImage.getHeight();
			label = new JLabel(new ImageIcon(ImageIO.read(new File(BG_IMG)).getScaledInstance(bgx, bgy, Image.SCALE_SMOOTH)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		label.setBounds(0, 0, 875, 750);
		JPanel back = new JPanel(new BorderLayout());
		back.setBounds(0, 0, 875, 750);
		back.add(main_center_panel, BorderLayout.CENTER);
		back.add(north_panel, BorderLayout.NORTH);
		back.add(west_panel, BorderLayout.WEST);
		back.add(east_panel, BorderLayout.EAST);
		back.add(south_panel, BorderLayout.SOUTH);
		back.setOpaque(false);
		add(back);
		add(label);
		
				
	}
	
}
