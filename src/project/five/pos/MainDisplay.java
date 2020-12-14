package project.five.pos;

import javax.swing.*;

import project.five.pos.device.actionbtn.ChangeFrameBtn;

public class MainDisplay extends JFrame {

	// ������ ���, �ǸŸ��
	JButton manageBtn, saleBtn;
	
	public MainDisplay(String device_id) {
		setLayout(null);
		
		manageBtn =  new JButton("������");
		manageBtn.setBounds(100, 250, 130, 130);
		saleBtn =  new JButton("�Ǹ�");
		saleBtn.setBounds(250, 250, 130, 130);
		
		manageBtn.addActionListener(new ChangeFrameBtn(this));
		saleBtn.addActionListener(new ChangeFrameBtn(this));
		
		add(manageBtn);
		add(saleBtn);
		
		TestSwingTools.initTestFrame(this, "main", true);
	}
	
	public static void main(String[] args) {
		new MainDisplay("1234");
	}
	
}
