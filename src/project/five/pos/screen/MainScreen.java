package project.five.pos.screen;

import javax.swing.JFrame;
import javax.swing.JPanel;

import project.five.pos.sale.OrderDisplay;

public class MainScreen extends JFrame {

	// javaSwing

	// 카테고리 탭
	// 상품, sold out 표시
	//  - Panel - GridLayout (행, 열, 수평갭, 수직갭)
	//  - Button (ActionListener 상품테이블 update , sold out시 버튼그림 바꾸기)


	// 결제,취소 버튼
	// - Panel - FlowLayout.SOUTH
	// - 결제 commit
	// - 취소 rollback
	public MainScreen() {

	}

	public static void main(String[] args) {
		new MainScreen();
	}
}
