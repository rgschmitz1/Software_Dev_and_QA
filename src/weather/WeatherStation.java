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

	private static String APIKEY = "03bd5a0f81e9de8ceab70cacdd93ae7b";
	private static String LOCATION = "Tacoma,US";
	
	/**
	 * The offset for the packet. Tells us which byte the temperature is stored at.
	 */
	private static final int TEMP_OFFSET = 12;

	/**
	 * The offset for the packet. Tells us which byte the humidity is stored at.
	 */
	private static final int HUMID_OFFSET = 33;

	/**
	 * The offset for the packet. Tells us which byte the pressure is stored at.
	 */
	private static final int PRESSURE_OFFSET = 7;

	/**
	 * The offset for the packet. Tells us which byte the WindSpeed value is stored at.
	 */
	private static final int WINDSPD_OFFSET = 14;

	/**
	 * The byte offset for the packet data.
	 */
	private static final int WINDDIR_OFFSET = 16;

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

	private static double KELVIN_FAHRENHEIT_CONSTANT = -459.67;
	private static double KELVIN_FAHRENHEIT_COEF = 9/5.0;
	private static double HPA_INHG_COEF = 0.02953;
	private static double MS_MPH_COEF = 2.23694;
	private static double MM_IN_COEF = 0.0393701;
	
	/**
	 * Constructor that generate all the data values for each value.
	 */
	public WeatherStation() {
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
		StringBuilder theUrl = new StringBuilder("http://api.openweathermap.org/data/2.5/weather?q=");
		theUrl.append(LOCATION);
		theUrl.append("&APPID=");
		theUrl.append(APIKEY);
		try {
			URL url = new URL(theUrl.toString());
			JSONObject jo;
			jo = (JSONObject) new JSONParser().parse(new BufferedReader(new InputStreamReader(url.openStream())));
			
			double kelvTemp = (double)((Map)jo.get("main")).get("temp");
			temp = (int)(10 * (kelvTemp * KELVIN_FAHRENHEIT_COEF + KELVIN_FAHRENHEIT_CONSTANT) + 0.5);
			humid = (int)((long)((Map)jo.get("main")).get("humidity"));
			long hpaPressure = (long)((Map)jo.get("main")).get("pressure");
			pressure = (int)(1000 * hpaPressure * HPA_INHG_COEF + 0.5);
			double msWindSpeed = (double)((Map)jo.get("wind")).get("speed");
			windspd = (int)(msWindSpeed * MS_MPH_COEF + 0.5);
			winddir = (int)((long)((Map)jo.get("wind")).get("deg"));
			Object rainObj = jo.get("rain");
			if (rainObj != null) {
				double mmRain = (double)((Map)rainObj).get("1h");
				rain = (int)(mmRain * MM_IN_COEF + 0.5);
			}
			sunrise = unixToVantageTime((long)(((Map)jo.get("sys")).get("sunrise")));
			sunset = unixToVantageTime((long)(((Map)jo.get("sys")).get("sunset")));
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
