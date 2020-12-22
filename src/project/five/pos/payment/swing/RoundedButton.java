package project.five.pos.payment.swing;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

//��ư ������
public class RoundedButton extends JButton {
   public RoundedButton() { super(); decorate(); } 
   public RoundedButton(String text) { super(text); decorate(); } 
   public RoundedButton(Action action) { super(action); decorate(); } 
   public RoundedButton(Icon icon) { super(icon); decorate(); } 
   public RoundedButton(String text, Icon icon) { super(text, icon); decorate(); } 
   protected void decorate() { setBorderPainted(false); setOpaque(false); }
   @Override 
   protected void paintComponent(Graphics g) {
	  //198 209 215
      Color c=new Color(227,222,211); //���� ����
      Color o=new Color(24,32,22); //���ڻ� ����
      int width = getWidth(); 
      int height = getHeight(); 
      Graphics2D graphics = (Graphics2D) g; 
      graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
      if (getModel().isArmed()) { graphics.setColor(c.darker()); } 
      else if (getModel().isRollover()) { graphics.setColor(c.brighter()); } 
      else { graphics.setColor(c); } 
      graphics.fillRoundRect(0, 0, width, height, 10, 10); 
      FontMetrics fontMetrics = graphics.getFontMetrics(); 
      Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds(); 
      int textX = (width - stringBounds.width) / 2; 
      int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent(); 
      graphics.setColor(o); 
      graphics.setFont(getFont()); 
      graphics.drawString(getText(), textX, textY); 
      graphics.dispose(); 
      super.paintComponent(g); 
      }
   }
