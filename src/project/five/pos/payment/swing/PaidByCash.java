package project.five.pos.payment.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class PaidByCash extends JFrame{

	final static String BG_IMG = "assets/images/miniback7.png";
	
	int price;	
	JButton card_btn;
	JButton cash_btn;
	JButton payment_btn;
	
	static int i_money;
	
	String[] choices = {"50000", "10000", "5000", "1000"};
			
	public PaidByCash(int price, JButton card_btn, JButton cash_btn, JButton payment_btn) {
		this.price = price;
		this.card_btn = card_btn;
		this.cash_btn = cash_btn;
		this.payment_btn = payment_btn;
		
		setTitle("결제금액 : " + this.price + "원");
		
		JPanel insertCash = new JPanel(new GridLayout(3,1,0,0));
		JPanel stnPanel = new JPanel();
		JPanel showMoney = new JPanel(new FlowLayout());
		JPanel clickPanel = new JPanel(new FlowLayout());
		
		JLabel cashIn = new JLabel("현금을 투입구에 넣어주세요");
		cashIn.setForeground(Color.WHITE);
		cashIn.setFont(new Font("카페24 숑숑 보통",Font.BOLD, 13));
		
		
		JLabel howMuch = new JLabel("투입 금액 :");
		howMuch.setForeground(Color.WHITE);
		howMuch.setFont(new Font("카페24 숑숑 보통",Font.BOLD, 13));
		
		JComboBox<String> howMuch2 = new JComboBox<>(choices);
		
		JButton cashOkay = new MiniButton("확인");
		
		cashOkay.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(e.getButton() == MouseEvent.BUTTON1) {
					
					String s_money = howMuch2.getSelectedItem().toString();
					i_money = Integer.parseInt(s_money);
					
					new SuccessCash(i_money, price, card_btn, cash_btn);
					dispose();
					
					
				}
			}
		});
		
		JButton cashNo = new MiniButton("취소");
		
		cashNo.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					cash_btn.setText("현금");
					cash_btn.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
					card_btn.setEnabled(true);
					payment_btn.setEnabled(false);
					dispose();
				}
			}
		});
		
		stnPanel.add(cashIn);
		
		showMoney.add(howMuch);
		showMoney.add(howMuch2);
		
		clickPanel.add(cashOkay);
		clickPanel.add(cashNo);
		
		stnPanel.setOpaque(false);
		showMoney.setOpaque(false);
		clickPanel.setOpaque(false);
		
		insertCash.add(stnPanel);
		insertCash.add(showMoney);
		insertCash.add(clickPanel);
		
		insertCash.setOpaque(false);
		
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
		back.add(insertCash, BorderLayout.CENTER);
		
		back.setOpaque(false);
		add(back);
		add(label);
		
		//this.add(insertCash, BorderLayout.CENTER);
	}
}
