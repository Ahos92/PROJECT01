package project.five.pos.payment.swing;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public enum CfmenuEnum {

	
	// Ŀ�� �̹��� �߰�
	ICEAMERICANO("���̽��Ƹ޸�ī��", "assets/menus/iceamericano.jfif"),
	ICECAFFERATTE("���̽�ī���", "assets/menus/icecafferatte.jfif");
	
	String cfname;
	String coffee_path;
	Image coffee;
	int x;
	int y;
	
	private CfmenuEnum(String cfname, String coffee_path) {
		this.cfname = cfname;
		this.coffee_path = coffee_path;
		
		try {
			this.coffee = ImageIO.read(new File(coffee_path));
			BufferedImage cfimage = ImageIO.read(new File(coffee_path));
			x = cfimage.getWidth();
			y = cfimage.getHeight();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public String getCfname() {
		return cfname;
	}
	public String getCoffee_path() {
		return coffee_path;
	}
	public Image getCoffee() {
		return coffee;
	}
}
