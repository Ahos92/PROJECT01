package project.five.pos.payment.swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import project.five.pos.db.DBManager;
import project.five.pos.payment.swing.btn.action.NumberField;

public class CheckMem extends JFrame {
	
	static Connection conn;
	static PreparedStatement ps;
	static ResultSet rs;
	
	String mobileNo ="";
	static String memberName ="";
	
	PayPanel main;
	
	public CheckMem(PayPanel main) {
		
		this.main = main;
		
		setTitle("멤버쉽 확인 창");
		
		JPanel chk_membership = new JPanel(new GridLayout(3,1,0,0));
		
		JPanel explain = new JPanel();
		JLabel explanation = new JLabel("멤버쉽 인증을 위해 멤버쉽 가입 할 때 이용했던 휴대폰 번호를 입력해주세요.");
		
		JPanel thr_txt = new JPanel(new FlowLayout());
		
		JTextField first_no = new JTextField("010");
		first_no.setEnabled(false);
		JLabel stick1 = new JLabel("-");
		JTextField middle_no = new JTextField(4);
		JLabel stick2 = new JLabel("-");
		JTextField last_no = new JTextField(4);
		
		middle_no.addKeyListener(new NumberField());
		last_no.addKeyListener(new NumberField());
		
		JPanel chk_mem = new JPanel(new FlowLayout());
		JButton yes_mem = new JButton("확인");
		JButton no_mem = new JButton("취소");
		
		JLabel iamMem = new JLabel("멤버임");
		
		// 다음 결제 프로세스 창
		yes_mem.addMouseListener(new MouseAdapter() {
					
				@Override
				public void mouseClicked(MouseEvent e) {
					
					mobileNo = "";
					if(e.getButton() == MouseEvent.BUTTON1) {
							mobileNo += first_no.getText();
							mobileNo += "-";
							mobileNo += middle_no.getText();
							mobileNo += "-";
							mobileNo += last_no.getText();
							
							//System.out.println(mobileNo);
							
							try {
								conn = DBManager.getConnection();

								ps = conn.prepareStatement("SELECT last_name, first_name FROM customer "
										+ "WHERE contact_no = ?");
								
								ps.setString(1, mobileNo);
								
								rs = ps.executeQuery();
								
								memberName = "";
								
								if(rs.next()) {
									
									do {
										memberName += rs.getString("last_name");
										memberName += rs.getString("first_name");
										
										
//										System.out.printf("%-15s%-10s\n",
//												rs.getString("last_name"),
//												rs.getString("first_name")
//												
//												);
										//System.out.println(memberName);
										
										new Correct(main);
								
										dispose();
									
									}while (rs.next()); 
										
								}
								else {
									new Incorrect();	
								}
								
								rs.close();
								ps.close();
								conn.close();
									

							} catch (SQLException ex) {
								ex.printStackTrace();
							}
							
					}
					
				}
			});
				
		// 결제 취소 버튼
		no_mem.addMouseListener(new MouseAdapter() {
					
				@Override
				public void mouseClicked(MouseEvent e) {
						
					if(e.getButton() == MouseEvent.BUTTON1) {
						dispose();
					}
				}
			});
		
		explain.add(explanation);
		
		thr_txt.add(first_no);
		thr_txt.add(stick1);
		thr_txt.add(middle_no);
		thr_txt.add(stick2);
		thr_txt.add(last_no);
		
		chk_mem.add(yes_mem);
		chk_mem.add(no_mem);
		
		
		
		
		chk_membership.add(explain);
		chk_membership.add(thr_txt);
		chk_membership.add(chk_mem);
		
		setVisible(true);
		//setLayout(null);
		setSize(500, 170);
		setLocation(1180, 500);
		
		this.add(chk_membership, BorderLayout.CENTER);
	}
	


	
}
