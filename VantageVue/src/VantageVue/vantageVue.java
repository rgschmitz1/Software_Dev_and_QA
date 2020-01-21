package VantageVue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;

public class vantageVue {

	private JFrame jFrame;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private JRadioButton rdbtnNewRadioButton_3;
	private JRadioButton rdbtnNewRadioButton_4;
	private JLabel lblNewLabel_1;
	private JPanel panel;
	private JPanel panelMoonPhase;
	private JLabel lblNewLabel_4;
	private JLabel label;
	private JLabel label_1;
	private JLabel lblNewLabel_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vantageVue window = new vantageVue();
					window.jFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void clock() {
		
		Thread clock = new Thread() {
			public void run() {
				try {
					for(;;) {
					Calendar cal = new GregorianCalendar();	
					int second = cal.get(Calendar.SECOND);
					int minute = cal.get(Calendar.MINUTE);
					int hour = cal.get(Calendar.HOUR);
					String curTime = String.format("%02d:%02d:%02d", hour, minute,second);
					
					lblNewLabel_2.setText("Time:  "+curTime);
				
					sleep(1000);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		clock.start();
	}
public void date() {
		
		Thread date = new Thread() {
			public void run() {
				try {
					for(;;) {
					Calendar cal = new GregorianCalendar();
					int month = 01;
					int day = cal.get(Calendar.DAY_OF_MONTH);
					int year = cal.get(Calendar.YEAR);
					String curTime = String.format("%02d/%02d/%02d", month, day, year);
					lblNewLabel_3.setText("Date:  "+curTime);
				
					sleep(1000);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		date.start();
	}

	/**
	 * Create the application.
	 */
	public vantageVue() {
		initialize();
		clock();
		date();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		jFrame = new JFrame();
		jFrame.setResizable(false);
		jFrame.getContentPane().setBackground(Color.GRAY);
		jFrame.getContentPane().setLayout(null);
		
	    rdbtnNewRadioButton = new JRadioButton("Alarms");
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnNewRadioButton.setBackground(Color.LIGHT_GRAY);
		rdbtnNewRadioButton.setBounds(530, 399, 150, 40);
		jFrame.getContentPane().add(rdbtnNewRadioButton);
		
		rdbtnNewRadioButton_1 = new JRadioButton("Barometer");
		rdbtnNewRadioButton_1.setBackground(Color.LIGHT_GRAY);
		rdbtnNewRadioButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnNewRadioButton_1.setBounds(530, 341, 150, 40);
		jFrame.getContentPane().add(rdbtnNewRadioButton_1);
		
		rdbtnNewRadioButton_2 = new JRadioButton("Graphs");
		rdbtnNewRadioButton_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnNewRadioButton_2.setBackground(Color.LIGHT_GRAY);
		rdbtnNewRadioButton_2.setBounds(530, 277, 150, 40);
		jFrame.getContentPane().add(rdbtnNewRadioButton_2);
		
		rdbtnNewRadioButton_3 = new JRadioButton("Weather Center");
		rdbtnNewRadioButton_3.setBackground(Color.LIGHT_GRAY);
		rdbtnNewRadioButton_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnNewRadioButton_3.setBounds(530, 209, 150, 40);
		jFrame.getContentPane().add(rdbtnNewRadioButton_3);
		
		rdbtnNewRadioButton_4 = new JRadioButton("Moon Phase");
		rdbtnNewRadioButton_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnNewRadioButton_4.setBackground(Color.LIGHT_GRAY);
		rdbtnNewRadioButton_4.setBounds(530, 149, 150, 40);
		jFrame.getContentPane().add(rdbtnNewRadioButton_4);
		rdbtnNewRadioButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			panel.removeAll();
			panel.updateUI();
			panel.add(label);
			panel.repaint();
			}
		});
		
		JRadioButton rdbtnNewRadioButton_5 = new JRadioButton("Home");
		rdbtnNewRadioButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				panel.updateUI();
				panel.add(lblNewLabel_2);
				panel.add(lblNewLabel_3);
				panel.add(lblNewLabel_4);
				panel.add(lblNewLabel_5);
				panel.repaint();
			}
		});
		ButtonGroup G = new ButtonGroup();
		G.add(rdbtnNewRadioButton);
		G.add(rdbtnNewRadioButton_1);
		G.add(rdbtnNewRadioButton_2);
		G.add(rdbtnNewRadioButton_3);
		G.add(rdbtnNewRadioButton_4);
		G.add(rdbtnNewRadioButton_5);
		rdbtnNewRadioButton_5.setSelected(true);
		rdbtnNewRadioButton_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnNewRadioButton_5.setBackground(Color.LIGHT_GRAY);
		rdbtnNewRadioButton_5.setForeground(new Color(0, 0, 0));
		rdbtnNewRadioButton_5.setToolTipText("Home");
		rdbtnNewRadioButton_5.setBounds(530, 85, 150, 40);
		jFrame.getContentPane().add(rdbtnNewRadioButton_5);
		
		JLabel lblNewLabel = new JLabel("Display Options");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(530, 26, 150, 40);
		jFrame.getContentPane().add(lblNewLabel);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(22, 85, 483, 354);
	    jFrame.getContentPane().add(panel);
	   panel.setLayout(null);
		
	   lblNewLabel_2 = new JLabel("clock");
	   lblNewLabel_2.setBounds(20, 141, 138, 79);
	   lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(lblNewLabel_2);
		
	    lblNewLabel_3 = new JLabel("date");
	    lblNewLabel_3.setBounds(20, 91, 217, 79);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Welcome to Davis's Vantage Vue");
		lblNewLabel_4.setBounds(10, 28, 399, 53);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 23));
		panel.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setBounds(247, 123, 138, 118);
		lblNewLabel_5.setIcon(new ImageIcon("C:\\Users\\Admin\\Documents\\TCSS 360\\VantageVue\\src\\weatherReak.GIF"));
		panel.add(lblNewLabel_5);
		
		label_1 = new JLabel("");
		label_1.setBounds(157, 307, 45, 13);
		panel.add(label_1);
		
//		panelMoonPhase = new JPanel();
//		panelMoonPhase.setBackground(Color.WHITE);
//		panelMoonPhase.setBounds(22, 85, 483, 354);
//		jFrame.getContentPane().add(panelMoonPhase);
//	    panelMoonPhase.setLayout(null);
	   
		label = new JLabel("");
		label.setBackground(Color.WHITE);
		label.setIcon(new ImageIcon("C:\\Users\\Admin\\Documents\\TCSS 360\\VantageVue\\src\\moonPhase.PNG"));
		label.setBounds(130, 20,400,300);
		
		lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Admin\\Documents\\TCSS 360\\VantageVue\\src\\Davis.PNG"));
		lblNewLabel_1.setBounds(22, 26, 238, 49);
		jFrame.getContentPane().add(lblNewLabel_1);
		jFrame.setTitle("VantageVue Console");
		jFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Documents\\TCSS 360\\VantageVue\\src\\Davis company logo.PNG"));
		jFrame.setBounds(500, 150, 700, 482);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
