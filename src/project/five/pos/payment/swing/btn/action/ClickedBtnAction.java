package project.five.pos.payment.swing.btn.action;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

public class ClickedBtnAction implements ActionListener{

	Container junior_panel;
	String payment_type;
	
	public ClickedBtnAction(Container junior_panel, String payment_type) {
		this.junior_panel = junior_panel;
		this.payment_type = payment_type;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		if(btn.getText().equals("카드")) {
			btn.setText("카드로 결제");
			btn.setBorder(BorderFactory.createSoftBevelBorder(SoftBevelBorder.LOWERED));
		}
		else if(btn.getText().equals("현금")) {
			btn.setText("현금으로 결제");
			btn.setBorder(BorderFactory.createSoftBevelBorder(SoftBevelBorder.LOWERED));
		}
		else if(btn.getText().equals("현금으로 결제")) {
			btn.setText("현금");
			btn.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		}
		else {
			btn.setText("카드");
			btn.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		}
		
	}
	
}
