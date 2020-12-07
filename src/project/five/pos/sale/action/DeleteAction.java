package project.five.pos.sale.action;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DeleteAction extends DefaultCellEditor {
	protected JButton button;

	private boolean isPushed;

	JTable table;
	DefaultTableModel dtm;

	public DeleteAction(JCheckBox checkBox, JTable table, DefaultTableModel dtm) {
		super(checkBox);
		this.table = table;
		this.dtm = dtm;
		button = new JButton();
		button.setOpaque(true);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				dtm.removeRow(row);
				/*
				 * 버튼 사용 중지 알아내서 버그 수정하기
				 */
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
			System.err.println("버튼 사용 중지");
		}
	}
}

