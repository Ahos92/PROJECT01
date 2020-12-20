package project.five.pos.cart.btn.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import project.five.pos.menu.MenuDisplay;

public class CancleAction implements ActionListener {
		
	JFrame present_frame;
	
	DefaultTableModel dtm;
	
	public CancleAction(JFrame present_frame, DefaultTableModel dtm) {
		this.present_frame = present_frame;
		this.dtm = dtm;
	}

	@Override
	public void actionPerformed(ActionEvent e) {	
		dtm.setNumRows(0);
		try {
			new MenuDisplay();
			present_frame.dispose();
			
		} catch (NullPointerException npe) {
			System.err.println("장바구니 초기화!");
		}
	}

}
