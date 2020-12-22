package project.five.pos.menu;


import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Menu;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.border.Border;

import project.five.pos.TestSwingTools;
import project.five.pos.cart.CartPopUpDisplay;
import project.five.pos.db.PosVO;
import project.five.pos.device.comp.btn.DeviceBtn;
import project.five.pos.device.comp.btn.action.ChangeFrameAction;
import project.five.pos.payment.swing.ImageEnum;
import project.five.pos.payment.swing.ImageLabel;

public class MenuDisplay extends JFrame{

	String[] cateG = MenuDAO.getCategories();
	static int allMenu = MenuDAO.allMenus();
	JPanel menubyul[] = new JPanel[cateG.length];
	JScrollPane[] scroll = new JScrollPane[cateG.length];
	static Object[][] allCart = new Object[allMenu][4];
	JPanel downP;

	String IMG_COVID = "assets/images/covid19.png";
	String BG_IMG = "assets/images/backimg5.jpg";
	
	public MenuDisplay() {
		TestSwingTools.initTestFrame(this, "�޴� ȭ��", true);
		MenuDisplay.allCart = new Object[MenuDisplay.allMenu][4];
		addCart.x = 0;
		
		JPanel center_panel = new JPanel();
		JTabbedPane category = new JTabbedPane();
		
		JPanel north_panel = new JPanel(new FlowLayout(FlowLayout.CENTER,5, 5));
		JPanel west_panel = new JPanel(new CardLayout(15, 15));
		JPanel east_panel = new JPanel(new CardLayout(15, 15));

		north_panel.setOpaque(false);
		west_panel.setOpaque(false);
		east_panel.setOpaque(false);
		
		JLabel title = new JLabel("�ֹ� ȭ�� ~ ");
		title.setFont(new Font("Courier" , Font.BOLD, 23));
		title.setForeground(getForeground().WHITE);
		
		JLabel label = null;
		try {
			BufferedImage bgImage = ImageIO.read(new File(BG_IMG));
			int bgx = bgImage.getWidth();
			int bgy = bgImage.getHeight();
			label = new JLabel(new ImageIcon(ImageIO.read(new File(BG_IMG)).getScaledInstance(bgx, bgy, Image.SCALE_SMOOTH)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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
		
		try {
			BufferedImage coImage = ImageIO.read(new File(IMG_COVID));
			int cox = coImage.getWidth();
			int coy = coImage.getHeight();
			east_panel.add(new JLabel(new ImageIcon(ImageIO.read(new File(IMG_COVID)).getScaledInstance(cox/5, coy/3, Image.SCALE_SMOOTH))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//int x = 0;
		for (int i = 0; i < cateG.length; i++) {
			menubyul[i] = new MenuPanel(cateG[i]);
			scroll[i] = new JScrollPane();
			scroll[i].setViewportView(menubyul[i]);
			scroll[i].setPreferredSize(new Dimension(450, 645));
			category.addTab(cateG[i], scroll[i]);
		}
		
		north_panel.add(title);
		center_panel.add(category);
		center_panel.setOpaque(false);
		
		downP = new SetPanel(this);
		downP.setOpaque(false);

		label.setLayout(new BorderLayout());
		label.add(north_panel, BorderLayout.NORTH);
		label.add(west_panel, BorderLayout.WEST);
		label.add(east_panel, BorderLayout.EAST);
		label.add(center_panel, BorderLayout.CENTER);
		label.add(downP, BorderLayout.SOUTH);

		add(label);
		
		setVisible(true);

	}

	public static void main(String[] args) {
		new MenuDisplay();
	}

}



class MenuPanel extends JPanel{
	private int count = 0;

	public MenuPanel(String cate) {
		Object[][] menus = MenuDAO.getMenus(cate);
		String[] namelist= new String[menus.length];
		String[] condilist= new String[menus.length];
		int[] pricelist= new int[menus.length]; 
		int[] countlist= new int[menus.length];

		for(int i = 0; i < menus.length; ++i) {
			namelist[i] = (String)menus[i][0];
			condilist[i] = (String)menus[i][1];
			countlist[i] = (int)menus[i][2];
			pricelist[i] = (int)menus[i][3];
		}
		JButton bt[] = new JButton[namelist.length];
		TextField[] suja = new TextField[namelist.length];
		Button[] minus = new Button[namelist.length];
		Button plus[] = new Button[namelist.length];
		JButton ok[] = new JButton[namelist.length];
		Label l[] = new Label[namelist.length];
		Label l2[] = new Label[namelist.length];
		ImageIcon icon[] = new ImageIcon[namelist.length];

		Dimension size = new Dimension();
		int sizeY = (namelist.length + 1) / 2 * 300;
		size.setSize(400, sizeY);


		// ��ư �κ�
		int x = 55; int y = 40;
		for (int i = 0; i < namelist.length; i++) {

			// �޴� ��ư
			String file_path;
			if(condilist[i] == null) {
				file_path ="assets/images/"+namelist[i]+".png";
			}else {
				file_path ="assets/images/"+namelist[i]+condilist[i]+".png";
			}
			bt[i] = new DeviceBtn(namelist[i], file_path, 140);
			if (i % 2 != 0 || i == 0) {
				bt[i].setBounds(x + (i % 2) * 175, y, 150, 170);
			} else {
				y+=290;
				bt[i].setBounds(x + (i % 2) * 175, y, 150, 170);
			}

			// ���� txt ��ư
			suja[i] = new TextField("0");
			suja[i].setBackground(Color.white);
			suja[i].setEditable(false);
			suja[i].setBounds(bt[i].getX() + 50, bt[i].getY() + 225, 40, 20);

			// "-" ��ư
			minus[i] = new Button("-");
			minus[i].setBounds(bt[i].getX() + 20, suja[i].getY(), 20, 20);
			minus[i].setEnabled(false);

			// "+" ��ư
			plus[i] = new Button("+");
			plus[i].setBounds(bt[i].getX() + 100, suja[i].getY(), 20, 20);
			plus[i].setEnabled(false);

			// ����
			l[i] = new Label(pricelist[i] + "��    (���� : "+ countlist[i]+")");
			l[i].setBounds(bt[i].getX() + 20, suja[i].getY() - 25, 120, 20);

			// ����
			if(condilist[i] == null) {
				l2[i] = new Label(" �� ");
				l2[i].setBounds(bt[i].getX() + 60, l[i].getY() - 25, 120, 20);
			}else {
				l2[i] = new Label("[   "+condilist[i]+"   ]");
				l2[i].setFont(new Font(null, Font.BOLD, 12));
				l2[i].setBounds(bt[i].getX() + 48, l[i].getY() - 25, 120, 20);
			}

			// Ȯ�� ��ư
			ok[i] = new JButton("Ȯ��");
			ok[i].setBounds(bt[i].getX()+20, suja[i].getY() + 30, 100, 20);
			ok[i].setEnabled(false);


			add(bt[i]);
			add(suja[i]);
			add(minus[i]);
			add(plus[i]);
			add(l[i]);
			add(l2[i]);
			add(ok[i]);

			setPreferredSize(size);
			setBackground(new Color(238, 230, 196));
			setLayout(null);
		}

		// �޴� �̺�Ʈ
		for (int i = 0; i < namelist.length; i++) {
			int j = i;

			// �޴���ư
			bt[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					minus[j].setEnabled(true);
					plus[j].setEnabled(true);
					bt[j].setEnabled(false);

					// ok[j] ����
					count = 0;

				}

			});

			// "-"��ư
			minus[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					if (count > 0) {

						count = count - 1;

						suja[j].setText(count + "");
						ok[j].setEnabled(true);

					} else {
						minus[j].setEnabled(false);

					}

				}

			});

 

			//"+"��ư
			plus[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					count = count + 1;
					suja[j].setText(count + "");
					ok[j].setEnabled(true);   //�߰�
					if (count > 0) {

						minus[j].setEnabled(true);

					if(count >= countlist[j])

							plus[j].setEnabled(false);

					} else {

						ok[j].setEnabled(false);  // �߰�

					}

				}

			});

 

			// Ȯ�� ��ư
			ok[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					if(count == 0) {
						bt[j].setEnabled(true);
					
					} else {

						new addCart(namelist[j],condilist[j],pricelist[j],count);  // ����

					}

					minus[j].setEnabled(false);
					plus[j].setEnabled(false);
					ok[j].setEnabled(false);

				}

			});

		}
	}
}

// ��ư ���� ���� ����
class addCart{
	static int x=0;
	
	public addCart(Object name, Object condi, Object price, Object count) {
		
		MenuDisplay.allCart[x][0]= name;
		MenuDisplay.allCart[x][1]= condi;
		MenuDisplay.allCart[x][2]= count;
		MenuDisplay.allCart[x][3]= price;

		x+=1;
		System.out.println(Arrays.deepToString(MenuDisplay.allCart));
	}
}



class SetPanel extends JPanel{
	
	public SetPanel(JFrame frame) {
		setLayout(new BorderLayout());
		
		JButton bt1 = new DeviceBtn("> To Main page", 130, 30, new ChangeFrameAction(frame), false);
		JButton bt2 = new DeviceBtn("�ֹ�", 60, 30);
		JButton bt3 = new DeviceBtn("�ʱ�ȭ", 80, 30);
		
		JLabel device_lab = new JLabel("device_ID : ");
		device_lab.setPreferredSize(new Dimension(120, 30));
		device_lab.setFont(new Font("Courier", Font.BOLD, 15));
		device_lab.setForeground(getForeground().white);
		
		JPanel center_p = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0)); 
		center_p.setOpaque(false);
		
		bt1.setBackground(new Color(108, 62, 37));
		
		center_p.add(bt2);
		center_p.add(bt3);

		add(device_lab, BorderLayout.WEST);
		add(center_p, BorderLayout.CENTER);
		add(bt1, BorderLayout.EAST);
		

		
		// �ֹ���ư
		bt2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				new CartPopUpDisplay(frame, "�ֹ� ����",1234, MenuDisplay.allCart);
				
				// �迭 Ȯ�ο�
				System.out.println(Arrays.deepToString(MenuDisplay.allCart));

			}
		});

		
		// �ʱ�ȭ ��ư
		bt3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ��â�� ������ ���� â�� ������
				MenuDisplay.allCart = new Object[MenuDisplay.allMenu][4];
				addCart.x = 0;
				new MenuDisplay();
				frame.dispose();
				
			}
		});
	}
}
