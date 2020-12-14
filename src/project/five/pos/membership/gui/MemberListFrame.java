package project.five.pos.membership.gui;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import project.five.pos.membership.dao.MemberDao;
import project.five.pos.membership.models.Member;
import project.five.pos.membership.util.Sample;

public class MemberListFrame extends JFrame {

	private String username; // ���ǰ�
	private JPanel contentPane;
	private JPanel southPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberListFrame frame = new MemberListFrame();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MemberListFrame() {
		this(null);
	}

	public MemberListFrame(String username) {
		this.username = username;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1032, 584);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JLabel label = new JLabel("\uD68C\uC6D0\uC815\uBCF4");
		label.setFont(new Font("����", Font.BOLD, 20));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(label, BorderLayout.NORTH);

		southPanel = new JPanel(new GridLayout(1, 2));
		JButton btnDelete = new JButton("����");
		JButton btnLogout = new JButton("\uB85C\uADF8\uC544\uC6C3");
		southPanel.add(btnDelete);
		southPanel.add(btnLogout);
		contentPane.add(southPanel, BorderLayout.SOUTH);

		btnLogout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "�α׾ƿ� �Ǿ����ϴ�.");
				dispose();
				new LoginFrame();
			}
		});

		// JTable ������ �����ϱ� (������, Į���̸�, ���̺���)
		// 1. Į���̸�
		Vector<String> memberName = Sample.getMemberName();
		// 2. ������
		MemberDao dao = MemberDao.getInstance();
		Vector<Member> members = dao.findByAll();
		// 3. ���̺���
		DefaultTableModel tableModel = new DefaultTableModel(memberName, 0);

		// 4. for�� ���鼭 �� �྿ ������ ���� �ֱ�
		for (int i = 0; i < members.size(); i++) {
			Vector<Object> row = new Vector<>();
			row.addElement(members.get(i).getId());
			row.addElement(members.get(i).getUsername());
			row.addElement(members.get(i).getPassword());
			row.addElement(members.get(i).getName());
			row.addElement(members.get(i).getBirth());
			row.addElement(members.get(i).getPhone());
			tableModel.addRow(row); // table�𵨿� �� �ֱ�
		}

		JTable table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);

		contentPane.add(scrollPane, BorderLayout.CENTER);

		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				
				//���� ���õ��� ������ row�� -1�� ��.
				if(row < 0) {
					row = table.getRowCount()-1;
				}
				Object id = table.getValueAt(row, 0);
				//UI����
				tableModel.removeRow(row);
				//DB����
				MemberDao dao = MemberDao.getInstance();
//				dao.delete((int)id);
			}
		});

		if (username == null) {
			JOptionPane.showMessageDialog(null, "�������� ���� ������Դϴ�.");
			dispose();
		} else {
			setVisible(true);
		}
	}

}