package project.five.pos.payment.swing;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AskMileage extends JFrame{
	
	static Connection conn;
	static PreparedStatement ps;
	static ResultSet rs;

	LocalDate today;
	Timestamp tstp2;
	int price;
	int actual_expenditure;
	String couponNo;
	int device_id;
	
	static int ml_as_you_wish = 0;
	static int expenditure_ml = 0;
	
	public AskMileage(Timestamp tstp2, int price, int actual_expenditure, String couponNo, int device_id) {
		this.tstp2 = tstp2;
		this.price = price;
		this.actual_expenditure = actual_expenditure;
		this.couponNo = couponNo;
		this.device_id = device_id;
		
		setTitle("���ϸ��� ���");
		
		// ���� ���ϸ��� �г�
		JPanel panel_ml = new JPanel(new CardLayout(15, 15));
		
		// ù��° ���ϸ��� �г�
		JPanel use_or_keep = new JPanel(new GridLayout(3,1,0,0));
		
		// �ι�° ���ϸ��� �г�
		JPanel input_mileage = new JPanel(new GridLayout(3,1,0,0));
		
		// ����° ���ϸ��� �г�
		JPanel show_total = new JPanel(new GridLayout(2,1,0,0));
		
		JLabel ask_mileage = new JLabel("������ ��� ���ϸ����� ����Ͻðڽ��ϱ�?");
		JLabel show_ml = new JLabel("���ϸ��� �ݾ� : " + CheckMem.memberMileage);
		JPanel flw_btn = new JPanel(new FlowLayout());
		
		JButton sure = new JButton("Ȯ��");
		
		sure.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(e.getButton() == MouseEvent.BUTTON1) {
					((CardLayout)panel_ml.getLayout()).next(panel_ml);
				}
			}
		});
		
		JButton nah = new JButton("�ƴϿ�");
		
		nah.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				JButton btn = (JButton) e.getSource();
				if(btn.getText().equals("�ƴϿ�")) {
										
					// ���� ���� DB ����
					new PaymentQuery(tstp2, price, actual_expenditure, couponNo, device_id);
					
					// ���� + ��޾� �߰�						
					new MembershipQuery(actual_expenditure);
					
					// ���� �г� �ʱ�ȭ																							
					new ResetMain();
					
					// ���� ����
					new SuccessPayment();
					
					dispose();
					
					
				}
				
			}
			
		});
		
		
		JLabel input_ml_label = new JLabel("����Ͻ� ���ϸ��� �ݾ��� �Է����ּ���", SwingConstants.CENTER);
		JPanel input_ml_field = new JPanel(null);
		JTextField input_ml_text = new JTextField(6);
		input_ml_text.setBounds(175,0,100,25);
		
		input_ml_text.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				
				char c = e.getKeyChar();
				 
				  JTextField src = (JTextField) e.getSource();
				  
				  if (!Character.isDigit(c)) {
					  e.consume();
					  return;
				  }
				  
				  else if(src.getText().length() >= 6) {
					  e.consume();
					  return;
				  }	  
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
								
			}

			@Override
			public void keyReleased(KeyEvent e) {
					
			}
			
		});
		
		JPanel input_ml_btn = new JPanel(new FlowLayout());
		JButton check_ml = new JButton("Ȯ��");
		
		
		JPanel flw_btn2 = new JPanel(new FlowLayout());
		
		// ������ Ȯ�� ��ư
		JButton okay = new JButton("Ȯ��");
		
		okay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				JButton btn = (JButton) e.getSource();
				if(btn.getText().equals("Ȯ��")) {
																
					// ���� ���� DB ���� (���ϸ��� ���밪 ����)
					new PaymentQuery(tstp2, price, expenditure_ml, couponNo, device_id);
					
					// ���� + ��޾� �߰� (���ϸ��� ���� �ݾ�)						
					new MembershipQuery(expenditure_ml, ml_as_you_wish);
					
					//���ϸ��� �Է°� �ʱ�ȭ
					ml_as_you_wish = 0;
					
					// ���� �г� �ʱ�ȭ
					new ResetMain();
					
					// ���� ����
					new SuccessPayment();
					
					dispose();	
					
				}
			}
			
		});
		
		//������ ���
		JButton no = new JButton("���");
		
		no.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				new CancelPayment();
				dispose();
			}
		});
		
		
		// ���ϸ��� üũ Ȯ�� ��ư
		check_ml.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				if(e.getButton() == MouseEvent.BUTTON1) {
					
					//���ϸ��� �Է°�
					ml_as_you_wish = Integer.parseInt(input_ml_text.getText());
					
					//������� + ���ϸ��� �Է� ����
					expenditure_ml = actual_expenditure - ml_as_you_wish;
								
					if(ml_as_you_wish % 100 != 0) {
						new HundredsMl();
					}
					else if(ml_as_you_wish > CheckMem.memberMileage) {
						new GreaterThanMl();						
					}
					else {
						JLabel calc_ml = new JLabel("<html>���� �ݾ�"+ actual_expenditure +
								"- ���ϸ���" + ml_as_you_wish + "<br/>"
								+ "=" + expenditure_ml +"</html>", SwingConstants.CENTER);
					
						// ����° ���ϸ��� �г�	
						show_total.add(calc_ml);
						flw_btn2.add(okay);
						flw_btn2.add(no);
						show_total.add(flw_btn2);
						
						panel_ml.add(show_total);
						
						((CardLayout)panel_ml.getLayout()).next(panel_ml);
						
					}
																									
				}
				
			}
		});
		
		// �ߵ� ��� ��ư
		JButton cancel_ml = new JButton("���");
		
		cancel_ml.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				new CancelPayment();
				dispose();
			}
		});
		
				
		// ù��° ���ϸ��� �г�
		use_or_keep.add(ask_mileage);
		use_or_keep.add(show_ml);
		
		flw_btn.add(sure);
		flw_btn.add(nah);
		
		use_or_keep.add(flw_btn);
		
		// �ι�° ���ϸ��� �г�	
		input_ml_field.add(input_ml_text);
		
		input_ml_btn.add(check_ml);
		input_ml_btn.add(cancel_ml);
		
		input_mileage.add(input_ml_label);
		input_mileage.add(input_ml_field);
		input_mileage.add(input_ml_btn);
		
		
		panel_ml.add(use_or_keep);
		panel_ml.add(input_mileage);
		
		
		setVisible(true);
		//setLayout(null);
		setSize(500, 185);
		setLocation(1180, 500);
		
		this.add(panel_ml, BorderLayout.CENTER);
	}
}
