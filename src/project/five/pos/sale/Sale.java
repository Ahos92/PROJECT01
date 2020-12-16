package project.five.pos.sale;


import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import project.five.pos.TestSwingTools;
import project.five.pos.db.MenuDAO;
import project.five.pos.db.PosVO;

public class Sale extends JFrame{
	int count = 0;
    String show = "";
    
	
	SaleDAO dao;
	
	public Sale() {
		TestSwingTools.initTestFrame(this, "�޴� ȭ��", true);
        Container con = this.getContentPane();
        JScrollPane scroll;
        
		dao = new SaleDAO();
		// �޴� �г�
		JPanel center_panel = new JPanel();
		add(center_panel);
		center_panel.setLayout(null);
		center_panel.setSize(0, 600);
		scroll = new JScrollPane(center_panel);
		scroll.setBounds(0,0,0, 400);
		
		
		// �迭 ���� �κ�
		String[] namelist = MenuDAO.getPnames();
		Integer[] pricelist = MenuDAO.getPprices();
		//System.out.println(namelist.length);
		//int price[] = { 5000, 5500, 6000, 6500, 7000};
		JButton bt[] = new JButton[namelist.length];
        TextField[] suja = new TextField[namelist.length];
        Button[] minus = new Button[namelist.length];
        Button plus[] = new Button[namelist.length];
        JButton ok[] = new JButton[namelist.length];
        Label l[] = new Label[namelist.length];
        ImageIcon icon[] = new ImageIcon[namelist.length];
        
        // ��ư �κ�
        int x = 60; int y = 50;
        for (int i = 0; i < namelist.length; i++) {
        	 
            // �޴� ��ư
            bt[i] = new JButton(namelist[i]);
            if (i % 2 != 0 || i == 0) {
                bt[i].setBounds(x + (i % 2) * 210, y, 150, 150);
            } else {
            	y+=260;
                bt[i].setBounds(x + (i % 2) * 210, y, 150, 150);
            }
            icon[i] = new ImageIcon(i + ".png");
            bt[i].setIcon(icon[i]);
 
            // ���� txt ��ư
            suja[i] = new TextField("0");
            suja[i].setBackground(Color.white);
            suja[i].setEditable(false);
            suja[i].setBounds(bt[i].getX() + 50, bt[i].getY() + 180, 40, 20);
 
            // "-" ��ư
            minus[i] = new Button("-");
            minus[i].setBounds(bt[i].getX() + 20, suja[i].getY(), 20, 20);
            minus[i].setEnabled(false);
 
            // "+" ��ư
            plus[i] = new Button("+");
            plus[i].setBounds(bt[i].getX() + 100, suja[i].getY(), 20, 20);
            plus[i].setEnabled(false);
 
            // ����
            l[i] = new Label(pricelist[i] + "��");
            l[i].setBounds(bt[i].getX() + 50, suja[i].getY() - 25, 100, 20);
 
            // Ȯ�� ��ư
            ok[i] = new JButton("Ȯ��");
            ok[i].setBounds(bt[i].getX()+20, suja[i].getY() + 30, 100, 20);
            ok[i].setEnabled(false);
 
            center_panel.add(bt[i]);
            center_panel.add(suja[i]);
            center_panel.add(minus[i]);
            center_panel.add(plus[i]);
            center_panel.add(l[i]);
            center_panel.add(ok[i]);
        }
        
        add(scroll);
        setVisible(true);
        
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
					ok[j].setEnabled(true);

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
                    ok[j].setEnabled(true);
                    if (count > 0) {
                        minus[j].setEnabled(true);
                    }
                }
            });
			
			// Ȯ�� ��ư
			ok[i].addActionListener(new ActionListener() {
				 
                @Override
                public void actionPerformed(ActionEvent e) {
                    show = bt[j].getActionCommand();
                    System.out.println("   " + show + "       " + pricelist[j] + "        " + count + "         " + pricelist[j] * count
                            + "��" + "\n");
                    ok[j].setEnabled(false);
                }
            });
		}
        
     // ����
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
	}
	
	
	public static void main(String[] args) {
		new Sale();
	}
	
}
