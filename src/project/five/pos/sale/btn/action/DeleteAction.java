package project.five.pos.sale.btn.action;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import project.five.pos.sale.SaleDisplay;

public class DeleteAction extends DefaultCellEditor {
	protected JButton button;

	private boolean isPushed;
	
	JTable table;
	DefaultTableModel dtm;
	JFrame frame;
	
	public DeleteAction(JCheckBox checkBox, JTable table, DefaultTableModel dtm, JFrame frame) {
		super(checkBox);
		this.table = table;
		this.dtm = dtm;
		this.frame = frame;
		button = new JButton();
		button.setOpaque(true);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				dtm.removeRow(row);
				if (dtm.getRowCount() == 0) {
					new SaleDisplay();
					frame.dispose();
					System.err.println("모든 항목이 취소 되었습니다!");
				}
				fireEditingStopped();
			}
		});
	}

	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		if (isSelected) {
			button.setForeground(table.getSelectionForeground());
			button.setBackground(table.getSelectionBackground());
		} else {
			button.setForeground(table.getForeground());
			button.setBackground(table.getBackground());
		}
		button.setText("X");
		isPushed = true;
		return button;
	}

	public Object getCellEditorValue() {
		isPushed = false;
		return null;
	}

	public boolean stopCellEditing() {
		isPushed = false;
		return super.stopCellEditing();
	}

	protected void fireEditingStopped() {
		try {
			super.fireEditingStopped();
			
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("해당 품목 취소!");
		}
	}
}

