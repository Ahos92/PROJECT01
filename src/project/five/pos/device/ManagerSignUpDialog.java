package project.five.pos.device;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import project.five.pos.TestSwingTools;
import project.five.pos.db.PosVO;
import project.five.pos.device.comp.DeviceLab;
import project.five.pos.device.comp.btn.DeviceBtn;
import project.five.pos.device.comp.tf.action.PromptAction;
import project.five.pos.device.comp.tf.DeviceTF;

public class ManagerSignUpDialog extends JDialog {

	JPanel center_p, south_p;
	JTextField id_tf, pw_tf, lname_tf, fname_tf, tel_tf;
	JLabel id_lab, pw_lab, lname_lab, fname_lab, tel_lab;
	JButton sign_btn;
	
	Font font;
	
	public ManagerSignUpDialog(JFrame frame, String title) {
		super(frame, title);
		setLayout(new BorderLayout());
		setSize(300, 350);
		setResizable(false);// 사이즈 변경 불가
		setLocationRelativeTo(null);
		
		center_p = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
		south_p = new JPanel();

		id_lab = new DeviceLab("아이디", 50, 30);
		id_tf = createTf("숫자 1~5 자리");

		pw_lab = new DeviceLab("비밀번호", 50, 30);
		pw_tf = new JPasswordField(16);
		pw_tf.setPreferredSize(new Dimension(40, 30));

		lname_lab = new DeviceLab("성", 50, 30);
		lname_tf = createTf("한글 1~10 글자");
		
		fname_lab = new DeviceLab("이름", 50, 30);
		fname_tf = createTf("한글 1~10 글자");
		
		tel_lab = new DeviceLab("전화번호", 50, 30);
		tel_tf = createTf("000-0000-0000 / 000-000-0000");
		
		sign_btn = new DeviceBtn("등록", 60, 30, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeviceDAO dao = new DeviceDAO();
				PosVO manager = new PosVO();
				int b_id = 0;
				try {
					b_id = Integer.parseInt(id_tf.getText());
				} catch (NumberFormatException nfe) {}
				manager.setBusiness_id(b_id);
				manager.setBusiness_pw(pw_tf.getText());
				manager.setB_last_name(lname_tf.getText());
				manager.setB_first_name(fname_tf.getText());
				manager.setB_contact_no(tel_tf.getText());
				
				if (dao.SighUPManager(manager)) {
					new ConfirmDialog(frame, "매니저 등록", "등록 완료!!");
					dispose();
				} else {
					new ConfirmDialog(frame, "매니저 등록", "정확히 입력해주세요!");
				}
			}
		});

		center_p.add(id_lab);
		center_p.add(id_tf);
		center_p.add(pw_lab);
		center_p.add(pw_tf);
		center_p.add(lname_lab);
		center_p.add(lname_tf);
		center_p.add(fname_lab);
		center_p.add(fname_tf);
		center_p.add(tel_lab);
		center_p.add(tel_tf);
		
		south_p.add(sign_btn);
		
		add(center_p, BorderLayout.CENTER);
		add(south_p, BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	private JTextField createTf(String txt) {
		JTextField tf = new DeviceTF(txt, 16, 40, 30);
		tf.addFocusListener(new PromptAction(tf, txt));
		return tf;
	}

}
