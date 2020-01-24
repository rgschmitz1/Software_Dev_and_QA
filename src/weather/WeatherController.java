package weather;
import java.nio.ByteBuffer;
//Another Test - weather
public class WeatherController implements Runnable {
	private static final int TEMP_OFFSET = 12;
	private static final int HUMID_OFFSET = 33;
	private static final int PRESSURE_OFFSET = 7;
	private static final int WINDSPD_OFFSET = 14;
	private static final int WINDDIR_OFFSET = 16;
	
	private WeatherStation station;
	private WeatherGUI gui;
	
	public WeatherController(WeatherStation station, WeatherGUI gui) {
		this.station = station;
		this.gui = gui;
	}
	
	@Override
	public void run() {
		while(!Thread.interrupted()) {
			byte[] packet = station.getNext();
			
			int temp = extractTemp(packet);
			int humid = extractHumid(packet);
			int pressure = extractPressure(packet);
			int windspd = extractWindSpd(packet);
			int winddir = extractWindDir(packet);
			
			gui.setTemp(temp);
			gui.setHumid(humid);
			gui.setPressure(pressure);
			gui.setWind(windspd, winddir);
		}
	}
	
	private void setMoonPhase() {
		
	}
	
	private int extractTemp(byte[] packet) {
		ByteBuffer buf = ByteBuffer.wrap(packet);
		return (int)buf.getChar(TEMP_OFFSET);
	}
	
	private int extractHumid(byte[] packet) {
		ByteBuffer buf = ByteBuffer.wrap(packet);
		return (int)buf.get(HUMID_OFFSET);
	}
	
	private int extractPressure(byte[] packet) {
		ByteBuffer buf = ByteBuffer.wrap(packet);
		return (int)buf.getChar(PRESSURE_OFFSET);
	}
	
	private int extractWindSpd(byte[] packet) {
		ByteBuffer buf = ByteBuffer.wrap(packet);
		return (int)buf.get(WINDSPD_OFFSET);
	}
	
	private int extractWindDir(byte[] packet) {
		ByteBuffer buf = ByteBuffer.wrap(packet);
		return (int)buf.getChar(WINDDIR_OFFSET);
	}
}
