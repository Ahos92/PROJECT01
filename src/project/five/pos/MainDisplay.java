package project.five.pos;

import java.awt.*;

import javax.swing.*;

import project.five.pos.device.actionbtn.ChangeFrameBtn;

public class MainDisplay extends JFrame {

	// 관리자 모드, 판매모드
	JButton manageBtn, saleBtn;
	JPanel centerP, southP;
	JLabel deviceId;
	
	public MainDisplay(String device_id) {
		setLayout(new BorderLayout());
		
		centerP = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 250));
		southP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		deviceId = new JLabel("DEVICE_ID : " + device_id);
		
		manageBtn =  new JButton("관리자");
		manageBtn.setPreferredSize(new Dimension(130, 130));
		saleBtn =  new JButton("판매");
		saleBtn.setPreferredSize(new Dimension(130, 130));
		
		manageBtn.addActionListener(new ChangeFrameBtn(this));
		saleBtn.addActionListener(new ChangeFrameBtn(this));
		
		centerP.add(manageBtn);
		centerP.add(saleBtn);
		southP.add(deviceId);
		
		add(centerP, BorderLayout.CENTER);
		add(southP, BorderLayout.SOUTH);
		
		TestSwingTools.initTestFrame(this, "main", true);
	}
	
	public static void main(String[] args) {
		new MainDisplay("1234");
	}
	
}
