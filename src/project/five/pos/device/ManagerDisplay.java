package project.five.pos.device;

import java.awt.*;

import javax.swing.*;

import project.five.pos.TestSwingTools;
import project.five.pos.device.comp.btn.DeviceBtn;
import project.five.pos.device.comp.btn.action.*;

public class ManagerDisplay extends JFrame {
	
	// 결제내역, 판매상품, 회원정보조회, 메뉴로 돌아가기 버튼
	JButton payHistory_btn, soldHistory_btn, memInfo_btn,
	 		back_btn, signUp_btn, productManage_btn, settle_btn;
	
	JPanel center_p, south_p;
	
	public ManagerDisplay() {
		setLayout(new BorderLayout());
		setResizable(false);
		
		center_p = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 30));
		south_p = new JPanel(new BorderLayout());

		payHistory_btn = new DeviceBtn("결제 내역 조회", 130, new LookUpAction(this));
		
		soldHistory_btn = new DeviceBtn("판매 내역 조회", 130, new LookUpAction(this));
		
		memInfo_btn = new DeviceBtn("회원 정보 조회", 130, new LookUpAction(this));
		
		signUp_btn =  new DeviceBtn("회원 가입", 130, new ChangeFrameAction(this));
	
		productManage_btn = new DeviceBtn("상품 관리", 130, new ChangeFrameAction(this));
	
		back_btn = new DeviceBtn("메뉴로 돌아가기", 130, 30, new ChangeFrameAction(this));

		settle_btn = new DeviceBtn("정산", 60, 30, new ChangeFrameAction(this));
		
		center_p.add(payHistory_btn);
		center_p.add(soldHistory_btn);
		center_p.add(memInfo_btn);
		center_p.add(signUp_btn);
		center_p.add(productManage_btn);
		south_p.add(back_btn, BorderLayout.WEST);	
		south_p.add(settle_btn, BorderLayout.EAST);
		
		add(center_p, BorderLayout.CENTER);
		add(south_p, BorderLayout.SOUTH);
		
		TestSwingTools.initTestFrame(this, "관리자 모드", true);
	}
	
	public static void main(String[] args) {
		new ManagerDisplay();
	}
}
