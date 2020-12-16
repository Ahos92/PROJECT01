package project.five.pos.device.comp;

import java.awt.Dimension;

import javax.swing.JLabel;

public class DeviceLab extends JLabel {

	public DeviceLab() {}
	
	public DeviceLab(String lab_txt, int width, int heghit) {
		setText(lab_txt);
		setPreferredSize(new Dimension(50, 30));
	}
}
