package project.five.pos.payment.swing;

import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;

public class ResetMain {

	public ResetMain() {
	
		PayPanel.card.show(PayPanel.card_panel, "버튼");
		PayPanel.card_btn.setText("카드");
		PayPanel.card_btn.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		PayPanel.cash_btn.setEnabled(true);
			
		PayPanel.cash_btn.setText("현금");
		PayPanel.cash_btn.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		PayPanel.card_btn.setEnabled(true);
		
		PayPanel.main_card.show(PayPanel.main_center_panel, "결제후");	
	}
}
