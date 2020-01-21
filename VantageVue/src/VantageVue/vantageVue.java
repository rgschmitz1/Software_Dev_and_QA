package VantageVue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.util.Calendar;
import java.util.GregorianCalendar;

import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;

public class vantageVue {

	private JFrame frmVantagevueConsole;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vantageVue window = new vantageVue();
					window.frmVantagevueConsole.setVisible(true);
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
		frmVantagevueConsole = new JFrame();
		frmVantagevueConsole.setResizable(false);
		frmVantagevueConsole.getContentPane().setBackground(Color.GRAY);
		frmVantagevueConsole.getContentPane().setLayout(null);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Alarms");
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnNewRadioButton.setBackground(Color.LIGHT_GRAY);
		rdbtnNewRadioButton.setBounds(530, 399, 150, 40);
		frmVantagevueConsole.getContentPane().add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Barometer");
		rdbtnNewRadioButton_1.setBackground(Color.LIGHT_GRAY);
		rdbtnNewRadioButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnNewRadioButton_1.setBounds(530, 341, 150, 40);
		frmVantagevueConsole.getContentPane().add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Graphs");
		rdbtnNewRadioButton_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnNewRadioButton_2.setBackground(Color.LIGHT_GRAY);
		rdbtnNewRadioButton_2.setBounds(530, 277, 150, 40);
		frmVantagevueConsole.getContentPane().add(rdbtnNewRadioButton_2);
		
		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("Weather Center");
		rdbtnNewRadioButton_3.setBackground(Color.LIGHT_GRAY);
		rdbtnNewRadioButton_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnNewRadioButton_3.setBounds(530, 209, 150, 40);
		frmVantagevueConsole.getContentPane().add(rdbtnNewRadioButton_3);
		
		JRadioButton rdbtnNewRadioButton_4 = new JRadioButton("Moon Phase");
		rdbtnNewRadioButton_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnNewRadioButton_4.setBackground(Color.LIGHT_GRAY);
		rdbtnNewRadioButton_4.setBounds(530, 149, 150, 40);
		frmVantagevueConsole.getContentPane().add(rdbtnNewRadioButton_4);
		
		JRadioButton rdbtnNewRadioButton_5 = new JRadioButton("Home");
		rdbtnNewRadioButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		rdbtnNewRadioButton_5.setSelected(true);
		rdbtnNewRadioButton_5.setAction(action);
		rdbtnNewRadioButton_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnNewRadioButton_5.setBackground(Color.LIGHT_GRAY);
		rdbtnNewRadioButton_5.setForeground(new Color(0, 0, 0));
		rdbtnNewRadioButton_5.setToolTipText("Home");
		rdbtnNewRadioButton_5.setBounds(530, 85, 150, 40);
		frmVantagevueConsole.getContentPane().add(rdbtnNewRadioButton_5);
		
		JLabel lblNewLabel = new JLabel("Display Options");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(530, 26, 150, 40);
		frmVantagevueConsole.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(22, 85, 483, 354);
		frmVantagevueConsole.getContentPane().add(panel);
	   panel.setLayout(null);
		
	   lblNewLabel_2 = new JLabel("clock");
	   lblNewLabel_2.setBounds(20, 141, 138, 79);
	   lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(lblNewLabel_2);
		
	    lblNewLabel_3 = new JLabel("date");
	    lblNewLabel_3.setBounds(20, 91, 217, 79);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Welcome to Davis's Vantage Vue");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNewLabel_4.setBounds(10, 28, 399, 53);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setIcon(new ImageIcon("src\\Davis.PNG"));
		lblNewLabel_1.setBounds(22, 26, 238, 49);
		frmVantagevueConsole.getContentPane().add(lblNewLabel_1);
		frmVantagevueConsole.setTitle("VantageVue Console");
		frmVantagevueConsole.setIconImage(Toolkit.getDefaultToolkit().getImage("src\\Davis company logo.PNG"));
		frmVantagevueConsole.setBounds(500, 150, 700, 482);
		frmVantagevueConsole.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	@SuppressWarnings("serial")
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
