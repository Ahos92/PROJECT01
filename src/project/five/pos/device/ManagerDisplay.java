package project.five.pos.device;

import java.awt.*;

import javax.swing.*;

import project.five.pos.SwingTools;
import project.five.pos.device.comp.DevicePanel;
import project.five.pos.device.comp.btn.DeviceBtn;
import project.five.pos.device.comp.btn.action.*;

public class ManagerDisplay extends JFrame {
	
	static String[]	image_path = {
			"assets/images/device/14.png",
			"assets/images/device/001-money.png",
			"assets/images/device/002-saled.png",
			"assets/images/device/003-member.png",
			"assets/images/device/004-sign-up.png",
			"assets/images/device/005-product-management.png"
	};
	
	// 결제내역, 판매상품, 회원정보조회, 메뉴로 돌아가기 버튼
	JButton payHistory_btn, soldHistory_btn, memInfo_btn,
	 		back_btn, signUp_btn, productManage_btn, settle_btn;
	JLabel device_lab;
	JPanel center_p, south_p, back_p;
	JScrollPane scroll;
	public ManagerDisplay(String device_id) {
		setLayout(new BorderLayout());
		setResizable(false);
		
		center_p = new DevicePanel(image_path[0], 890, 789, 
									new FlowLayout(FlowLayout.CENTER, 200, 80));
		
		south_p = new DevicePanel(image_path[0], 890, 750, new BorderLayout());
		
		payHistory_btn = new DeviceBtn("결제 내역 조회", image_path[1], 130, new ChangeFrameAction(this));
		
		soldHistory_btn = new DeviceBtn("판매 내역 조회", image_path[2], 130, new ChangeFrameAction(this));
		
		memInfo_btn = new DeviceBtn("회원 정보 조회", image_path[3], 130, new ChangeFrameAction(this));
		
		signUp_btn =  new DeviceBtn("회원 가입", image_path[4], 130, new ChangeFrameAction(this));
	
		productManage_btn = new DeviceBtn("상품 관리", image_path[5], 130, new ChangeFrameAction(this));
	
		device_lab = new JLabel("Device_ID : " + device_id);
		
		back_btn = new DeviceBtn("메뉴로 돌아가기", 130, 30, new ChangeFrameAction(this, device_id));

		settle_btn = new DeviceBtn("정산", 60, 30, new ChangeFrameAction(this, device_id));

		center_p.add(payHistory_btn);
		center_p.add(soldHistory_btn);
		center_p.add(memInfo_btn);
		center_p.add(signUp_btn);
		center_p.add(productManage_btn);
		
		south_p.add(device_lab, BorderLayout.CENTER);
		south_p.add(back_btn, BorderLayout.WEST);	
		south_p.add(settle_btn, BorderLayout.EAST);
		
		add(center_p, BorderLayout.CENTER);
		add(south_p, BorderLayout.SOUTH);
		
		SwingTools.initTestFrame(this, "관리자 모드", true);
	}

}
