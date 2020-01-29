package tests;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.EventQueue;
import java.util.concurrent.TimeUnit;

import weather.WeatherController;
import weather.WeatherGUI;
import weather.WeatherStation;

public class RainfallTest {
	
	WeatherGUI gui;
	WeatherStation station;
	WeatherController controller;
	
	@Before
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
	public void testPositive() {
		int rainfall = station.getRain();
		boolean positive;
		if (rainfall > 0) {
			positive = true;
		} else {
			positive = false;
		}
		assertTrue("Rainfall is in negative.", positive);
	}
	
	@Test
	public void testNegative() {
		int rainfall = station.getRain();
		if (rainfall < 0) {
			fail("Rainfall is in negative.");
		} 
	}
	
	@Test
	public void testMinimumAndMaximum() {
		int rainfall = station.getRain();
		boolean range;
		if (rainfall >= 0 && rainfall <= 100) {
			range = true;
		} else {
			range = false;
		}
		assertTrue("Rainfall is in range.", range);
	}

}
