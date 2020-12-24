package project.five.pos.device;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import project.five.pos.SwingTools;
import project.five.pos.device.comp.*;
import project.five.pos.device.comp.btn.DeviceBtn;
import project.five.pos.device.comp.btn.action.ChangeFrameAction;
import project.five.pos.device.comp.btn.action.SettleAction;

public class SettlePopUpDisplay extends JDialog {

	JLabel msg_lab01, msg_lab02;
	JButton yes_btn, no_btn;
	JPanel center_p, south_p;
	
	public SettlePopUpDisplay(JFrame frame, String title, String device_id) {
		super(frame, title);
		setLayout(new BorderLayout());
		setSize(300, 200);
		setResizable(false);
		setLocationRelativeTo(null);
		
		center_p = new JPanel(new FlowLayout(FlowLayout.CENTER));
		south_p = new JPanel(new FlowLayout(FlowLayout.CENTER));
			
		msg_lab01 = new DeviceLab("������ �� �̻� �ǸŸ� �� �� ���� �˴ϴ�.", 230, 40);
		msg_lab02 = new DeviceLab("���� ���� �Ͻðڽ��ϱ�?", 150, 40);
		
		yes_btn = new JButton("��");
		yes_btn.setPreferredSize(new Dimension(70, 30));
		yes_btn.addActionListener(new SettleAction(frame, this, device_id));
		
		no_btn = new JButton("�ƴϿ�"); 
		no_btn.setPreferredSize(new Dimension(70, 30));
		no_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		center_p.add(msg_lab01);
		center_p.add(msg_lab02);
		south_p.add(yes_btn);
		south_p.add(no_btn);
		
		add(center_p, BorderLayout.CENTER);
		add(south_p, BorderLayout.SOUTH);
		
		setVisible(true);
	}
}
