package project.five.pos.sale.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import project.five.pos.sale.SaleDTO;

public class CancleAction implements ActionListener {
		
	JFrame presentFrame;
	JFrame pastFrame;
	
	DefaultTableModel dtm;
	
	public CancleAction(JFrame presentFrame, JFrame pastFrame, DefaultTableModel dtm) {
		this.presentFrame = presentFrame;
		this.pastFrame = pastFrame;
		this.dtm = dtm;
	}

	
	public CancleAction(DefaultTableModel dtm) {
		this.dtm = dtm;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		// �ֹ����� ���̺� �ʱ�ȭ
		dtm.setNumRows(0);
		
		try {
			
			// ȭ����ȯ ������ �޾��� �� ����
			presentFrame.setVisible(false);
			pastFrame.setVisible(true);
			
		} catch (NullPointerException npe) {
			
			// ���̺��� ������ �޾��� �� ����
			System.err.println("��ٱ��� �ʱ�ȭ!");
		}
	}

}
