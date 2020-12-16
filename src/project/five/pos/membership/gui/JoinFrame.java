package project.five.pos.membership.gui;

import java.awt.EventQueue;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import project.five.pos.membership.gui.JoinFrame;
import project.five.pos.membership.dao.MemberDao;
import project.five.pos.membership.models.Member;

public class JoinFrame extends JFrame {

	private JPanel contentPane;
	private JLabel lblJoin;
	private JButton joinCompleteBtn;
	
	private JTextField tfFirst_name;	// 성
	private JTextField tfLast_name;		// 이름
	private JTextField tfContact_no;	// 전화번호


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JoinFrame frame = new JoinFrame();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JoinFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(430, 490);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblJoin = new JLabel("회원가입");
		Font f1 = new Font("D2Coding", Font.BOLD, 20); 
		lblJoin.setFont(f1); 
		lblJoin.setBounds(159, 41, 101, 20);
		contentPane.add(lblJoin);
		
		JLabel lblName_last = new JLabel("성");
		lblName_last.setBounds(69, 113, 69, 20);		// 69, 210, 69, 20
		contentPane.add(lblName_last);

		JLabel lblName_first = new JLabel("이름");
		lblName_first.setBounds(69, 163, 69, 20);		// 69, 210, 69, 20
		contentPane.add(lblName_first);					

		JLabel lblConatact_no = new JLabel("전화번호");
		lblConatact_no.setBounds(69, 213, 69, 20);			// 69, 304, 69, 20
		contentPane.add(lblConatact_no);
		
		tfLast_name = new JTextField();
		tfLast_name.setColumns(10);
		tfLast_name.setBounds(159, 106, 186, 35);	// 159, 203, 186, 35
		contentPane.add(tfLast_name);

		tfFirst_name = new JTextField();
		tfFirst_name.setColumns(10);
		tfFirst_name.setBounds(159, 156, 186, 35);	// new
		contentPane.add(tfFirst_name);

		tfContact_no = new JTextField();
		tfContact_no.setColumns(10);
		tfContact_no.setBounds(159, 206, 186, 35);	// 159, 297, 186, 35
		contentPane.add(tfContact_no);
		
		joinCompleteBtn = new JButton("회원가입완료");
		joinCompleteBtn.setBounds(206, 256, 139, 29);
		contentPane.add(joinCompleteBtn);
		
		setVisible(true);
		//회원가입완료 액션
		joinCompleteBtn.addActionListener(new ActionListener() {
			
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
					JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "회원가입이 실패하였습니다.");
				}	
			}
		});

	}
}

