package weather;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
//import java.awt.image.Raster;
import java.awt.image.WritableRaster;
//import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;

/**
 * The main GUI for the weather console.
 * 
 * @author Group 6
 */
public class WeatherGUI extends JFrame {
	/**
	 * The unique ID for the Serializable interface
	 */
	private static final long serialVersionUID = 562997229458227901L;
	
	/**
     * The title for the application window.
     */
	private static final String TITLE = "Weather Station";

    /**
     * The number of moon phase images there are, will be used to create the ImageIcon array.
     */
    private static final int NUM_IMAGES = 8;
    
    /**
     * Default location for fetching current weather
     */
    private static final String DEFAULT_LOCATION = "Tacoma,US";

    /**
     * Shows the temperature
     */
    private JLabel tempReadout;
    /**
     * Shows the humidity
     */
    private JLabel humidReadout;
    /**
     * Shows the pressure
     */
    private JLabel pressureReadout;
    /**
     * Shows the graph
     */
    private GraphPanel graphPanel;
    /**
     * Shows the wind data
     */
    private WindPanel windPanel;
    /**
     * Shows the moon phase
     */
    private JLabel moonLabel;
    /**
     * holds the images for each moon phase
     */
    private ImageIcon[] moonImages = new ImageIcon[NUM_IMAGES];
    /**
     * Shows the rainfall
     */
    private JLabel rainReadout;
    /**
     * Shows the date
     */
    private JLabel dateReadout;
    /**
     * Shows the time
     */
    private JLabel timeReadout;
    /**
     * Shows the time of sunrise
     */
    private JLabel sunriseReadout;
    /**
     * Shows the time of sunset
     */
    private JLabel sunsetReadout;

    /**
     * The background image
     */
    private Image background;

    /**
     * Previous weather summary state
     */
    private int summaryState;
    
    /**
     * This is a field that holds the location the station is currently monitoring
     */
    private String theLocation;
    
    /**
     * The method that will initialize the GUI to its default starting state.
     */
    @SuppressWarnings("serial")
	public void start() {
    	summaryState = -1;
    	this.setContentPane(new JPanel() {        	
        	@Override
        	public void paintComponent(Graphics g) {
        		super.paintComponent(g);
        		if (background != null) {
        			g.drawImage(background, 0, 0, null);
        		}
        	}
        });
    	
        this.setTitle(TITLE);
        //this.setLayout(new FlowLayout());
        this.setLayout(new BorderLayout());
        
        // Sets the initial size and position of the application window
        setSize(1200, 500);
        setLocationRelativeTo(null);
        
        theLocation = DEFAULT_LOCATION;
        
        JPanel tempPanel = new JPanel();
        tempPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        tempReadout = new JLabel("Temp: -- °F");
        tempPanel.add(tempReadout);
        
        JPanel humidPanel = new JPanel();
        humidPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        humidReadout = new JLabel("Humid: -- %");
        humidPanel.add(humidReadout);
        
        JPanel pressurePanel = new JPanel();
        pressurePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        pressureReadout = new JLabel("Pressure: -- inhg");
        pressurePanel.add(pressureReadout);

        graphPanel = new GraphPanel(getWidth()/2, getHeight()/2);
        windPanel = new WindPanel(getHeight()/9);

        JPanel moonPanel = new JPanel();
        moonLabel = new JLabel();
        moonPanel.add(moonLabel);
        moonPanel.setPreferredSize(new Dimension(190,50));
         
        JPanel rainPanel = new JPanel();
        rainPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        rainReadout = new JLabel("Rain: -- in./hr.");
        rainPanel.add(rainReadout);
        
        JPanel datePanel = new JPanel();
        datePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        dateReadout = new JLabel("Date: ---, --- --, ----");
        datePanel.add(dateReadout);
        
        JPanel timePanel = new JPanel();
        timePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        timeReadout = new JLabel("Time: --:--:-- a.m.");
        timePanel.add(timeReadout);  
        
        JPanel sunrisePanel = new JPanel();
        sunrisePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        sunriseReadout = new JLabel("Sunrise: --:-- a.m.");
        sunrisePanel.add(sunriseReadout); 
        
        JPanel sunsetPanel = new JPanel();
        sunsetPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        sunsetReadout = new JLabel("Sunset: --:-- p.m.");
        sunsetPanel.add(sunsetReadout);

        JPanel northLayoutPanel = new JPanel();
        northLayoutPanel.setLayout(new FlowLayout());

        JPanel eastLayoutPanel = new JPanel();
        eastLayoutPanel.setLayout(new BoxLayout(eastLayoutPanel, BoxLayout.Y_AXIS));

//        JPanel southLayoutPanel = new JPanel();
//        southLayoutPanel.setLayout(new GridLayout(2, 4, 40, 20));

        // Example Button addition into the South Panel Layout
        // ADD BUTTONS TO SOUTH PANEL.
//        JButton tempButton = new JButton(new GraphButtonAction(GraphPanel.TEMP_SENSOR, "Graph Temperature"));
//        JButton pressureButton = new JButton(new GraphButtonAction(GraphPanel.PRESSURE_SENSOR, "Graph Pressure"));
//        JButton humidityButton = new JButton(new GraphButtonAction(GraphPanel.HUMIDITY_SENSOR, "Graph Humidity"));
//        JButton rainfallButton = new JButton(new GraphButtonAction(GraphPanel.RAINFALL_SENSOR, "Graph Rainfall"));
//        JButton windspeedButton = new JButton(new GraphButtonAction(GraphPanel.WIND_SENSOR, "Graph Wind Speed"));
	    
//        southLayoutPanel.add(tempButton);
//        southLayoutPanel.add(pressureButton);
//        southLayoutPanel.add(humidityButton);
//        southLayoutPanel.add(rainfallButton);
//        southLayoutPanel.add(windspeedButton);

        northLayoutPanel.add(tempPanel);
        northLayoutPanel.add(humidPanel);
        northLayoutPanel.add(pressurePanel);
        northLayoutPanel.add(rainPanel);
        northLayoutPanel.add(datePanel);
        northLayoutPanel.add(timePanel);
        northLayoutPanel.add(sunrisePanel);
        northLayoutPanel.add(sunsetPanel);

        windPanel.setOpaque(false);
        moonPanel.setOpaque(false);
        eastLayoutPanel.add(windPanel);
        eastLayoutPanel.add(moonPanel);
        
        northLayoutPanel.setOpaque(false);
        eastLayoutPanel.setOpaque(false);
//        southLayoutPanel.setOpaque(false);
        graphPanel.setOpaque(false);
        
        setJMenuBar(createMenuBar());
        
        add(northLayoutPanel, BorderLayout.NORTH);
        add(eastLayoutPanel, BorderLayout.EAST);
//        add(southLayoutPanel, BorderLayout.SOUTH);
        add(graphPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    
    public void setTitleBar() {
    	this.setTitle(TITLE + " - " + getCity());
    }
    
    /**
     * This is a private helper method that creates a menu bar that can be added onto a JFrame
     * 
     * @return the Menubar to be added to a JFrame
     */
    private JMenuBar createMenuBar() {
    	final JMenuBar temp = new JMenuBar();
    	final JMenu cityMenu = new JMenu("Cities");

    	JRadioButtonMenuItem tacomaButton = new JRadioButtonMenuItem("Tacoma, WA");   
    	JRadioButtonMenuItem seattleButton = new JRadioButtonMenuItem("Seattle, WA");        
    	JRadioButtonMenuItem spokaneButton = new JRadioButtonMenuItem("Spokane, WA");
    	JRadioButtonMenuItem anchorageButton = new JRadioButtonMenuItem("Anchorage, AK");
    	JRadioButtonMenuItem mauiButton = new JRadioButtonMenuItem("Kahului, HI");

    	final ButtonGroup cityBtnGroup = new ButtonGroup();

    	cityBtnGroup.add(tacomaButton);
    	cityBtnGroup.add(seattleButton);
    	cityBtnGroup.add(spokaneButton);
    	cityBtnGroup.add(anchorageButton);
    	cityBtnGroup.add(mauiButton);

    	tacomaButton.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			theLocation = "Tacoma,US";
    		}
    	});

    	seattleButton.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			theLocation = "Seattle,US";
    		}
    	});

    	spokaneButton.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			theLocation = "Spokane,US";
    		}
    	});

    	anchorageButton.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			theLocation = "Anchorage,US";
    		}
    	});

    	mauiButton.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			theLocation = "Kahului,US";
    		}
    	});

    	tacomaButton.setSelected(true);

    	cityMenu.add(tacomaButton);
    	cityMenu.add(seattleButton);
    	cityMenu.add(spokaneButton);
    	cityMenu.add(anchorageButton);
    	cityMenu.add(mauiButton);

    	temp.add(cityMenu);
    	
    	final JMenu graphMenu = new JMenu("Graph");
    	
    	JRadioButtonMenuItem tempButton = new JRadioButtonMenuItem(new GraphButtonAction(GraphPanel.TEMP_SENSOR, "Temperature"));
    	JRadioButtonMenuItem pressureButton = new JRadioButtonMenuItem(new GraphButtonAction(GraphPanel.PRESSURE_SENSOR, "Pressure"));
    	JRadioButtonMenuItem humidityButton = new JRadioButtonMenuItem(new GraphButtonAction(GraphPanel.HUMIDITY_SENSOR, "Humidity"));
    	JRadioButtonMenuItem rainfallButton = new JRadioButtonMenuItem(new GraphButtonAction(GraphPanel.RAINFALL_SENSOR, "Rainfall"));
    	JRadioButtonMenuItem windspeedButton = new JRadioButtonMenuItem(new GraphButtonAction(GraphPanel.WIND_SENSOR, "Wind Speed"));
        
    	final ButtonGroup graphBtnGroup = new ButtonGroup();

    	tempButton.setSelected(true);
    	
    	graphBtnGroup.add(tempButton);
    	graphBtnGroup.add(pressureButton);
    	graphBtnGroup.add(humidityButton);
    	graphBtnGroup.add(rainfallButton);
    	graphBtnGroup.add(windspeedButton);
    	
    	graphMenu.add(tempButton);
    	graphMenu.add(pressureButton);
    	graphMenu.add(humidityButton);
    	graphMenu.add(rainfallButton);
    	graphMenu.add(windspeedButton);
    	
    	temp.add(graphMenu);

    	return temp;
	}

	/**
     * Sets the temperature
     * 
     * @param temp the temperature to set
     */
    public void setTemp(int temp) {
    	String raw = Integer.toString(temp);
    	tempReadout.setText("Temp: " + raw.substring(0,raw.length()-1) + "." + raw.substring(raw.length()-1) + " °F");
    	if (graphPanel != null) {
    		graphPanel.updateSensorValue(GraphPanel.TEMP_SENSOR, temp);
    	}
    }
    
    /**
     * Sets the humidity
     * 
     * @param humid the humidity to set
     */
    public void setHumid(int humid) {
    	humidReadout.setText("Humid: " + Integer.toString(humid)+" %");
    	if (graphPanel != null) {
    		graphPanel.updateSensorValue(GraphPanel.HUMIDITY_SENSOR, humid);
    	}
    }
    
    /**
     * Sets the pressure
     * 
     * @param pressure to set
     */
    public void setPressure(int pressure) {
    	String raw = Integer.toString(pressure);
    	pressureReadout.setText("Pressure:" + raw.substring(0,raw.length()-3) + "." + raw.substring(raw.length()-3)+" inhg");
    	if (graphPanel != null) {
    		graphPanel.updateSensorValue(GraphPanel.PRESSURE_SENSOR, pressure);
    	}
    }

    /**
     * Sets the wind velocity
     * 
     * @param windspd the speed of the wind
     * @param winddir the direction of the wind
     */
     public void setWind(int windspd, int winddir) {
	windPanel.updateWind(windspd, winddir);
	if (graphPanel != null) {
    		graphPanel.updateSensorValue(GraphPanel.WIND_SENSOR, windspd);
    	}
     }

    /**
     * Method creates the 8 moon phase icons and all the moon phase names.
     * @param moon the integer value indicating which moon to show.
     *             Comes from WeatherController.java
     */
    public void setMoonPhase(int moon) {
        //Create array of lunar choices lunar phase choices.
        String[] phases = { "New", "Waxing Crescent", "First Quarter",
                "Waxing Gibbous", "Full", "Waning Gibbous",
                "Third Quarter", "Waning Crescent" };

        //Retrieves the images and places them into an ImageIcon array.
        for (int i = 0; i < NUM_IMAGES; i++) {
            String imageName = "image" + i + ".png";
            URL url = getClass().getResource(String.format("/Lunar_Phases/%s", imageName));

            ImageIcon icon = new ImageIcon(url);
            Image image =icon.getImage();
            Image newImg = image.getScaledInstance(100,100, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(newImg);
            moonImages[i] = resizedIcon;
        }

        moonLabel.setText(phases[moon]);
        moonLabel.setVerticalTextPosition(JLabel.BOTTOM);
        moonLabel.setHorizontalTextPosition(JLabel.CENTER);
        moonLabel.setIcon(moonImages[moon]);
    }
    
    /**
     * Set the current rainfall rate
     * @param rain rainfall to set
     */
    public void setRain(int rain) {
    	String raw = Integer.toString(rain);
    	rainReadout.setText("Rain: " + raw.substring(0, raw.length()-1) + "." + raw.substring(raw.length()-1) + " in./hr.");
    	if (graphPanel != null) {
    		graphPanel.updateSensorValue(GraphPanel.RAINFALL_SENSOR, rain);
    	}
    }
    
    /**
     * Set the current date
     * @param date The current date
     */
    public void setDate(Date date) {
    	String raw = date.toString();
    	dateReadout.setText("Date: " + raw.substring(0, 3) + ", " + raw.substring(4, 7) + " "
    		+ raw.substring(8, 10) + ", " + raw.substring(raw.length()-4));
    	
    }
	
    /**
     * Set the current time
     * @param time The current time of day
     */
    public void setTime(Date time) {
    	String raw = time.toString();
    	int timePeriod = Integer.valueOf(raw.substring(11, 13));
    	if (timePeriod < 12) {
    		if (timePeriod == 0) {
    			timeReadout.setText("Time: 12" + raw.substring(13, 19) + " AM");
    		} else {
    			timeReadout.setText("Time: " + raw.substring(11, 19) + " AM");
    		}
    	} else {
    		if (timePeriod == 12) {
    			timeReadout.setText("Time: 12" + raw.substring(13, 19) + " PM");
    		} else {
    			timeReadout.setText("Time: " + Integer.toString((timePeriod-12)) + raw.substring(13, 19) + " PM");
    		}
    	}	
    }
    
    /**
     * Sets the time of sunrise
     * 
     * @param sunrise the time of sunrise to set
     */
    public void setSunrise(int sunrise) {
    	sunriseReadout.setText("Sunrise: 0" + Integer.toString(sunrise/100) + ":" 
    		+ String.format("%02d", sunrise%100) + " AM");
    }
    
    /**
     * Sets the time of sunset
     * 
     * @param sunset the time of sunset to set
     */
    public void setSunset(int sunset) {
    	sunsetReadout.setText("Sunset: 0" + Integer.toString(sunset/100) + ":" 
        		+ String.format("%02d", sunset%100) + " PM"); 
    }
	
    /**
     * Return the current date
     * @return current date
     */
    public String getDate() {
    	return dateReadout.getText();
    }
	
    /**
     * Return the current time
     * @return current time
     */
    public String getTime() {
    	return timeReadout.getText();
    }
	
    /**
     * Return the estimated time of sunrise
     * @return sunrise time
     */
    public String getSunrise() {
    	return sunriseReadout.getText();
    }
	
    /**
     * Return the estimate time of sunset
     * @return sunset time
     */
    public String getSunset() {
    	return sunsetReadout.getText();
    }
    
    public String getCity() {
    	return theLocation;
    }
    
    public void graphTick() {
    	if (graphPanel != null) {
    		graphPanel.updateDisplay();
    	}
	}
    
    /**
     * The action for each of the graph buttons that will change what the graph graphs.
     * 
     * @author Group 6
     */
    private class GraphButtonAction extends AbstractAction {
    	/**
		 * The ID for the Serializable interface
		 */
		private static final long serialVersionUID = 3612147189752191515L;
		/**
		 * The sensor type to have the graph start showing
		 */
		private int type;
    	
		/**
		 * The constructor for the Action
		 * 
		 * @param type the sensor type that the button will cause the graph to start showing
		 * @param buttonName the name of the button
		 */
    	public GraphButtonAction(int type, String buttonName) {
    		super(buttonName);
    		this.type = type;
    	}
    	
    	/**
    	 * Performs the actual setting of the graph sensor type
    	 * 
    	 * @param e the action event for the button pressed, will be not be used
    	 */
		@Override
		public void actionPerformed(ActionEvent e) {
			graphPanel.setSensorType(type);
		}
    }

	public void setWeatherSummary(int state) {
		if (state != summaryState) {
			String imgName = "sunny";
			
			switch(state) {
				case WeatherController.CLOUDY:
					imgName = "cloudy";
					break;
				case WeatherController.COLD:
					imgName = "cold";
					break;
				case WeatherController.DRY:
					imgName = "dry";
					break;
				case WeatherController.NIGHT:
					imgName = "night";
					break;
				case WeatherController.RAINY:
					imgName = "rainy";
					break;
				case WeatherController.WINDY:
					imgName = "windy";
					break;
			}
			
			URL url = getClass().getResource(String.format("/Backgrounds/%s.jpg", imgName));
//	        File f = new File(url.getPath());
			BufferedImage image;
			try {
				image = ImageIO.read(url);
				WritableRaster raster = image.getRaster();
				for (int y=0; y<image.getHeight(); y++) {
					for (int x=0; x<image.getWidth(); x++) {
						float[] pixel = new float[3];
						raster.getPixel(x, y, pixel);
						for (int i=0; i<3; i++) {
							pixel[i] = 255-pixel[i];
							pixel[i] /= 5;
							pixel[i] = 255-pixel[i];
						}
						raster.setPixel(x, y, pixel);
					}
				}
		        Image newImg = image.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
		        ImageIcon resizedIcon = new ImageIcon(newImg);
		        background = resizedIcon.getImage();
		        getContentPane().repaint();
			} catch (IOException e) {
				e.printStackTrace();
			}
	        
	        summaryState = state;
		}
	}
	
//	private Color avgColor(BufferedImage img) {
//		Raster raster = img.getData();
//		
//		double[] color = {0,0,0};
//		for (int y=0; y<raster.getHeight(); y++) {
//			for (int x=0; x<raster.getWidth(); x++) {
//				double[] curColor = new double[3];
//				raster.getPixel(x, y, curColor);
//				for (int i=0; i<color.length; i++) {
//					color[i] += curColor[i];
//				}
//			}
//		}
//		for (int i=0; i<color.length; i++) {
//			color[i] /= raster.getWidth() * raster.getHeight();
//		}
//		Color out = new Color(255-(int)color[0], 255-(int)color[1], 255-(int)color[2]);
//		return out;
//	}
}
