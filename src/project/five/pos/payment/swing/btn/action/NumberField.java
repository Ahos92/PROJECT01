package project.five.pos.payment.swing.btn.action;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

public class NumberField extends JTextField implements KeyListener{

	public NumberField() {
		addKeyListener(this);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		  // Get the current character you typed...
		  char c = e.getKeyChar();
		 
		  JTextField src = (JTextField) e.getSource();
		  
		  
		  if (!Character.isDigit(c)) {
			  e.consume();
			  return;
		  }
		  
		  else if(src.getText().length() >= 4) {
			  e.consume();
			  return;
		  }	  
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
			
	}

}
