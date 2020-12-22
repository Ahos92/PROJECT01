package project.five.pos.payment.swing;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Incorrect extends JFrame{

	public Incorrect() {
		
		setTitle("멤버쉽 오류");
		 
        JPanel NewWindowContainer = new JPanel();
        setContentPane(NewWindowContainer);
        
        JLabel NewLabel = new JLabel("멤버쉽 정보가 올바르지 않습니다. 다시 한번 확인해주세요");
        
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
