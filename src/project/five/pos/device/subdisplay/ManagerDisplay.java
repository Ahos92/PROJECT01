package project.five.pos.device.subdisplay;

import java.awt.*;

import javax.swing.*;

import project.five.pos.device.actionbtn.ChangeFrameBtn;
import project.five.pos.device.actionbtn.InquiryActionBtn;
import project.five.pos.TestSwingTools;

public class ManagerDisplay extends JFrame {
	
	// ��������, �ǸŻ�ǰ, ȸ��������ȸ, �޴��� ���ư��� ��ư
	JButton pay_history, sold_history, mem_info, back_to_menu;
	
	public ManagerDisplay() {
		setLayout(null);
		
		setPreferredSize(new Dimension(500, 100));
		
		pay_history = new JButton("���� ���� ��ȸ");
		sold_history = new JButton("�Ǹ� ���� ��ȸ");
		mem_info = new JButton("ȸ�� ���� ��ȸ");
		back_to_menu = new JButton("�޴��� ���ư���");	
		
		pay_history.setBounds(30, 50, 130, 130);
		sold_history.setBounds(180, 50, 130, 130);
		mem_info.setBounds(330, 50, 130, 130);
		back_to_menu.setBounds(160, 250, 150, 30);
		
		pay_history.addActionListener(new InquiryActionBtn(this));
		sold_history.addActionListener(new InquiryActionBtn(this));
		mem_info.addActionListener(new InquiryActionBtn(this));
		back_to_menu.addActionListener(new ChangeFrameBtn(this));
		
		add(pay_history);
		add(sold_history);
		add(mem_info);
		add(back_to_menu);	
		
		TestSwingTools.initTestFrame(this, "������ ���", false);
	}
	
	public static void main(String[] args) {
		new ManagerDisplay().setVisible(true);
	}
}
