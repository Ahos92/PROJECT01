package project.five.pos.sale.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import project.five.pos.sale.SaleDAO;
import project.five.pos.sale.SaleDTO;

public class PaymentPageAction implements ActionListener{

	JFrame nextFrame;
	JFrame presentFrame;
	
	
	public PaymentPageAction(JFrame presentFrame, JFrame nextFrame, ArrayList<SaleDTO> cartlist, int cartNumber) {
		this.nextFrame = nextFrame;
		this.presentFrame = presentFrame;
		this.cartlist = cartlist;
		this.cartNumber = cartNumber;	
	}

	ArrayList<SaleDTO> cartlist; 
	int cartNumber;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		SaleDAO dao = new SaleDAO(); 
		dao.saveCartlist(cartlist, cartNumber++);
		presentFrame.setVisible(false);
		nextFrame.setVisible(true);
	}
}
