package project.five.pos.device.comp.btn.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import project.five.pos.device.DeviceDAO;
import project.five.pos.device.comp.dialog.LoadingDialog;

public class SettleAction implements ActionListener {

	DeviceDAO device;
	
	JFrame frame;
	JDialog dialog;
	public SettleAction(JFrame frame, JDialog dialog) {
		this.frame = frame;
		this.dialog = dialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		device = new DeviceDAO();		
		
		// pos.saveDailyAmount(); - 하루 매출 저장 
		
		// pos.deleteAmonthAgoDate(); - 30일지난 데이터 테이블에서 삭제	
		if (device.saveDailyAmount() && device.deleteAmonthAgoDate()) {
			new LoadingDialog(frame, "정산 중 ...", dialog);
			
		} else {
			JOptionPane.showMessageDialog(frame, "정상적인 처리가 되지 않았습니다!!", "처리 오류!", 
												JOptionPane.ERROR_MESSAGE);
			dialog.dispose();
		}

	}

}
