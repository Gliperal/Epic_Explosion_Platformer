package utilities;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

public class PublicVariables {
	private static String applicationDirectory = null;
	
	public static int	mapWidth = -1
			,			mapHeight = /*TODO*/1000
			,			mapScale = 10;
	
	/**
	 * Retrieves the file folder in which the code is contained and returns it. Return includes trailing '/'
	 * 
	 * @return The application directory as a string
	 */
	public static String getApplicationDirectory() {
		if(applicationDirectory == null) {
			applicationDirectory = /*GameControl.instance()*/new PublicVariables().getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
			try {
				applicationDirectory = URLDecoder.decode(applicationDirectory, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				ErrorLog.addError("Application directory decoding error");
			}
		}
		return applicationDirectory + "/../";
	}
	
	public static void takeInformations(ArrayList<String> informations) {
		for(String line : informations) {
			if(line.startsWith("mapWidth:")) {
				mapWidth = Integer.parseInt(line.substring(9));
			}
		}
	}
}
