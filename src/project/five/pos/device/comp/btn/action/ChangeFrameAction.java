package project.five.pos.device.comp.btn.action;

import java.awt.event.*;

import javax.swing.*;

import project.five.pos.MainDisplay;
import project.five.pos.device.*;
import project.five.pos.device.comp.dialog.ConfirmDialog;
import project.five.pos.device.comp.dialog.LoginDialog;
import project.five.pos.device.comp.dialog.ManagerSignUpDialog;
import project.five.pos.device.comp.dialog.SettleDialog;
import project.five.pos.manage.ProductManage;
import project.five.pos.membership.gui.*;
import project.five.pos.sale.SaleDisplay;

public class ChangeFrameAction implements ActionListener{

	JFrame changeable_frame, present_frame;
	JTextField id_tf, pw_tf;

	public ChangeFrameAction(JFrame present_frame, JTextField id_tf, JTextField pw_tf) {
		this.present_frame = present_frame;
		this.id_tf = id_tf;
		this.pw_tf = pw_tf;
	}

	public ChangeFrameAction(JFrame present_frame) {
		this.present_frame = present_frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String check = e.getActionCommand();
		
		if (check.equals("메뉴로 돌아가기")) {
			changeable_frame = new MainDisplay("1234");

		} else if(check.equals("로그인")){			
			DeviceDAO dao = new DeviceDAO();
			int id = 0;
			String pw = pw_tf.getText();
			try {
				id = Integer.parseInt(id_tf.getText());
			}catch (NumberFormatException nfe) {}

			if (dao.searchAdmin(id, pw)) {
				changeable_frame = new ManagerDisplay();
				System.out.println("로그인 성공!");
			} else {
				new ConfirmDialog(present_frame, "로그인 실패!", "아이디나 비밀번호가 맞지 않습니다!");
			}

		} else if(check.equals("관리자")) {
			new LoginDialog(present_frame, "관리자 로그인");

		} else if(check.equals("관리자 메뉴로 돌아가기") 
				|| check.equals("아니요")) {
			changeable_frame = new ManagerDisplay();

		} else if(check.equals("회원 가입")) {
			changeable_frame = new JoinFrame();

		} else if(check.equals("상품 관리")) {
			changeable_frame = new ProductManage();

		} else if(check.equals("매니저 등록")) {
			new ManagerSignUpDialog(present_frame, check);
			
		} else if(check.equals("정산")) {
			new SettleDialog(present_frame, check);
			
		} else if (check.equals("판매") 
				|| check.equals("취소하기")) {
			changeable_frame = new SaleDisplay();
			
		} 

		// dialog 띄울 때 변할 화면 Null
		try {
			changeable_frame.setVisible(true);
			present_frame.dispose();
		} catch (NullPointerException ne) {

		}
	}
}
