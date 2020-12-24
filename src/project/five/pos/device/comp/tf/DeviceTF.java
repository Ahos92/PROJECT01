package project.five.pos.device.comp.tf;

import java.awt.Dimension;
import java.awt.event.*;

import javax.swing.JTextField;

import project.five.pos.device.comp.tf.action.PromptAction;

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
