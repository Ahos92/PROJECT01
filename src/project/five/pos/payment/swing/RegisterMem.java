package project.five.pos.payment.swing;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RegisterMem extends JFrame{

	public RegisterMem() {
		setTitle("����� ���� â");
		// ����, ���⼭ setDefaultCloseOperation() ���Ǹ� ���� ���ƾ� �Ѵ�
        // �����ϰ� �Ǹ� �� â�� ������ ��� â�� ���α׷��� ���ÿ� ������
        
        JPanel NewWindowContainer = new JPanel();
        setContentPane(NewWindowContainer);
        
        JLabel NewLabel = new JLabel("����� ���� ���Ǵ� ī���Ϳ��� �̿����ּ���. �����մϴ�.");
        
        NewWindowContainer.add(NewLabel);
        
        
        setSize(400,100);
        setResizable(false);
        setLocation(800, 400);
        setVisible(true);

	}
}
