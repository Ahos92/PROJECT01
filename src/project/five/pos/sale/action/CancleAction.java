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
		
		// 주문내역 테이블 초기화
		dtm.setNumRows(0);
		
		try {
			
			// 화면전환 생성자 받았을 때 적용
			presentFrame.setVisible(false);
			pastFrame.setVisible(true);
			
		} catch (NullPointerException npe) {
			
			// 테이블변수 생성자 받았을 때 적용
			System.err.println("장바구니 초기화!");
		}
	}

}
