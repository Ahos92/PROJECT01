package project.five.pos.sale;

import javax.swing.JFrame;

public class TestSwingTools extends JFrame {

	String title;
	boolean visible;
	
	public static void initTestFrame(JFrame frame, String title, boolean visible) {
		frame.setTitle(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 800);
		frame.setLocation(1300, 100);
		frame.setVisible(visible);
	}
	
}
