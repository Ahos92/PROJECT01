package project.five.pos.payment.swing;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import project.five.pos.cart.CartDAO;
import project.five.pos.db.DBManager;
import project.five.pos.db.PosVO;
import project.five.pos.menu.MenuDisplay;
import project.five.pos.payment.swing.btn.action.ClickedBtnAction;
import project.five.pos.payment.swing.btn.action.NumberField;

// ��������
//�󺧿� ��ư ������ ���� = �� �� ������Ʈ �̱� ������
//�гο� ������ �ʹ� Ŀ��

public class AskCoupon extends JFrame {
	
	static Connection conn;
	static PreparedStatement ps;
	static ResultSet rs;
			
	int price;
	JFrame frame;
	int order_num;
	
	static int change;
	int actual_expenditure;
	
	static int couponPrice;
	static String couponNo;
	static String couponName;

	static String startDate;
	static String expiredDate;

	static LocalDate today;
	static LocalDateTime today2;
	static Timestamp tstp2;
	
	final static String BG_IMG = "assets/images/miniback7.png";
	
	JLabel order_cart;
	
	CartDAO cart;
	
	public AskCoupon(int price, JFrame frame, int order_num, 
			ArrayList<String> lists2, ArrayList<PosVO> update_cart, String device_id) {
		this.price = price;
		this.frame = frame;
		this.order_num = order_num;
		
		int device_id_int = Integer.parseInt(device_id);
		
		cart = new CartDAO();
		
		// ���� ��¥ ��
		today = LocalDate.now();
		
		// ���� �ð� ��¿�
		today2 =LocalDateTime.now(ZoneId.of("Asia/Seoul"));
		
		tstp2 = Timestamp.valueOf(today2);
		
		setTitle("����");
		
        // ���� �Է� �г� 3�� 1. ������� 2. �����Է� 3. ���� �̹���, ���� ���� ǥ��
		JPanel panel04 = new JPanel(new CardLayout(15, 15));
		
		// 1. �������
		JPanel yes_or_no = new JPanel(new GridLayout(2,1,0,0));
		// 2. ���� �Է�
		JPanel input_coupon = new JPanel(new GridLayout(3,1,0,0));
		// 3. ���� �̹��� + ��������, ���� ���� ǥ��
		JPanel display_price = new JPanel(new GridLayout(1,2,0,0));
		
		JLabel ask = new JLabel("������ ����Ͻðڽ��ϱ�?", SwingConstants.CENTER);
		ask.setForeground(Color.WHITE);
		ask.setFont(new Font("ī��24 ���� ����",Font.BOLD, 13));
	
		JPanel yesnobtn = new JPanel(new FlowLayout());
		
		JButton yes = new MiniButton("��");
		
		// ���� ���� ���μ��� â
		yes.addMouseListener(new MouseAdapter() {
					
			@Override
			public void mouseClicked(MouseEvent e) {
						
				if(e.getButton() == MouseEvent.BUTTON1) {
					((CardLayout)panel04.getLayout()).next(panel04);
				}
			}
		});
		
		JPanel result_panel = new JPanel(new GridLayout(3,1,0,0));
		result_panel.setOpaque(false);
		
		JLabel result_explain = new JLabel("�ֹ��Ͻ� ��ǰ ����Ʈ", SwingConstants.CENTER);
		result_explain.setForeground(Color.WHITE);
		result_explain.setFont(new Font("ī��24 ���� ����",Font.BOLD, 36));
		
		JPanel order_list = new JPanel(new FlowLayout());
		order_list.setOpaque(false);
		
		
		
		for(int i = 0; i < lists2.size(); i++) {
			order_list.add(new SetLabel(lists2.get(i), update_cart.get(i).getSelected_item(), 36));
		}
				
		JLabel order_no = new JLabel("�ֹ� ��ȣ : " + order_num, SwingConstants.CENTER);
		order_no.setForeground(Color.WHITE);
		order_no.setFont(new Font("ī��24 ���� ����",Font.BOLD, 36));
		
		result_panel.add(result_explain);
		result_panel.add(order_list);
		result_panel.add(order_no);
		
		PayPanel.main_center_panel.add("������", result_panel);
				
		JButton no = new MiniButton("�ƴϿ�");
		
		no.addActionListener(new ActionListener() {
						
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				JButton btn = (JButton) e.getSource();
				if(btn.getText().equals("�ƴϿ�")) {
					actual_expenditure = price;
					
					if(CheckMem.memberOn == true && CheckMem.memberMileage >= 1000) {
						new AskMileage(tstp2, price, actual_expenditure, couponNo, device_id_int);
						
						dispose();	
						
					}else if(CheckMem.memberOn == true){
						
						// ���� ���� DB ����
						new PaymentQuery(tstp2, price, actual_expenditure, couponNo, device_id_int);						
						
						cart.saveCartlist(today2, order_num, lists2, update_cart, device_id_int);
						
						// ���� + ��޾� �߰�						
						new MembershipQuery(actual_expenditure);
										
						// ���� �г� �ʱ�ȭ
						new ResetMain();
						
						// ���� ����
						new SuccessPayment();
						
						dispose();	
					}
					
					else {
						
						// ���� ���� DB ����
						new PaymentQuery(tstp2, price, actual_expenditure, couponNo, device_id_int);						
									
						cart.saveCartlist(today2, order_num, lists2, update_cart, device_id_int);
						
						
						// ���� �г� �ʱ�ȭ
						new ResetMain();
						
						// ���� ����
						new SuccessPayment();
						
						dispose();	
					}
				}
				
			}
			
		});
				
		JLabel input_label = new JLabel("������ȣ�� �Է����ּ���", SwingConstants.CENTER);
		input_label.setForeground(Color.WHITE);
		input_label.setFont(new Font("ī��24 ���� ����",Font.BOLD, 13));
		
		JPanel input_field = new JPanel(null);
		JTextField input_text = new JTextField(6);
		input_text.setBounds(175,0,115,25);
		
		input_text.addKeyListener(new NumberField(6));
					
		JPanel input_btn = new JPanel(new FlowLayout());
		JButton check = new MiniButton("Ȯ��");
		JButton cancel = new MiniButton("���");
		
		
		JPanel left_coupon = new JPanel(null);
		JPanel coupon_name = new JPanel();
		coupon_name.setBounds(0, 0, 200, 25);
		
		JPanel coupon_img = new JPanel(new CardLayout(15, 15));
		coupon_img.setBounds(0, 25, 220, 90);
					
		JPanel right_coupon = new JPanel(new GridLayout(2,1,0,0));
		JPanel total_calc = new JPanel();
		JPanel lcheck_panel = new JPanel(); 
		
		for(CouponEnum cpname : CouponEnum.values()) {
			coupon_img.add(cpname.getCname(), new CouponLabel(cpname));
		}
		
	
		JButton check2 = new MiniButton("Ȯ��");
		
		check2.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				// ���� ���� DB ����
				new PaymentQuery(tstp2, price, actual_expenditure, couponNo, device_id_int);
				
				cart.saveCartlist(today2, order_num, lists2, update_cart, device_id_int);
				
				// ��� �϶� ����
				if(CheckMem.memberOn == true) {
					new MembershipQuery(actual_expenditure);
				}
				
				//������ȣ �ʱ�ȭ
				couponNo = "";
								
				// ���� �г� �ʱ�ȭ
				new ResetMain();	
				
				// ���� ����
				new SuccessPayment();
				dispose();
			}
		});
		
		JButton cancel2 = new MiniButton("���");
		
		// ���� ��� ��ư
		cancel2.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
												
				new CancelPayment();
				dispose();								
			}
		});
		
		PayPanel.main_center_panel.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					
					//��ư ���(�Լ� ClickedBtnAction)
					PayPanel.main_card.show(PayPanel.main_center_panel, "������");	
					new MenuDisplay(device_id);
					
					frame.dispose();
				}
				
			}
		});
		
		// ���� ���� ���μ��� â
		check.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseClicked(MouseEvent e) {
					
					
					
					if(e.getButton() == MouseEvent.BUTTON1) {
							couponNo = input_text.getText();
							
							try {
								conn = DBManager.getConnection();

								ps = conn.prepareStatement("SELECT * FROM coupon "
										+ "WHERE coupon_no = ?");
								
								ps.setString(1, couponNo);
								
								rs = ps.executeQuery();
								
								couponName = "";
								couponPrice = 0;
								startDate ="";
								expiredDate = "";
								if(rs.next()) {
									
									do {
										AskCoupon.couponNo = rs.getString("coupon_no");
										AskCoupon.couponName = rs.getString("coupon_name");
										AskCoupon.couponPrice = rs.getInt("coupon_price");
										AskCoupon.startDate = rs.getString("start_date").substring(0, 10);
										AskCoupon.expiredDate = rs.getString("expired_date").substring(0, 10);
																																															
										LocalDate stDate = LocalDate.parse(AskCoupon.startDate);
										LocalDate exDate = LocalDate.parse(AskCoupon.expiredDate);
										
										// ������ ���� ����
										if(today.isBefore(stDate) || today.isAfter(exDate)) {
											new ExpiredCoupon();
										}
										else {
										JLabel coupon_label = new JLabel("����Ͻ� ������ : " + AskCoupon.couponName + "�Դϴ�.", SwingConstants.CENTER);
										coupon_label.setForeground(Color.WHITE);
										coupon_label.setFont(new Font("ī��24 ���� ����",Font.BOLD, 12));
										
										
										((CardLayout)coupon_img.getLayout()).show(coupon_img, AskCoupon.couponName);
										
										//�� �����
										actual_expenditure = (price - AskCoupon.couponPrice);
										// �Ž����� ���
										change = PaidByCash.i_money - actual_expenditure;
										
										if(ClickedBtnAction.getPaymentType().toString().contains("ī��")) {
											JLabel calc_label2 = new JLabel("<html>ī�� ���� �Դϴ�<br/>���� �ݾ� : " + price + "-" + AskCoupon.couponPrice + "=" + actual_expenditure + " (��)<html>", SwingConstants.CENTER);
											calc_label2.setForeground(Color.WHITE);
											calc_label2.setFont(new Font("ī��24 ���� ����",Font.BOLD, 13));
											total_calc.add(calc_label2);
											}
										else {
											JLabel calc_label = new JLabel("<html>���� �ݾ� : " + price + "-" + AskCoupon.couponPrice + "=" + actual_expenditure + " (��)<br/>�Ա� �ݾ� : " + PaidByCash.i_money + "(��)"
													+ "<br/>�Ž����� : "+  change + "(��)</html>", SwingConstants.CENTER);
											calc_label.setForeground(Color.WHITE);
											calc_label.setFont(new Font("ī��24 ���� ����",Font.BOLD, 13));
											total_calc.add(calc_label);
										}
																																																												
										// - ����° �г�
										coupon_name.add(coupon_label);
										left_coupon.add(coupon_name);
										left_coupon.add(coupon_img);
										
										coupon_name.setOpaque(false);
										coupon_img.setOpaque(false);
										lcheck_panel.setOpaque(false);
										total_calc.setOpaque(false);
										
										lcheck_panel.add(check2);
										lcheck_panel.add(cancel2);
										right_coupon.add(total_calc);
										right_coupon.add(lcheck_panel);
										
										left_coupon.setOpaque(false);
										right_coupon.setOpaque(false);
										
										display_price.add(left_coupon);
										display_price.add(right_coupon);
										
										display_price.setOpaque(false);
											
										// ���ο� 3�� �г� �߰�
										panel04.add(display_price);

										((CardLayout)panel04.getLayout()).next(panel04);
										}
										
									}while (rs.next()); 
										
								}
								else {
									// �������� �ʴ� ���� �Դϴ�
									new NoneCoupon();	
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
			
		// �ڷΰ��� ��ư
		cancel.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					((CardLayout)panel04.getLayout()).previous(panel04);
				}
				
			}
		});
		
		// - ù��° �г�
		yesnobtn.add(yes);
		yesnobtn.add(no);
		yes_or_no.add(ask);
		yes_or_no.add(yesnobtn);
		
		ask.setOpaque(false);
		yesnobtn.setOpaque(false);
		
		// - �ι�° �г�
		input_field.add(input_text);
		input_btn.add(check);
		input_btn.add(cancel);
		
		input_field.setOpaque(false);
		input_btn.setOpaque(false);
		
		input_coupon.add(input_label);
		input_coupon.add(input_field);
		input_coupon.add(input_btn);
		
		yes_or_no.setOpaque(false);
		input_coupon.setOpaque(false);
		
		// ���ο� 1, 2�� �гε� �߰�
		panel04.add(yes_or_no);
		panel04.add(input_coupon);
		
		panel04.setOpaque(false);
		
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
		back.add(panel04, BorderLayout.CENTER);
		
		back.setOpaque(false);
		add(back);
		add(label);
		
		//this.add(panel04, BorderLayout.CENTER);
	}

}
