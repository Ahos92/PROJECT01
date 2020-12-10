package project.five.pos.device.actionbtn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.graalvm.compiler.lir.LIRInstruction.Def;

import project.five.pos.device.InqTableModel;

public class RepaintAction implements ActionListener {

	String btn_text;
	JComboBox<String> select_column;
	JTextField select_data;
	DefaultTableModel old_dtm;
	DefaultTableModel new_dtm;
	
	public RepaintAction(String btn_text, DefaultTableModel old_dtm,
					JComboBox<String> select_column, JTextField select_data) {
		this.btn_text = btn_text;
		this.old_dtm = old_dtm;
		this.select_column = select_column;
		this.select_data = select_data;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("검색")) {
			new_dtm = new InqTableModel(btn_text, select_column, select_data);
			
		} else {
			new_dtm = new InqTableModel(btn_text);			
		}
		
		// 정렬 구현안됨
		((InqTableModel)new_dtm).changeData(old_dtm, new_dtm);	
	}
}
