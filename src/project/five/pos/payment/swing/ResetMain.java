package project.five.pos.payment.swing;

import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;

public class ResetMain {

	public ResetMain() {
	
		PayPanel.card.show(PayPanel.card_panel, "��ư");
		PayPanel.card_btn.setText("ī��");
		PayPanel.card_btn.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		PayPanel.cash_btn.setEnabled(true);
			
		PayPanel.cash_btn.setText("����");
		PayPanel.cash_btn.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		PayPanel.card_btn.setEnabled(true);
		
		PayPanel.main_card.show(PayPanel.main_center_panel, "������");	
	}
}
