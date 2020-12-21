package project.five.pos.device;

import java.awt.Dimension;

import javax.swing.JTextField;

public class DeviceTF extends JTextField {
public DeviceTF() {}
	
	public DeviceTF(int width, int height) {
		setPreferredSize(new Dimension(width, height));
	}
	
	public DeviceTF(String txt, int columns, int width, int height) {
		super(txt, columns);
		setPreferredSize(new Dimension(width, height));
	}
	
}
