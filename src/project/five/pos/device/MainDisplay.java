package project.five.pos.device;

import java.awt.*;

import javax.swing.*;

import project.five.pos.SwingTools;
import project.five.pos.device.comp.DevicePanel;
import project.five.pos.device.comp.btn.DeviceBtn;
import project.five.pos.device.comp.btn.action.*;

public class MainDisplay extends JFrame {

	static String[] image_path = {
			"assets/images/device/14.png",
			"assets/images/device/setting.png",
			"assets/images/device/coin.png",
	};
	// 관리자 모드, 판매모드
	JButton manage_btn, sale_btn, managerSign_btn;
	JPanel center_p, south_p;
	JLabel deviceId_lab;
	
	public MainDisplay(String device_id) {
		setLayout(new BorderLayout());
		setResizable(false);	
		
		center_p = new DevicePanel(image_path[0], 890, 789, 
									new FlowLayout(FlowLayout.CENTER, 100, 250));
		south_p = new DevicePanel(image_path[0], 890, 789, new BorderLayout());
		
		deviceId_lab = new JLabel("DEVICE_ID : " + device_id);
		
		manage_btn = new DeviceBtn("관리자", image_path[1], 180, new ChangeFrameAction(this, device_id));

		sale_btn = new DeviceBtn("판매", image_path[2], 180, new ChangeFrameAction(this, device_id));

		managerSign_btn = new DeviceBtn("매니저 등록", 100, 30, new ChangeFrameAction(this));
		
		center_p.add(manage_btn);
		center_p.add(sale_btn);
		south_p.add(deviceId_lab, BorderLayout.WEST);
		south_p.add(managerSign_btn, BorderLayout.EAST);
		
		add(center_p, BorderLayout.CENTER);
		add(south_p, BorderLayout.SOUTH);
		SwingTools.initTestFrame(this, "main", true);
	}

}
