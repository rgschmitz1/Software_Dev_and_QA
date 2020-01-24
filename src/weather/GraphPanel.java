package weather;

import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;
import java.awt.Shape;
import java.awt.geom.*;
import java.awt.geom.Line2D.Double;

public class GraphPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -819909597063406070L;
	
	private static final int NUM_DIVS = 10;
	
	private Stroke myStroke;
	private List<double[]> lines;
	private List<Integer> prevVals;
	private int index;
	
	public GraphPanel(int width, int height) {
		super();
		myStroke = new BasicStroke(1);
		this.setPreferredSize(new Dimension(width, height));
		this.setMinimumSize(new Dimension(width, height));
		lines = new ArrayList<>(Arrays.asList(
				new double[]{0, 1-1.0/(NUM_DIVS+1), 1-1.0/(NUM_DIVS+1), 1-1.0/(NUM_DIVS+1)},
				new double[]{1.0/(NUM_DIVS+1), 0, 1.0/(NUM_DIVS+1), 1-1.0/(NUM_DIVS+1)}));
		
		prevVals = new ArrayList<>();
		for (int i=0; i<NUM_DIVS; i++) {
			prevVals.add(null);
		}
		
		index = 0;
	}
	
	public void updateDayTemp(int temp) {
		prevVals.set(index++, temp);
		index %= NUM_DIVS;
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
		
		for (double[] line : lines) {
			g2d.draw(new Line2D.Double(line[0]*getWidth(), line[1]*getHeight(), line[2]*getWidth(), line[3]*getHeight()));
		}
		
		double divW = getWidth()/(double)(NUM_DIVS+1);
		double divH = getHeight()/(double)(NUM_DIVS+1);
		
		g2d.translate(0, getHeight());
		g2d.rotate(-Math.PI/2);
		g2d.drawString("temp", (float)(2*divH), (float)(divW/2));
		g2d.rotate(Math.PI/2);
		g2d.translate(0, -getHeight());
		
		for (int i=0; i<NUM_DIVS; i++) {
			Integer temp = prevVals.get((index+i)%NUM_DIVS);
			if (temp != null) {
				g2d.draw(new Rectangle.Double(divW + i*divW, getHeight()-(temp/1000.0)*getHeight(), divW, (temp/1000.0)*getHeight()-divH));
			}
		}
		
		g2d.dispose();
	}
}
