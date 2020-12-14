package project.five.pos.payment.swing;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class CouponLabel extends JLabel{

	public CouponLabel(CouponEnum cp) {
		super(new ImageIcon(cp.coupon
				.getScaledInstance(130, 60, Image.SCALE_SMOOTH)));
	}
}
