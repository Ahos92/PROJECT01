package project.five.pos.sale.btn;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class CartBtn extends JButton {

	public CartBtn(String name, ActionListener action) {
		setText(name);
		addActionListener(action);
	}
	
	
	
}
