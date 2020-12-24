package project.five.pos.device.comp.btn.action;

import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

import project.five.pos.device.*;
import project.five.pos.membership.gui.*;
import project.five.pos.menu.ProductManage;
import project.five.pos.payment.swing.CheckMem;
import project.five.pos.menu.MenuDisplay;

public class ChangeFrameAction implements ActionListener{

	JFrame present_frame, changeble_frame;
	JTextField id_tf, pw_tf;
	String device_id;

	public ChangeFrameAction(JFrame present_frame, JTextField id_tf, JTextField pw_tf, String device_id) {
		this.present_frame = present_frame;
		this.id_tf = id_tf;
		this.pw_tf = pw_tf;
		this.device_id = device_id;
	}

	public ChangeFrameAction(JFrame present_frame) {
		this.present_frame = present_frame;
	}

	public ChangeFrameAction(JFrame present_frame, String device_id) {
		this.present_frame = present_frame;
		this.device_id = device_id;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		CheckMem.memberOn = false;
		
		String check = e.getActionCommand();
		
		if (check.equals("메뉴로 돌아가기")
				||check.equals("> To Main page")) {

			changeble_frame =  new MainDisplay(device_id);

		} else if(check.equals("로그인")){			
			DeviceDAO device = new DeviceDAO();
			int id = 0;
			String pw = pw_tf.getText();
			try {
				id = Integer.parseInt(id_tf.getText());
			}catch (NumberFormatException nfe) {}

			if (device.searchAdmin(id, pw)) {
				changeble_frame =  new ManagerDisplay(device_id);
				System.out.println("로그인 성공!");
			} else {
				JOptionPane.showMessageDialog(present_frame, "아이디나 비밀번호가 맞지않습니다.", "오류", 
						JOptionPane.ERROR_MESSAGE);
			}

		} else if(check.equals("관리자")) {
			new LoginPopUpDisplay(present_frame, "관리자 로그인", device_id);


		} else if(check.equals("아니요")) {

			changeble_frame = new ManagerDisplay(device_id);

		} else if(check.equals("회원 가입")) {

			new JoinFrame(present_frame, "회원 가입");


		} else if(check.equals("상품 관리")) {

			try {
				new ProductManage(present_frame, "상품 관리");
			} catch (IOException e1) {e1.printStackTrace();
			}


		} else if(check.equals("매니저 등록")) {
			new ManagerSignUpPopUpDisplay(present_frame, check);
			
		} else if(check.equals("정산")) {
			new SettlePopUpDisplay(present_frame, check, device_id);
			
		} else if (check.equals("판매") 
				|| check.equals("취소하기")) {
			changeble_frame = new MenuDisplay(device_id);
			
		} else if (check.equals("판매 내역 조회")
				|| check.equals("결제 내역 조회")
				|| check.equals("회원 정보 조회")) {
			new LookUpPopUpDisplay(present_frame, check);
		}

		// dialog 띄울 때 변할 화면 Null
		try {
			changeble_frame.setVisible(true);
			present_frame.dispose();
		} catch (NullPointerException ne) {

		}
	}
}
