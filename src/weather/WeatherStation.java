package weather;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Random;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * A "proxy" class that simulates a physical weather station and generates packets of data.
 *
 * @author Group 6 Ilya B. Neil H. Jason L. Brandon K.
 * @version January 26, 2020
 */
public class WeatherStation{
	/**
	 * Controls whether the station data is retrieved from the Weather API or
	 * is randomly generated
	 */
	private static final boolean API_ENABLED = true;
	
	/**
	 * Disables rain when randomly generating data for testing
	 */
	private static final boolean DISABLE_RAIN = false;
	
	/**
	 * Stores the temperature value.
	 */
	private int temp;
	/**
	 * Stores the humidity value.
	 */
	private int humid;
	/**
	 * Stores the air pressure value.
	 */
	private int pressure;
	/**
	 * The WindSpeed value.
	 */
	private int windspd;
	/**
	 * The Wind Direction value.
	 */
	private int winddir;
	/**
	 * The total amount of rainfall (in) value.
	 */
	private int rain;
	/**
	 * The int value of sunrise(time).
	 */
	private int sunrise;
	/**
	 * The int value of sunset(time).
	 */
	private int sunset;

	private static final String APIKEY = "03bd5a0f81e9de8ceab70cacdd93ae7b";
	private static final String DEFAULT_LOCATION = "Tacoma,US";
	
	/**
	 * A random generator for generating the different values.
	 */
	private static final Random RANDOM = new Random();

	/**
	 * The minimum temperature value.
	 */
	private static final int MIN_TEMP = 100;

	/**
	 * The maximum temperature value.
	 */
	private static final int MAX_TEMP = 1000;

	/**
	 * The amount the temperature should deviate up/down.
	 */
	private static final double TEMP_DEV = 50.0;

	/**
	 * The offset for the packet. Tells us which byte the temperature is stored at.
	 */
	private static final int TEMP_OFFSET = 12;

	/**
	 * Minimum humidity value.
	 */
	private static final int MIN_HUMID = 0;

	/**
	 * Maximum humidity value.
	 */
	private static final int MAX_HUMID = 100;

	/**
	 * Deviation of the humidity value (up/down).
	 */
	private static final double HUMID_DEV = 20.0;

	/**
	 * The offset for the packet. Tells us which byte the humidity is stored at.
	 */
	private static final int HUMID_OFFSET = 33;

	/**
	 * Minimum air (parametric) pressure value.
	 */
	private static final int MIN_PRESSURE = 29000;

	/**
	 * Maximum air (parametric) pressure value.
	 */
	private static final int MAX_PRESSURE = 31000;

	/**
	 * Deviation of the pressure value (Changes in up/down).
	 */
	private static final double PRESSURE_DEV = 200.0;

	/**
	 * The offset for the packet. Tells us which byte the pressure is stored at.
	 */
	private static final int PRESSURE_OFFSET = 7;

	/**
	 * Minimum WindSpeed value.
	 */
	private static final int MIN_WINDSPD = 0;

	/**
	 * Maximum WindSpeed value.
	 */
	private static final int MAX_WINDSPD = 60;

	/**
	 * Deviation of WindSpeed value.
	 */
	private static final double WINDSPD_DEV = 10.0;

	/**
	 * The offset for the packet. Tells us which byte the WindSpeed value is stored at.
	 */
	private static final int WINDSPD_OFFSET = 14;

	/**
	 * The Wind Direction direction allowing 360 degrees.
	 */
	private static final int WINDDIR_MOD = 360;

	/**
	 * Deviation of Wind Direction.
	 */
	private static final double WINDDIR_DEV = 10.0;

	/**
	 * The byte offset for the packet data.
	 */
	private static final int WINDDIR_OFFSET = 16;

	/**
	 * Minimum Rain fall value.
	 */
	private static final int MIN_RAIN = 0;

	/**
	 * Maximum rain fall value.
	 */
	private static final int MAX_RAIN = 100;

	/**
	 * Deviation of rain fall.
	 */
	private static final double RAIN_DEV = 4;

	/**
	 * The offset for the packet. Tells us which byte the WindSpeed value is stored at.
	 */
	private static final int RAIN_OFFSET = 44;

	/**
	 * Offset values of sunrise for the packet. To know which byte the data is stored at.
	 */
	private static final int SUNRISE_OFFSET = 91;

	/**
	 * Offset values of sunrise for the packet. To know which byte the data is stored at.
	 */
	private static final int SUNSET_OFFSET = 93;

	/**
	 * Conversion coefficient from HPA to INHG units
	 */
	private static final double HPA_INHG_COEF = 0.02953;
	
	/**
	 * Conversion coefficient from mm to inches
	 */
	private static final double MM_IN_COEF = 0.0393701;
	
    /**
     * This is a field that holds the location the station is currently monitoring
     */
	private String theLocation;
	
	/**
	 * Constructor that generate all the data values for each value.
	 */
	public WeatherStation() {
		temp = MIN_TEMP + RANDOM.nextInt(MAX_TEMP - MIN_TEMP);
		humid = MIN_HUMID + RANDOM.nextInt(MAX_HUMID - MIN_HUMID);
		pressure = MIN_PRESSURE + RANDOM.nextInt(MAX_PRESSURE - MIN_PRESSURE);
		windspd = MIN_WINDSPD + RANDOM.nextInt(MAX_WINDSPD - MIN_WINDSPD);
		winddir = RANDOM.nextInt(WINDDIR_MOD);
		rain = MIN_RAIN + RANDOM.nextInt(MAX_RAIN - MIN_RAIN);
		sunrise = (100*(5 + RANDOM.nextInt(3))) + RANDOM.nextInt(60);
		sunset = (100*(5 + RANDOM.nextInt(3))) + RANDOM.nextInt(60);
		theLocation = DEFAULT_LOCATION;
	}

	/**
	 * Updates the Sensors and generates a packet with the data.
	 * @return a byte packet with size 99.
	 */
	public byte[] getNext() {
		try {
			Thread.sleep(2000);
			updateSensors();
			
			byte[] packet = new byte[99];
			ByteBuffer buf = ByteBuffer.wrap(packet);
			
			buf.putChar(TEMP_OFFSET, (char)getTemp());
			buf.put(HUMID_OFFSET, (byte)getHumid());
			buf.putChar(PRESSURE_OFFSET, (char)getPressure());
			buf.put(WINDSPD_OFFSET, (byte)getWindspd());
			buf.putChar(WINDDIR_OFFSET, (char)winddir);
			buf.put(RAIN_OFFSET, (byte)getRain());
			buf.put(SUNRISE_OFFSET, (byte)(getSunrise()/100));
			buf.put(SUNRISE_OFFSET + 1, (byte)(getSunrise()%100));
			buf.put(SUNSET_OFFSET, (byte)(getSunset()/100));
			buf.put(SUNSET_OFFSET + 1, (byte)(getSunset()%100));
			
			return packet;
		} catch (InterruptedException e) {
			return null;
		}
	}

	/**
	 * Updates all the data values from the OpenWeatherMap API
	 */
	@SuppressWarnings("rawtypes")
	private void updateSensors() {
		if (API_ENABLED) {
			StringBuilder theUrl = new StringBuilder("http://api.openweathermap.org/data/2.5/weather?q=");
			theUrl.append(theLocation);
			theUrl.append("&APPID=");
			theUrl.append(APIKEY);
			theUrl.append("&units=imperial");
			
			try {
				// Grab json from OpenWeatherMap API
				URL url = new URL(theUrl.toString());
				JSONObject jo = (JSONObject) new JSONParser().parse(new BufferedReader(new InputStreamReader(url.openStream())));
				
				// Fetch current forecast data
				Map forecast = (Map) jo.get("main");
				temp = (int) (10 * (double) forecast.get("temp"));
				humid = ((Long) forecast.get("humidity")).intValue();
				pressure = (int) (1000 * (long)forecast.get("pressure") * HPA_INHG_COEF + 0.5);
				
				// Fetch wind speed and direction data
				Map wind = (Map)jo.get("wind");
				windspd = ((Double) wind.get("speed")).intValue();
				if (wind.get("deg") != null) {
					winddir = (int)((long) wind.get("deg"));
				} else {
					winddir = 0;
				}
				
				// Fetch rain data
				Map rainObj = (Map) jo.get("rain");
				if (rainObj != null) {
					rain = (int)(10 * (double) rainObj.get("1h") * MM_IN_COEF + 0.5);
				} else {
					rain = 0;
				}
				
				// Fetch sunrise/sunset data
				Map sys = (Map)jo.get("sys");
				sunrise = unixToVantageTime((long) (sys.get("sunrise")));
				sunset = unixToVantageTime((long) (sys.get("sunset")));
			} catch (IOException | ParseException e) {
				e.printStackTrace();
			}
		} else {
			temp = getTemp() + (int)(RANDOM.nextGaussian() * TEMP_DEV + 0.5);
			humid = getHumid() + (int)(RANDOM.nextGaussian() * HUMID_DEV + 0.5);
			pressure = getPressure() + (int)(RANDOM.nextGaussian() * PRESSURE_DEV + 0.5);
			windspd = getWindspd() + (int)(RANDOM.nextGaussian() * WINDSPD_DEV + 0.5);
			winddir += (int)(RANDOM.nextGaussian() * WINDDIR_DEV + 0.5);
			rain = DISABLE_RAIN ? 0 : rain + (int)(RANDOM.nextGaussian() * RAIN_DEV + 0.5);
			
			temp = Math.max(Math.min(getTemp(), MAX_TEMP), MIN_TEMP);
			humid = Math.max(Math.min(getHumid(), MAX_HUMID), MIN_HUMID);
			pressure = Math.max(Math.min(getPressure(), MAX_PRESSURE), MIN_PRESSURE);
			windspd = Math.max(Math.min(getWindspd(), MAX_WINDSPD), MIN_WINDSPD);
			winddir = (winddir + WINDDIR_MOD)%WINDDIR_MOD;
			rain = Math.max(Math.min(rain, MAX_RAIN), MIN_RAIN);
		}
	}

	private int unixToVantageTime(long unix) {
		unix *= 1000L;
		Date date = new Date(unix);
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		return 100 * calendar.get(Calendar.HOUR) + calendar.get(Calendar.MINUTE);
	}
	
	/**
	 * Used to set the location of the city the station is monitoring
	 * 
	 * @param theNewCity that is being monitored.
	 */
	public void setCity(String theNewCity) {
		theLocation = theNewCity;
	}
	
	/**
	 * Get Humidity value.
	 * @return the integer value for humidity.
	 */
	public int getHumid() {
		return humid;
	}

	/**
	 * Get Temperature value.
	 * @return the integer value for temperature.
	 */
	public int getTemp() {
		return temp;
	}

	/**
	 * Get Parametric Pressure.
	 * @return Integer value for Pressure.
	 */
	public int getPressure() {
		return pressure;
	}

	/**
	 * Get WindSpeed.
	 * @return Integer value for Wind Speed.
	 */
	public int getWindspd() {
		return windspd;
	}

	/**
	 * Get Rain fall value in inches.
	 * @return Integer value for Rainfall.
	 */
	public int getRain() {
		return rain;
	}

	/**
	 * Get time of Sunrise (am)
	 * @return Integer value for Sunrise.
	 */
	public int getSunrise() {
		return sunrise;
	}

	/**
	 * Get time of Sunset (pm)
	 * @return Integer value for Sunset.
	 */
	public int getSunset() {
		return sunset;
	}
}
