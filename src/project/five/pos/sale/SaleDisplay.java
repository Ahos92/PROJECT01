package project.five.pos.sale;


import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Label;
import java.awt.Menu;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
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
import javax.swing.JTextField;

import project.five.pos.TestSwingTools;
import project.five.pos.db.MenuDAO;
import project.five.pos.db.PosVO;

public class SaleDisplay extends JFrame{
	int count = 0;
	String show = "";
	int cartNo =0;
	int on=0;


	public SaleDisplay() {
		TestSwingTools.initTestFrame(this, "메뉴 화면", true);
		Container con = this.getContentPane();
		JScrollPane scroll;

		// 배열 설정 부분
		String[] category = MenuDAO.getCategories();
		JMenu menu[] = new JMenu[category.length];

		// 위패널
		Panel upP = new Panel();
		JMenuBar categ = new JMenuBar();
		for (int i = 0; i < category.length; i++) {
			menu[i] = new JMenu(category[i]);
			categ.add(menu[i]);
		}
		upP.add(categ);

		String cate = menu[1].getActionCommand();
		Object[][] menus = MenuDAO.getMenus(cate);
		String[] namelist= new String[menus.length];
		String[] condilist= new String[menus.length];
		int[] pricelist= new int[menus.length]; 
		int[] countlist= new int[menus.length];

		for (int i = 0; i < menus.length; i++) {
			for (int j = 0; j < menus[i].length; j++) {
				System.out.print(menus[i][j] + " ");
				System.out.println();
			}
		}

		for(int i = 0; i < menus.length; ++i) {
			namelist[i] = (String)menus[i][0];
			pricelist[i] = (int)menus[i][1];
			countlist[i] = (int)menus[i][2];
			condilist[i] = (String)menus[i][3];
		}
		JButton bt[] = new JButton[namelist.length];
		TextField[] suja = new TextField[namelist.length];
		Button[] minus = new Button[namelist.length];
		Button plus[] = new Button[namelist.length];
		JButton ok[] = new JButton[namelist.length];
		Label l[] = new Label[namelist.length];
		Label l2[] = new Label[namelist.length];
		ImageIcon icon[] = new ImageIcon[namelist.length];
		Object[][] cart = new Object[namelist.length][4];;



		// 메뉴 패널
		JPanel center_panel = new JPanel();
		add(center_panel);
		center_panel.setLayout(null);
		center_panel.setSize(0, 600);
		scroll = new JScrollPane(center_panel);
		scroll.setBounds(0,0,0, 400);

		// 버튼 부분
		int x = 60; int y = 40;
		for (int i = 0; i < namelist.length; i++) {

			// 메뉴 버튼
			bt[i] = new JButton(namelist[i]);
			if (i % 2 != 0 || i == 0) {
				bt[i].setBounds(x + (i % 2) * 210, y, 150, 150);
			} else {
				y+=290;
				bt[i].setBounds(x + (i % 2) * 210, y, 150, 150);
			}
			icon[i] = new ImageIcon(i + ".png");
			bt[i].setIcon(icon[i]);

			// 숫자 txt 버튼
			suja[i] = new TextField("0");
			suja[i].setBackground(Color.white);
			suja[i].setEditable(false);
			suja[i].setBounds(bt[i].getX() + 50, bt[i].getY() + 205, 40, 20);

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
				l2[i] = new Label(">ㅇ<");
				l2[i].setBounds(bt[i].getX() + 48, l[i].getY() - 25, 120, 20);
			}else {
				l2[i] = new Label("[   "+condilist[i]+"   ]");
				l2[i].setFont(new Font(null, Font.BOLD, 12));
				l2[i].setBounds(bt[i].getX() + 48, l[i].getY() - 25, 120, 20);
			}

			// 확인 버튼
			ok[i] = new JButton("확인");
			ok[i].setBounds(bt[i].getX()+20, suja[i].getY() + 30, 100, 20);
			ok[i].setEnabled(false);

			center_panel.add(bt[i]);
			center_panel.add(suja[i]);
			center_panel.add(minus[i]);
			center_panel.add(plus[i]);
			center_panel.add(l[i]);
			center_panel.add(l2[i]);
			center_panel.add(ok[i]);
		}
		// 아래패널
		Panel downP = new Panel();
		downP.setBackground(new Color(108, 62, 37));

		Button bt1 = new Button("주문");
		Button bt2 = new Button("초기화");
		downP.add(bt1);
		downP.add(bt2);

		add(upP, BorderLayout.NORTH);
		add(scroll);
		add(downP, BorderLayout.SOUTH);
		setVisible(true);

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
					ok[j].setEnabled(true);

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
					ok[j].setEnabled(true);
					if (count > 0) {
						minus[j].setEnabled(true);
						if(count >= countlist[j])
							plus[j].setEnabled(false);
					}
				}
			});

			// 확인 버튼
			
			ok[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("카트넘버 : "  + cartNo);
					show = bt[j].getActionCommand();
					cart[cartNo][0]=namelist[j];
					cart[cartNo][1]=condilist[j];
					cart[cartNo][2]=count;
					cart[cartNo][3]=count * pricelist[j];
					
					cartNo+=1;
					minus[j].setEnabled(false);
					plus[j].setEnabled(false);
					ok[j].setEnabled(false);
				}
			});
		}
		
		// 주문버튼
        bt1.addActionListener(new ActionListener() {
 
            @Override
            public void actionPerformed(ActionEvent e) {
                // 주문 확인
            	//JOptionPane.showMessageDialog(null, Arrays.deepToString(cart) + "\n주문되었습니다. \n이용해주셔서 감사합니다.");
                
                // cart배열(메뉴명, 구분, 가격, 수량) 넘기면서 화면 전환되는 부분
//                for(int j = 0; j < cart.length; j++) {
//                	Arrays.fill(cart[j], null);
//                }
                cartNo=0;
                System.out.println(cart[0][1]);
                System.out.println(cart[1][0]);
                new CartDisplay("1234", cart);
                dispose();
            }
        });
 
        // 초기화 버튼
        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < menus.length; i++) {
                    bt[i].setEnabled(true);
                    minus[i].setEnabled(false);
                    plus[i].setEnabled(false);
                    suja[i].setText("0");
                }
                //System.out.println(Arrays.deepToString(cart));
                for(int i = 0; i < cart.length; i++) {
                	Arrays.fill(cart[i], null);
                }
                cartNo=0;
                //System.out.println(Arrays.deepToString(cart));
            }
        });

		// 끄기
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}


	public static void main(String[] args) {
		new SaleDisplay();
	}

}
