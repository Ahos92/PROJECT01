package project.five.pos.payment.swing;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class ImageLabel extends JLabel{

	public ImageLabel(ImageEnum img) {
		super(new ImageIcon(img.image
				.getScaledInstance(img.x/3, img.y/2, Image.SCALE_AREA_AVERAGING)));
	}
}
