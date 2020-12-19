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
		
		// pos.saveDailyAmount(); - �Ϸ� ���� ���� 
		
		// pos.deleteAmonthAgoDate(); - 30������ ������ ���̺��� ����	
		if (pos.saveDailyAmount() && pos.deleteAmonthAgoDate()) {
			new LoadingDialog(frame, "���� �� ...", dialog);
			
		} else {
			JOptionPane.showMessageDialog(frame, "�������� ó���� ���� �ʾҽ��ϴ�!!", "ó�� ����!", 
												JOptionPane.ERROR_MESSAGE);
			dialog.dispose();
		}
	
		// �������� �ʱ�ȭ 					 
		// ���Ἲ ���� / �Ϸ翡 �����ִ� �����Ϳ� ������ ������ pk�ȵ�
		// �� �׷��� �ʴ��� �Ѵ޵��� ���������� �����Ϳ� �������������� Ŀ��	
	}

}
