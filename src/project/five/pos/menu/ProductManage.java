package project.five.pos.menu;

import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.plaf.synth.SynthScrollBarUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import project.five.pos.TestSwingTools;
//import project.five.pos.device.comp.btn.DeviceBtn;
import project.five.pos.db.DBManager;
import project.five.pos.device.comp.btn.DeviceBtn;
import project.five.pos.device.comp.btn.action.ChangeFrameAction;
import project.five.pos.menu.AddMenu;
import project.five.pos.menu.UpdateMenu;
import project.five.pos.menu.DeleteMenu;

public class ProductManage extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JButton jBtnAddRow;
	private JButton jBtnEditRow; 
	private JButton search;
	private JComboBox<String> catego;
	private JTextField menuName;
	private JButton toMain; // 추가
	private JTable table;
	private JScrollPane scrollPane;
	private JPanel panel;
	private AddMenu addD;
	private UpdateMenu upD;
	private DeleteMenu delD;
	
	private String[] category = {"전체","Coffee","Tea","Deserts","Frappe"};
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
	
//	private Image background =new ImageIcon(
//			"C:\\Users\\손지원\\git\\PROJECT01\\src\\project\\five\\pos\\manage\\tree.png").getImage();
//	
	JFrame pf;
	public ProductManage(JFrame frame, String name) throws IOException {
		super(frame, name);
		this.pf = frame;
		setSize(500, 760);
		setLocationRelativeTo(null);
		setResizable(false);
		setModal(true);
		
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
		
		scrollPane.setSize(460,450);
		scrollPane.setLocation(10, 260);
		panel.add(scrollPane);
		add(panel);
		
		
		toMain = new DeviceBtn("", 100, new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		toMain.setIcon(new ImageIcon(ImageIO
				.read(new File("assets/images/home-page.png"))
				.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		toMain.setBounds(20, 10, 60, 60);
		panel.add(toMain);
		
		JLabel title = new JLabel("Menu Table");
		title.setFont(new Font("Courier",Font.BOLD,25));
		title.setBounds(173, 50, 200, 50);
		panel.add(title);
		
		
		
		catego = new JComboBox<String>(category);
		catego.setBounds(30, 205, 100, 30);
		panel.add(catego);
		
		menuName = new JTextField(30);
		menuName.setBounds(145, 205, 250, 30);
		panel.add(menuName);
		
		search = new JButton();
		search.setIcon(new ImageIcon(ImageIO
					.read(new File("assets/images/search.png"))
					.getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
		search.setSize(40, 40);
		search.setLocation(420, 200);
		search.addActionListener(this);
		panel.add(search);
		
		select();
		initialize(); // 값 변화
		
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
//	public void paint(Graphics g) {//그리는 함수
//		g.drawImage(background, 0, 0, null);//background를 그려줌
//	}

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
			System.out.println(e.toString());
		}finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (SQLException e) {}
		}
	}
	
	private void search(String cate, String Mname) {
		System.out.println("'"+cate+"'" + " '"+Mname+"'");
		
		try {
			con = DBManager.getConnection();
			if(cate == null || cate == "") {
				pstmt = con.prepareStatement("select * from product where product_name like '%'||?||'%'");
				pstmt.setString(1, Mname);
			} else {
				pstmt = con.prepareStatement("SELECT * FROM product where product_category=?"
						+ " and product_name like '%'||?||'%'");
				pstmt.setString(1, cate);
				pstmt.setString(2, Mname);
			}
			
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
			e.printStackTrace();
			System.out.println("ㅠㅠ"+e);
		}finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (SQLException e) {System.out.println(e.toString());}
		}
	}
	
	
	private void initialize() {
		// 테이블에 한줄 추가
		jBtnAddRow = new JButton("메뉴 추가");
		jBtnAddRow.setBounds(60, 130, 120, 40);
		jBtnAddRow.addActionListener(this);
		panel.add(jBtnAddRow);
		
		// 테이블 정보 수정
		jBtnEditRow = new JButton("메뉴 수정");
		jBtnEditRow.setBounds(305, 130, 120, 40);
		jBtnEditRow.addActionListener(this);
		panel.add(jBtnEditRow);
				
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jBtnAddRow) {
			addD = new AddMenu(this.pf, "메뉴 추가");
			addD.setVisible(true);
			jBtnAddRow.requestFocus();
			model.setRowCount(0);
			select();
		} else if (e.getSource() == jBtnEditRow) {
			upD = new UpdateMenu(this.pf, "메뉴 수정");
			upD.setVisible(true);
			jBtnEditRow.requestFocus();
			model.setRowCount(0);
			select();
		} else if(e.getSource() == search) {
			model.setRowCount(0);
			if(catego.getSelectedItem().toString().equals("전체")) {
				select();
			}else {
				search(catego.getSelectedItem().toString(),
						menuName.getText().toString());
			}
			
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

}


