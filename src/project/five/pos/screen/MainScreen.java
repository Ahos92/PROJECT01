package project.five.pos.screen;

import javax.swing.JFrame;
import javax.swing.JPanel;

import project.five.pos.sale.OrderDisplay;

public class MainScreen extends JFrame {

	// javaSwing

	// ī�װ� ��
	// ��ǰ, sold out ǥ��
	//  - Panel - GridLayout (��, ��, ����, ������)
	//  - Button (ActionListener ��ǰ���̺� update , sold out�� ��ư�׸� �ٲٱ�)


	// ����,��� ��ư
	// - Panel - FlowLayout.SOUTH
	// - ���� commit
	// - ��� rollback
	public MainScreen() {

	}

	public static void main(String[] args) {
		new MainScreen();
	}
}
