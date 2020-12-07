package project.five.pos.payment.swing;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public enum ImageEnum {
	
	// ��õ�޴� �̹��� �߰�
	
	
	COFFEE("Ŀ��", "assets/images/rccoffee.jpg"),
	LATTE("��", "assets/images/rclatte.jpg"),
	NEW("�Ÿ޴�", "assets/images/rcnew.jpg"),
	ROLL("��", "assets/images/rcroll.jpg");
	
	String lname;
	String img_path;
	Image image;
	
	
	private ImageEnum(String lname, String img_path) {
		this.lname = lname;
		this.img_path =img_path;
		
		try {
			this.image = ImageIO.read(new File(img_path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String getLname() {
		return lname;
	}
	public String getImg_path() {
		return img_path;
	}
	public Image getImage() {
		return image;
	}
}
