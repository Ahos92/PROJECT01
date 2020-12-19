package project.five.pos.device.comp.btn.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import project.five.pos.db.PosDAO;
import project.five.pos.device.comp.dialog.LoadingDialog;

public class SettleAction implements ActionListener {

	PosDAO pos;
	JFrame frame;
	JDialog dialog;
	public SettleAction(JFrame frame, JDialog dialog) {
		this.frame = frame;
		this.dialog = dialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		pos = new PosDAO();		
		
		// pos.saveDailyAmount(); - 하루 매출 저장 
		
		// pos.deleteAmonthAgoDate(); - 30일지난 데이터 테이블에서 삭제	
		if (pos.saveDailyAmount() && pos.deleteAmonthAgoDate()) {
			new LoadingDialog(frame, "정산 중 ...", dialog);
			
		} else {
			JOptionPane.showMessageDialog(frame, "정상적인 처리가 되지 않았습니다!!", "처리 오류!", 
												JOptionPane.ERROR_MESSAGE);
			dialog.dispose();
		}
	
		// 시퀀스들 초기화 					 
		// 무결성 오류 / 하루에 보여주는 데이터에 관련이 있으면 pk안됨
		// 꼭 그렇지 않더라도 한달동안 가지고있을 데이터에 시퀀스가무한히 커짐	
	}

}
