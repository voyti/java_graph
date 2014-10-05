package generator;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

public class DrawingClass {
	   public void drawBlackOval(Graphics2D g2d, int x, int y, int w, int h) {
	        g2d.setColor(Color.BLACK);
	        g2d.fillOval(x, y, w, h);
	    }
	   
	   public void drawEmptyOval(Graphics2D g2d, int x, int y, int w, int h) {
	        g2d.setColor(Color.BLACK);
	        g2d.drawOval(x, y, w, h);
	   }
	   
	   public void drawColoredOvalWithAplha(Graphics2D g2d, int x, int y, int w, int h, Color color, float alpha) {
		   g2d.setComposite(makeComposite(alpha));
	       g2d.setColor(color);
	       g2d.fillOval(x, y, w, h);
	   }
	   
	   private AlphaComposite makeComposite(float alpha) {
		   int type = AlphaComposite.SRC_OVER;
		   return(AlphaComposite.getInstance(type, alpha));
		 }
	   
}
