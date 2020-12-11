package project.five.pos.payment.swing;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class AskCoupon extends JFrame {
	
	int price;
	int spend = 10000;
	int discount = 500;
	
	public AskCoupon(int price) {
		this.price = price;
		Container c = this.getContentPane();

		 
		
		setTitle("����");
		
        // ���� �Է� �г� 4�� 1. ������� 2. �����Է� 3. ���� �̹���, ���� ���� ǥ��, 4. ������ (�ε�ǥ��)
		JPanel panel04 = new JPanel(new CardLayout(15, 15));
		
		// 1. �������
		JPanel yes_or_no = new JPanel(new GridLayout(2,1,0,0));
		// 2. ���� �Է�
		JPanel input_coupon = new JPanel(new GridLayout(3,1,0,0));
		// 3. ���� �̹��� + ��������, ���� ���� ǥ��
		JPanel display_price = new JPanel(new GridLayout(1,2,0,0));
		// 4. ������ (�ε� ǥ��) �ڵ� ����
		JPanel loading = new JPanel();
		
		JLabel ask = new JLabel("������ ����Ͻðڽ��ϱ�?", SwingConstants.CENTER);
	
		JPanel yesnobtn = new JPanel(new FlowLayout());
		JButton yes = new JButton("��");
		JButton no = new JButton("�ƴϿ�");
		
		// ���� ���� ���μ��� â
		yes.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(e.getButton() == MouseEvent.BUTTON1) {
					((CardLayout)panel04.getLayout()).next(panel04);
				}
			}
		});
		
		// ���� ��� ��ư
		no.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(e.getButton() == MouseEvent.BUTTON1) {
					dispose();
				}
			}
		});
		
		JLabel input_label = new JLabel("������ȣ�� �Է����ּ���", SwingConstants.CENTER);
		
		JPanel input_field = new JPanel(null);
		JTextField input_text = new JTextField("�Է¶�");
		input_text.setBounds(175,0,100,25);
		
		JPanel input_btn = new JPanel(new FlowLayout());
		JButton check = new JButton("Ȯ��");
		JButton cancel = new JButton("���");
		
		// ���� ���� ���μ��� â
		check.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					((CardLayout)panel04.getLayout()).next(panel04);
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
		JPanel left_coupon = new JPanel(new GridLayout(2,1,0,0));
		JPanel coupon_name = new JPanel();
		JPanel coupon_img = new JPanel();
		
		JLabel coupon_label = new JLabel("����Ͻ� ������ : (�����̸�) �Դϴ�.");
		JLabel input_img = new JLabel("���� �̹���");
		
		JPanel right_coupon = new JPanel(new GridLayout(2,1,0,0));
		JPanel total_calc = new JPanel();
		JPanel lcheck_panel = new JPanel(); 
		
		JLabel calc_label = new JLabel("�� ������ : " + (spend - price + discount) + "�Դϴ�");
		JButton check2 = new JButton("Ȯ��");
		JButton cancel2 = new JButton("���");
		
		// ���ο� 4���� �гε� �߰�
		panel04.add(yes_or_no);
		panel04.add(input_coupon);
		panel04.add(display_price);
		panel04.add(loading);
		
		// �󺧿� ��ư ������ ����
		// �гο� ������ �ʹ� Ŀ��
		
		// - ù��° �г�
		yesnobtn.add(yes);
		yesnobtn.add(no);
		yes_or_no.add(ask);
		yes_or_no.add(yesnobtn);
		
		// - �ι�° �г�
		input_field.add(input_text);
		input_btn.add(check);
		input_btn.add(cancel);
		input_coupon.add(input_label);
		input_coupon.add(input_field);
		input_coupon.add(input_btn);
		
		// - ����° �г�
		
		coupon_name.add(coupon_label);
		coupon_img.add(input_img);
		left_coupon.add(coupon_name);
		left_coupon.add(coupon_img);
		
		total_calc.add(calc_label);
		lcheck_panel.add(check2);
		lcheck_panel.add(cancel2);
		right_coupon.add(total_calc);
		right_coupon.add(lcheck_panel);
		
		display_price.add(left_coupon);
		display_price.add(right_coupon);
		
		// - �׹�° �г�
		
		

		
		setVisible(true);
		//setLayout(null);
		setSize(500, 170);
		setLocation(1180, 500);
		
		this.add(panel04, BorderLayout.CENTER);
	}

}
