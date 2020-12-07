package project.five.pos.sale.render;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

public class DeleteBtnRender extends JButton implements TableCellRenderer {

	Font font = new Font("±Ã¼­", Font.PLAIN, 10);
	
	public DeleteBtnRender() {
		setOpaque(true);
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		setBackground(isSelected?table.getSelectionBackground():table.getBackground());
		setText("X");
		setFont(font);
		return this;
	}
}


