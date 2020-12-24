package project.five.pos.device.table;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import project.five.pos.cart.btn.action.DeleteAction;
import project.five.pos.cart.btn.action.UpDownAction;
import project.five.pos.cart.btn.render.DeleteBtnRender;
import project.five.pos.cart.btn.render.UpDonwBtnRender;

public class PosCellEditor {
	
	public PosCellEditor() {}
	
	public void createcellBtn(JTable cart_table, DefaultTableModel dtm, JDialog dialog, 
								String btn_txt, int cell_btn_size) {
		if (btn_txt.equals("취소")) {
			cart_table.getColumn(btn_txt).setCellRenderer(new DeleteBtnRender());
			cart_table.getColumn(btn_txt).setCellEditor(new DeleteAction(new JCheckBox(), cart_table, dtm, dialog));
			cart_table.getColumn(btn_txt).setPreferredWidth(cell_btn_size);
		} else {
			cart_table.getColumn(btn_txt).setCellRenderer(new UpDonwBtnRender(btn_txt));
			cart_table.getColumn(btn_txt).setCellEditor(new UpDownAction(new JCheckBox(), cart_table, btn_txt));
			cart_table.getColumn(btn_txt).setPreferredWidth(cell_btn_size);
		}
	}

	public void setWitdth(String title, JTable cart_table) {
		if (title.equals("판매 내역 조회")) {
			cart_table.getColumn("판매 날짜").setPreferredWidth(55);
			cart_table.getColumn("주문 번호").setPreferredWidth(20);
			cart_table.getColumn("상품 이름").setPreferredWidth(120);
			cart_table.getColumn("수량").setPreferredWidth(25);
			cart_table.getColumn("가격").setPreferredWidth(55);
			
		} else if (title.equals("결제 내역 조회")){
			cart_table.getColumn("결제일").setPreferredWidth(80);
			cart_table.getColumn("결제 수단").setPreferredWidth(15);
			cart_table.getColumn("은행 이름").setPreferredWidth(15);
			cart_table.getColumn("카드 번호").setPreferredWidth(70);
			cart_table.getColumn("마일리지 사용").setPreferredWidth(25);
			cart_table.getColumn("결제 금액").setPreferredWidth(20);
			cart_table.getColumn("사용 금액").setPreferredWidth(20);
			cart_table.getColumn("쿠폰 사용").setPreferredWidth(20);
			
		} else if (title.equals("회원 정보 조회")) {
			cart_table.getColumn("회원 번호").setPreferredWidth(40);
			cart_table.getColumn("이름").setPreferredWidth(30);
			cart_table.getColumn("전화 번호").setPreferredWidth(100);
			cart_table.getColumn("등급").setPreferredWidth(25);
			cart_table.getColumn("총 사용금액").setPreferredWidth(55);
			cart_table.getColumn("적립률").setPreferredWidth(55);
			cart_table.getColumn("마일리지").setPreferredWidth(55);
			
		} else if (title.equals("주문 내역")) {
			cart_table.getColumn("메뉴").setPreferredWidth(110);
			cart_table.getColumn("옵션").setPreferredWidth(35);
			cart_table.getColumn("수량").setPreferredWidth(25);
			cart_table.getColumn("가격").setPreferredWidth(50);
			cart_table.getColumn("▲").setPreferredWidth(75);
			cart_table.getColumn("▼").setPreferredWidth(75);
			cart_table.getColumn("취소").setPreferredWidth(75);
			
		} else if (title.equals("상품 관리")) {
			cart_table.getColumn("No").setPreferredWidth(25);
			cart_table.getColumn("메뉴명").setPreferredWidth(140);
			cart_table.getColumn("가격").setPreferredWidth(50);
			cart_table.getColumn("수량").setPreferredWidth(25);
			cart_table.getColumn("카테고리").setPreferredWidth(40);
			cart_table.getColumn("구분").setPreferredWidth(35);
			cart_table.getColumn("삭제").setPreferredWidth(55);
			
		}
	}
}
