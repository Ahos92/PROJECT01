package project.five.pos.manage;

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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import project.five.pos.db.DBManager;
import project.five.pos.manage.AddMenu;
//import swing.ExTableAddBtn.TableCell;

public class ProductManage extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JButton jBtnAddRow = null;
	private JButton jBtnSaveRow = null;
	private JButton jBtnEditRow = null;
	private JTable table;
	private JScrollPane scrollPane;
	private JPanel panel;
	private AddMenu addD;
	private UpdateMenu upD;
	

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
	private String colNames[] = {"No","�޴���","����","����","ī�װ�","����","����"};
	private DefaultTableModel model = new DefaultTableModel(colNames,0)
	{public boolean isCellEditable(int i, int c){ return false; }};


	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	
	public ProductManage() {
		panel = new JPanel();
		panel.setLayout(null); 
		table = new JTable(model);
		//table.setFont(new Font("Courier", Font.PLAIN, 20));
		table.getTableHeader().setReorderingAllowed(false); // �÷��� �̵� �Ұ�
		table.getTableHeader().setResizingAllowed(false); // �÷� ũ�� ���� �Ұ�
		//table.getColumnModel().getColumn(6).setCellRenderer(new TableCell());
		//table.getColumnModel().getColumn(6).setCellEditor(new TableCell());
		
		//table.addMouseListener(new JTableMouseListener());
		scrollPane = new JScrollPane(table);
		scrollPane.setSize(500,450);
		scrollPane.setLocation(100, 210);
		panel.add(scrollPane);
		add(panel);
		setTitle("Menu Management Page");
		setBounds(630, 180, 700, 800);
		select();
		initialize(); // �� ��ȭ
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	

	// ���콺�� Ŭ���� ���� ��ǥ�� ���ؼ� �� ���� ������
	public class JTableMouseListener implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent e) {

			JTable jtable = (JTable)e.getSource();
			int row = jtable.getSelectedRow();
			int col = jtable.getSelectedColumn();

			System.out.println(model.getValueAt(row, col));
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
		}
		@Override
		public void mousePressed(MouseEvent e) {
		}
		@Override
		public void mouseReleased(MouseEvent e) {
		}
	}

	private void select() {
		String sql = "SELECT * FROM product order by product_no";
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,"hr","1234");
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
						//,���� ��ư
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
		// ���̺� ���� �߰�
		jBtnAddRow = new JButton("�޴� �߰�");
		jBtnAddRow.setBounds(150, 100, 120, 40);
		jBtnAddRow.addActionListener(this);
		panel.add(jBtnAddRow);
		
		// ���̺� ���� ����
		jBtnEditRow = new JButton("�޴� ����");
		jBtnEditRow.setBounds(400, 100, 120, 40);
		jBtnEditRow.addActionListener(this);
		panel.add(jBtnEditRow);
				
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jBtnAddRow) {
			addD = new AddMenu(this, "�޴� �߰�");
			addD.setVisible(true);
			jBtnAddRow.requestFocus();
			model.setRowCount(0);
			select();
		} else if (e.getSource() == jBtnEditRow) {
			upD = new UpdateMenu(this, "�޴� ����");
			upD.setVisible(true);
			jBtnEditRow.requestFocus();
			model.setRowCount(0);
			select();
		}
	}
	
	



	public static void main(String[] args) {
		new ProductManage();
	}

}


