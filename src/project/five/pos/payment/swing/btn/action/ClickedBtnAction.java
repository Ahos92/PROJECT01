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
		if(btn.getText().equals("ī��")) {
			btn.setText("ī��� ����");
			btn.setBorder(BorderFactory.createSoftBevelBorder(SoftBevelBorder.LOWERED));
		}
		else if(btn.getText().equals("����")) {
			btn.setText("�������� ����");
			btn.setBorder(BorderFactory.createSoftBevelBorder(SoftBevelBorder.LOWERED));
		}
		else if(btn.getText().equals("�������� ����")) {
			btn.setText("����");
			btn.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		}
		else {
			btn.setText("ī��");
			btn.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		}
		
	}
	
}
