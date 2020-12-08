package project.five.pos.device;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

import project.five.pos.device.action.ChangeFrameBtn;
import project.five.pos.sale.TestSwingTools;

public class DeviceDisplay extends JFrame {

	// 테스트
	// 관리자 ID : project
	// 관리자 PW : five
	
	// device_id : 1234
	//  어느 프레임이든 출력되게 설정
	String device_id = "1234";

	//
	JPanel info;
	// 포스기 이름 라벨
	JLabel device_info;
	// 관리자모드 버튼
	JButton manager_btn;
				
	
	static JFrame f2;
	static {
		f2 = new ManagerDisplay();
	}
	/* 
	 	매개변수 JFrame frame
	 		-관리자모드 들어가면 현재프레임 닫고 관리자모드 프레임 실행 하기 위함
	 */
	public DeviceDisplay() {
		setLayout(new BorderLayout());
		
		info = new JPanel(new BorderLayout());
		
		setPreferredSize(new Dimension(500, 20));
		
		device_info = new JLabel("DEVICE_ID : " + device_id);
		info.add(device_info, BorderLayout.WEST);
		
		manager_btn = new JButton("관리자");
		info.add(manager_btn, BorderLayout.EAST);
		
		add(info, BorderLayout.SOUTH);
		
		manager_btn.addActionListener(new ChangeFrameBtn(this));
		
		TestSwingTools.initTestFrame(this, "POS TEST", true);
	}
	
	public static void main(String[] args) {
		new DeviceDisplay();

	}
	
}
