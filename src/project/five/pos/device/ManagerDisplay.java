package project.five.pos.device;

import java.awt.*;

import javax.swing.*;

import project.five.pos.TestSwingTools;
import project.five.pos.device.comp.btn.DeviceBtn;
import project.five.pos.device.comp.btn.action.*;

public class ManagerDisplay extends JFrame {
	
	// ��������, �ǸŻ�ǰ, ȸ��������ȸ, �޴��� ���ư��� ��ư
	JButton payHistory_btn, soldHistory_btn, memInfo_btn,
	 		back_btn, signUp_btn, productManage_btn, settle_btn;
	
	JPanel center_p, south_p;
	
	public ManagerDisplay() {
		setLayout(new BorderLayout());
		setResizable(false);
		
		center_p = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 30));
		south_p = new JPanel(new BorderLayout());

		payHistory_btn = new DeviceBtn("���� ���� ��ȸ", 130, new LookUpAction(this));
		
		soldHistory_btn = new DeviceBtn("�Ǹ� ���� ��ȸ", 130, new LookUpAction(this));
		
		memInfo_btn = new DeviceBtn("ȸ�� ���� ��ȸ", 130, new LookUpAction(this));
		
		signUp_btn =  new DeviceBtn("ȸ�� ����", 130, new ChangeFrameAction(this));
	
		productManage_btn = new DeviceBtn("��ǰ ����", 130, new ChangeFrameAction(this));
	
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
