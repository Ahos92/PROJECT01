package project.five.pos.payment.swing;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Incorrect extends JFrame{

	public Incorrect() {
		
		setTitle("멤버쉽 오류");
		// 주의, 여기서 setDefaultCloseOperation() 정의를 하지 말아야 한다
        // 정의하게 되면 새 창을 닫으면 모든 창과 프로그램이 동시에 꺼진다
        
        JPanel NewWindowContainer = new JPanel();
        setContentPane(NewWindowContainer);
        
        JLabel NewLabel = new JLabel("멤버쉽 정보가 올바르지 않습니다. 다시 한번 확인해주세요");
        
        NewWindowContainer.add(NewLabel);
        
        
        setSize(400,100);
        setResizable(false);
        setLocation(800, 400);
        setVisible(true);
	}
}
