package project.five.pos.cart.btn.action;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import project.five.pos.menu.MenuDisplay;

public class DeleteAction extends DefaultCellEditor {
	protected JButton button;

	private boolean isPushed;
	
	JTable table;
	DefaultTableModel dtm;
	JDialog cart;
	
	public DeleteAction(JCheckBox checkBox, JTable table, DefaultTableModel dtm, JDialog cart) {
		super(checkBox);
		this.table = table;
		this.dtm = dtm;
		this.cart = cart;
		button = new JButton();
		button.setOpaque(true);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				dtm.removeRow(row);
				if (dtm.getRowCount() == 0) {
//					new MenuDisplay();
					cart.dispose();
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

