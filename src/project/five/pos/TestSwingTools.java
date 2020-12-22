package project.five.pos;


import javax.swing.JFrame;

public class TestSwingTools extends JFrame {

	String title;
	boolean visible;
	
	public static void initTestFrame(JFrame frame, String title, boolean visible) {
		frame.setTitle(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(875, 750);
		frame.setLocationRelativeTo(null);
		frame.setVisible(visible);
	}
	
}
