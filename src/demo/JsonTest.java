package demo;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class JsonTest {
	static String APIKEY = "03bd5a0f81e9de8ceab70cacdd93ae7b";
	static String LOCATION = "Tacoma,US";
	
	public static void main(String[] args) {
		StringBuilder theUrl = new StringBuilder("http://api.openweathermap.org/data/2.5/weather?q=");
		theUrl.append(LOCATION);
		theUrl.append("&APPID=");
		theUrl.append(APIKEY);
		
		try {
			URL url = new URL(theUrl.toString());
			JSONObject jo = (JSONObject) new JSONParser().parse(new BufferedReader(new InputStreamReader(url.openStream())));
			// Get Current Temp
			Map<String, Double> mainfield = (Map<String, Double>) jo.get("main");
			System.out.println(mainfield.get("temp"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}   
	}
}