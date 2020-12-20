package project.five.pos.cart.btn.render;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class UpDonwBtnRender extends JButton implements TableCellRenderer {
	
	String UpDown;
	Font font = new Font("πŸ≈¡", Font.CENTER_BASELINE, 10);
	
	public UpDonwBtnRender(String UpDown) {
		this.UpDown = UpDown;
		setOpaque(true);
	}
	
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		setBackground(isSelected?table.getSelectionBackground():table.getBackground());
		setText(UpDown);	
		setFont(font);
	    return this;
	}
}
