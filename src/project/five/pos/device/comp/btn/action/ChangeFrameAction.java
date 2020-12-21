package project.five.pos.device.comp.btn.action;

import java.awt.event.*;

import javax.swing.*;

import project.five.pos.MainDisplay;
import project.five.pos.device.*;
import project.five.pos.device.comp.dialog.LoginDialog;
import project.five.pos.device.comp.dialog.ManagerSignUpDialog;
import project.five.pos.device.comp.dialog.SettleDialog;
import project.five.pos.membership.gui.*;
import project.five.pos.menu.ProductManage;
import project.five.pos.menu.MenuDisplay;

public class ChangeFrameAction implements ActionListener{

	JFrame present_frame, changeble_frame;
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
		
		if (check.equals("�޴��� ���ư���")
				||check.equals("> To Main page")) {
			changeble_frame =  new MainDisplay("1234");

		} else if(check.equals("�α���")){			
			DeviceDAO device = new DeviceDAO();
			int id = 0;
			String pw = pw_tf.getText();
			try {
				id = Integer.parseInt(id_tf.getText());
			}catch (NumberFormatException nfe) {}

			if (device.searchAdmin(id, pw)) {
				changeble_frame =  new ManagerDisplay();
				System.out.println("�α��� ����!");
			} else {
				JOptionPane.showMessageDialog(present_frame, "���̵� ��й�ȣ�� �����ʽ��ϴ�.", "����", 
						JOptionPane.ERROR_MESSAGE);
			}

		} else if(check.equals("������")) {
			new LoginDialog(present_frame, "������ �α���");

		} else if(check.equals("������ �޴��� ���ư���") 
				|| check.equals("�ƴϿ�")
				|| check.equals("��������")) {
			changeble_frame = new ManagerDisplay();

		} else if(check.equals("ȸ�� ����")) {
			changeble_frame = new JoinFrame();

		} else if(check.equals("��ǰ ����")) {
			changeble_frame = new ProductManage();

		} else if(check.equals("�Ŵ��� ���")) {
			new ManagerSignUpDialog(present_frame, check);
			
		} else if(check.equals("����")) {
			new SettleDialog(present_frame, check);
			
		} else if (check.equals("�Ǹ�") 
				|| check.equals("����ϱ�")) {
			changeble_frame = new MenuDisplay();
			
		} 

		// dialog ��� �� ���� ȭ�� Null
		try {
			changeble_frame.setVisible(true);
			present_frame.dispose();
		} catch (NullPointerException ne) {

		}
	}
}
