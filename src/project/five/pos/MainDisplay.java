package project.five.pos;

import java.awt.*;

import javax.swing.*;

import project.five.pos.device.btn.action.ChangeFrameBtn;

public class MainDisplay extends JFrame {

	// 관리자 모드, 판매모드
	JButton manage_btn, sale_btn;
	JPanel center_p, south_p;
	JLabel deviceId_lab;
	
	public MainDisplay(String device_id) {
		setLayout(new BorderLayout());
		
		center_p = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 250));
		south_p = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		deviceId_lab = new JLabel("DEVICE_ID : " + device_id);
		
		manage_btn =  new JButton("관리자");
		manage_btn.setPreferredSize(new Dimension(130, 130));
		manage_btn.addActionListener(new ChangeFrameBtn(this));

		sale_btn =  new JButton("판매");
		sale_btn.setPreferredSize(new Dimension(130, 130));
		sale_btn.addActionListener(new ChangeFrameBtn(this));
		
		
		center_p.add(manage_btn);
		center_p.add(sale_btn);
		south_p.add(deviceId_lab);
		
		add(center_p, BorderLayout.CENTER);
		add(south_p, BorderLayout.SOUTH);
		
		TestSwingTools.initTestFrame(this, "main", true);
	}
	
	public static void main(String[] args) {
		new MainDisplay("1234");
	}
	
}
