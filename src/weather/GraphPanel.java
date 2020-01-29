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

/**
 * The JPanel on which graph data can be displayed.
 * 
 * @author Group 6
 *
 */
public class GraphPanel extends JPanel {
	/**
	 * The unique ID for the Serializable interface
	 */
	private static final long serialVersionUID = -819909597063406070L;
	
	/**
	 * The number of graph divisions
	 */
	private static final int NUM_DIVS = 10;
	
	/**
	 * Temperature ID
	 */
	public static final int TEMP_SENSOR = 0;
	/**
	 * Pressure ID
	 */
	public static final int PRESSURE_SENSOR = 1;
	/**
	 * Humidity ID
	 */
	public static final int HUMIDITY_SENSOR = 2;
	/**
	 * Rainfall ID
	 */
	public static final int RAINFALL_SENSOR = 3;
	/**
	 * Number of sensors able to be graphed
	 */
	public static final int NUM_SENSORS = 4;
	
	/**
	 * The minimum values of the respective sensors
	 */
	private static final int[] SENSOR_MINS = {100, 29000, 0, 0};
	/**
	 * The maximum values of the respective sensors
	 */
	private static final int[] SENSOR_MAXES = {1000, 31000, 100, 100};
	/**
	 * The names of the respective sensors
	 */
	private static final String[] SENSOR_NAMES = {"Temp", "Pressure", "Humidity", "Rainfall"};
	
	/**
	 * The Stroke with which the graph can be drawn
	 */
	private Stroke myStroke;
	/**
	 * The lines making up the background of the graph
	 */
	private List<double[]> lines;
	/**
	 * A circular buffer of the previous sensor values
	 */
	private List<Integer> prevVals;
	/**
	 * The index of the first element of the prevVals circular buffer
	 */
	private int index;	
	/**
	 * The type of the sensor currently being graphed
	 */
	private int sensorType;
	
	/**
	 * The constructor, initializes the panel with everything needed for it to draw itself
	 * 
	 * @param width the width of the graph
	 * @param height the height of the graph
	 */
	public GraphPanel(int width, int height) {
		super();
		myStroke = new BasicStroke(1);
		this.setPreferredSize(new Dimension(width, height));
		this.setMinimumSize(new Dimension(width, height));
		// lines is initialized with coordinates from 0 to 1 on each dimension
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
	
	/**
	 * Updates the sensor value of the type matches the currently graphed type
	 * 
	 * @param type the type of the sensor data
	 * @param value the value of the sensor reading
	 */
	public void updateSensorValue(int type, int value) {
		if (type == sensorType) {
			prevVals.set(index++, value);
			index %= NUM_DIVS;
			repaint();
		}
	}
	
	/**
	 * Retrieves the most recent sensor value
	 * 
	 * @return the most recent sensor value
	 */
	public int getCurrentSensorValue() {
		return prevVals.get((index+prevVals.size()-1)%prevVals.size());
	}
	
	/**
	 * Defines what is needed to draw every aspect of the graph
	 * @param theGraphics the Graphics device on which to draw
	 */
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
		
		// draw the background of the graph
		for (double[] line : lines) {
			g2d.draw(new Line2D.Double(line[0]*getWidth(), line[1]*getHeight(), line[2]*getWidth(), line[3]*getHeight()));
		}
		
		double divW = getWidth()/(double)(NUM_DIVS+1);
		double divH = getHeight()/(double)(NUM_DIVS+1);
		
		// draw the name of the sensor data type
		g2d.translate(0, getHeight());
		g2d.rotate(-Math.PI/2);
		g2d.drawString(SENSOR_NAMES[sensorType], (float)(2*divH), (float)(divW/2));
		g2d.rotate(Math.PI/2);
		g2d.translate(0, -getHeight());
		
		// draw the bars of the graph
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

	/**
	 * Sets which kind of sensor data to display on the graph
	 * 
	 * @param type the type of the sensor data to display on the graph
	 */
	public void setSensorType(int type) {
		if (type != sensorType) {
			sensorType = type;
			for (int i=0; i<prevVals.size(); i++) {
				prevVals.set(i, null);
			}
		}
	}
	
	/**
	 * Retrieves the type of the sensor data being displayed on the graph
	 * 
	 * @return the type of the sensor data being displayed on the graph
	 */
	public int getSensorType() {
		return sensorType;
	}
}
