package project.five.pos.payment.swing;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Incorrect extends JFrame{

	public Incorrect() {
		
		setTitle("����� ����");
		// ����, ���⼭ setDefaultCloseOperation() ���Ǹ� ���� ���ƾ� �Ѵ�
        // �����ϰ� �Ǹ� �� â�� ������ ��� â�� ���α׷��� ���ÿ� ������
        
        JPanel NewWindowContainer = new JPanel();
        setContentPane(NewWindowContainer);
        
        JLabel NewLabel = new JLabel("����� ������ �ùٸ��� �ʽ��ϴ�. �ٽ� �ѹ� Ȯ�����ּ���");
        
        NewWindowContainer.add(NewLabel);
        
        
        setSize(400,100);
        setResizable(false);
        setLocation(800, 400);
        setVisible(true);
	}
}
