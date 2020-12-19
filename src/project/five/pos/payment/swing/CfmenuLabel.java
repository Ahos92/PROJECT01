package project.five.pos.payment.swing;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class CfmenuLabel extends JLabel{

	public CfmenuLabel(CfmenuEnum cfimg) {
		super(new ImageIcon(cfimg.coffee
				.getScaledInstance(cfimg.x/3, cfimg.y/4, Image.SCALE_AREA_AVERAGING)));
	}
}
