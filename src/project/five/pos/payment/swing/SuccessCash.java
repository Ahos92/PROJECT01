package project.five.pos.payment.swing;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class SuccessCash extends JFrame{

	int money;
	int price;
	
	JButton card_btn;
	JButton cash_btn;
	
	public SuccessCash(int money, int price, JButton card_btn, JButton cash_btn) {
		this.money = money;
		this.price = price;
		
		this.card_btn = card_btn;
		this.cash_btn = cash_btn;
		
		setTitle("���� ���� Ȯ�� ��..");
		        
        JPanel NewWindowContainer = new JPanel();
        setContentPane(NewWindowContainer);
        JLabel NewLabel = new JLabel("�ܾ��� �����մϴ�");
        JLabel NewLabel2 = new JLabel("���� ���� Ȯ�� �Ϸ�");
        try {
			// �ܾ� ����			
	        if(money < price) {
	        	Thread.sleep(1000);
	        	NewWindowContainer.add(NewLabel);
	        	
	        	cash_btn.setText("����");
				cash_btn.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
				card_btn.setEnabled(true);
	        }
	        // �ܾ� ���
	        else {
	        	Thread.sleep(1000);
	        	NewWindowContainer.add(NewLabel2);
	        	        	
	        }
	        NewWindowContainer.addMouseListener(new MouseAdapter() {
	        	
	        	@Override
	        	public void mouseClicked(MouseEvent e) {
	        		if(e.getButton() == MouseEvent.BUTTON1)
	        			dispose();
	        	
	        	}
	        });
	        
		} catch (InterruptedException ex) {
			
			ex.printStackTrace();
		}
                                                                       
        setSize(400,100);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
	}
}
