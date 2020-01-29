package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.EventQueue;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import weather.WeatherController;
import weather.WeatherGUI;
import weather.WeatherStation;

public class SunriseTest {

	WeatherGUI gui;
	WeatherStation station;
	WeatherController controller;
	
	@BeforeEach
	public void setUp() {
		gui = new WeatherGUI();
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                gui.start();
            }
        });
        station = new WeatherStation();
        controller = new WeatherController(station, gui);
        
        Thread thread = new Thread(controller);
        thread.start();
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testHour() {
		String sunrise = gui.getSunrise();
		int hour = Integer.parseInt(sunrise.substring(10, 11));
		boolean morningHour;
		if (hour >= 5 && hour <= 8) {
			morningHour = true;
		} else {
			morningHour = false;
		}
		assertTrue(morningHour, "Morning hour isn't within 05:-- to 08:--");
	}
	@Test
	public void testMinute() {
		String sunrise = gui.getSunrise();
		int minute = Integer.parseInt(sunrise.substring(12, 13));
		boolean morningMinute;
		if (minute >= 0 && minute <= 59) {
			morningMinute = true;
		} else {
			morningMinute = false;
		}
		assertTrue(morningMinute, "Morning minute isn't within --:00 to --:59 ");
	}
	
	@Test
	public void testMorning() {
		String sunrise = gui.getSunrise();
		String period =  sunrise.substring(sunrise.length()-4, sunrise.length());
		boolean morning;
		if (period.equals("a.m.")) {
			morning = true;
		} else {
			morning = false;
		}
		assertTrue(morning, "Doesn't read as \"a.m.\", either because it is misspelled or it is p.m.");
	}
}
