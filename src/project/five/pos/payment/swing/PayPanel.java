package project.five.pos.payment.swing;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

import project.five.pos.db.PosVO;
import project.five.pos.device.comp.btn.action.ChangeFrameAction;
import project.five.pos.payment.swing.btn.action.BtnAction;
import project.five.pos.payment.swing.btn.action.ClickedBtnAction;

public class PayPanel extends JFrame {
	
	JFrame frame;
		
	// �� ��� �̹���
	final static String BG_IMG = "assets/images/backimg5.jpg";
	
	//�ڷγ� ���� �̹���
	final static String IMG_COVID = "assets/images/covid19.png";
	
	static JPanel main_center_panel;
	
	public static JPanel center_panel;
	public static CardLayout main_card;
	
	static JPanel junior_panel;
	static JPanel junior_panel2;
	
	static JButton ckmem_btn;
	static JButton register_btn;
	
	public static JPanel card_panel;
	public static CardLayout card;
	
	static JButton card_btn;
	static JButton cash_btn;
							
	public PayPanel(int order_num, int price, ArrayList<String> lists2, ArrayList<PosVO> update_cart) throws IOException {
				
		//�������� �г� ����
		JPanel south_panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		main_center_panel = new JPanel();
		main_center_panel.setOpaque(false);
		main_card = new CardLayout();
		main_center_panel.setLayout(main_card);
		
		center_panel = new JPanel(new GridLayout(4, 4, 5, 5));
		
		
		JPanel north_panel = new JPanel(new FlowLayout(FlowLayout.CENTER,5, 5));
		JPanel west_panel = new JPanel(new CardLayout(15, 15));
		JPanel east_panel = new JPanel(new CardLayout(15, 15));
				
		south_panel.setOpaque(false);
		center_panel.setOpaque(false);
		north_panel.setOpaque(false);
		west_panel.setOpaque(false);
		east_panel.setOpaque(false);
		
		main_center_panel.add("������", center_panel);
		
		JPanel productPanel = new JPanel(new GridLayout(2, 1, 0, 0));
		productPanel.setOpaque(false);
		
		JLabel stnsProduct = new JLabel("�ֹ� �Ͻ� ��ǰ ����Ʈ", SwingConstants.CENTER);
		stnsProduct.setForeground(Color.WHITE);
		stnsProduct.setFont(new Font("ī��24 ���� ����",Font.BOLD, 16));
		
		JPanel productList = new JPanel(new FlowLayout());
		productList.setOpaque(false);
		
		//�ִϾ� �г� ����
		junior_panel = new JPanel(new GridLayout(1, 2, 5, 5));	// ī��, ����
		
		// ����� �г� ����
		card_panel = new JPanel();
		card_panel.setOpaque(false);
		card = new CardLayout();
		 
		card_panel.setLayout(card);
		
		junior_panel2 = new JPanel(new GridLayout(1, 2, 5, 5));	// ����� �Է� / ���
				
		card_panel.add("��ư", junior_panel2);
	
		
		junior_panel.setOpaque(false);
		junior_panel2.setOpaque(false);
		
		
		//ī�� �̸�
		JLabel branch_name = new JLabel("Fancy a cuppa?");
		branch_name.setForeground(Color.WHITE);
		branch_name.setFont(new Font("ī��24 ���� ����",Font.BOLD, 36));
				
		JLabel total_price = new JLabel("�� ���� �ݾ� : " + price + "(��)", SwingConstants.CENTER);
		total_price.setForeground(Color.WHITE);
		total_price.setFont(new Font("ī��24 ���� ����",Font.BOLD, 16));
				
		//��ư ����
		ckmem_btn = new RoundedButton("����� �Է�");
		ckmem_btn.setFont(new Font("ī��24 ���� ����",Font.BOLD, 16));
		
		register_btn = new RoundedButton("����� ���");
		register_btn.setFont(new Font("ī��24 ���� ����",Font.BOLD, 16));
		
		register_btn.addActionListener(new BtnAction(register_btn));
		ckmem_btn.addActionListener(new BtnAction(ckmem_btn));

		card_btn = new RoundedButton("ī��");
		card_btn.setFont(new Font("ī��24 ���� ����",Font.BOLD, 16));
		cash_btn = new RoundedButton("����");
		cash_btn.setFont(new Font("ī��24 ���� ����",Font.BOLD, 16));
		
		
		//��ư �׵θ� ����
		card_btn.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		cash_btn.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		
		JButton cancel_btn = new RoundedButton("����ϱ�");
		cancel_btn.addActionListener(new ChangeFrameAction(this));
		
		JButton payment_btn = new RoundedButton("�����ϱ�");
		payment_btn.setEnabled(false);
		
		payment_btn.addActionListener(new BtnAction(payment_btn, price, this, order_num, lists2, update_cart));
		
		
		//��ư ���(�Լ� ClickedBtnAction)
		card_btn.addActionListener(new ClickedBtnAction(junior_panel, "ī��", card_btn, cash_btn, payment_btn, price));
		cash_btn.addActionListener(new ClickedBtnAction(junior_panel,"����", card_btn, cash_btn, payment_btn, price));
		
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
			BufferedImage coImage = ImageIO.read(new File(IMG_COVID));
			int cox = coImage.getWidth();
			int coy = coImage.getHeight();
			east_panel.add(new JLabel(new ImageIcon(ImageIO.read(new File(IMG_COVID)).getScaledInstance(cox/5, coy/3, Image.SCALE_SMOOTH))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < lists2.size(); i++) {
			productList.add(new SetLabel(lists2.get(i), update_cart.get(i).getSelected_item(), 14));
		}
				
		//��ٱ��� ����Ʈ �гε�
		productPanel.add(stnsProduct);
		productPanel.add(productList);
		
		//��ٱ��� �г�
		center_panel.add(productPanel);
		
		//����� �г�
		center_panel.add(card_panel);
		junior_panel2.add(ckmem_btn);
		junior_panel2.add(register_btn);
	
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
		JLabel label = null;
		try {
			BufferedImage bgImage = ImageIO.read(new File(BG_IMG));
			int bgx = bgImage.getWidth();
			int bgy = bgImage.getHeight();
			label = new JLabel(new ImageIcon(ImageIO.read(new File(BG_IMG)).getScaledInstance(bgx, bgy, Image.SCALE_SMOOTH)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		label.setBounds(0, 0, 875, 750);
		JPanel back = new JPanel(new BorderLayout());
		back.setBounds(0, 0, 875, 750);
		back.add(main_center_panel, BorderLayout.CENTER);
		back.add(north_panel, BorderLayout.NORTH);
		back.add(west_panel, BorderLayout.WEST);
		back.add(east_panel, BorderLayout.EAST);
		back.add(south_panel, BorderLayout.SOUTH);
		back.setOpaque(false);
		add(back);
		add(label);
		
				
	}
	
}
