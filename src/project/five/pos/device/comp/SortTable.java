package project.five.pos.device.comp;

import java.util.Comparator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class SortTable {

	public SortTable(JTable table) {
		TableRowSorter sorter = new TableRowSorter<>();
		Comparator<Integer> comparator = new IntegerComp();
		sorter.setModel(table.getModel());
		for (int i = 0; i < table.getColumnCount(); i++) {		
			char ch = table.getValueAt(1, i).toString().charAt(0);
			if (ch >= '0' && ch <= '9') {
			  sorter.setComparator(i, comparator);
			}
		};
		
		table.setRowSorter(sorter);
	}
	
}
