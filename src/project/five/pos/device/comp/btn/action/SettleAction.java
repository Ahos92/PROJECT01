package project.five.pos.device.comp.btn.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import project.five.pos.db.PosDAO;
import project.five.pos.device.comp.dialog.LoadingDialog;
import project.five.pos.sale.CartDAO;

public class SettleAction implements ActionListener {

	PosDAO pos;
	CartDAO cart;

	JFrame frame;
	JDialog dialog;
	public SettleAction(JFrame frame, JDialog dialog) {
		this.frame = frame;
		this.dialog = dialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		cart = new CartDAO();
		pos = new PosDAO();		
	
		// pos.saveDailyAmount(); - 하루 매출 저장 
		
		// 30일지난 데이터 테이블에서 삭제 (12/17 기준 cart table만 적용)	
		//	pos.deleteAmonthAgoDate(); - payment 삭제도 추가
		
		if (pos.saveDailyAmount() && pos.deleteAmonthAgoDate()) {
			// 정산 중 입니다 팝업창 확인 누르면 프로그램 종료
			new LoadingDialog(frame, "정산 중 ...", dialog);
			
		} else {
			// 정상적인 처리가 되지 않았습니다. 팝업창 하나 생성
			dialog.dispose();
		}
	
		// 시퀀스들 초기화 					 
		// 무결성 오류 / 하루에 보여주는 데이터에 관련이 있으면 pk안됨
		// 꼭 그렇지 않더라도 한달동안 가지고있을 데이터에 시퀀스가무한히 커짐	
	}

}
