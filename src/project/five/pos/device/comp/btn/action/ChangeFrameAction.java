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
		
		if (check.equals("�޴��� ���ư���")
				||check.equals("> To Main page")) {

			changeble_frame =  new MainDisplay(device_id);

		} else if(check.equals("�α���")){			
			DeviceDAO device = new DeviceDAO();
			int id = 0;
			String pw = pw_tf.getText();
			try {
				id = Integer.parseInt(id_tf.getText());
			}catch (NumberFormatException nfe) {}

			if (device.searchAdmin(id, pw)) {
				changeble_frame =  new ManagerDisplay(device_id);
				System.out.println("�α��� ����!");
			} else {
				JOptionPane.showMessageDialog(present_frame, "���̵� ��й�ȣ�� �����ʽ��ϴ�.", "����", 
						JOptionPane.ERROR_MESSAGE);
			}

		} else if(check.equals("������")) {
			new LoginPopUpDisplay(present_frame, "������ �α���", device_id);


		} else if(check.equals("�ƴϿ�")) {

			changeble_frame = new ManagerDisplay(device_id);

		} else if(check.equals("ȸ�� ����")) {

			new JoinFrame(present_frame, "ȸ�� ����");


		} else if(check.equals("��ǰ ����")) {

			try {
				new ProductManage(present_frame, "��ǰ ����");
			} catch (IOException e1) {e1.printStackTrace();
			}


		} else if(check.equals("�Ŵ��� ���")) {
			new ManagerSignUpPopUpDisplay(present_frame, check);
			
		} else if(check.equals("����")) {
			new SettlePopUpDisplay(present_frame, check, device_id);
			
		} else if (check.equals("�Ǹ�") 
				|| check.equals("����ϱ�")) {
			changeble_frame = new MenuDisplay(device_id);
			
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
