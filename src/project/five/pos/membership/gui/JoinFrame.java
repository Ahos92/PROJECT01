package project.five.pos.membership.gui;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import project.five.pos.TestSwingTools;
import project.five.pos.device.comp.DeviceLab;
import project.five.pos.device.comp.btn.DeviceBtn;
import project.five.pos.device.comp.tf.DeviceTF;
import project.five.pos.device.comp.tf.action.PromptAction;
import project.five.pos.membership.dao.MemberDao;
import project.five.pos.membership.models.Member;

public class JoinFrame extends JDialog {

	private JPanel contentPane;
	private JLabel lblJoin;
	private JButton joinCompleteBtn;
	
	
	private JTextField tfFirst_name;	// ��
	private JTextField tfLast_name;		// �̸�
	private JTextField tfContact_no;	// ��ȭ��ȣ

	JPanel center_p, south_p;
	JLabel lblName_last, lblName_first, lblConatact_no;


	public JoinFrame(JFrame frame, String title) {
		super(frame, title);
		setLayout(new BorderLayout());
		setSize(300, 350);
		setResizable(false);// ������ ���� �Ұ�
		setLocationRelativeTo(null);
		setModal(true);
		
		center_p = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
		south_p = new JPanel();
		
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setSize(430, 490);
//		setLocationRelativeTo(null);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPane);
//		contentPane.setLayout(null);
		
//		lblJoin = new JLabel("ȸ������");
//		Font f1 = new Font("D2Coding", Font.BOLD, 20); 
//		lblJoin.setFont(f1); 
//		lblJoin.setBounds(159, 41, 101, 20);
//		contentPane.add(lblJoin);
		
		
		lblName_last = new DeviceLab("��", 50, 30);
//		lblName_last.setBounds(69, 113, 69, 20);		// 69, 210, 69, 20
//		contentPane.add(lblName_last);
		tfLast_name = createTf("�ѱ� 1~10 ����");

		lblName_first = new DeviceLab("�̸�", 50, 30);
//		lblName_first.setBounds(69, 163, 69, 20);		// 69, 210, 69, 20
//		contentPane.add(lblName_first);					
		tfFirst_name = createTf("�ѱ� 1~10 ����");
		
		lblConatact_no = new DeviceLab("��ȭ��ȣ", 50, 30);
//		lblConatact_no.setBounds(69, 213, 69, 20);			// 69, 304, 69, 20
//		contentPane.add(lblConatact_no);
		tfContact_no = createTf("000-0000-0000 / 000-000-0000");
		
		
//		tfLast_name = new JTextField();
//		tfLast_name.setColumns(10);
//		tfLast_name.setBounds(159, 106, 186, 35);	// 159, 203, 186, 35
//		contentPane.add(tfLast_name);
//
//		tfFirst_name = new JTextField();
//		tfFirst_name.setColumns(10);
//		tfFirst_name.setBounds(159, 156, 186, 35);	// new
//		contentPane.add(tfFirst_name);
//
//		tfContact_no = new JTextField();
//		tfContact_no.setColumns(10);
//		tfContact_no.setBounds(159, 206, 186, 35);	// 159, 297, 186, 35
//		contentPane.add(tfContact_no);
		
		joinCompleteBtn = new DeviceBtn("���", 60, 30, new ActionListener() {
//		joinCompleteBtn.setBounds(206, 256, 139, 29);
//		contentPane.add(joinCompleteBtn);
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Member member = new Member();
				member.setContact_no(tfContact_no.getText());
				member.setLast_name(tfLast_name.getText());
				member.setFirst_name(tfFirst_name.getText());
				
				member.setAmount_price(0);
				member.setAccumulation_pct(0.01);
				member.setCustomer_no("");
				member.setMembership("bronze");
				member.setMileage(0);
				
				MemberDao dao = MemberDao.getInstance();
				int result = dao.save(member);
				
				if(result == 1) {
					JOptionPane.showMessageDialog(null, "ȸ�������� �Ϸ�Ǿ����ϴ�.");
					dispose();		// ȸ������ �Ϸ�Ǹ� frame ����
				}else {
					JOptionPane.showMessageDialog(null, "ȸ�������� �����Ͽ����ϴ�.");
				}	
			}
		});
		
		center_p.add(lblName_last);
		center_p.add(tfLast_name);
		center_p.add(lblName_first);
		center_p.add(tfFirst_name);
		center_p.add(lblConatact_no);
		center_p.add(tfContact_no);

		
		south_p.add(joinCompleteBtn);
		
		add(center_p, BorderLayout.CENTER);
		add(south_p, BorderLayout.SOUTH);
		
		setVisible(true);
		
		//ȸ�����ԿϷ� �׼�
//		joinCompleteBtn.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				Member member = new Member();
//				member.setContact_no(tfContact_no.getText());
//				member.setLast_name(tfLast_name.getText());
//				member.setFirst_name(tfFirst_name.getText());
//				
//				member.setAmount_price(0);
//				member.setAccumulation_pct(0.01);
//				member.setCustomer_no("");
//				member.setMembership("bronze");
//				member.setMileage(0);
//				
//				MemberDao dao = MemberDao.getInstance();
//				int result = dao.save(member);
//				
//				if(result == 1) {
//					JOptionPane.showMessageDialog(null, "ȸ�������� �Ϸ�Ǿ����ϴ�.");
//					dispose();
//				}else {
//					JOptionPane.showMessageDialog(null, "ȸ�������� �����Ͽ����ϴ�.");
//				}	
//			}
//		});
		
		

	}
	
	private JTextField createTf(String txt) {
		JTextField tf = new DeviceTF(txt, 16, 40, 30);
		tf.addFocusListener(new PromptAction(tf, txt));
		return tf;
	}
	
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					JoinFrame frame = new JoinFrame();
//
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//		new JoinFrame();
//	}
}