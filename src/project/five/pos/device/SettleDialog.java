package project.five.pos.device;

import java.awt.BorderLayout;

import javax.swing.*;

import project.five.pos.TestSwingTools;
import project.five.pos.device.comp.*;
import project.five.pos.device.comp.btn.DeviceBtn;
import project.five.pos.device.comp.btn.action.ChangeFrameAction;
import project.five.pos.device.comp.btn.action.SettleAction;

public class SettleDialog extends JDialog {

	JLabel msg_lab;
	JButton yes_btn, no_btn;
	
	public SettleDialog(JFrame frame, String title) {
		super(frame, title);
		setLayout(new BorderLayout());
		setSize(300, 200);
		setResizable(false);
		setLocationRelativeTo(null);
		
		msg_lab = new DeviceLab("정말 정산 하시겠습니까?", 70, 40);
		msg_lab.setHorizontalAlignment(JLabel.CENTER);
		
		yes_btn = new DeviceBtn("예", 50, new SettleAction());
		no_btn = new DeviceBtn("아니요", 50, new ChangeFrameAction(frame));
		
		add(msg_lab, BorderLayout.CENTER);
		add(yes_btn, BorderLayout.SOUTH);
		add(no_btn, BorderLayout.SOUTH);
		
		setVisible(true);
	}
}
