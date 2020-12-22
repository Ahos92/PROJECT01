package project.five.pos.manage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import project.five.pos.db.DBManager;

public class AddMenu extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private Connection con;
	private PreparedStatement pstmt;
	
	private int TextX = 20;
	private JTextField[] rows = new JTextField[3];
	private String[] category = {"","Coffee","Tea","Deserts","Frappe"};
	private String[] condition = {"","ICE","HOT"};
	
	private JPanel panel;
    private JLabel title,list;
    private JButton saveB, exitB;
    private JComboBox<String> categ, condi;
	
	AddMenu(JFrame owner,String str){
		super(owner,str,true);
		
		panel = new JPanel();
		panel.setLayout(null);
        add(panel);
        
        title = new JLabel("[ ADD MENU ]");
        title.setBounds(225, 10, 250, 50);
        title.setFont(new Font("Courier",Font.PLAIN,20));
        title.setForeground (Color.GRAY);
        panel.add(title);
        
        list = new JLabel(" 메뉴이름                        가격                            수량                        카테고리                      구분");
        list.setBounds(40, 80, 500, 30);
        panel.add(list);
        
        for(int i = 0; i<3;i++) {
        	rows[i] = new JTextField(10);
        	rows[i].setBounds(TextX, 105, 100, 35);
        	panel.add(rows[i]);
        	TextX+=110;
        }
        
        categ = new JComboBox<String>(category);
        categ.setBounds(TextX, 105, 100, 35);
        panel.add(categ);
        
        condi = new JComboBox<String>(condition);
        condi.setBounds(TextX+110, 105, 100, 35);
        panel.add(condi);

        saveB = new JButton("SAVE");
        saveB.setBounds(150, 190, 100, 30);
        saveB.addActionListener(this);
        panel.add(saveB);
        
        exitB = new JButton("EXIT");
        exitB.setBounds(330, 190, 100, 30);
        exitB.addActionListener(this);
        panel.add(exitB);
        
        setBounds(680, 350, 600, 300);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                dispose(); //다이얼로그 제거
            }
        });
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == saveB) {
			add();
			for(int i=0;i<3;i++) {
				rows[i].setText("");
			}
		} else if(e.getSource() == exitB) {
			dispose(); //다이얼로그 제거
		}
	}
	
	public void add() {
		String sql = "insert into product values("
				+ "product_seq.nextval,?,?,?,?,?)";
		
		try {
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, rows[0].getText());
			pstmt.setInt(2, Integer.valueOf(rows[1].getText()));
			pstmt.setInt(3, Integer.valueOf(rows[2].getText()));
			pstmt.setString(4, categ.getSelectedItem().toString());
			pstmt.setNString(5, condi.getSelectedItem().toString());
			
			int cnt = pstmt.executeUpdate();
			
			System.out.printf("%d행이 추가되었습니다.\n", cnt);
			
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}finally {
			try {
				pstmt.close();
				con.close();
			} catch (Exception e2) {}
		}
	}
}
