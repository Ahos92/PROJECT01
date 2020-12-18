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
	
		// pos.saveDailyAmount(); - �Ϸ� ���� ���� 
		
		// 30������ ������ ���̺��� ���� (12/17 ���� cart table�� ����)	
		//	pos.deleteAmonthAgoDate(); - payment ������ �߰�
		
		if (pos.saveDailyAmount() && pos.deleteAmonthAgoDate()) {
			// ���� �� �Դϴ� �˾�â Ȯ�� ������ ���α׷� ����
			new LoadingDialog(frame, "���� �� ...", dialog);
			
		} else {
			// �������� ó���� ���� �ʾҽ��ϴ�. �˾�â �ϳ� ����
			dialog.dispose();
		}
	
		// �������� �ʱ�ȭ 					 
		// ���Ἲ ���� / �Ϸ翡 �����ִ� �����Ϳ� ������ ������ pk�ȵ�
		// �� �׷��� �ʴ��� �Ѵ޵��� ���������� �����Ϳ� �������������� Ŀ��	
	}

}
