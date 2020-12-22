package project.five.pos.cart;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import project.five.pos.TestSwingTools;
import project.five.pos.cart.btn.CartBtn;
import project.five.pos.cart.btn.action.*;
import project.five.pos.cart.btn.render.*;
import project.five.pos.db.PosVO;
import project.five.pos.device.comp.DevicePanel;
import project.five.pos.device.comp.btn.DeviceBtn;
import project.five.pos.device.table.PosCellEditor;

public class CartPopUpDisplay extends JDialog {

	JPanel south_p, center_p, north_p;

	JButton cancle_btn, check_btn;

	JLabel info_lab;

	JTable cart_table;
	DefaultTableModel dtm;
	JScrollPane scroll;
	String[] header= {"메뉴", "옵션", "수량", "가격", "▲", "▼", "취소"};
	PosCellEditor cell_edit;
	int cell_btn_size;
	
	int order_num;
	CartDAO cart;
	
	String[] images_path = {
			"assets/images/device/14.png",
			"assets/images/device/smile.png",
			"assets/images/device/sad.png"
	};

	int width;
	int height;

	public CartPopUpDisplay(JFrame frame, String title, int device_id, Object[][] select_list) {	
		super(frame, title);
		width = 400;
		height = 400;
		setLayout(new BorderLayout());
		setSize(width, height);
		setResizable(false);// 사이즈 변경 불가
		setLocationRelativeTo(null);
		setModal(true);

		cart = new CartDAO();

		setLayout(new BorderLayout());

		north_p = new DevicePanel(images_path[0], width, height, new BorderLayout());
		south_p = new DevicePanel(images_path[0], width, height);
		center_p = new DevicePanel(images_path[0], width, height, new FlowLayout(FlowLayout.CENTER, 40, 40));

		info_lab = new JLabel("주문 내역 확인");
		info_lab.setFont(new Font("Courier", Font.BOLD, 20));
		info_lab.setPreferredSize(new Dimension(width, 30));

		// 주문 번호
		order_num = cart.MaxOrderNumber();
		order_num++;

		// 선택된 상품 테이블
		dtm = new DefaultTableModel(select_list, header);

		// null값 제거 
		int delete = 0;
		for (int i = 0; i < select_list.length; i++) {
			if (select_list[i][0] == null) {
				dtm.removeRow(delete);
				delete--;
			} 
			delete++;
		}

		cart_table = new JTable(dtm);
		scroll = new JScrollPane(cart_table);
		scroll.setPreferredSize(new Dimension(width - 30, 150));

		cell_edit = new PosCellEditor();
		cell_edit.setWitdth(title, cart_table);
		
		// 수량 조절 버튼
		cell_btn_size = 40;
		cell_edit.createcellBtn(cart_table, dtm, this, "취소", cell_btn_size);
		
		cell_edit.createcellBtn(cart_table, dtm, this, "▲", cell_btn_size);
		
		cell_edit.createcellBtn(cart_table, dtm, this, "▼", cell_btn_size);
		
		// 취소
		cancle_btn = new DeviceBtn(30, images_path[2], new CancleAction(this, dtm));
		
		// 결제
		check_btn = new DeviceBtn(60, images_path[1],
				new PaymentPageAction(this, frame, dtm, order_num, device_id)
		);

		north_p.add(info_lab, BorderLayout.WEST);
		north_p.add(cancle_btn, BorderLayout.EAST);
		center_p.add(scroll);
		center_p.add(check_btn);

		add(north_p, BorderLayout.NORTH);
		add(south_p, BorderLayout.SOUTH);
		add(center_p, BorderLayout.CENTER);

		setVisible(true);
	}

}

