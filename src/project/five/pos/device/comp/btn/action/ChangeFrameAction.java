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
		
		if (check.equals("�޴��� ���ư���")) {
			changeable_frame = new MainDisplay("1234");

		} else if(check.equals("�α���")){			
			DeviceDAO dao = new DeviceDAO();
			int id = 0;
			String pw = pw_tf.getText();
			try {
				id = Integer.parseInt(id_tf.getText());
			}catch (NumberFormatException nfe) {}

			if (dao.searchAdmin(id, pw)) {
				changeable_frame = new ManagerDisplay();
				System.out.println("�α��� ����!");
			} else {
				new ConfirmDialog(present_frame, "�α��� ����!", "���̵� ��й�ȣ�� ���� �ʽ��ϴ�!");
			}

		} else if(check.equals("������")) {
			new LoginDialog(present_frame, "������ �α���");

		} else if(check.equals("������ �޴��� ���ư���") 
				|| check.equals("�ƴϿ�")) {
			changeable_frame = new ManagerDisplay();

		} else if(check.equals("ȸ�� ����")) {
			changeable_frame = new JoinFrame();

		} else if(check.equals("��ǰ ����")) {
			changeable_frame = new ProductManage();

		} else if(check.equals("�Ŵ��� ���")) {
			new ManagerSignUpDialog(present_frame, check);
			
		} else if(check.equals("����")) {
			new SettleDialog(present_frame, check);
			
		} else if (check.equals("�Ǹ�") 
				|| check.equals("����ϱ�")) {
			changeable_frame = new SaleDisplay();
			
		} 

		// dialog ��� �� ���� ȭ�� Null
		try {
			changeable_frame.setVisible(true);
			present_frame.dispose();
		} catch (NullPointerException ne) {

		}
	}
}
