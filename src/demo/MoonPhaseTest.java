package demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MoonPhaseTest {
	
	public int moonPhase() {
		// Average moon phase period +/-0.27 days
		BigDecimal moonPeriod = new BigDecimal("29.5305882");
		// Divide moon phase by 2 to determine the current phase
		moonPeriod = moonPeriod.divide(new BigDecimal("2"));
		// JSON file containing moon phase data for 2020
		File file = new File("src/Lunar_Phases/mooninfo_2020.json");
		// Fetch the current date and time
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		String date = sdf.format(System.currentTimeMillis());

		// Parse JSON data for the current date and time, return 
		try {
			JSONArray json = (JSONArray) new JSONParser().parse(new BufferedReader(new FileReader(file.toString())));
			Pattern pattern = Pattern.compile(".*" + date + ".*");
			for (int i=0; i < json.size(); i++) {
				Matcher matcher = pattern.matcher(json.get(i).toString());
				if (matcher.find()) {
					@SuppressWarnings("rawtypes")
					Map moonPhase = (Map) json.get(i);
					BigDecimal currentPhase = new BigDecimal(moonPhase.get("phase").toString());
					currentPhase = currentPhase.divide(new BigDecimal("25"), 0,  RoundingMode.HALF_UP);
					BigDecimal age = new BigDecimal(moonPhase.get("age").toString());
					if (age.compareTo(moonPeriod) == 1) {
						switch(currentPhase.toString()) {
						case "1":
							currentPhase = currentPhase.add(new BigDecimal("6"));
							break;
						case "2":
							currentPhase = currentPhase.add(new BigDecimal("4"));
							break;
						case "3":
							currentPhase = currentPhase.add(new BigDecimal("2"));
							break;
						default:
							break;
						}
					}
					return currentPhase.intValue();
				}
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public static void main(String[] args) {
		// Get todays moon phase
		MoonPhaseTest mpt = new MoonPhaseTest();
		System.out.println(mpt.moonPhase());
	}

}
