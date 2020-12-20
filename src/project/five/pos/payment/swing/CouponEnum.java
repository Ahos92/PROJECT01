package project.five.pos.payment.swing;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public enum CouponEnum {

	// ÄíÆù ÀÌ¹ÌÁö Ãß°¡
	SUMMER("¿©¸§ÄíÆù", "assets/coupons/summercoupon.png"),
	WINTER("°Ü¿ïÄíÆù", "assets/coupons/wintercoupon.png"),
	NOVEMBER("11¿ù ÄíÆù", "assets/coupons/novembercoupon.png"),
	DECEMBER("12¿ù ÄíÆù", "assets/coupons/decembercoupon.png"),
	CHRISTMAS("¼ºÅºÀı ÄíÆù", "assets/coupons/christmas.png");

	String cname;
	String coupon_path;
	Image coupon;
	int x;
	int y;
	
	private CouponEnum(String cname, String coupon_path) {
		this.cname = cname;
		this.coupon_path = coupon_path;
		
		try {
			this.coupon = ImageIO.read(new File(coupon_path));
			BufferedImage cimage = ImageIO.read(new File(coupon_path));
			x = cimage.getWidth();
			y = cimage.getHeight();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String getCname() {
		return cname;
	}
	public String getCoupon_path() {
		return coupon_path;
	}
	public Image getCoupon() {
		return coupon;
	}
	
	
}
