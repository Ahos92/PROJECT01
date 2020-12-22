package project.five.pos.payment.swing;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CancelPayment extends JFrame {

	public CancelPayment() {
		
		setTitle("결제 중..");

        JPanel NewWindowContainer = new JPanel();
        setContentPane(NewWindowContainer);
        
        
        JLabel NewLabel = new JLabel("결제가 취소되었습니다.");
        
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
