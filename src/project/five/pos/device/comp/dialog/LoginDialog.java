package project.five.pos.device.comp.dialog;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import project.five.pos.device.comp.btn.action.ChangeFrameAction;

public class LoginDialog extends JDialog {

	private JPanel center_p, south_p;
	private JLabel id_lab, pw_lab;
	private JTextField id_tf, pw_tf;
	private JButton confirm_btn;
	
	public LoginDialog(JFrame frame, String title) {
		super(frame, title);
		setLayout(new BorderLayout());
		setSize(200, 130);
		setResizable(false);
		setLocationRelativeTo(null);

		south_p = new JPanel(new FlowLayout(FlowLayout.CENTER));
		center_p = new JPanel(new GridLayout(2, 2, 0, 5));
		
		id_lab = new JLabel("아이디   ");
		id_tf = new JTextField(10);
		
		pw_lab = new JLabel("비밀번호");
		pw_tf = new JPasswordField(10);
		
		confirm_btn = new JButton("로그인");
		confirm_btn.addActionListener(new ChangeFrameAction(frame, id_tf, pw_tf));

		center_p.add(id_lab);
		center_p.add(id_tf);
		center_p.add(pw_lab);
		center_p.add(pw_tf);	
		south_p.add(confirm_btn);
		
		add(center_p, BorderLayout.CENTER);
		add(south_p, BorderLayout.SOUTH);

		setVisible(true);
	}
	
}
