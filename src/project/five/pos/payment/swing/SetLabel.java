package project.five.pos.payment.swing;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;

import project.five.pos.db.PosVO;

public class SetLabel extends JLabel{

	public SetLabel(String data, int data2, int size) {
		setText(data + " " + data2 + "°³");
		
		setForeground(Color.WHITE);
		setFont(new Font("Serif",Font.BOLD, size));
		
	}


}
