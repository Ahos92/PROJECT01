package project.five.pos.sale;

import javax.swing.JFrame;

public class TestSwingTools extends JFrame {

	String name;
	boolean visible;
	
	public static void initTestFrame(JFrame frame, String name, boolean visible) {
		frame.setTitle(name);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 300);
		frame.setLocation(1300, 100);
		frame.setVisible(visible);
	}
	
}
