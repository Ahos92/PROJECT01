package project.five.pos.payment.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RegisterMem extends JFrame{
	
	// �ּ�ó���� minibackground ���� �ڵ�

	final static String BG_IMG = "assets/images/miniback7.png";
	
	public RegisterMem() {
		setTitle("����� ���� â");
		// ����, ���⼭ setDefaultCloseOperation() ���Ǹ� ���� ���ƾ� �Ѵ�
        // �����ϰ� �Ǹ� �� â�� ������ ��� â�� ���α׷��� ���ÿ� ������
        
        JPanel NewWindowContainer = new JPanel();
        NewWindowContainer.setBackground(new Color(250, 249, 247));
        setContentPane(NewWindowContainer);
        
        JLabel NewLabel = new JLabel("����� ���� ���Ǵ� ī���Ϳ��� �̿����ּ���. �����մϴ�.");
        NewLabel.setForeground(new Color(1, 1, 1));
        NewLabel.setFont(new Font("ī��24 ���� ����",Font.BOLD, 13));
        NewLabel.setOpaque(false);
        
        NewWindowContainer.add(NewLabel);
        //NewWindowContainer.setOpaque(false);
        
        NewWindowContainer.addMouseListener(new MouseAdapter() {
        	
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if(e.getButton() == MouseEvent.BUTTON1)
        			dispose();
        	
        	}
        });
        
        setSize(400,100);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        
//        JLabel label = null;
//		try {
//			BufferedImage bgImage = ImageIO.read(new File(BG_IMG));
//			int bgx = bgImage.getWidth();
//			int bgy = bgImage.getHeight();
//			label = new JLabel(new ImageIcon(ImageIO.read(new File(BG_IMG)).getScaledInstance(bgx, bgy, Image.SCALE_SMOOTH)));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		label.setBounds(0, 0, 400, 100);
//		JPanel back = new JPanel(new BorderLayout());
//		back.setBounds(0, 0, 400, 100);
//		back.add(NewWindowContainer, BorderLayout.CENTER);
//		
//		back.setOpaque(false);
//		add(back);
//		add(label);
        
       

	}
}
