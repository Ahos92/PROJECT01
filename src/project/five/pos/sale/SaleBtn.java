package project.five.pos.sale;

import javax.swing.JButton;

public class SaleBtn extends JButton {

	String name;
	
	public SaleBtn(String name, int size) {
		super.setText(name);
		super.setSize(size, size);
		
		if (name.equals("����")) {			
		
		} else if (name.equals("���")) {
		
		} else {
			System.err.println("���� / ��� �ܿ� ���� �̱���");
		}
		
	}
	
	
	
}
