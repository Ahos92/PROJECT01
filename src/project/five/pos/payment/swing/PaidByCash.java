package project.five.pos.payment.swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class PaidByCash extends JFrame{

	int price;	
	JButton card_btn;
	JButton cash_btn;
	JButton payment_btn;
	
	static int i_money;
	
	String[] choices = {"50000", "10000", "5000", "1000"};
			
	public PaidByCash(int price, JButton card_btn, JButton cash_btn, JButton payment_btn) {
		this.price = price;
		this.card_btn = card_btn;
		this.cash_btn = cash_btn;
		this.payment_btn = payment_btn;
		
		setTitle("�����ݾ� : " + this.price + "��");
		
		JPanel insertCash = new JPanel(new GridLayout(3,1,0,0));
		JPanel stnPanel = new JPanel();
		JPanel showMoney = new JPanel(new FlowLayout());
		JPanel clickPanel = new JPanel(new FlowLayout());
		
		JLabel cashIn = new JLabel("������ ���Ա��� �־��ּ���");
		
		JLabel howMuch = new JLabel("���� �ݾ� :");
		JComboBox<String> howMuch2 = new JComboBox<>(choices);
		
		JButton cashOkay = new JButton("Ȯ��");
		
		cashOkay.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(e.getButton() == MouseEvent.BUTTON1) {
					
					String s_money = howMuch2.getSelectedItem().toString();
					i_money = Integer.parseInt(s_money);
					
					new SuccessCash(i_money, price, card_btn, cash_btn);
					dispose();
					
					
				}
			}
		});
		
		JButton cashNo = new JButton("���");
		
		cashNo.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					cash_btn.setText("����");
					cash_btn.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
					card_btn.setEnabled(true);
					payment_btn.setEnabled(false);
					dispose();
				}
			}
		});
		
		stnPanel.add(cashIn);
		
		showMoney.add(howMuch);
		showMoney.add(howMuch2);
		
		clickPanel.add(cashOkay);
		clickPanel.add(cashNo);
		
		insertCash.add(stnPanel);
		insertCash.add(showMoney);
		insertCash.add(clickPanel);
		
		setVisible(true);
		//setLayout(null);
		setSize(500, 170);
		setLocationRelativeTo(null);
		
		this.add(insertCash, BorderLayout.CENTER);
	}
}
