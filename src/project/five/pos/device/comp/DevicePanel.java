package project.five.pos.device.comp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class DevicePanel extends JPanel {

	BufferedImage icon;
	JScrollPane scrollPane;
	ImageIcon background;
	
	String imagePath;
	int width, height;
	public DevicePanel(String imagePath) {
		this.imagePath = imagePath;
	}

	public DevicePanel(String imagePath, int width, int height, LayoutManager layout) {
		super(layout);
		this.imagePath = imagePath;
		this.width = width;
		this.height = height;
	}

	public DevicePanel(String imagePath, int width, int height) {
		this.imagePath = imagePath;
		this.width = width;
		this.height = height;
	}	
	
	@Override
	protected void paintComponent(Graphics g) {
		try {
			icon = ImageIO.read(new File(imagePath));
			background = new ImageIcon(icon.getScaledInstance(width, height, Image.SCALE_SMOOTH));
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		g.drawImage(background.getImage(), 0, 0, null);
		setOpaque(false);
		super.paintComponent(g);
	}
}
