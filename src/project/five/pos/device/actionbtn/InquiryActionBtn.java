package project.five.pos.device.actionbtn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import project.five.pos.device.subdisplay.InquiryDisplay;

public class InquiryActionBtn implements ActionListener{

	JFrame manager_f;
	JFrame inq_f;
	
	public InquiryActionBtn(JFrame manager_f) {
		this.manager_f = manager_f;

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		inq_f = new InquiryDisplay(e.getActionCommand());
		inq_f.setVisible(true);
		manager_f.setVisible(false);
	}
}
