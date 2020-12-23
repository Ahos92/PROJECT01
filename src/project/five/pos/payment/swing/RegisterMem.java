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
	
	// 주석처리는 minibackground 적용 코드

	final static String BG_IMG = "assets/images/miniback7.png";
	
	public RegisterMem() {
		setTitle("멤버쉽 가입 창");
		// 주의, 여기서 setDefaultCloseOperation() 정의를 하지 말아야 한다
        // 정의하게 되면 새 창을 닫으면 모든 창과 프로그램이 동시에 꺼진다
        
        JPanel NewWindowContainer = new JPanel();
        NewWindowContainer.setBackground(new Color(250, 249, 247));
        setContentPane(NewWindowContainer);
        
        JLabel NewLabel = new JLabel("멤버쉽 가입 문의는 카운터에서 이용해주세요. 감사합니다.");
        NewLabel.setForeground(new Color(1, 1, 1));
        NewLabel.setFont(new Font("카페24 숑숑 보통",Font.BOLD, 13));
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
