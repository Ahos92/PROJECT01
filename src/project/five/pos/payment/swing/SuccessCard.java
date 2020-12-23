package project.five.pos.payment.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SuccessCard extends JFrame{

	public SuccessCard() {
		
		setTitle("���� ���� Ȯ�� ��..");
		
        JPanel NewWindowContainer = new JPanel();
        NewWindowContainer.setBackground(new Color(250, 249, 247));
        setContentPane(NewWindowContainer);
        
        
        JLabel NewLabel = new JLabel("ī�� �ν� �Ϸ�.");
        NewLabel.setForeground(new Color(1, 1, 1));
        NewLabel.setFont(new Font("ī��24 ���� ����",Font.BOLD, 13));
        NewLabel.setOpaque(false);
        
        try {
			Thread.sleep(2000);
			NewWindowContainer.add(NewLabel);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
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
	}
}
