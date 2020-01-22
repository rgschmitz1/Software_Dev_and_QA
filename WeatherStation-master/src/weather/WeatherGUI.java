package weather;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
	
    private JLabel tempReadout;
    private JLabel humidReadout;
    private JLabel pressureReadout;
    
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
        
        add(tempPanel);
        add(humidPanel);
        add(pressurePanel);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public void setTemp(int temp) {
    	String raw = Integer.toString(temp);
    	tempReadout.setText(raw.substring(0,raw.length()-1) + "." + raw.substring(raw.length()-1) + " °F");
    }
    
    public void setHumid(int humid) {
    	humidReadout.setText(Integer.toString(humid)+" %");
    }
    
    public void setPressure(int pressure) {
    	String raw = Integer.toString(pressure);
    	pressureReadout.setText(raw.substring(0,raw.length()-3) + "." + raw.substring(raw.length()-3)+" in.");
    }
}
