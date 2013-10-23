package www.forum_rallye.data;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.Vector;
import android.util.JsonReader;
import android.util.Log;

public class StaticReader {
	



	
	private static GregorianCalendar readDate(JsonReader reader){
		GregorianCalendar cal = null;;
		String tmp = null;
		try {
			tmp = reader.nextString();
		} catch (IOException e) {
			Log.d("DEBUG","Crash dans readDate");
			e.printStackTrace();
		}
		if(tmp != null)
			cal = new GregorianCalendar(Integer.parseInt(tmp.substring(0, 4)),
										Integer.parseInt(tmp.substring(5, 7)),
										Integer.parseInt(tmp.substring(8)));
		return cal;
	}
	
	private static GregorianCalendar readTimestamp(JsonReader reader){
		GregorianCalendar cal = null;;
		
		String tmp = null;
		try {
			tmp = reader.nextString();
		} catch (IOException e) {
			Log.d("DEBUG","Crash dans readTimestamp");
			e.printStackTrace();
		}
		if(tmp != null)
			cal = new GregorianCalendar(Integer.parseInt(tmp.substring(0, 4)),
										Integer.parseInt(tmp.substring(5, 7)),
										Integer.parseInt(tmp.substring(8,10)),
										Integer.parseInt(tmp.substring(11,13)),
										Integer.parseInt(tmp.substring(14,16)),
										Integer.parseInt(tmp.substring(17,19)));
		return cal;
	}
	
}
