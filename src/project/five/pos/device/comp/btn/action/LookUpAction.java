package project.five.pos.device.comp.btn.action;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import project.five.pos.device.LookUpDisplay;
//import project.five.pos.membership.gui.MemberListFrame;

public class LookUpAction implements ActionListener{

	JFrame manager_frame;

	public LookUpAction(JFrame manager_frame) {
		this.manager_frame = manager_frame;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new LookUpDisplay(e.getActionCommand());
		manager_frame.dispose();
	}
}
