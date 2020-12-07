package project.five.pos.payment.swing;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import project.five.pos.payment.swing.btn.action.BtnAction;
import project.five.pos.payment.swing.btn.action.ClickedBtnAction;



public class PayPanel extends JFrame {
	
	ImageIcon bg = new ImageIcon(ImageIO.read(new File("assets/images/background.jpg")).getScaledInstance(1000, 1000, Image.SCALE_SMOOTH));
	
	//�ڷγ� ���� �̹���
	final static String IMG_COVID = "C:\\�ڹ� ����� ����\\covid19.png";

	static int price = 5000;
	static String lists = "���̽� �Ƹ޸�ī��";
	
	
	
	public PayPanel() throws IOException {
		
		
		
		JTextField register = new JTextField("����� �Է�");
		JTextField input_cp = new JTextField("���� �Է�");
		
		// ������ ���� �Լ�		
		
		
		
		//�������� �г� ����
		JPanel south_panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JPanel center_panel = new JPanel(new GridLayout(5, 5, 5, 5));
		JPanel north_panel = new JPanel(new FlowLayout(FlowLayout.CENTER,5, 5));
		JPanel west_panel = new JPanel(new CardLayout(15, 15));
		JPanel east_panel = new JPanel(new CardLayout(15, 15));
		
		south_panel.setOpaque(false);
		center_panel.setOpaque(false);
		north_panel.setOpaque(false);
		west_panel.setOpaque(false);
		east_panel.setOpaque(false);
		
		//�ִϾ� �г� ����
		JPanel junior_panel = new JPanel(new GridLayout(1, 2, 5, 5));	// ī��, ����
		
		JPanel junior_panel2 = new JPanel(new GridLayout(1, 2, 5, 5));	// ����� �Է� / ���
		
		
		//�� ����
		JLabel branch_name = new JLabel("Fancy a cuppa?");
		branch_name.setForeground(Color.WHITE);
		
		
		JLabel speicality = new JLabel("��õ �޴�");
		
		
		JLabel item_list = new JLabel("�ֹ��Ͻ� ��ǰ :" + lists + "(�̹����� ��ü)");
		JLabel total_price = new JLabel("�� ������ �ݾ� : " + price, SwingConstants.CENTER);
		
		
		item_list.setForeground(Color.WHITE);
		total_price.setForeground(Color.WHITE);
		
		
		//��ư ����
		JButton ckmem_btn = new JButton("����� �Է�");
		
		
		JButton register_btn = new JButton("����� ���");
		
		register_btn.addActionListener(new BtnAction(register_btn));
		
		
		
		JButton card_btn = new JButton("ī��");
		JButton cash_btn = new JButton("����");
		
		//��ư �׵θ� ����
		card_btn.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		cash_btn.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		
		JButton cancel_btn = new JButton("����ϱ�");
		JButton payment_btn = new JButton("�����ϱ�");
		
		//��ư ���(�Լ� ClickedBtnAction)
		card_btn.addActionListener(new ClickedBtnAction(junior_panel, "ī��"));
		cash_btn.addActionListener(new ClickedBtnAction(junior_panel,"����"));
		
		//�г� �߰�
		
		
		// �̸� ���� ���
		north_panel.add(branch_name);
		
		// ��õ�޴� �߰�
		for(ImageEnum imgname : ImageEnum.values()) {
			west_panel.add(imgname.getLname(), new ImageLabel(imgname));
		}
		// �޴� ������Ʈ(Ŭ��) -> �ð� ������ ���� �ڵ����� �ٲ㺸��
		west_panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(e.getButton() == MouseEvent.BUTTON1) {
					((CardLayout)west_panel.getLayout()).next(west_panel);
				}
			}
		});
		
		// �ڷγ� ����ũ ������ �߰�
		try {
			east_panel.add(new JLabel(new ImageIcon(ImageIO.read(new File(IMG_COVID)).getScaledInstance(150, 400, Image.SCALE_SMOOTH))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//��ٱ��� �г�
		center_panel.add(item_list);
		
		//����� �г�
		center_panel.add(junior_panel2);
		junior_panel2.add(ckmem_btn);
		junior_panel2.add(register_btn);
		
		
		//���� �г�
		center_panel.add(input_cp);
		
		//���� Ÿ�� �г�
		center_panel.add(junior_panel);
		junior_panel.add(card_btn);
		junior_panel.add(cash_btn);
		
		
		center_panel.add(total_price);
		
		//���� ���� �г�
		south_panel.add(cancel_btn);
		south_panel.add(payment_btn);
		
		//���� �ҽ�
		setVisible(true);
		
		ProSwingTools.initTestFrame(this);
		JLabel label = new JLabel(bg);
		label.setBounds(0, 0, 1000, 900);
		JPanel back = new JPanel(new BorderLayout());
		back.setBounds(0, 0, 1000, 900);
		back.add(center_panel, BorderLayout.CENTER);
		back.add(north_panel, BorderLayout.NORTH);
		back.add(west_panel, BorderLayout.WEST);
		back.add(east_panel, BorderLayout.EAST);
		back.add(south_panel, BorderLayout.SOUTH);
		back.setOpaque(false);
		add(back);
		add(label);
		
		
	}
	
	public static void main(String[] args) {
		try {
			new PayPanel();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
