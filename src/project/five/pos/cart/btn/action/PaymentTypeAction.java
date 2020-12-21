package project.five.pos.cart.btn.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class PaymentTypeAction extends MouseAdapter {

	JButton anotherBtn;
	String typeName;
	
	public PaymentTypeAction(String typeName, JButton anotherBtn) {
		this.typeName = typeName;
		this.anotherBtn = anotherBtn;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		JButton click = (JButton)e.getSource();
		click.setEnabled(true);
		anotherBtn.setEnabled(false);
		typeName = click.getActionCommand();		
	}
	
}
