package project.five.pos.device;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import project.five.pos.TestSwingTools;
import project.five.pos.device.actionbtn.ChangeFrameBtn;
import project.five.pos.device.subdisplay.ManagerDisplay;


public class DeviceDisplay extends JFrame {

	// �׽�Ʈ
	// ������ ID : 123
	// ������ PW : 45

	//
	JPanel info;
	// ������ �̸� ��
	JLabel device_info;
	// �����ڸ�� ��ư
	JButton manager_btn;
	JDialog login_confirm;
	
	public DeviceDisplay(String device_id) {
		
		setLayout(new BorderLayout());
		
		info = new JPanel(new BorderLayout());
		
		setPreferredSize(new Dimension(500, 20));
		
		device_info = new JLabel("DEVICE_ID : " + device_id);
		info.add(device_info, BorderLayout.WEST);
		
		manager_btn = new JButton("������");
		info.add(manager_btn, BorderLayout.EAST);
		
		add(info, BorderLayout.SOUTH);
		
		JFrame f = this;
		manager_btn.addActionListener(new ChangeFrameBtn(this));
		
		TestSwingTools.initTestFrame(this, "POS TEST", true);
	}
	
}
