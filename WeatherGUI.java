package weather;
import java.awt.*;
import java.net.URL;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.*;

public class WeatherGUI extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 562997229458227901L;

	/**
     * The title for the application window.
     */
	private static final String TITLE = "Weather Station";
	
    /**
     * The proportion of the screen's dimensions by which to scale the
     * application window when it first opens.
     */
    private static final double SCALE = 1 / 3.0;

    /**
     * The number of moon phase images there are, will be used to create the ImageIcon array.
     */
    private static final int NUM_IMAGES = 8;

    private JLabel tempReadout;
    private JLabel humidReadout;
    private JLabel pressureReadout;
    private GraphPanel graphPanel;
    private WindPanel windPanel;
    private JLabel moonReadout;
    private JLabel moonLabel;
    private ImageIcon[] images = new ImageIcon[NUM_IMAGES];
    private JLabel rainReadout;
    private JLabel dateReadout;
    private JLabel timeReadout;
    private JLabel sunriseReadout;
    private JLabel sunsetReadout;


    /**
     * The method that will initialize the GUI to its default starting state.
     */
    public void start() {
        final Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
        
        // Reads the icon into myIcon. Performed this particular way in order to facilitate
        // deploying the application into a runnable JAR file.
        /*final InputStream is = getClass().getClassLoader().getResourceAsStream("scv.png");
        try {
            myIcon = new ImageIcon(is.readAllBytes());
        } catch (final IOException e) {
        }
        setIconImage(myIcon.getImage());*/
        
        this.setTitle(TITLE);
        this.setLayout(new FlowLayout());
        
        // Sets the initial size and position of the application window
        setSize((int) (screenDim.width * SCALE), (int) (screenDim.height * SCALE));
        setLocationRelativeTo(null);
        
        JPanel tempPanel = new JPanel();
        tempPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        tempReadout = new JLabel("-- °F");
        tempPanel.add(tempReadout);
        
        JPanel humidPanel = new JPanel();
        humidPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        humidReadout = new JLabel("-- %");
        humidPanel.add(humidReadout);
        
        JPanel pressurePanel = new JPanel();
        pressurePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        pressureReadout = new JLabel("-- in");
        pressurePanel.add(pressureReadout);

        graphPanel = new GraphPanel(getWidth()/2, getHeight()/2);
        windPanel = new WindPanel(getHeight()/3);


        JPanel moonPanel = new JPanel();
        moonPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        moonReadout = new JLabel();
        moonLabel = new JLabel();
        moonPanel.add(moonLabel);
        moonPanel.add(moonReadout);
         
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

        add(tempPanel);
        add(humidPanel);
        add(pressurePanel);
        add(rainPanel);
        add(datePanel);
        add(timePanel);
        add(sunrisePanel);
        add(sunsetPanel);    
        add(graphPanel);
        add(windPanel);
        add(moonPanel);



        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public void setTemp(int temp) {
    	String raw = Integer.toString(temp);
    	tempReadout.setText(raw.substring(0,raw.length()-1) + "." + raw.substring(raw.length()-1) + " °F");
    	if (graphPanel != null) {
    		graphPanel.updateDayTemp(temp);
    	}
    }
    
    public void setHumid(int humid) {
    	humidReadout.setText(Integer.toString(humid)+" %");
    }
    
    public void setPressure(int pressure) {
    	String raw = Integer.toString(pressure);
    	pressureReadout.setText(raw.substring(0,raw.length()-3) + "." + raw.substring(raw.length()-3)+" in.");
    }

	public void setWind(int windspd, int winddir) {
		windPanel.updateWind(windspd, winddir);
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
            Image newImg = image.getScaledInstance(50,50, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(newImg);
            images[i] = resizedIcon;
        }

        moonReadout.setText(phases[moon]);
        moonLabel.setIcon(images[moon]);
    }
    
    // set the current rainfall rate
    public void setRain(int rain) {
    	String raw = Integer.toString(rain);
    	rainReadout.setText("Rain: " + raw.substring(0, raw.length()-1) + "." + raw.substring(raw.length()-1) + " in./hr.");
    }
    
    // set the current date
    public void setDate(Date date) {
    	String raw = date.toString();
    	dateReadout.setText("Date: " + raw.substring(0, 3) + ", " + raw.substring(4, 7) + " "
    		+ raw.substring(8, 10) + ", " + raw.substring(raw.length()-4));
    	
    }
    
    // set the current time
    public void setTime(Date time) {
    	String raw = time.toString();
    	int timePeriod = Integer.valueOf(raw.substring(11, 13));
    	if (timePeriod < 12) {
    		if (timePeriod == 0) {
    			timeReadout.setText("Time: 12" + raw.substring(13, 19) + " a.m.");
    		} else {
    			timeReadout.setText("Time: " + raw.substring(11, 19) + " a.m.");
    		}
    	} else {
    		if (timePeriod == 12) {
    			timeReadout.setText("Time: 12" + raw.substring(13, 19) + " p.m.");
    		} else {
    			timeReadout.setText("Time: " + Integer.toString((timePeriod-12)) + raw.substring(13, 19) + " p.m.");
    		}
    	}	
    }
    
    // set the sunrise time
    public void setSunrise(int sunrise) {
    	sunriseReadout.setText("Sunrise: 0" + Integer.toString(sunrise/100) + ":" 
    		+ Integer.toString(sunrise%100) + " a.m.");
    }
    
    // set the sunset time
    public void setSunset(int sunset) {
    	sunsetReadout.setText("Sunset: 0" + Integer.toString(sunset/100) + ":" 
        		+ Integer.toString(sunset%100) + " p.m.");
    }
}
