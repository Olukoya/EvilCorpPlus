import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Random;
public class WeatherMan {

	final int MILLS_IN_DAY = 24*60*60*1000;
	private HashMap<String,Integer> recognizedDates = new HashMap<String,Integer>();
	public WeatherMan(){
	}
	
	public Date getDate(String d){
	String[] input = d.split("/");
	GregorianCalendar cal = 
	new GregorianCalendar();
	int day = Integer.parseInt(input[1]);
	int month= Integer.parseInt(input[0])-1;
	int year= Integer.parseInt(input[2]);
	cal.set(year, month, day);
	
	return cal.getTime();
	}
	
	public String daysSinceDate(Date d){
	Date currentDate = new Date();
	return "It was " + (int) ((stripDate(currentDate).getTime()-stripDate(d).getTime())/MILLS_IN_DAY)
	+ " days ago.";
	
	}
	
	
	public String getTemp(String d){
	Random r = new Random();
	int t=0;
	if(recognizedDates.containsKey(d)){
	t = recognizedDates.get(d);
	}
	else{	
	t=r.nextInt(5);
	recognizedDates.put(d, t);
	}
	
	switch(t){
	case(0): return "It was a bright an sun shining day with a temperature in the high 80s.";
	case(1): return "It was relatively humid with an average temperature of 75 degrees.";
	case(2): return "It was a pleasant day with clouds overhead. Average temperature was 68 degrees.";
	case(3): return "It was pretty cold outside with a freezing temperature of 23 degrees.";
	case(4): return "It was so cold you couldn't even walk outside with a temperature high of -24 degrees.";
	default: return "";
	}
	}
	
	public String dayoftheWeek(Date d){
	String date = "That was a " + new SimpleDateFormat("EEEEEEEEE").format(d);
	return date;
	}
	
	public Date stripDate(Date d){
	GregorianCalendar currentDate = new GregorianCalendar();
	currentDate.setTime(d);
	currentDate.set(Calendar.HOUR, 0);
	currentDate.set(Calendar.MINUTE, 0);
	currentDate.set(Calendar.SECOND, 0);
	return currentDate.getTime();
	}
}