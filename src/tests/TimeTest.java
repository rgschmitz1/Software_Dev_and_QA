package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.EventQueue;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import weather.WeatherController;
import weather.WeatherGUI;
import weather.WeatherStation;

public class TimeTest {
	
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
			TimeUnit.MILLISECONDS.sleep(2200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testHour() {
		String time = gui.getTime();
		Integer hour = Integer.parseInt(time.substring(6, 8));
		Date currentTime = new Date();
		String today = currentTime.toString();
		Integer currentHour = Integer.parseInt(today.substring(11, 13));
		if (currentHour == 0) {
			currentHour = 12;
		}
		if (currentHour > 12) {
			currentHour -= 12;
		}
		if (hour == currentHour) {
			assertTrue(true);
		} else {
			fail("Hours of time don't match.");
		}
	}
	
	@Test
	public void testMinute() {
		String time = gui.getTime();
		String minute = time.substring(9, 11);
		Date currentTime = new Date();
		String today = currentTime.toString();
		String currentMinute = today.substring(14, 16);
		if (minute.equals(currentMinute)) {
			assertTrue(true);
		} else {
			fail("Minutes of time don't match.");
		}
	}
	
	@Test
	public void testSeconds() {
		String time = gui.getTime();
		String second = time.substring(12, 14);
		Date currentTime = new Date();
		String today = currentTime.toString();
		String currentSecond = today.substring(17, 19);
		if (second.equals(currentSecond)) {
			assertTrue(true);
		} else {
			fail("Seconds of time don't match.");
		}
	}
}