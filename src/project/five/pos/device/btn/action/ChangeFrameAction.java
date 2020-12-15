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
		if (e.getActionCommand().equals("�޴��� ���ư���")) {
			changeable_frame = new MainDisplay("1234");

		} else if(e.getActionCommand().equals("�α���")){			
			String id = id_tf.getText();
			String pw = pw_tf.getText();
			DeviceDAO dao = new DeviceDAO();
			try {
				if (dao.searchAdmin(Integer.parseInt(id_tf.getText()), pw_tf.getText())) {
					changeable_frame = new ManagerDisplay();
					System.out.println("�α��� ����!");

				} else {
					new LoginConfirmDialog(present_frame, "�α��� ����!");
				}
			}catch (NumberFormatException nfe) {
				new LoginConfirmDialog(present_frame, "�α��� ����!");
			}

		} else if(e.getActionCommand().equals("������")) {
			login_confirm = new LoginDialog(present_frame, "������ �α���");

		} else if(e.getActionCommand().equals("������ �޴��� ���ư���")) {
			changeable_frame = new ManagerDisplay();

		} else if(e.getActionCommand().equals("ȸ�� ����")) {
			changeable_frame = new JoinFrame();

		} else if(e.getActionCommand().equals("��ǰ ����")) {
			changeable_frame = new ProductManage();

		}

		// dialog ��� �� ���� ȭ�� Null
		try {
			changeable_frame.setVisible(true);
			present_frame.dispose();
		} catch (NullPointerException ne) {
			System.err.println("������ �α��� ���� ��");
		}
	}
}
