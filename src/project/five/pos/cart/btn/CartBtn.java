package project.five.pos.cart.btn;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class CartBtn extends JButton {

	public CartBtn() {
	}
	
	public CartBtn(String name, ActionListener action) {
		setText(name);
		addActionListener(action);
	}
	
	public CartBtn(String name, int length) {
		setText(name);
		setPreferredSize(new Dimension(length, length));
	}
	
	public String getTypeName() {
		
		return null;
	}
	
}
