package project.five.pos.payment.swing;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SuccessCard extends JFrame{

	public SuccessCard() {
		
		setTitle("���� ���� Ȯ�� ��..");
		// ����, ���⼭ setDefaultCloseOperation() ���Ǹ� ���� ���ƾ� �Ѵ�
        // �����ϰ� �Ǹ� �� â�� ������ ��� â�� ���α׷��� ���ÿ� ������
        
        JPanel NewWindowContainer = new JPanel();
        setContentPane(NewWindowContainer);
        
        
        JLabel NewLabel = new JLabel("ī�� �ν� �Ϸ�.");
        
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
        setLocation(800, 400);
        setVisible(true);
	}
}
