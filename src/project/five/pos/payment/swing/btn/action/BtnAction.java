package project.five.pos.payment.swing.btn.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import project.five.pos.payment.swing.AskCoupon;
import project.five.pos.payment.swing.CheckMem;
import project.five.pos.payment.swing.PayPanel;
import project.five.pos.payment.swing.RegisterMem;

public class BtnAction implements ActionListener{

	JButton btn;
	int price;
	boolean check;
	PayPanel main;
	JFrame frame;
	int order_num;
	
	public BtnAction(JButton btn, int price, JFrame frame, int order_num) {
		this.btn = btn;
		this.price = price;
		this.frame = frame;
		this.order_num = order_num;
	}
	public BtnAction(JButton btn) {
		this.btn = btn;
	}
	
	public BtnAction(PayPanel main) {
		this.main = main;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(btn.getText().equals("멤버쉽 입력")) {
			new CheckMem(main);
		}
		
		if(btn.getText().equals("멤버쉽 등록")){
			new RegisterMem();
		}
		
		if(btn.getText().equals("결제하기")) {
			new AskCoupon(price, frame, order_num);
		}
			
	}

}
