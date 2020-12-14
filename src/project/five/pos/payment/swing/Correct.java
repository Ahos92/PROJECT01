package project.five.pos.payment.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
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
		
        JPanel NewWindowContainer = new JPanel(new GridLayout(2,1,1,1));
        
        setContentPane(NewWindowContainer);
        
        JLabel NewLabel = new JLabel("����� ������ Ȯ�� �Ǿ����ϴ�.");
        JButton recognize = new JButton("üũ");
       
        JPanel mem = new JPanel(new BorderLayout(0, 0));
        mem.setOpaque(false);
        iamMem = new JLabel(CheckMem.memberName + "�� �ݰ����ϴ�.\n���õ� ���� �Ϸ� �Ǽ���", JLabel.CENTER);
        iamMem.setFont(new Font("Serif", Font.BOLD, 14));
        mem.add(iamMem);
        
        PayPanel.card_panel.add("���", mem);
        
        iamMem.setForeground(Color.WHITE);
         
		recognize.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton) e.getSource();
					if(btn.getText().equals("üũ")) {
						PayPanel.card.show(PayPanel.card_panel, "���");
						
						dispose();				
				}
			}
			
		});
		
        NewWindowContainer.add(NewLabel);
        NewWindowContainer.add(recognize);
        
        
        setSize(400,100);
        setResizable(false);
        setLocation(800, 400);
        setVisible(true);
	}
}
