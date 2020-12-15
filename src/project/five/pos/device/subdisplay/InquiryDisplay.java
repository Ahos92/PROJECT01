package project.five.pos.device.subdisplay;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

import javax.swing.*;
import javax.swing.event.RowSorterListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;

import project.five.pos.TestSwingTools;
import project.five.pos.device.*;
import project.five.pos.device.actionbtn.ChangeFrameBtn;
import project.five.pos.device.actionbtn.TableRepaintAction;
import project.five.pos.sale.*;

public class InquiryDisplay extends JFrame {

	JComboBox<String> select_column;
	JTextField select_data;
	JLabel headLb, categoryLb, selectNameLb;

	JPanel northP, centerP, southP;

	JButton backBtn, searchBtn, allInqBtn;

	JTable table;
	JScrollPane scroll;
	DefaultTableModel dtm;
	public InquiryDisplay(String btn_text) {
		setLayout(new BorderLayout());

		northP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		centerP = new JPanel();
		southP = new JPanel();	
		
		// ���
		headLb = new JLabel(btn_text);

		// ��ȸ ���̺�
		dtm = new InqTableModel(btn_text);
		table = new JTable(dtm);
		table.setAutoCreateRowSorter(true); // ���̺� ����/���� ��ȯ
		table.getTableHeader().setReorderingAllowed(false); // ���̺� �����Ұ�
		scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(480, 500));
		// �˻� 
		String[] list = {"�ֹ� ��ȣ", "��ǰ �̸�"};
		select_column = new JComboBox<String>(list);
		select_column.setPreferredSize(new Dimension(100, 20));
		categoryLb = new JLabel("ī�װ�");
		select_data = new JTextField(8);
		selectNameLb = new JLabel("�˻� ��");
		searchBtn = new JButton("�˻�");
		searchBtn.addActionListener(new TableRepaintAction(btn_text, dtm,
															select_column, select_data)); 
		allInqBtn = new JButton("��ü����");
		allInqBtn.addActionListener(new TableRepaintAction(btn_text, dtm,
															select_column, select_data)); 	
				
		// �ڷΰ���
		backBtn = new JButton("������ �޴��� ���ư���");
		backBtn.addActionListener(new ChangeFrameBtn(this));
		
		southP.add(backBtn);
		centerP.add(scroll);
		centerP.add(categoryLb);
		centerP.add(select_column);
		centerP.add(selectNameLb);
		centerP.add(select_data);
		centerP.add(searchBtn);
		centerP.add(allInqBtn);
		northP.add(headLb);
		
		add(northP, BorderLayout.NORTH);
		add(centerP, BorderLayout.CENTER);
		add(southP, BorderLayout.SOUTH);

		TestSwingTools.initTestFrame(this, "INQ TEST", false);
	}

	public static void main(String[] args) {
		new InquiryDisplay("�Ǹ� ���� ��ȸ").setVisible(true);
	}
}
