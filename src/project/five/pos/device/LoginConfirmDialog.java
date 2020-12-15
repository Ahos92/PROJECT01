package project.five.pos.device;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LoginConfirmDialog extends JDialog {

	private JLabel msg_lab;
	private JPanel south_p;
	private JButton check_btn;
	
	public LoginConfirmDialog(JFrame frame, String title) {
		super(frame, title);
		setLayout(new BorderLayout());
		setSize(250, 130);
		setLocationRelativeTo(null);
		
		msg_lab = new JLabel("���̵� ��й�ȣ�� ���� �ʽ��ϴ�!");
		msg_lab.setHorizontalAlignment(msg_lab.CENTER);
		
		check_btn = new JButton("Ȯ��");
		check_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		south_p = new JPanel(new FlowLayout(FlowLayout.CENTER));
		south_p.add(check_btn);
		
		add(msg_lab, BorderLayout.CENTER);
		add(south_p, BorderLayout.SOUTH);
	
		setVisible(true);
	}
}
