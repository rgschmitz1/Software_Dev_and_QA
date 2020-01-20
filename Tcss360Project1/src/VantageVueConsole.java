import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JToggleButton;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class VantageVueConsole {

	private JFrame frmVantageVueConsole;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VantageVueConsole window = new VantageVueConsole();
					window.frmVantageVueConsole.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VantageVueConsole() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVantageVueConsole = new JFrame();
		frmVantageVueConsole.setTitle("Vantage Vue Simulator");
		frmVantageVueConsole.setBounds(100, 100, 680, 348);
		frmVantageVueConsole.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		frmVantageVueConsole.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(0, 6, 1, 1));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setEnabled(false);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("HEAT");
		lblNewLabel_1.setForeground(Color.YELLOW);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("DEW");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(Color.YELLOW);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("CHILL");
		lblNewLabel_3.setForeground(Color.YELLOW);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("ET");
		lblNewLabel_4.setForeground(Color.YELLOW);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setEnabled(false);
		panel.add(lblNewLabel_5);
		
		JToggleButton tglbtnLight = new JToggleButton("LIGHT");
		tglbtnLight.setForeground(Color.BLACK);
		tglbtnLight.setBackground(Color.WHITE);
		panel.add(tglbtnLight);
		
		JToggleButton tglbtnTemp = new JToggleButton("TEMP");
		lblNewLabel_1.setLabelFor(tglbtnTemp);
		tglbtnTemp.setForeground(Color.WHITE);
		tglbtnTemp.setBackground(Color.BLACK);
		panel.add(tglbtnTemp);
		
		JToggleButton tglbtnHum = new JToggleButton("HUM");
		tglbtnHum.setForeground(Color.WHITE);
		tglbtnHum.setBackground(Color.BLACK);
		panel.add(tglbtnHum);
		
		JToggleButton tglbtnWind = new JToggleButton("WIND");
		tglbtnWind.setForeground(Color.WHITE);
		tglbtnWind.setBackground(Color.BLACK);
		panel.add(tglbtnWind);
		
		JToggleButton tglbtnRain = new JToggleButton("RAIN");
		tglbtnRain.setForeground(Color.WHITE);
		tglbtnRain.setBackground(Color.BLACK);
		panel.add(tglbtnRain);
		
		JToggleButton tglbtnBar = new JToggleButton("BAR");
		tglbtnBar.setForeground(Color.WHITE);
		tglbtnBar.setBackground(Color.BLACK);
		panel.add(tglbtnBar);
		
		JToggleButton tglbtn2nd = new JToggleButton("2ND");
		tglbtn2nd.setForeground(Color.BLACK);
		tglbtn2nd.setBackground(Color.YELLOW);
		panel.add(tglbtn2nd);
		
		JToggleButton tglbtnWxcen = new JToggleButton("WxCen");
		tglbtnWxcen.setForeground(Color.WHITE);
		tglbtnWxcen.setBackground(Color.BLACK);
		panel.add(tglbtnWxcen);
		
		JToggleButton tglbtnGraph = new JToggleButton("GRAPH");
		tglbtnGraph.setForeground(Color.WHITE);
		tglbtnGraph.setBackground(Color.BLACK);
		panel.add(tglbtnGraph);
		
		JToggleButton tglbtnHiLow = new JToggleButton("HI/LOW");
		tglbtnHiLow.setForeground(Color.WHITE);
		tglbtnHiLow.setBackground(Color.BLACK);
		panel.add(tglbtnHiLow);
		
		JToggleButton tglbtnTime = new JToggleButton("TIME");
		tglbtnTime.setForeground(Color.WHITE);
		tglbtnTime.setBackground(Color.BLACK);
		panel.add(tglbtnTime);
		
		JToggleButton tglbtnDone = new JToggleButton("DONE");
		tglbtnDone.setForeground(Color.WHITE);
		tglbtnDone.setBackground(Color.BLACK);
		panel.add(tglbtnDone);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setEnabled(false);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("ALARM");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_7.setForeground(Color.YELLOW);
		panel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("SET");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_8.setForeground(Color.YELLOW);
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("CLEAR");
		lblNewLabel_9.setForeground(Color.YELLOW);
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("UNIT");
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_10.setForeground(Color.YELLOW);
		panel.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("SETUP");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_11.setForeground(Color.YELLOW);
		panel.add(lblNewLabel_11);
	}

}
