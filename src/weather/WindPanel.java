package weather;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

public class WindPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7614555071611756523L;
	
	private int speed;
	private int dir;
	private Stroke myStroke;
	
	public WindPanel(int diam) {
		super();
		speed = 0;
		dir = 0;
		myStroke = new BasicStroke(1);
		this.setPreferredSize(new Dimension(diam, diam));
		this.setMinimumSize(new Dimension(diam, diam));
	}
	
	public void updateWind(int speed, int dir) {
		this.speed = speed;
		this.dir = dir;
		repaint();
	}
	
	@Override
	public void paintComponent(final Graphics theGraphics) {
		super.paintComponent(theGraphics);
		final Graphics2D g2d = (Graphics2D)theGraphics;
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                 RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                 RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
                 RenderingHints.VALUE_STROKE_PURE);
		
		g2d.setColor(Color.BLACK);
		g2d.setStroke(myStroke);
		
		double innerDiam = getWidth()*0.9;
		
		g2d.draw(new Ellipse2D.Double((getWidth()-innerDiam)/2, (getHeight()-innerDiam)/2, innerDiam, innerDiam));
		g2d.drawString(Integer.toString(speed) + " mph", getWidth()*(0.25f), getHeight()/2.0f);
		
		g2d.translate(getWidth()/2.0, getHeight()/2.0);
		g2d.rotate(dir * Math.PI/180);
		g2d.fillPolygon(new int[] {-getWidth()/10, 0, getWidth()/10},
						new int[] {(int) (getHeight()*(-0.85)/2) , (int) (-getHeight()/2.0), (int) (getHeight()*(-0.85)/2)}, 3);
		g2d.rotate(dir * -Math.PI/180);
		g2d.translate(-getWidth()/2.0, -getHeight()/2.0);
		
		g2d.dispose();
	}
}
