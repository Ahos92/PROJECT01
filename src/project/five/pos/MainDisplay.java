package project.five.pos;

import java.awt.*;

import javax.swing.*;

import project.five.pos.device.comp.btn.DeviceBtn;
import project.five.pos.device.comp.btn.action.*;

public class MainDisplay extends JFrame {

	// ������ ���, �ǸŸ��
	JButton manage_btn, sale_btn, managerSign_btn;
	JPanel center_p, south_p;
	JLabel deviceId_lab;
	
	public MainDisplay(String device_id) {
		setLayout(new BorderLayout());
		setResizable(false);
		
		center_p = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 250));
		south_p = new JPanel(new BorderLayout());
		
		deviceId_lab = new JLabel("DEVICE_ID : " + device_id);
		
		manage_btn = new DeviceBtn("������", 130, 130, new ChangeFrameAction(this));

		sale_btn = new DeviceBtn("�Ǹ�", 130, 130, new ChangeFrameAction(this));

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
