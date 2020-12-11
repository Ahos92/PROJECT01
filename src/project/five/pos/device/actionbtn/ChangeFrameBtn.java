package project.five.pos.device.actionbtn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;

import project.five.pos.device.DeviceDisplay;
import project.five.pos.device.subdisplay.ConfirmDialog;
import project.five.pos.device.subdisplay.LoginDialog;
import project.five.pos.device.subdisplay.ManagerDisplay;

public class ChangeFrameBtn implements ActionListener{

	JFrame changeable_frame;
	JFrame present_frame;
	JDialog login_confirm;

	JTextField id_fd;
	JTextField pw_fd;
	public ChangeFrameBtn(JFrame present_frame, JTextField id_fd, JTextField pw_fd) {
		this.present_frame = present_frame;
		this.id_fd = id_fd;
		this.pw_fd = pw_fd;
	}

	public ChangeFrameBtn(JFrame present_frame) {
		this.present_frame = present_frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("메뉴로 돌아가기")) {
			changeable_frame = new DeviceDisplay("1234");

		} else if(e.getActionCommand().equals("로그인")){			
			// 테스트 할동안 주석처리
//			String id = id_fd.getText();
//			String pw = pw_fd.getText();
//			if (id.equals("123") && pw.equals("45")) {
				changeable_frame = new ManagerDisplay();
				System.out.println("로그인 성공!");
//				
//			} else if (!(id.equals("123")) || !(pw.equals("45"))) {
//				login_confirm = new ConfirmDialog(present_frame, "test2");
//			}
			
		} else if(e.getActionCommand().equals("관리자")) {
			login_confirm = new LoginDialog(present_frame, "test");

		} else if(e.getActionCommand().equals("관리자 메뉴로 돌아가기")) {
			changeable_frame = new ManagerDisplay();
		}

		// dialog 띄울 때 변할 화면 Null
		try {
			changeable_frame.setVisible(true);
			present_frame.setVisible(false);
		} catch (NullPointerException ne) {
			System.err.println("관리자 로그인 진행 중");
		}
	}
}
