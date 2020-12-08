package project.five.pos.sale;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import project.five.pos.sale.action.*;
import project.five.pos.sale.render.*;

public class OrderDisplay extends JFrame {

	JPanel southPanel;
	JPanel centerPanel;

	// ����, ��� ��ư	
	JButton payBtn;
	JButton cancleBtn;
	
	
	// �ֹ����� ���� ��
	JLabel info_label;
	
	// �ֹ����� ���̺�
	JTable table;
	JScrollPane scroll;
	static String header[]	= {"�޴�", "�ɼ�", "����", "��", "��", "����", "���"};
	Object selectlist[][];
	int cellBtnSize;
	static ArrayList<SaleDTO> cartlist = new ArrayList<>();
	int orderCount;

	Font font;
	
	public OrderDisplay() {	
		// ���� ������
		// 	 ��ư���� ��ǰ�� ���� ���� ���� (�ѹ��� �ֹ���)
		// ���ο� �ֹ���ȣ ���ö� 1����
		SaleDAO dao = new SaleDAO();
		int orderNumber = dao.MaxOrderNumber();
		orderNumber++;
//		cartlist.add(dao.testOrder("�Ƹ޸�ī��", "HOT", 2));
		cartlist.add(dao.testOrder("�Ƹ޸�ī��", "ICE", 1));
		cartlist.add(dao.testOrder("ȫ��", "HOT", 1));
		cartlist.add(dao.testOrder("����ũ", null, 1));
//		cartlist.add(dao.testOrder("����", null, 1));
		orderCount = cartlist.size();	
		
		// �׽�Ʈ �� ����ȭ��
		JFrame f = new JFrame();
		JLabel l = new JLabel("TEST ����ȭ�� �Դϴ�.");
		l.setHorizontalAlignment(l.CENTER);
		f.add(l);
		TestSwingTools.initTestFrame(f, "���� ȭ��", false);
		//////////////////////////////////////////////////
	
		
		
		setLayout(new BorderLayout());
		southPanel = new JPanel();
		centerPanel = new JPanel();
		
		// �ֹ���ȣ, �ֹ����� �� ǥ��
		info_label = new JLabel("�ֹ� ����");
		add(info_label, BorderLayout.NORTH);
		
		// �ֹ� ���� ȭ��
		//	- ��ǰ��ü ���� �迭�� �޾Ƽ� ���
		selectlist = new Object[orderCount][6];
		for (int i = 0; i < orderCount; i++) {
			selectlist[i][0] = cartlist.get(i).getProduct_name();
			selectlist[i][1] = cartlist.get(i).getTermsofcondition();
			selectlist[i][2] = cartlist.get(i).getOrder_count();
			selectlist[i][5] = cartlist.get(i).getProduct_price() * cartlist.get(i).getOrder_count();
		};
		DefaultTableModel dtm = new DefaultTableModel(selectlist, header);
		table = new JTable(dtm);
		scroll = new JScrollPane(table);
		
		cellBtnSize = 40;
		table.getColumn("���").setCellRenderer(new DeleteBtnRender());
		table.getColumn("���").setCellEditor(new DeleteAction(new JCheckBox(), table, dtm));
		table.getColumn("���").setPreferredWidth(cellBtnSize);
		
		table.getColumn("��").setCellRenderer(new UpDonwBtnRender("��"));
		table.getColumn("��").setCellEditor(new UpDownAction(new JCheckBox(), table, "��"));
		table.getColumn("��").setPreferredWidth(cellBtnSize);
		
		table.getColumn("��").setCellRenderer(new UpDonwBtnRender("��"));
		table.getColumn("��").setCellEditor(new UpDownAction(new JCheckBox(), table, "��"));
		table.getColumn("��").setPreferredWidth(cellBtnSize);
		
		// �гη� �ٲܰŸ� �����ϱ�
		scroll.setPreferredSize(new Dimension(480, 100));
		centerPanel.add(scroll);

		payBtn = new SaleBtn("����", 50);
		cancleBtn = new SaleBtn("���", 50);

		// ���� ��ư -> cartTable�� ������ ����(commit X) �� ����Frame false , ���������� true
		payBtn.addActionListener(new PaymentPageAction(this, f, dtm, orderNumber));
		
		// ��� ��ư -> (����ȭ������ ���ư���) ��ٱ��� �ʱ�ȭ
		cancleBtn.addActionListener(new CancleAction(dtm));
		
		southPanel.add(payBtn);
		southPanel.add(cancleBtn);
		add(southPanel, BorderLayout.SOUTH);
		add(centerPanel, BorderLayout.CENTER);
		
		TestSwingTools.initTestFrame(this, "��ٱ��� ȭ��", true);
	}

	public static void main(String[] args) {	
		new OrderDisplay();

	}

}

