package project.five.pos.device;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import project.five.pos.device.action.ChangeFrameBtn;
import project.five.pos.sale.TestSwingTools;

public class DeviceDisplay extends JFrame {

	// �׽�Ʈ
	// ������ ID : 123
	// ������ PW : 45
	
	// device_id : 1234
	//  ��� �������̵� ��µǰ� ����
	String device_id = "1234";

	//
	JPanel info;
	// ������ �̸� ��
	JLabel device_info;
	// �����ڸ�� ��ư
	JButton manager_btn;
	JDialog login_confirm;
	
	static JFrame f2;
	static {
		f2 = new ManagerDisplay();
	}

	public DeviceDisplay() {
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
	
	public static void main(String[] args) {
		new DeviceDisplay();

	}
	
}
