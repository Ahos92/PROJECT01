package project.five.pos.device.comp.btn.action;

import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;


import project.five.pos.device.*;
import project.five.pos.manage.ProductManage;

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
			//changeable_frame = new MainDisplay("1234");

		} else if(check.equals("메인으로")) {
			//changeable_frame = new ManagerDisplay();

		} else if(check.equals("상품 관리")) {
			try {
				changeable_frame = new ProductManage();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		} 

		// dialog 띄울 때 변할 화면 Null
		try {
			changeable_frame.setVisible(true);
			present_frame.dispose();
		} catch (NullPointerException ne) {

		}
	}
}