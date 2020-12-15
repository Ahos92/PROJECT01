package project.five.pos.device.btn;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class DeviceBtn extends JButton {

	public DeviceBtn(String btn_text, int width, int height, ActionListener action) {
		setText(btn_text);
		setPreferredSize(new Dimension(width, height));
		addActionListener(action);
	}
	
}
