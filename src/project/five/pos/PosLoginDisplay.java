package project.five.pos;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import project.five.pos.device.*;
import project.five.pos.device.comp.DevicePanel;
import project.five.pos.device.comp.btn.DeviceBtn;

public class PosLoginDisplay extends JFrame {

	Font font;
	JLabel id_lab, pw_lab;
	JTextField id_tf, pw_tf;
	JButton login_btn;
	JPanel back_p;
	JScrollPane scroll;
	public PosLoginDisplay() {

		back_p = new DevicePanel("assets/images/back2.jpg", 
								800, 460, new FlowLayout(FlowLayout.LEFT));
		
		scroll = new JScrollPane(back_p);  
		setContentPane(scroll);
		
		font = new Font("바탕", font.BOLD, 20);

		id_lab = new JLabel("Device_id");
		id_lab.setFont(font);
		id_lab.setForeground(getForeground().WHITE);
		
		id_tf = new JTextField(10);

		pw_lab = new JLabel("Password");
		pw_lab.setFont(font);		
		pw_lab.setForeground(getForeground().WHITE);
		
		pw_tf = new JTextField(10);
		

		JFrame f = this;
		login_btn = new DeviceBtn("login");
		login_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeviceDAO device = new DeviceDAO();
				int id = 0;
				try {
					id = Integer.parseInt(id_tf.getText());
				}catch (NumberFormatException nfe) {}

				if (device.searchPOS(id, pw_tf.getText())) {
					new MainDisplay(id_tf.getText());			
					dispose();
				} else {
					JOptionPane.showMessageDialog(f, "아이디나 비밀번호가 맞지않습니다.", "오류",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		back_p.add(id_lab);
		back_p.add(id_tf);
		back_p.add(pw_lab);
		back_p.add(pw_tf);
		back_p.add(login_btn);

		TestSwingTools.posloginFrame(this);
	}

	public static void main(String[] args) {
		new PosLoginDisplay();
	}
}
