package project.five.pos.sale.btn;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class SaleBtn extends JButton {

	public SaleBtn(String name, ActionListener action) {
		setText(name);
		addActionListener(action);
	}
	
	
	
}
