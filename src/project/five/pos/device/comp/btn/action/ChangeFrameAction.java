package project.five.pos.device.comp.btn.action;

import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

import project.five.pos.device.*;
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
		
<<<<<<< HEAD
		if (check.equals("�޴��� ���ư���")) {
=======
		if (check.equals("�޴��� ���ư���")
				||check.equals("> To Main page")) {
>>>>>>> branch 'developer' of https://github.com/Ahos92/PROJECT01.git
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
			new LoginPopUpDisplay(present_frame, "������ �α���");

<<<<<<< HEAD
		} else if(check.equals("������ �޴��� ���ư���") 
				|| check.equals("�ƴϿ�")
				|| check.equals("��������")) {
=======
		} else if(check.equals("�ƴϿ�")) {
>>>>>>> branch 'developer' of https://github.com/Ahos92/PROJECT01.git
			changeble_frame = new ManagerDisplay();

		} else if(check.equals("ȸ�� ����")) {
<<<<<<< HEAD
			changeble_frame = new JoinFrame();
=======
			new JoinFrame(present_frame, "ȸ�� ����");
>>>>>>> branch 'developer' of https://github.com/Ahos92/PROJECT01.git

		} else if(check.equals("��ǰ ����")) {
<<<<<<< HEAD
			changeble_frame = new ProductManage();
=======
			try {
				new ProductManage(present_frame, "��ǰ ����");
			} catch (IOException e1) {e1.printStackTrace();
			}
>>>>>>> branch 'developer' of https://github.com/Ahos92/PROJECT01.git

		} else if(check.equals("�Ŵ��� ���")) {
			new ManagerSignUpPopUpDisplay(present_frame, check);
			
		} else if(check.equals("����")) {
			new SettlePopUpDisplay(present_frame, check);
			
		} else if (check.equals("�Ǹ�") 
				|| check.equals("����ϱ�")) {
			changeble_frame = new MenuDisplay();
			
		} else if (check.equals("�Ǹ� ���� ��ȸ")
				|| check.equals("���� ���� ��ȸ")
				|| check.equals("ȸ�� ���� ��ȸ")) {
			new LookUpPopUpDisplay(present_frame, check);
		}

		// dialog ��� �� ���� ȭ�� Null
		try {
			changeble_frame.setVisible(true);
			present_frame.dispose();
		} catch (NullPointerException ne) {

		}
	}
}
