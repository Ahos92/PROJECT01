package project.five.pos.device;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import project.five.pos.TestSwingTools;
import project.five.pos.device.comp.*;
import project.five.pos.device.comp.btn.DeviceBtn;
import project.five.pos.device.comp.btn.action.ChangeFrameAction;
import project.five.pos.device.comp.btn.action.SettleAction;

public class SettlePopUpDisplay extends JDialog {

	JLabel msg_lab01, msg_lab02;
	JButton yes_btn, no_btn;
	JPanel center_p, south_p;
	
	public SettlePopUpDisplay(JFrame frame, String title, String device_id) {
		super(frame, title);
		setLayout(new BorderLayout());
		setSize(300, 200);
		setResizable(false);
		setLocationRelativeTo(null);
		
		center_p = new JPanel(new FlowLayout(FlowLayout.CENTER));
		south_p = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		center_p.setBackground(new Color(238, 230, 196));
		south_p.setBackground(new Color(238, 230, 196));
		
		msg_lab01 = new DeviceLab("오늘은 더 이상 판매를 할 수 없게 됩니다.", 230, 40);
		msg_lab02 = new DeviceLab("정말 정산 하시겠습니까?", 150, 40);
		
		yes_btn = new DeviceBtn("예", 70, 30, new SettleAction(frame, this, device_id));
		no_btn = new DeviceBtn("아니요", 70, 30, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		center_p.add(msg_lab01);
		center_p.add(msg_lab02);
		south_p.add(yes_btn);
		south_p.add(no_btn);
		
		add(center_p, BorderLayout.CENTER);
		add(south_p, BorderLayout.SOUTH);
		
		setVisible(true);
	}
}
