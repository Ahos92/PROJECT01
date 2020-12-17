package project.five.pos.device.comp.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import project.five.pos.device.comp.DeviceLab;

public class LoadingDialog extends JDialog {
	JLabel msg_lab;
	JButton ok_btn;
	JPanel center_p, south_p;

	public LoadingDialog(JFrame frame, String title, JDialog settle) {
		super(frame, title);
		setLayout(new BorderLayout());
		setSize(200, 130);
		setResizable(false);
		setLocationRelativeTo(null);
		
		
		center_p = new JPanel(new FlowLayout(FlowLayout.CENTER));
		south_p = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		msg_lab = new DeviceLab("정산이 완료 되었습니다.", 150, 30);
		ok_btn = new JButton("확인");
		ok_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {		
					Thread.sleep(1000);
					System.exit(0);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}	
				
			}
		});
		
		center_p.add(msg_lab);
		south_p.add(ok_btn);
		
		add(center_p, BorderLayout.CENTER);
		add(south_p, BorderLayout.SOUTH);
		
		settle.dispose();
		
		setVisible(true);	

		try {		
			Thread.sleep(2000);	
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}	
		

	}

}
