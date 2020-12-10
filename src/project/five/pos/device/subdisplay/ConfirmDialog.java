package project.five.pos.device.subdisplay;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ConfirmDialog extends JDialog {

	private JLabel msg;
	private JPanel south_p;
	private JButton check;
	
	public ConfirmDialog(JFrame frame, String title) {
		super(frame, title);
		setLayout(new BorderLayout());
		setBounds(1420, 400, 250, 130);
		
		msg = new JLabel("아이디나 비밀번호가 맞지 않습니다!");
		msg.setHorizontalAlignment(msg.CENTER);
		
		check = new JButton("확인");
		check.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		south_p = new JPanel(new FlowLayout(FlowLayout.CENTER));
		south_p.add(check);
		
		add(msg, BorderLayout.CENTER);
		add(south_p, BorderLayout.SOUTH);
	
		setVisible(true);
	}
}
