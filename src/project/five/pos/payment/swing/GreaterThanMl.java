package project.five.pos.payment.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GreaterThanMl extends JFrame{

	public GreaterThanMl() {
		setTitle("���ϸ��� ����");
		
        JPanel NewWindowContainer = new JPanel();
        NewWindowContainer.setBackground(new Color(250, 249, 247));
        setContentPane(NewWindowContainer);
        
        JLabel NewLabel = new JLabel("�Է°��� ������ ��� ���ϸ������� �ʰ��Ͽ����ϴ�.");
        NewLabel.setForeground(new Color(1, 1, 1));
        NewLabel.setFont(new Font("ī��24 ���� ����",Font.BOLD, 13));
        NewLabel.setOpaque(false);
        
        NewWindowContainer.add(NewLabel);
        
        
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
