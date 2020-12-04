package project.five.pos.sale;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import project.five.pos.sale.action.*;

public class OrderDisplay extends JFrame {
	
	JPanel southPanel;
	JPanel centerPanel;

	// ����, ��� ��ư	
	JButton payBtn;
	JButton cancleBtn;
	
	// �ֹ�������
	JTable table;
	JScrollPane scroll;
	static String header[]	= {"�޴�", "�ɼ�", "����", "�Ѱ���"};
	Object selectlist[][];
	static SaleDAO dao;
	static {
		dao = new SaleDAO();
	}
	static ArrayList<SaleDTO> cartlist = new ArrayList<>();
	int orderCount;

	public OrderDisplay() {	
		setLayout(new BorderLayout());
		southPanel = new JPanel();
		centerPanel = new JPanel();
	
		// ���� ������
		// 	 ��ư���� ��ǰ�� ���� ���� ���� (�ѹ��� �ֹ���)
		// ���ο� �ֹ���ȣ ���ö� 1����
		int orderNumber = dao.MaxOrderNumber();
		orderNumber++;
		cartlist.add(dao.testOrder("�Ƹ޸�ī��", "HOT", 2));
		cartlist.add(dao.testOrder("�Ƹ޸�ī��", "ICE", 1));
		cartlist.add(dao.testOrder("ȫ��", "HOT", 1));
		cartlist.add(dao.testOrder("����ũ", null, 1));
		cartlist.add(dao.testOrder("����", null, 1));
		orderCount = cartlist.size();
		
		// �ֹ���ȣ, �ֹ����� �� ǥ��
		
		// �ֹ� ���� ȭ��
		selectlist = new Object[orderCount][4];
		for (int i = 0; i < orderCount; i++) {
			selectlist[i][0] = cartlist.get(i).getProduct_name();
			selectlist[i][1] = cartlist.get(i).getTermsofcondition();
			selectlist[i][2] = cartlist.get(i).getOrder_count();
			selectlist[i][3] = cartlist.get(i).getProduct_price() * cartlist.get(i).getOrder_count();
		};
	
		table = new JTable(selectlist, header);
		scroll = new JScrollPane(table);
		add(scroll);
	
		payBtn = new SaleBtn("����", 50);
		cancleBtn = new SaleBtn("���", 50);
		
		// ����ȭ�� �׽�Ʈ Frame���� ����
		JFrame f = new JFrame("���� ȭ�� test");
		JLabel l = new JLabel("TEST ����ȭ�� �Դϴ�.");
		Font font;
		font = new Font("����", Font.BOLD, 30);
		l.setFont(font);
		l.setHorizontalAlignment(l.CENTER);
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
		f.setSize(500, 300);
		f.add(l);
		f.setLocation(1300, 100);
		f.setVisible(false);
		
		// ���� ��ư -> cartTable�� ������ ����(commit X) �� ����Frame false , ���������� true
		payBtn.addActionListener(new PaymentPageAction(this, f, cartlist, orderNumber));
		
		// ��� ��ư -> (����ȭ������ ���ư���) ��ٱ��� �ʱ�ȭ
		cancleBtn.addActionListener(new CancleAction());
		
		southPanel.add(payBtn);
		southPanel.add(cancleBtn);
		add(southPanel, BorderLayout.SOUTH);
		
		setTitle("��ٱ��� ȭ��");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 300);
		setLocation(1300, 100);
		setVisible(true);
	}

	public static void main(String[] args) {		
		new OrderDisplay();
	}

}

