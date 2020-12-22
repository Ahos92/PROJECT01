package project.five.pos.menu;


import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.awt.Menu;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import project.five.pos.TestSwingTools;
import project.five.pos.cart.CartPopUpDisplay;
import project.five.pos.db.PosVO;
import project.five.pos.device.comp.btn.DeviceBtn;
import project.five.pos.device.comp.btn.action.ChangeFrameAction;

public class MenuDisplay extends JFrame{

	String[] cateG = MenuDAO.getCategories();
	static int allMenu = MenuDAO.allMenus();
	JPanel menubyul[] = new JPanel[cateG.length];
	JScrollPane[] scroll = new JScrollPane[cateG.length];
	static Object[][] allCart = new Object[allMenu][4];
	JPanel downP;


	public MenuDisplay() {
		TestSwingTools.initTestFrame(this, "메뉴 화면", true);
		MenuDisplay.allCart = new Object[MenuDisplay.allMenu][4];
		addCart.x = 0;
		JTabbedPane category = new JTabbedPane();

		//int x = 0;
		for (int i = 0; i < cateG.length; i++) {
			menubyul[i] = new MenuPanel(cateG[i]);
			scroll[i] = new JScrollPane();
			scroll[i].setViewportView(menubyul[i]);
			category.addTab(cateG[i], scroll[i]);
		}

		add(category);

		downP = new SetPanel(this);
		downP.setBackground(new Color(108, 62, 37));

		add(downP, BorderLayout.SOUTH);
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
		int sizeY = (namelist.length + 1) / 2 * 300 + 20;
		size.setSize(400, sizeY);


		// 버튼 부분
		int x = 55; int y = 40;
		for (int i = 0; i < namelist.length; i++) {

			// 메뉴 버튼
			String file_path;
			if(condilist[i] == null) {
				file_path ="assets/images/"+namelist[i]+".png";
			}else {
				file_path ="assets/images/"+namelist[i]+condilist[i]+".png";
			}
			bt[i] = new DeviceBtn(namelist[i], file_path, 140);
			if (i % 2 != 0 || i == 0) {
				bt[i].setBounds(x + (i % 2) * 210, y, 150, 170);
			} else {
				y+=290;
				bt[i].setBounds(x + (i % 2) * 210, y, 150, 170);
			}

			// 숫자 txt 버튼
			suja[i] = new TextField("0");
			suja[i].setBackground(Color.white);
			suja[i].setEditable(false);
			suja[i].setBounds(bt[i].getX() + 50, bt[i].getY() + 225, 40, 20);

			// "-" 버튼
			minus[i] = new Button("-");
			minus[i].setBounds(bt[i].getX() + 20, suja[i].getY(), 20, 20);
			minus[i].setEnabled(false);

			// "+" 버튼
			plus[i] = new Button("+");
			plus[i].setBounds(bt[i].getX() + 100, suja[i].getY(), 20, 20);
			plus[i].setEnabled(false);

			// 가격
			l[i] = new Label(pricelist[i] + "원    (수량 : "+ countlist[i]+")");
			l[i].setBounds(bt[i].getX() + 20, suja[i].getY() - 25, 120, 20);

			// 구분
			if(condilist[i] == null) {
				l2[i] = new Label(" ♥ ");
				l2[i].setBounds(bt[i].getX() + 60, l[i].getY() - 25, 120, 20);
			}else {
				l2[i] = new Label("[   "+condilist[i]+"   ]");
				l2[i].setFont(new Font(null, Font.BOLD, 12));
				l2[i].setBounds(bt[i].getX() + 48, l[i].getY() - 25, 120, 20);
			}

			// 확인 버튼
			ok[i] = new JButton("확인");
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
			setBackground(new Color(	143, 74, 7));
			setLayout(null);
		}

		// 메뉴 이벤트
		for (int i = 0; i < namelist.length; i++) {
			int j = i;

			// 메뉴버튼
			bt[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					minus[j].setEnabled(true);
					plus[j].setEnabled(true);
					bt[j].setEnabled(false);

					// ok[j] 삭제
					count = 0;

				}

			});

			// "-"버튼
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

 

			//"+"버튼
			plus[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					count = count + 1;
					suja[j].setText(count + "");
					ok[j].setEnabled(true);   //추가
					if (count > 0) {

						minus[j].setEnabled(true);

					if(count >= countlist[j])

							plus[j].setEnabled(false);

					} else {

						ok[j].setEnabled(false);  // 추가

					}

				}

			});

 

			// 확인 버튼
			ok[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					if(count == 0) {
						bt[j].setEnabled(true);
					
					} else {

						new addCart(namelist[j],condilist[j],pricelist[j],count);  // 변경

					}

					minus[j].setEnabled(false);
					plus[j].setEnabled(false);
					ok[j].setEnabled(false);

				}

			});

		}
	}
}

// 버튼 값을 누적 저장
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
		
		JButton bt1 = new DeviceBtn("> To Main page", 130, 30, new ChangeFrameAction(frame));
		JButton bt2 = new JButton("주문");
		JButton bt3 = new JButton("초기화");
		
		bt1.setBackground(new Color(153, 102, 51));
		
		add(bt1);
		add(bt2);
		add(bt3);
		

		
		// 주문버튼
		bt2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				new CartPopUpDisplay(frame, "주문 내역",1234, MenuDisplay.allCart);
				
				// 배열 확인용
				System.out.println(Arrays.deepToString(MenuDisplay.allCart));

			}
		});

		
		// 초기화 버튼
		bt3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 새창이 열리고 이전 창이 지워짐
				MenuDisplay.allCart = new Object[MenuDisplay.allMenu][4];
				addCart.x = 0;
				new MenuDisplay();
				frame.dispose();
				
			}
		});
	}
}
