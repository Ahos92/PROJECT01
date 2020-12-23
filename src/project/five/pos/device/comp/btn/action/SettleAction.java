package project.five.pos.device.comp.btn.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import project.five.pos.device.DeviceDAO;
import project.five.pos.device.LoadingPopUpDisplay;

public class SettleAction implements ActionListener {

	DeviceDAO device;
	
	JFrame frame;
	JDialog dialog;
	String device_id;
	
	public SettleAction(JFrame frame, JDialog dialog, String device_id) {
		this.frame = frame;
		this.dialog = dialog;
		this.device_id = device_id;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		device = new DeviceDAO();		
			
		if (device.saveDailyAmount(device_id) && device.deleteAmonthAgoDate()) {
			new LoadingPopUpDisplay(frame, "���� �� ...", dialog);
			
		} else {
			JOptionPane.showMessageDialog(frame
										, "�������� ó���� ���� �ʾҽ��ϴ�!! \n���翡 ���� ���ּ��� 02-0000-0000"
										, "ó�� ����!"
										, JOptionPane.ERROR_MESSAGE
			);	
			dialog.dispose();
		}

	}

}
