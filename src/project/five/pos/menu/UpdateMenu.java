package project.five.pos.menu;

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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import project.five.pos.db.DBManager;

public class UpdateMenu extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;

	static Connection con;
	static PreparedStatement pstmt;
	static ResultSet rs;
	
	
	private int TextX = 100;
	private JTextField[] rows = new JTextField[3];
	
	private JPanel panel;
	private JLabel title,list;
    private JButton saveB, exitB;
	
	UpdateMenu(JFrame owner, String str){
		super(owner, str, true);
		
		panel = new JPanel();
		panel.setLayout(null);
        add(panel);
        
        title = new JLabel("[ UPDATE MENU ]");
        title.setBounds(200, 10, 250, 50);
        title.setFont(new Font("Courier",Font.PLAIN,20));
        title.setForeground (Color.GRAY);
        panel.add(title);
        
        list = new JLabel("  메뉴이름                                가격                                      수량");
        list.setBounds(120, 80, 500, 30);
        panel.add(list);
        
        for(int i = 0; i<3;i++) {
        	rows[i] = new JTextField(10);
        	rows[i].setBounds(TextX, 105, 100, 35);
        	panel.add(rows[i]);
        	TextX+=140;
        }

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
			update();
			try {
			for(int i=0;i<5;i++) {
				rows[i].setText("");
			}
			}catch(IndexOutOfBoundsException syserr){
				
			}
			
		} else if(e.getSource() == exitB) {
			dispose(); //다이얼로그 제거
		}
	}
	
	public void update() {
		String sql = "UPDATE product SET product_price=?, product_count=?"
				+ " WHERE product_name=?";
		
		try {
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, Integer.valueOf(rows[1].getText()));
			pstmt.setInt(2, Integer.valueOf(rows[2].getText()));
			pstmt.setString(3, rows[0].getText());
			
			int cnt = pstmt.executeUpdate();
			
			System.out.printf("%d행이 변경되었습니다.\n", cnt);
			
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (Exception e2) {}
		}
	}
}
