package project.five.pos.payment.swing;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Incorrect extends JFrame{

	public Incorrect() {
		
		setTitle("����� ����");
		 
        JPanel NewWindowContainer = new JPanel();
        setContentPane(NewWindowContainer);
        
        JLabel NewLabel = new JLabel("����� ������ �ùٸ��� �ʽ��ϴ�. �ٽ� �ѹ� Ȯ�����ּ���");
        
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