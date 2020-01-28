package weather;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WeatherStationTest {

@BeforeAll
static void setUpBeforeClass() throws Exception {
}

private WeatherStation myWeatherStation;

	@BeforeEach
	public void setup() {
	myWeatherStation = new WeatherStation();
	}

	@Test
	public void testhumid() {
		
			assertEquals(60, myWeatherStation.humid, 60);
		
	}
	@Test
	public void testhumidnotnull() {
		
		assertNotNull(myWeatherStation.humid);
	
}
	@Test
	public void testtemp() {
		
			assertEquals(1000, myWeatherStation.temp, 1000);
		
	} 
	@Test
	public void testtempnotnull() {
		
		assertNotNull(myWeatherStation.temp);
	
}
	@Test
	public void testpressure() {
		
			assertEquals(30000, myWeatherStation.pressure, 30000);
		
	}
	@Test
	public void testwindspd() {
	
			assertEquals(30, myWeatherStation.windspd, 30);
		
	} @Test
	public void testwinddir() {

			assertEquals(60, myWeatherStation.humid, 60);
		
	} @Test
	public void testrain() {
	
			assertEquals(60, myWeatherStation.rain, 60);
	
	} @Test
	public void testsunrise() {
		
			assertEquals(10000, myWeatherStation.sunrise, 10000);
		
	} @Test
	public void testsunset() {
		
			assertEquals(1000, myWeatherStation.sunset, 1000);
		
	}
	}