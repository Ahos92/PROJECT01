package project.five.pos.device.subdisplay;

import java.awt.*;

import javax.swing.*;

import project.five.pos.device.actionbtn.ChangeFrameBtn;
import project.five.pos.device.actionbtn.InquiryActionBtn;
import project.five.pos.TestSwingTools;

public class ManagerDisplay extends JFrame {
	
	// 결제내역, 판매상품, 회원정보조회, 메뉴로 돌아가기 버튼
	JButton pay_history, sold_history, mem_info, back_to_menu;
	
	public ManagerDisplay() {
		setLayout(null);
		
		setPreferredSize(new Dimension(500, 100));
		
		pay_history = new JButton("결제 내역 조회");
		sold_history = new JButton("판매 내역 조회");
		mem_info = new JButton("회원 정보 조회");
		back_to_menu = new JButton("메뉴로 돌아가기");	
		
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
		
		TestSwingTools.initTestFrame(this, "관리자 모드", false);
	}
	
	public static void main(String[] args) {
		new ManagerDisplay().setVisible(true);
	}
}
