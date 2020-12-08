package project.five.pos.device.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import project.five.pos.device.InquiryDisplay;

public class InquiryAction implements ActionListener{

	JFrame manager_f;
	JFrame inq_f;
	
	public InquiryAction(JFrame manager_f) {
		this.manager_f = manager_f;

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		inq_f = new InquiryDisplay(e.getActionCommand());
		inq_f.setVisible(true);
		manager_f.setVisible(false);
	}
}
