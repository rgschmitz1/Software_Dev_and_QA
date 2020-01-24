package weather;
import java.nio.ByteBuffer;
import java.util.Random;

public class WeatherStation{
	private int temp;
	private int humid;
	private int pressure;
	private int windspd;
	private int winddir;
	private Random random;
	
	private static final int MIN_TEMP = 100;
	private static final int MAX_TEMP = 1000;
	private static final double TEMP_DEV = 50.0;
	private static final int TEMP_OFFSET = 12;
	
	private static final int MIN_HUMID = 0;
	private static final int MAX_HUMID = 100;
	private static final double HUMID_DEV = 20.0;
	private static final int HUMID_OFFSET = 33;
	
	private static final int MIN_PRESSURE = 29000;
	private static final int MAX_PRESSURE = 31000;
	private static final double PRESSURE_DEV = 200.0;
	private static final int PRESSURE_OFFSET = 7;
	
	private static final int MIN_WINDSPD = 0;
	private static final int MAX_WINDSPD = 60;
	private static final double WINDSPD_DEV = 10.0;
	private static final int WINDSPD_OFFSET = 14;
	
	private static final int WINDDIR_MOD = 360;
	private static final double WINDDIR_DEV = 10.0;
	private static final int WINDDIR_OFFSET = 16;
	
	public WeatherStation() {
		random = new Random();
		temp = MIN_TEMP + random.nextInt(MAX_TEMP - MIN_TEMP);
		humid = MIN_HUMID + random.nextInt(MAX_HUMID - MIN_HUMID);
		pressure = MIN_PRESSURE + random.nextInt(MAX_PRESSURE - MIN_PRESSURE);
		windspd = MIN_WINDSPD + random.nextInt(MAX_WINDSPD - MIN_WINDSPD);
		winddir = random.nextInt(WINDDIR_MOD);
	}
	
	public byte[] getNext() {
		try {
			Thread.sleep(2000);
			updateSensors();
			
			byte[] packet = new byte[99];
			ByteBuffer buf = ByteBuffer.wrap(packet);
			
			buf.putChar(TEMP_OFFSET, (char)temp);
			buf.put(HUMID_OFFSET, (byte)humid);
			buf.putChar(PRESSURE_OFFSET, (char)pressure);
			buf.put(WINDSPD_OFFSET, (byte)windspd);
			buf.putChar(WINDDIR_OFFSET, (char)winddir);
			
			return packet;
		} catch (InterruptedException e) {
			return null;
		}
	}
	
	private void updateSensors() {
		temp += (int)(random.nextGaussian() * TEMP_DEV + 0.5);
		humid += (int)(random.nextGaussian() * HUMID_DEV + 0.5);
		pressure += (int)(random.nextGaussian() * PRESSURE_DEV + 0.5);
		windspd += (int)(random.nextGaussian() * WINDSPD_DEV + 0.5);
		winddir += (int)(random.nextGaussian() * WINDDIR_DEV + 0.5);
		
		temp = Math.max(Math.min(temp, MAX_TEMP), MIN_TEMP);
		humid = Math.max(Math.min(humid, MAX_HUMID), MIN_HUMID);
		pressure = Math.max(Math.min(pressure, MAX_PRESSURE), MIN_PRESSURE);
		windspd = Math.max(Math.min(windspd, MAX_WINDSPD), MIN_WINDSPD);
		winddir = (winddir + WINDDIR_MOD)%WINDDIR_MOD;
	}
}
