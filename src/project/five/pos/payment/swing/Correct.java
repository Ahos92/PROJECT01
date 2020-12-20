package project.five.pos.payment.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Correct extends JFrame{

	PayPanel main;
	JLabel iamMem;
	
	public Correct(PayPanel main) {
		
		this.main = main;
		
		setTitle("����� Ȯ��");
		
        JPanel NewWindowContainer = new JPanel();
        
        setContentPane(NewWindowContainer);
        
        JLabel NewLabel = new JLabel("����� ������ Ȯ�� �Ǿ����ϴ�.");
             
        JPanel mem = new JPanel(new BorderLayout(0, 0));
        mem.setOpaque(false);
        iamMem = new JLabel("<html>" + CheckMem.memberName + "�� �ݰ����ϴ�. ���õ� ���� �Ϸ� �Ǽ���<br/>���ϸ��� : "
        + CheckMem.memberMileage +"<br/>���ϸ��� ����� 1000�� �̻���� �����մϴ�.</html>", JLabel.CENTER);
        iamMem.setFont(new Font("Serif", Font.BOLD, 14));
        mem.add(iamMem);
        
        PayPanel.card_panel.add("���", mem);
        
        iamMem.setForeground(Color.WHITE);
         
        NewWindowContainer.add(NewLabel);
              
        NewWindowContainer.addMouseListener(new MouseAdapter() {
        	
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if(e.getButton() == MouseEvent.BUTTON1)
        			
        			PayPanel.card.show(PayPanel.card_panel, "���");
        			dispose();
        	
        	}
        });
                
        setSize(400,100);
        setResizable(false);
        setLocation(800, 400);
        setVisible(true);
	}
}
