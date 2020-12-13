package project.five.pos.payment.swing.btn.action;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import project.five.pos.payment.swing.PaidByCard;
import project.five.pos.payment.swing.PaidByCash;

public class ClickedBtnAction implements ActionListener{

	Container junior_panel;
	String payment_type;
	JButton card_btn;
	JButton cash_btn;
	JButton payment_btn;
	int price;
	
	private static String paymentType;
	
	public ClickedBtnAction(Container junior_panel, String payment_type, JButton card_btn, JButton cash_btn, JButton payment_btn, int price) {
		this.junior_panel = junior_panel;
		this.payment_type = payment_type;
		this.card_btn = card_btn;
		this.cash_btn = cash_btn;
		this.payment_btn = payment_btn;
		this.price = price;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		if(btn.getText().equals("카드")) {
			
			
			
			btn.setText("카드로 결제");
			btn.setBorder(BorderFactory.createSoftBevelBorder(SoftBevelBorder.LOWERED));
			cash_btn.setEnabled(false);
			new PaidByCard(price, card_btn, cash_btn, payment_btn);
			
			setPaymentType("CARD");
			payment_btn.setEnabled(true);
			
		}
		
		else if(btn.getText().equals("카드로 결제")){ 
			btn.setText("카드");
			btn.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			cash_btn.setEnabled(true);	
		}
		
		else if(btn.getText().equals("현금")) {
			
			
			
			btn.setText("현금으로 결제");
			btn.setBorder(BorderFactory.createSoftBevelBorder(SoftBevelBorder.LOWERED));
			card_btn.setEnabled(false);
			new PaidByCash(price, card_btn, cash_btn, payment_btn);
			
			setPaymentType("CASH");
			payment_btn.setEnabled(true);
		}
		
		else if(btn.getText().equals("현금으로 결제")) {
			btn.setText("현금");
			btn.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			card_btn.setEnabled(true);		
		}
		
	}

	public static String getPaymentType() {
		return paymentType;
	}

	public static void setPaymentType(String paymentType) {
		ClickedBtnAction.paymentType = paymentType;
	}
	
}
