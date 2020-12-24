package project.five.pos;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
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
	JTextField id_tf; 
	JPasswordField pw_tf;
	JButton login_btn;
	JPanel back_p, front_p;
	JScrollPane scroll;
	public PosLoginDisplay() {
		
		back_p = new DevicePanel("assets/images/back2.png", 890, 798);
		back_p.setOpaque(false);
		
		font = new Font("바탕", font.BOLD, 20);

		id_lab = new JLabel("Device_id");
		id_lab.setFont(font);
		id_lab.setForeground(getForeground().WHITE);
		id_lab.setBounds(10, 333, 150, 30);
		
		id_tf = new JTextField(10);
		id_tf.setBounds(120, 333, 100, 30);
		
		pw_lab = new JLabel("Password");
		pw_lab.setFont(font);		
		pw_lab.setForeground(getForeground().WHITE);
		pw_lab.setBounds(12, 383, 150, 30);
		
		pw_tf = new JPasswordField(10);
		pw_tf.setBounds(120, 383, 100, 30);
		
		JFrame f = this;
		login_btn = new DeviceBtn("login");
		login_btn.setBounds(240, 328, 80, 90);
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

		add(id_lab);
		add(id_tf);
		add(login_btn);
		add(pw_lab);
		add(pw_tf);
		
		add(back_p);
		SwingTools.posloginFrame(this);
	}

	public static void main(String[] args) {
		new PosLoginDisplay();
	}
}
