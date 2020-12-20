package project.five.pos.payment.swing.btn.action;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NextCardLbAction implements ActionListener{

	Container west_panel;
	
	public NextCardLbAction(Container west_panel) {
		this.west_panel = west_panel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		((CardLayout)west_panel.getLayout()).next(west_panel);
	}

}
