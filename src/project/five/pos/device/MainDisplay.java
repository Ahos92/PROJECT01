package project.five.pos.device;

import java.awt.*;

import javax.swing.*;

import project.five.pos.TestSwingTools;
import project.five.pos.device.comp.DevicePanel;
import project.five.pos.device.comp.btn.DeviceBtn;
import project.five.pos.device.comp.btn.action.*;

public class MainDisplay extends JFrame {

	static String[] image_path = {
			"assets/images/device/14.png",
			"assets/images/device/setting.png",
			"assets/images/device/coin.png",
	};
	// ������ ���, �ǸŸ��
	JButton manage_btn, sale_btn, managerSign_btn;
	JPanel center_p, south_p;
	JLabel deviceId_lab;
	
	public MainDisplay(String device_id) {
		setLayout(new BorderLayout());
		setResizable(false);
		
		center_p = new DevicePanel(image_path[0], 500, 750, 
									new FlowLayout(FlowLayout.CENTER, 20, 250));
		south_p = new DevicePanel(image_path[0], 500, 750, new BorderLayout());
		
		deviceId_lab = new JLabel("DEVICE_ID : " + device_id);
		
		manage_btn = new DeviceBtn("������", image_path[1], 130, new ChangeFrameAction(this));

		sale_btn = new DeviceBtn("�Ǹ�", image_path[2], 130, new ChangeFrameAction(this));

		managerSign_btn = new DeviceBtn("�Ŵ��� ���", 100, 30, new ChangeFrameAction(this));
		
		center_p.add(manage_btn);
		center_p.add(sale_btn);
		south_p.add(deviceId_lab, BorderLayout.WEST);
		south_p.add(managerSign_btn, BorderLayout.EAST);
		
		add(center_p, BorderLayout.CENTER);
		add(south_p, BorderLayout.SOUTH);
		
		TestSwingTools.initTestFrame(this, "main", true);
	}
	
	public static void main(String[] args) {
		new MainDisplay("1234");
	}
	
}