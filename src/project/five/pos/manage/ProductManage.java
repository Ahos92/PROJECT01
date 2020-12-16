package project.five.pos.manage;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import project.five.pos.db.DBManager;
import project.five.pos.manage.AddMenu;
import project.five.pos.manage.UpdateMenu;
import project.five.pos.manage.DeleteMenu;

public class ProductManage extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JButton jBtnAddRow;
	private JButton jBtnEditRow;
	private JTable table;
	private JScrollPane scrollPane;
	private JPanel panel;
	private AddMenu addD;
	private UpdateMenu upD;
	private DeleteMenu delD;
	
	private String colNames[] = {"No","메뉴명","가격","수량","카테고리","구분","삭제"};
	private DefaultTableModel model = new DefaultTableModel(colNames,0)
	{public boolean isCellEditable(int row, int col) {
		switch(col) { // 삭제 버튼 row만 작동하고 나머지 col은 작동 불가(=수정불가) 
		case 6:
			return true;
		default:
			return false;
		}
	}};


	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
	public ProductManage() {
		panel = new JPanel();
		panel.setLayout(null); 
		table = new JTable(model);
		//table.setFont(new Font("Courier", Font.PLAIN, 20));
		table.getTableHeader().setReorderingAllowed(false); // 컬럼들 이동 불가
		table.getTableHeader().setResizingAllowed(false); // 컬럼 크기 조절 불가
		
		//table.addMouseListener(new JTableMouseListener());
		scrollPane = new JScrollPane(table);
		
		table.getColumnModel().getColumn(6).setCellRenderer(new TableCell());
		table.getColumnModel().getColumn(6).setCellEditor(new TableCell());
		
		scrollPane.setSize(500,450);
		scrollPane.setLocation(100, 210);
		panel.add(scrollPane);
		add(panel);
		setTitle("Menu Management Page");
		setBounds(630, 180, 700, 800);
		select();
		initialize(); // 값 변화
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void select() {
		String sql = "SELECT * FROM product order by product_no";
		try {
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				model.addRow(new Object[] {
						rs.getInt("product_no"),
						rs.getString("product_name"),
						rs.getInt("product_price"),
						rs.getInt("product_count"),
						rs.getString("product_category"),
						rs.getString("termsofcondition")
				});
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (SQLException e) {}
		}
	}
	
	
	private void initialize() {
		// 테이블에 한줄 추가
		jBtnAddRow = new JButton("메뉴 추가");
		jBtnAddRow.setBounds(150, 100, 120, 40);
		jBtnAddRow.addActionListener(this);
		panel.add(jBtnAddRow);
		
		// 테이블 정보 수정
		jBtnEditRow = new JButton("메뉴 수정");
		jBtnEditRow.setBounds(400, 100, 120, 40);
		jBtnEditRow.addActionListener(this);
		panel.add(jBtnEditRow);
				
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jBtnAddRow) {
			addD = new AddMenu(this, "메뉴 추가");
			addD.setVisible(true);
			jBtnAddRow.requestFocus();
			model.setRowCount(0);
			select();
		} else if (e.getSource() == jBtnEditRow) {
			upD = new UpdateMenu(this, "메뉴 수정");
			upD.setVisible(true);
			jBtnEditRow.requestFocus();
			model.setRowCount(0);
			select();
		}
	}
	
	class TableCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer{
		JButton del;
		
		public TableCell() {
			
			del = new JButton("삭제");
			del.addActionListener(e -> {
				int result = JOptionPane.showConfirmDialog(null, "정말 삭제하시겠습니까?",
						"Delete Menu",JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION) {
					int row = table.getSelectedRow();
					if(row == -1)
						row+=1;
					System.out.println(row);
					System.out.println(model.getValueAt(row, 0).toString());
					delD = new DeleteMenu(model.getValueAt(row, 0).toString());
					model.setRowCount(0);
					select();
				}
			});
		}
		@Override
		public Object getCellEditorValue() {
			return null;
		}
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			return del;
		}
		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			return del;
		}
		
	}
	
	
	public static void main(String[] args) {
		new ProductManage();
	}

}


