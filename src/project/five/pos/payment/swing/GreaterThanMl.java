package project.five.pos.payment.swing;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GreaterThanMl extends JFrame{

	public GreaterThanMl() {
		setTitle("���ϸ��� ����");
		
        JPanel NewWindowContainer = new JPanel();
        setContentPane(NewWindowContainer);
        
        JLabel NewLabel = new JLabel("�Է°��� ������ ��� ���ϸ������� �ʰ��Ͽ����ϴ�.");
        
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
        setLocation(800, 400);
        setVisible(true);
	}
}
