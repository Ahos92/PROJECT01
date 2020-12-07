package project.five.pos.payment.swing.btn.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import project.five.pos.payment.swing.RegisterMem;

public class BtnAction implements ActionListener{

	JButton btn;
	
	
	public BtnAction(JButton btn) {
		this.btn = btn;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(btn.getText().equals("¸â¹ö½± µî·Ï")){
		new RegisterMem();
		}
		
		
	}

}
