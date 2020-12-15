package project.five.pos.device.btn.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.graalvm.compiler.lir.LIRInstruction.Def;

import project.five.pos.device.table.LookUpTableModel;

public class TableRepaintAction implements ActionListener {

	String btn_text;
	JComboBox<String> selectColumn_box;
	JTextField selectData_tf;
	DefaultTableModel old_dtm, new_dtm;
	
	public TableRepaintAction(String btn_text, DefaultTableModel old_dtm,
					JComboBox<String> selectColumn_box, JTextField selectData_tf) {
		this.btn_text = btn_text;
		this.old_dtm = old_dtm;
		this.selectColumn_box = selectColumn_box;
		this.selectData_tf = selectData_tf;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("°Ë»ö")) {
			new_dtm = new LookUpTableModel(btn_text, selectColumn_box, selectData_tf);
			
		} else {
			new_dtm = new LookUpTableModel(btn_text);			
		}
	
		((LookUpTableModel)new_dtm).changeData(old_dtm, new_dtm);	
	}
}
