package project.five.pos.device;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class PromptAction implements FocusListener {

	JTextField tf;
	String txt;
	
	
	
	public PromptAction(JTextField tf, String txt) {
		this.tf = tf;
		this.txt = txt;
	}

	@Override
	public void focusGained(FocusEvent e) {
		if (tf.getText().equals(txt)) {
			tf.setText("");
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (tf.getText().isEmpty()) {
			tf.setText(txt);
		}
		
	}

	
}
