package project.five.pos.device.btn;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class DeviceBtn extends JButton {

	/* 
  		직사각형 버튼
	 */
	public DeviceBtn(String btn_text, int width, int height, ActionListener action) {
		setText(btn_text);
		setPreferredSize(new Dimension(width, height));
		addActionListener(action);
	}
	
	
	/* 
	  	정사각형 버튼
	 */
	public DeviceBtn(String btn_text, int length, ActionListener action) {
		setText(btn_text);
		setPreferredSize(new Dimension(length, length));
		addActionListener(action);
	}
	
}
