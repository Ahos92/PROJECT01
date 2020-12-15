package project.five.pos;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import project.five.pos.device.subdisplay.ConfirmDialog;
import project.five.pos.sale.SaleDAO;

public class PosLoginDisplay extends JFrame {

	Font font;
	JLabel id_lab, pw_lab;
	JTextField id_tf, pw_tf;
	JButton login_btn;
	
	public PosLoginDisplay() {
		font = new Font("바탕", 10, 20);
		
		id_lab = new JLabel("Device_id");
		id_lab.setBounds(500, 150, 100, 30);
		id_lab.setFont(font);
		
		id_tf = new JTextField(20);
		id_tf.setBounds(600, 150, 120, 30);
		id_tf.setFont(font);
		
		pw_lab = new JLabel("Password");
		pw_lab.setBounds(500, 200, 100, 30);
		pw_lab.setFont(font);		
		
		pw_tf = new JTextField(20);
		pw_tf.setBounds(600, 200, 120, 30);
		pw_tf.setFont(font);
		
		JFrame f = this;
		login_btn = new JButton("login");
		login_btn.setBounds(730, 150, 70, 80);
		login_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SaleDAO dao = new SaleDAO();
				if (dao.searchPOS(Integer.parseInt(id_tf.getText()), pw_tf.getText())) {
					new MainDisplay(id_tf.getText());			
					dispose();
				} else {
					new ConfirmDialog(f, "로그인 실패!");
				}
			}
		});

		add(id_lab);
		add(id_tf);
		add(pw_lab);
		add(pw_tf);
		add(login_btn);
		
		setTitle("FivePoS");
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(820, 500);
		setLocationRelativeTo(null);
		setVisible(true);	
	}
	
	public static void main(String[] args) {
		new PosLoginDisplay();
	}
}
