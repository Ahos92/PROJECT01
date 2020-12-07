package project.five.pos.sale.action;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class UpDownAction extends DefaultCellEditor {
	protected JButton button;

	private boolean isPushed;

	JTable table;

	String text;
	
	public UpDownAction(JCheckBox checkBox, JTable table, String text) {
		super(checkBox);
		this.table = table;
		button = new JButton(text);
		button.setOpaque(true);
	
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				int cntCol = 2;
				int priceCol = 5;
				int product_cnt = (int)table.getValueAt(row, cntCol);
				int price = (int)table.getValueAt(row, priceCol) / product_cnt;

				if (button.getText().equals("▲")) {
			
					table.setValueAt(++product_cnt, row, cntCol);
					table.setValueAt(product_cnt * price, row, priceCol);
					
				} else if (button.getActionCommand().equals("▼")) {
	
					if (product_cnt <= 1) {
						System.err.println("최소 1개이상 주문입니다!");
					}else {
						
						table.setValueAt(--product_cnt, row, cntCol);
						table.setValueAt(product_cnt * price, row, priceCol);
					}
					
				}
				//버튼 사용 중지 알아내서 버그 수정하기
				fireEditingStopped();
			}
		});
	}

	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		button.setBackground(isSelected?table.getSelectionBackground():table.getBackground());
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


