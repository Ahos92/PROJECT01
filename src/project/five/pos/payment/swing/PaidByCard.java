package project.five.pos.payment.swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import project.five.pos.payment.swing.btn.action.NumberField;

public class PaidByCard extends JFrame{

	int price;
	
	String[] bankCode = 
		{"은행을 선택해주세요", "(NH)농협은행", "(KB)국민은행",
							"(SH)신한은행", "(IBK)기업은행"};
	
	static String cardId;
	static String cardNumber;
	
	JButton card_btn;
	JButton cash_btn;
	JButton payment_btn;
		
	public PaidByCard(int price, JButton card_btn, JButton cash_btn, JButton payment_btn) {
		this.price = price;
		this.card_btn = card_btn;
		this.cash_btn = cash_btn;
		this.payment_btn = payment_btn;
				
		setTitle("결제금액 : " + this.price + "원(일시불)");
		
		JPanel insertCard = new JPanel(new GridLayout(3,1,0,0));
		JPanel stnPanel2 = new JPanel();
		JPanel numPanel = new JPanel(new FlowLayout());
		JPanel btnCard2 = new JPanel(new FlowLayout());
		
		JLabel cardExplain = new JLabel("카드를 투입하세요");
		
		JComboBox<String> selectCode = new JComboBox<>(bankCode);
		
		JTextField first_cardNum = new JTextField(4);
		JLabel cStick1 = new JLabel("-");
		JTextField second_cardNum  = new JTextField(4);
		JLabel cStick2 = new JLabel("-");
		JTextField third_cardNum = new JTextField(4);
		JLabel cStick3 = new JLabel("-");
		JTextField last_cardNum = new JTextField(4);
		
		first_cardNum.addKeyListener(new NumberField());
		second_cardNum.addKeyListener(new NumberField());
		third_cardNum.addKeyListener(new NumberField());
		last_cardNum.addKeyListener(new NumberField());
		
		JButton cardOkay = new JButton("확인");
		
		cardNumber = "";
		cardOkay.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				if(e.getButton() == MouseEvent.BUTTON1) {
					
					String selectedCard = selectCode.getSelectedItem().toString();
					
					if(selectedCard.contains("IBK")) {
						cardId = selectedCard.substring(1, 4);						
					}
					else {
						cardId = selectedCard.substring(1, 3);						
					}
					
					if(first_cardNum.getText().length() < 4 || second_cardNum.getText().length() < 4
							|| third_cardNum.getText().length() < 4 || last_cardNum.getText().length() < 4) {
						new NotenoughNo();
					}
					else {					
						cardNumber += first_cardNum.getText();
						cardNumber += "-****-****-";
						cardNumber += last_cardNum.getText();
						
						
						
						new SuccessCard();
						dispose();
						
						selectedCard = "";
					}
					
					
					
					
				}
			}
		});
		
		JButton cardNo = new JButton("취소");
		
		cardNo.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					card_btn.setText("카드");
					card_btn.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
					cash_btn.setEnabled(true);
					payment_btn.setEnabled(false);					
					dispose();
				}
			}
		});
		
		stnPanel2.add(cardExplain);
		
		numPanel.add(selectCode);
		numPanel.add(first_cardNum);
		numPanel.add(cStick1);
		numPanel.add(second_cardNum);
		numPanel.add(cStick2);
		numPanel.add(third_cardNum);
		numPanel.add(cStick3);
		numPanel.add(last_cardNum);
		
		btnCard2.add(cardOkay);
		btnCard2.add(cardNo);
		
		insertCard.add(stnPanel2);
		insertCard.add(numPanel);
		insertCard.add(btnCard2);
		
		setVisible(true);
		//setLayout(null);
		setSize(500, 170);
		setLocation(1180, 500);
		
		this.add(insertCard, BorderLayout.CENTER);
	}
}
