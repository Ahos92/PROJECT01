package project.five.pos.manage;

import java.awt.Rectangle;
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



public class TestCode extends JFrame{

	private static final long serialVersionUID = 1L;
	private JButton jBtnAddRow = null;
	private JButton jBtnSaveRow = null;
	private JButton jBtnEditRow = null;
	private JButton jBtnDelRow = null;
	private JTable table;
	private JScrollPane scrollPane;
	private JPanel panel;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
	private String colNames[] = {"No","�޴���","����","����","ī�װ�","����","����"};
	private DefaultTableModel model = new DefaultTableModel(colNames,0);

	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public TestCode() {
		panel = new JPanel();
		panel.setLayout(null);
		table = new JTable(model);
		//table.addMouseListener(new JTableMouseListener());
		scrollPane = new JScrollPane(table);
		scrollPane.setSize(500,200);
		panel.add(scrollPane);
		initialize();
		select();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		add(panel);
		setSize(540, 400);
		setVisible(true);
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
		jBtnAddRow = new JButton();
		jBtnAddRow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getActionCommand());
				DefaultTableModel model2 = (DefaultTableModel) table.getModel();
				model2.addRow(new String[] {"","","","","",""});
			}
		});
		jBtnAddRow.setBounds(30, 222, 120, 25);
		jBtnAddRow.setText("�߰�");
		panel.add(jBtnAddRow);

		// ���̺� ���� ����
		jBtnSaveRow = new JButton();
		jBtnSaveRow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getActionCommand());
				DefaultTableModel model2 = (DefaultTableModel) table.getModel();
				int row = table.getSelectedRow();
				if(row<0) return; // ������ �ȵ� ���¶�� -1����
				String sql = "insert into product values("
						+ "product_seq.nextval,?,?,?,?,?)";

				try {
					Class.forName(driver);
					con = DriverManager.getConnection(url,"hr","1234");
					pstmt = con.prepareStatement(sql);

					pstmt.setString(1, String.valueOf(model2.getValueAt(row, 1)));
					pstmt.setInt(2, Integer.valueOf(model2.getValueAt(row, 2).toString()));
					pstmt.setInt(3, Integer.valueOf(model2.getValueAt(row, 3).toString()));
					//pstmt.setNull(3, (int) model2.getValueAt(row, 3));
					pstmt.setString(4, String.valueOf(model2.getValueAt(row, 4)));
					pstmt.setNString(5, (String)model2.getValueAt(row, 5));

					int cnt = pstmt.executeUpdate();

				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				}finally {
					try {
						pstmt.close();
						con.close();
					} catch (Exception e2) {}
				}
				model2.setRowCount(0); // ��ü ���̺� ȭ���� ������
				select(); // ������ �ٽ� ��ü���� �޾ƿ�
			}
		});
		jBtnSaveRow.setBounds(182, 222, 120, 25);
		jBtnSaveRow.setText("����");
		panel.add(jBtnSaveRow);

		// ���õ� ���̺��� ���� �����ϴ� �κ�
		jBtnEditRow = new JButton();
		jBtnEditRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.out.println(e.getActionCommand());
				DefaultTableModel model2 = (DefaultTableModel) table.getModel();
				int row = table.getSelectedRow();
				if(row<0) return; // ������ �ȵ� ���¸� -1����

				String sql = "UPDATE product SET product_price=?, product_count=?"
						+ " WHERE product_no=?";

				try {
					Class.forName(driver);
					con = DriverManager.getConnection(url,"hr","1234");
					pstmt = con.prepareStatement(sql);

					pstmt.setInt(1, Integer.valueOf(model2.getValueAt(row, 2).toString()));
					pstmt.setInt(2, Integer.valueOf(model2.getValueAt(row, 3).toString()));
					pstmt.setString(3, String.valueOf(model2.getValueAt(row, 0)));

					int cnt = pstmt.executeUpdate();
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				} finally {
					try {
						pstmt.close();
						con.close();
					} catch (Exception e2) {}
				}
				model2.setRowCount(0); // ��ü ���̺� ȭ�� ������
				select(); // ���� �� ���� �ٽ� �޾ƿ�
			}
		});
		jBtnEditRow.setBounds(182, 270, 120, 25);
		jBtnEditRow.setText("����");
		panel.add(jBtnEditRow);

		// ���õ� ���̺� ���� �����ϴ� �κ�
		jBtnDelRow = new JButton();
		jBtnDelRow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getActionCommand());
				DefaultTableModel model2 = (DefaultTableModel) table.getModel();
				int row = table.getSelectedRow();
				if(row<0) return; // ������ �ȵ� ���¸� -1����
				String sql = "DELETE FROM PRODUCT WHERE product_no=?";

				try {
					Class.forName(driver);
					con = DriverManager.getConnection(url,"hr","1234");
					pstmt = con.prepareStatement(sql);

					pstmt.setString(1, (String) model2.getValueAt(row, 0));
					int cnt = pstmt.executeUpdate();

				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				} finally {
					try {
						pstmt.close();
						con.close();
					} catch (Exception e2) {}
				}
				model2.removeRow(row); // ���̺� ���� ���� ����
			}
		});
		jBtnDelRow.setBounds(new Rectangle(320, 222, 120, 25));
		jBtnDelRow.setText("����");
		panel.add(jBtnDelRow);
	}
	
	public static void main(String[] args) {
		new TestCode();
	}
}
