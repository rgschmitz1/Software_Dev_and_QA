package weather;
import java.awt.*;
import java.net.URL;

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

        add(tempPanel);
        add(humidPanel);
        add(pressurePanel);
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
}
