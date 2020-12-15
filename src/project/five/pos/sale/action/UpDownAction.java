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
	
	public UpDownAction(JCheckBox check_box, JTable table, String text) {
		super(check_box);
		this.table = table;
		button = new JButton(text);
		button.setOpaque(true);
	
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				int cnt_col = 2;
				int price_col = 5;
				int product_cnt = (int)table.getValueAt(row, cnt_col);
				int result_price = (int)table.getValueAt(row, price_col) / product_cnt;

				if (button.getText().equals("▲")) {
			
					table.setValueAt(++product_cnt, row, cnt_col);
					table.setValueAt(product_cnt * result_price, row, price_col);
					
				} else if (button.getActionCommand().equals("▼")) {
	
					if (product_cnt <= 1) {
						System.err.println("최소 1개이상 주문입니다!");
					}else {
						
						table.setValueAt(--product_cnt, row, cnt_col);
						table.setValueAt(product_cnt * result_price, row, price_col);
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


