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
import project.five.pos.device.comp.btn.DeviceBtn;

public class CartPopUpDisplay extends JDialog {

	JPanel south_p, center_p, north_p;

	JButton cancle_btn, check_btn;

	JLabel info_lab;

	JTable cart_table;
	DefaultTableModel dtm;
	JScrollPane scroll;
	String[] header= {"메뉴", "옵션", "수량", "가격", "▲", "▼", "취소"};
	int cell_btn_size;

	int order_num;
	CartDAO cart;
	
	String[] images_path = {
			"assets/images/device/credit-card.png",
			"assets/images/device/money.png",
			"assets/images/device/close.png"
	};

	int width;
	int height;
	
	public CartPopUpDisplay(JFrame frame, String title, int device_id, Object[][] select_list) {	
		super(frame, title);
		width = 400;
		height = 500;
		setLayout(new BorderLayout());
		setSize(width, height);
		setResizable(false);// 사이즈 변경 불가
		setLocation(810, 200);
		setModal(true);

		cart = new CartDAO();

		setLayout(new BorderLayout());

		north_p = new JPanel(new BorderLayout());
		south_p = new JPanel();
		center_p = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 40));

		info_lab = new JLabel("주문 내역 확인");
		info_lab.setFont(new Font("바탕", Font.BOLD, 20));
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
		scroll.setPreferredSize(new Dimension(width - 15, height - 350));

		cellWitdths(cart_table);

		// 수량 조절 버튼
		cell_btn_size = 40;	
		createcellBtn(cart_table, "취소", cell_btn_size);

		createcellBtn(cart_table, "▲", cell_btn_size);

		createcellBtn(cart_table, "▼", cell_btn_size);
		
		// 결제
		check_btn = new DeviceBtn("확인", images_path[1], 120,  
				new PaymentPageAction(this, frame, dtm, order_num, device_id)
		);


		// 취소
		cancle_btn = new DeviceBtn(50, images_path[2],new CancleAction(this, dtm));

		north_p.add(info_lab, BorderLayout.WEST);
		north_p.add(cancle_btn, BorderLayout.EAST);
		center_p.add(scroll);
		center_p.add(check_btn);

		add(north_p, BorderLayout.NORTH);
		add(south_p, BorderLayout.SOUTH);
		add(center_p, BorderLayout.CENTER);

		setVisible(true);
	}

	
	private void cellWitdths(JTable cart_table) {
		cart_table.getColumn("메뉴").setPreferredWidth(110);
		cart_table.getColumn("옵션").setPreferredWidth(35);
		cart_table.getColumn("수량").setPreferredWidth(25);
		cart_table.getColumn("가격").setPreferredWidth(50);
		cart_table.getColumn("▲").setPreferredWidth(75);
		cart_table.getColumn("▼").setPreferredWidth(75);
		cart_table.getColumn("취소").setPreferredWidth(75);

	}

	/*
	 *	테이블에 삽입할 버튼 만드는 메서드 		
	 */
	private void createcellBtn(JTable cart_table, String btn_txt, int cell_btn_size) {
		if (btn_txt.equals("취소")) {
			cart_table.getColumn(btn_txt).setCellRenderer(new DeleteBtnRender());
			cart_table.getColumn(btn_txt).setCellEditor(new DeleteAction(new JCheckBox(), cart_table, dtm, this));
			cart_table.getColumn(btn_txt).setPreferredWidth(cell_btn_size);
		} else {
			cart_table.getColumn(btn_txt).setCellRenderer(new UpDonwBtnRender(btn_txt));
			cart_table.getColumn(btn_txt).setCellEditor(new UpDownAction(new JCheckBox(), cart_table, btn_txt));
			cart_table.getColumn(btn_txt).setPreferredWidth(cell_btn_size);
		}
	}

}

