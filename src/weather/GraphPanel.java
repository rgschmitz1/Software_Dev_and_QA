package weather;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JPanel;

public class GraphPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -819909597063406070L;
	
	private static final int NUM_DIVS = 10;
	
	public static final int TEMP_SENSOR = 0;
	public static final int PRESSURE_SENSOR = 1;
	public static final int HUMIDITY_SENSOR = 2;
	public static final int RAINFALL_SENSOR = 3;
	public static final int NUM_SENSORS = 4;
	
	private static final int[] SENSOR_MINS = {100, 29000, 0, 0};
	private static final int[] SENSOR_MAXES = {1000, 31000, 100, 100};
	private static final String[] SENSOR_NAMES = {"Temp", "Pressure", "Humidity", "Rainfall"};
	
	private Stroke myStroke;
	private List<double[]> lines;
	private List<Integer> prevVals;
	private int index;	
	private int sensorType;
	
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
		sensorType = TEMP_SENSOR;
		
		index = 0;
	}
	
	public void updateSensorValue(int type, int value) {
		if (type == sensorType) {
			prevVals.set(index++, value);
			index %= NUM_DIVS;
			repaint();
		}
	}
	
	public int getCurrentSensorValue() {
		return prevVals.get((index+prevVals.size()-1)%prevVals.size());
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
		g2d.drawString(SENSOR_NAMES[sensorType], (float)(2*divH), (float)(divW/2));
		g2d.rotate(Math.PI/2);
		g2d.translate(0, -getHeight());
		
		int min = SENSOR_MINS[sensorType];
		int max = SENSOR_MAXES[sensorType];
		for (int i=0; i<NUM_DIVS; i++) {
			Integer val = prevVals.get((index+i)%NUM_DIVS);
			if (val != null) {
				double yOffset = ((val-min)/(double)(max-min)) * (getHeight() - divH);
				g2d.draw(new Rectangle.Double(divW + i*divW,
						getHeight()-divH-yOffset,
						divW,
						yOffset)
					);
			}
		}
		
		g2d.dispose();
	}

	public void setSensorType(int type) {
		if (type != sensorType) {
			sensorType = type;
			for (int i=0; i<prevVals.size(); i++) {
				prevVals.set(i, null);
			}
		}
	}
	
	public int getSensorType() {
		return sensorType;
	}
}
