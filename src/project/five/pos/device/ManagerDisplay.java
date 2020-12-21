package project.five.pos.device;

import java.awt.*;

import javax.swing.*;

import project.five.pos.TestSwingTools;
import project.five.pos.device.comp.DevicePanel;
import project.five.pos.device.comp.btn.DeviceBtn;
import project.five.pos.device.comp.btn.action.*;

public class ManagerDisplay extends JFrame {
	
	static String[]	image_path = {
			"assets/images/device/14.png",
			"assets/images/device/001-money.png",
			"assets/images/device/003-coffee-bean.png",
			"assets/images/device/002-member.png",
			"assets/images/device/004-sign-up.png",
			"assets/images/device/005-product-management.png"
	};
	
	// ��������, �ǸŻ�ǰ, ȸ��������ȸ, �޴��� ���ư��� ��ư
	JButton payHistory_btn, soldHistory_btn, memInfo_btn,
	 		back_btn, signUp_btn, productManage_btn, settle_btn;
	
	JPanel center_p, south_p, back_p;
	JScrollPane scroll;
	public ManagerDisplay() {
		setLayout(new BorderLayout());
		setResizable(false);
		
		center_p = new DevicePanel(image_path[0], 500, 750, 
									new FlowLayout(FlowLayout.CENTER, 50, 50));
		
		south_p = new DevicePanel(image_path[0], 500, 750, new BorderLayout());
		
		payHistory_btn = new DeviceBtn("���� ���� ��ȸ", image_path[1], 130, new LookUpAction(this));
		
		soldHistory_btn = new DeviceBtn("�Ǹ� ���� ��ȸ", image_path[2], 130, new LookUpAction(this));
		
		memInfo_btn = new DeviceBtn("ȸ�� ���� ��ȸ", image_path[3], 130, new LookUpAction(this));
		
		signUp_btn =  new DeviceBtn("ȸ�� ����", image_path[4], 130, new ChangeFrameAction(this));
	
		productManage_btn = new DeviceBtn("��ǰ ����", image_path[5], 130, new ChangeFrameAction(this));
	
		back_btn = new DeviceBtn("�޴��� ���ư���", 130, 30, new ChangeFrameAction(this));

		settle_btn = new DeviceBtn("����", 60, 30, new ChangeFrameAction(this));

		center_p.add(payHistory_btn);
		center_p.add(soldHistory_btn);
		center_p.add(memInfo_btn);
		center_p.add(signUp_btn);
		center_p.add(productManage_btn);
		south_p.add(back_btn, BorderLayout.WEST);	
		south_p.add(settle_btn, BorderLayout.EAST);
		
		add(center_p, BorderLayout.CENTER);
		add(south_p, BorderLayout.SOUTH);
		
		TestSwingTools.initTestFrame(this, "������ ���", true);
	}
	
	public static void main(String[] args) {
		new ManagerDisplay();
	}
}
