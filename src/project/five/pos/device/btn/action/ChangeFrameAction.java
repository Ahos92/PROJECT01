package project.five.pos.device.btn.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;

import project.five.pos.MainDisplay;
import project.five.pos.device.DeviceDAO;
import project.five.pos.device.LoginConfirmDialog;
import project.five.pos.device.LoginDialog;
import project.five.pos.device.ManagerDisplay;
import project.five.pos.manage.ProductManage;
import project.five.pos.membership.gui.JoinFrame;
import project.five.pos.membership.gui.LoginFrame;

public class ChangeFrameAction implements ActionListener{

	JFrame changeable_frame, present_frame;
	JDialog login_confirm;
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
		if (e.getActionCommand().equals("메뉴로 돌아가기")) {
			changeable_frame = new MainDisplay("1234");

		} else if(e.getActionCommand().equals("로그인")){			
			String id = id_tf.getText();
			String pw = pw_tf.getText();
			DeviceDAO dao = new DeviceDAO();
			try {
				if (dao.searchAdmin(Integer.parseInt(id_tf.getText()), pw_tf.getText())) {
					changeable_frame = new ManagerDisplay();
					System.out.println("로그인 성공!");

				} else {
					new LoginConfirmDialog(present_frame, "로그인 실패!");
				}
			}catch (NumberFormatException nfe) {
				new LoginConfirmDialog(present_frame, "로그인 실패!");
			}

		} else if(e.getActionCommand().equals("관리자")) {
			login_confirm = new LoginDialog(present_frame, "관리자 로그인");

		} else if(e.getActionCommand().equals("관리자 메뉴로 돌아가기")) {
			changeable_frame = new ManagerDisplay();

		} else if(e.getActionCommand().equals("회원 가입")) {
			changeable_frame = new JoinFrame();

		} else if(e.getActionCommand().equals("상품 관리")) {
			changeable_frame = new ProductManage();

		}

		// dialog 띄울 때 변할 화면 Null
		try {
			changeable_frame.setVisible(true);
			present_frame.dispose();
		} catch (NullPointerException ne) {
			System.err.println("관리자 로그인 진행 중");
		}
	}
}
