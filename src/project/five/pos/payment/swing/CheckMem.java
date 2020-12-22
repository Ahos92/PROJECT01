package project.five.pos.payment.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import project.five.pos.db.DBManager;
import project.five.pos.payment.swing.btn.action.NumberField;

public class CheckMem extends JFrame {
	
	static Connection conn;
	static PreparedStatement ps;
	static ResultSet rs;
	
	static String mobileNo;
	static String memberName;
	static String memberGrade;
	static double memberPct;
	static int memberPrice;
	static int memberMileage;
	static boolean memberOn = false;
	
	final static String BG_IMG = "assets/images/miniback7.png";
	
	PayPanel main;
	
	public CheckMem(PayPanel main) {
		
		
		
		this.main = main;
		
		setTitle("멤버쉽 확인 창");
		
		JPanel chk_membership = new JPanel(new GridLayout(3,1,0,0));
		
		JPanel explain = new JPanel();
		JLabel explanation = new JLabel("<html>멤버쉽 인증을 위해 멤버쉽 가입 할 때 이용했던<br/> "
				+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
				+ "휴대폰 번호를 입력해주세요.</html>", SwingConstants.CENTER);
		explanation.setForeground(new Color(245, 245, 220));
		explanation.setFont(new Font("카페24 숑숑 보통",Font.BOLD, 13));
		
		JPanel thr_txt = new JPanel(new FlowLayout());
		
		JTextField first_no = new JTextField("010");
		first_no.setEnabled(false);
		JLabel stick1 = new JLabel("-");
		JTextField middle_no = new JTextField(4);
		JLabel stick2 = new JLabel("-");
		JTextField last_no = new JTextField(4);
		
		middle_no.addKeyListener(new NumberField(4));
		last_no.addKeyListener(new NumberField(4));
		
		JPanel chk_mem = new JPanel(new FlowLayout());
		JButton yes_mem = new MiniButton("확인");
		JButton no_mem = new MiniButton("취소");
		

		// 다음 결제 프로세스 창
		yes_mem.addMouseListener(new MouseAdapter() {
					
				@Override
				public void mouseClicked(MouseEvent e) {
					
					memberName = "";
					mobileNo = "";
					if(e.getButton() == MouseEvent.BUTTON1) {
							mobileNo += first_no.getText();
							mobileNo += "-";
							mobileNo += middle_no.getText();
							mobileNo += "-";
							mobileNo += last_no.getText();
																				
							try {
								conn = DBManager.getConnection();

								ps = conn.prepareStatement("SELECT last_name, first_name, membership,"
										+ " accumulation_pct, amount_price, mileage FROM customer "
										+ "WHERE contact_no = ?");
								
								ps.setString(1, mobileNo);
								
								rs = ps.executeQuery();
								
								
								
								if(rs.next()) {
									
									do {
										memberName += rs.getString("last_name");
										memberName += rs.getString("first_name");
										memberGrade = rs.getString("membership");
										memberPct = rs.getDouble("accumulation_pct");
										memberPrice = rs.getInt("amount_price");
										memberMileage = rs.getInt("mileage");
										
										
										
										System.out.printf("%-15s%-10s%-10s%-10d%-10d%-10d\n",
												rs.getString("last_name"),
												rs.getString("first_name"),
												rs.getString("membership"),
												rs.getInt("accumulation_pct"),
												rs.getInt("amount_price"),
												rs.getInt("mileage")
												
												);
										//System.out.println(memberName);
										CheckMem.memberOn = true;
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
		
		explain.setOpaque(false);
		thr_txt.setOpaque(false);
		chk_mem.setOpaque(false);
		
		chk_membership.add(explain);
		chk_membership.add(thr_txt);
		chk_membership.add(chk_mem);
		//chk_membership.setBackground(new Color(250, 249, 247));
		chk_membership.setOpaque(false);
		
		setVisible(true);
		//setLayout(null);
		setSize(500, 210);
		setLocationRelativeTo(null);
		
		JLabel label = null;
		try {
			BufferedImage bgImage = ImageIO.read(new File(BG_IMG));
			int bgx = bgImage.getWidth();
			int bgy = bgImage.getHeight();
			label = new JLabel(new ImageIcon(ImageIO.read(new File(BG_IMG)).getScaledInstance(bgx, bgy, Image.SCALE_SMOOTH)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		label.setBounds(0, 0, 500, 170);
		JPanel back = new JPanel(new BorderLayout());
		back.setBounds(0, 0, 500, 170);
		back.add(chk_membership, BorderLayout.CENTER);
		
		back.setOpaque(false);
		add(back);
		add(label);
		
		//this.add(chk_membership, BorderLayout.CENTER);
	}
		
}
