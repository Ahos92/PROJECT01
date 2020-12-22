package project.five.pos.device.btn;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import project.five.pos.TestSwingTools;
//import sun.java2d.loops.FillRect;

public class DeviceBtn extends JButton {

	// 이미지 참고 - https://www.flaticon.com/
	BufferedImage s;

	public DeviceBtn() {
	}

	public DeviceBtn(int length, String file_path, ActionListener action) {
		setPreferredSize(new Dimension(length, length));
		addActionListener(action);
		try {
			s = ImageIO.read(new File(file_path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setIcon(new ImageIcon(s.getScaledInstance(length, length,Image.SCALE_SMOOTH)));
		decorate(true);

	}

	public DeviceBtn(String btn_name) {
		super(btn_name);
		decorate(true);
	}

	/* 
  		직사각형 버튼
	 */
	public DeviceBtn(String btn_text, int width, int height, ActionListener action) {
		setText(btn_text);
		setPreferredSize(new Dimension(width, height));
		addActionListener(action);
		decorate(true);
	}
	// 이미지
	public DeviceBtn(String btn_text, String file_path, int width, int height, ActionListener action) {
		setText(btn_text);
		setPreferredSize(new Dimension(width, height));
		addActionListener(action);

		try {
			s = ImageIO.read(new File(file_path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setIcon(new ImageIcon(s.getScaledInstance(width, height,Image.SCALE_SMOOTH)));

		decorate(false);
	}


	/* 
	  	정사각형 버튼
	 */
	public DeviceBtn(String btn_text, int length, ActionListener action) {
		setText(btn_text);
		setPreferredSize(new Dimension(length, length));
		addActionListener(action);
		decorate(true);
	}

	// 아이콘
	public DeviceBtn(String btn_text, String file_path, int length, ActionListener action) {
		setText(btn_text);
		setPreferredSize(new Dimension(length, length));
		addActionListener(action);

		try {
			s = ImageIO.read(new File(file_path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setIcon(new ImageIcon(s.getScaledInstance(length, length-20,Image.SCALE_SMOOTH)));

		decorate(false);
	}
	
	// 메뉴 이미지 (action 제외)
	public DeviceBtn(String btn_text, String file_path, int length) {
		setText(btn_text);
		ImageIcon s = new ImageIcon(file_path);
		Image image = s.getImage();
		Image changeI = image.getScaledInstance(length, length, Image.SCALE_SMOOTH);
		setIcon(new ImageIcon(changeI));

		decorate(false);
	}

	public void decorate(boolean areafilled) {
		setHorizontalTextPosition(JButton.CENTER);
		setVerticalTextPosition(JButton.BOTTOM);
		setContentAreaFilled(areafilled);
		setOpaque(false);
		setBorderPainted(false);
	}


	@Override
	protected void paintComponent(Graphics g) {
		int width = getWidth(); 
		int height = getHeight(); 
		Graphics2D graphics = (Graphics2D) g; 
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 

		if (getModel().isArmed()) {
			graphics.setColor(getBackground().darker()); 

		} else if (getModel().isRollover()) { 
			graphics.setColor(getBackground().LIGHT_GRAY); 

		} else { 
			graphics.setColor(new Color(255, 0, 0, 0)); 
		} 


		graphics.fillRoundRect(0, 0, width, height, 10, 10); 
		graphics.setColor(getForeground()); 
		graphics.setFont(getFont()); 

		super.paintComponent(g);
	}

}
