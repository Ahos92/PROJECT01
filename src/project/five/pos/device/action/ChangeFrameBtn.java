package project.five.pos.device.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import project.five.pos.device.DeviceDisplay;
import project.five.pos.device.ManagerDisplay;

public class ChangeFrameBtn implements ActionListener{

	JFrame changeable_frame;
	JFrame present_frame;
	
	public ChangeFrameBtn(JFrame present_frame) {
		this.present_frame = present_frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("메뉴로 돌아가기")) {
			changeable_frame = new DeviceDisplay();
		} else if(e.getActionCommand().contains("관리자")){
			changeable_frame = new ManagerDisplay();
		} 
		
		changeable_frame.setVisible(true);
		present_frame.setVisible(false);
	}
}
