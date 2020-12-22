package project.five.pos;


import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestSwingTools extends JFrame {

	String title;
	boolean visible;
	
	public static void initTestFrame(JFrame frame, String title, boolean visible) {
		frame.setTitle(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(890, 789);
		frame.setLocationRelativeTo(null);
		frame.setVisible(visible);
		frame.setResizable(false);
	}

	public static void posloginFrame(JFrame frame) {
		frame.setTitle("FivePoS");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(890, 789);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);	
		frame.setResizable(false);
	}
	
	
}
