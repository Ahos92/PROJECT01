package project.five.pos.device;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import project.five.pos.device.btn.action.ChangeFrameBtn;

public class LoginDialog extends JDialog {

	private JPanel center_p;
	private JLabel id_l;
	private JTextField id_fd;
	private JLabel pw_l;
	private JTextField pw_fd;
	
	private JPanel south_p;
	private JButton confirm;
	
	public LoginDialog(JFrame frame, String title) {
		super(frame, title);
		setLayout(new BorderLayout());
		setSize(200, 130);
		setLocationRelativeTo(null);
		
		id_l = new JLabel("아이디   ");
		id_fd = new JTextField(10);
		
		pw_l = new JLabel("비밀번호");
		pw_fd = new JPasswordField(10);
		
		center_p = new JPanel(new GridLayout(2, 2, 0, 5));
		center_p.add(id_l);
		center_p.add(id_fd);
		center_p.add(pw_l);
		center_p.add(pw_fd);
		add(center_p, BorderLayout.CENTER);

		confirm = new JButton("로그인");
		
		south_p = new JPanel(new FlowLayout(FlowLayout.CENTER));
		south_p.add(confirm);
		add(south_p, BorderLayout.SOUTH);
		
		confirm.addActionListener(new ChangeFrameBtn(frame, id_fd, pw_fd));

		setVisible(true);
	}
	
}
