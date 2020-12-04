package project.five.pos.sale;

import javax.swing.JButton;

public class SaleBtn extends JButton {

	String name;
	
	public SaleBtn(String name, int size) {
		super.setText(name);
		super.setSize(size, size);
		
		if (name.equals("결제")) {			
		
		} else if (name.equals("취소")) {
		
		} else {
			System.err.println("결제 / 취소 외에 아직 미구현");
		}
		
	}
	
	
	
}
