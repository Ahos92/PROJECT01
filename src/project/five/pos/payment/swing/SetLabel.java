package project.five.pos.payment.swing;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class SetLabel extends JLabel{

	public SetLabel(String data, int size) {
		setText(data);
		setForeground(Color.WHITE);
		setFont(new Font("Serif",Font.BOLD, size));
		
	}
}
