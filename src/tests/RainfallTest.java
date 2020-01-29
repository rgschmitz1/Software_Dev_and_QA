package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.awt.EventQueue;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import weather.WeatherController;
import weather.WeatherGUI;
import weather.WeatherStation;

public class RainfallTest {
	
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
	public void testPositive() {
		int rainfall = station.getRain();
		boolean positive;
		if (rainfall > 0) {
			positive = true;
		} else {
			positive = false;
		}
		assertTrue(positive, "Rainfall is in negative.");
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
		assertTrue(range, "Rainfall is in range.");
	}
}
