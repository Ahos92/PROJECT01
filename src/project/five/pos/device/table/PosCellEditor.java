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
		if (btn_txt.equals("���")) {
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
		if (title.equals("�Ǹ� ���� ��ȸ")) {
			cart_table.getColumn("�Ǹ� ��¥").setPreferredWidth(55);
			cart_table.getColumn("�ֹ� ��ȣ").setPreferredWidth(20);
			cart_table.getColumn("��ǰ �̸�").setPreferredWidth(120);
			cart_table.getColumn("����").setPreferredWidth(25);
			cart_table.getColumn("����").setPreferredWidth(55);
			
		} else if (title.equals("���� ���� ��ȸ")){
			cart_table.getColumn("������").setPreferredWidth(80);
			cart_table.getColumn("���� ����").setPreferredWidth(15);
			cart_table.getColumn("���� �̸�").setPreferredWidth(15);
			cart_table.getColumn("ī�� ��ȣ").setPreferredWidth(70);
			cart_table.getColumn("���ϸ��� ���").setPreferredWidth(25);
			cart_table.getColumn("���� �ݾ�").setPreferredWidth(20);
			cart_table.getColumn("��� �ݾ�").setPreferredWidth(20);
			cart_table.getColumn("���� ���").setPreferredWidth(20);
			
		} else if (title.equals("ȸ�� ���� ��ȸ")) {
			cart_table.getColumn("ȸ�� ��ȣ").setPreferredWidth(40);
			cart_table.getColumn("�̸�").setPreferredWidth(30);
			cart_table.getColumn("��ȭ ��ȣ").setPreferredWidth(100);
			cart_table.getColumn("���").setPreferredWidth(25);
			cart_table.getColumn("�� ���ݾ�").setPreferredWidth(55);
			cart_table.getColumn("������").setPreferredWidth(55);
			cart_table.getColumn("���ϸ���").setPreferredWidth(55);
			
		} else if (title.equals("�ֹ� ����")) {
			cart_table.getColumn("�޴�").setPreferredWidth(110);
			cart_table.getColumn("�ɼ�").setPreferredWidth(35);
			cart_table.getColumn("����").setPreferredWidth(25);
			cart_table.getColumn("����").setPreferredWidth(50);
			cart_table.getColumn("��").setPreferredWidth(75);
			cart_table.getColumn("��").setPreferredWidth(75);
			cart_table.getColumn("���").setPreferredWidth(75);
			
		} else if (title.equals("��ǰ ����")) {
			cart_table.getColumn("No").setPreferredWidth(25);
			cart_table.getColumn("�޴���").setPreferredWidth(140);
			cart_table.getColumn("����").setPreferredWidth(50);
			cart_table.getColumn("����").setPreferredWidth(25);
			cart_table.getColumn("ī�װ�").setPreferredWidth(40);
			cart_table.getColumn("����").setPreferredWidth(35);
			cart_table.getColumn("����").setPreferredWidth(55);
			
		}
	}
}
