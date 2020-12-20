package project.five.pos;


import javax.swing.JFrame;

public class TestSwingTools extends JFrame {

	String title;
	boolean visible;
	
	public static void initTestFrame(JFrame frame, String title, boolean visible) {
		frame.setTitle(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 800);
		frame.setLocationRelativeTo(null);
		frame.setVisible(visible);
		
		
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setSize(430, 490);
//		setLocationRelativeTo(null);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPane);
//		contentPane.setLayout(null);
	}
	
}
