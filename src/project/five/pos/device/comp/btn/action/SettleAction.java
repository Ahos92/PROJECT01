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
		
		// pos.saveDailyAmount(); - �Ϸ� ���� ���� 
		
		// pos.deleteAmonthAgoDate(); - 30������ ������ ���̺��� ����	
		if (device.saveDailyAmount() && device.deleteAmonthAgoDate()) {
			new LoadingDialog(frame, "���� �� ...", dialog);
			
		} else {
			JOptionPane.showMessageDialog(frame, "�������� ó���� ���� �ʾҽ��ϴ�!!", "ó�� ����!", 
												JOptionPane.ERROR_MESSAGE);
			dialog.dispose();
		}

	}

}
