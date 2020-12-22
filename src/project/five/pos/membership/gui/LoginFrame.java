//package project.five.pos.membership.gui;
//
//import java.awt.EventQueue;
//
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JTextField;
//import javax.swing.border.EmptyBorder;
//import project.five.pos.membership.gui.LoginFrame;
//import project.five.pos.membership.dao.MemberDao;
//
//public class LoginFrame extends JFrame {
//
//	private JPanel contentPane;
//	private JTextField tfPhnoe; 
//	private JButton loginBtn, joinBtn;
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					LoginFrame frame = new LoginFrame();
//	
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the frame.
//	 */
//	public LoginFrame() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setSize(400, 300);
//		setLocationRelativeTo(null);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));	
//		setContentPane(contentPane);
//		contentPane.setLayout(null);
//		
//		JLabel lblLogin = new JLabel("user ID");		
//		lblLogin.setBounds(41, 52, 69, 35);
//		contentPane.add(lblLogin);
//		
//		
//		tfPhnoe = new JTextField();
//		tfPhnoe.setBounds(157, 52, 176, 35);
//		contentPane.add(tfPhnoe);
//		tfPhnoe.setColumns(10);
//		
//		joinBtn = new JButton("Sign UP");
//		joinBtn.setBounds(200, 150, 100, 30);
//		contentPane.add(joinBtn);
//		
//		loginBtn = new JButton("Sign IN");
//		loginBtn.setBounds(80, 150, 100, 30);
//		contentPane.add(loginBtn);
//		
//		
//		setVisible(true);
//		//회원가입 액션
//		joinBtn.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				JoinFrame frame = new JoinFrame();
//			}
//		});
//		
//		//로그인 액션
//		loginBtn.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				String id = tfPhnoe.getText();				
//				MemberDao dao = MemberDao.getInstance();
//				int result = dao.findByUsernameAndPassword(id);		
//				if(result == 1) {
//					//로그인 성공 메시지
//					JOptionPane.showMessageDialog(null, "로그인 성공");
//					//회원 정보 리스트 화면 이동과 동시에 username 세션값으로 넘김.
//					MemberListFrame mlf = new MemberListFrame(id);
//					//현재 화면 종료
//					dispose();
//				}else {
//					JOptionPane.showMessageDialog(null, "로그인 실패");
//				}
//				
//			}
//		});
//	}
//}