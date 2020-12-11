package project.five.pos;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import project.five.pos.device.DeviceDisplay;
import project.five.pos.device.subdisplay.ConfirmDialog;
import project.five.pos.sale.SaleDAO;

public class PosLoginDisplay extends JFrame {

	Font font;
	JLabel id_l, pw_l;
	JTextField id_tf, pw_tf;
	JButton login_btn;
	
	public PosLoginDisplay() {
		font = new Font("바탕", 10, 20);
		
		id_l = new JLabel("Device_id");
		id_l.setBounds(500, 150, 100, 30);
		id_l.setFont(font);
		add(id_l);
		
		id_tf = new JTextField(20);
		id_tf.setBounds(600, 150, 120, 30);
		id_tf.setFont(font);
		add(id_tf);
		
		pw_l = new JLabel("Password");
		pw_l.setBounds(500, 200, 100, 30);
		pw_l.setFont(font);		
		add(pw_l);
		
		pw_tf = new JTextField(20);
		pw_tf.setBounds(600, 200, 120, 30);
		pw_tf.setFont(font);
		add(pw_tf);
		
		JFrame f = this;
		login_btn = new JButton("login");
		login_btn.setBounds(730, 150, 70, 80);
		login_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SaleDAO dao = new SaleDAO();
				if (dao.searchPOS(Integer.parseInt(id_tf.getText()), pw_tf.getText())) {
					// 메인 디스플레이 호출  
					// 디바이스 아이디도 메인으로 넘겨주고 나머지 패널로 바꿔서 화면전환하기
					new DeviceDisplay(id_tf.getText());
					setVisible(false);
				} else {
					new ConfirmDialog(f, "로그인 실패!");
				}
			}
		});
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
