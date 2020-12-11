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
		
		setTitle("멤버쉽 확인");
		
        JPanel NewWindowContainer = new JPanel(new GridLayout(2,1,1,1));
        
        setContentPane(NewWindowContainer);
        
        JLabel NewLabel = new JLabel("멤버쉽 정보가 확인 되었습니다.");
        JButton recognize = new JButton("체크");
       
        JPanel mem = new JPanel(new BorderLayout(0, 0));
        mem.setOpaque(false);
        iamMem = new JLabel(CheckMem.memberName + "님 반갑습니다.\n오늘도 좋은 하루 되세요", JLabel.CENTER);
        iamMem.setFont(new Font("Serif", Font.BOLD, 14));
        mem.add(iamMem);
        
        PayPanel.card_panel.add("멤버", mem);
        
        iamMem.setForeground(Color.WHITE);
         
		recognize.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton) e.getSource();
					if(btn.getText().equals("체크")) {
						PayPanel.card.show(PayPanel.card_panel, "멤버");
						
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
