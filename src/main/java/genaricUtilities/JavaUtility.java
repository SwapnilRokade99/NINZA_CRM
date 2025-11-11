package genaricUtilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.Date;

public class JavaUtility {
	
	public int generateRandomNumber() {
		Random random=new Random();
		int randomNum=random.nextInt(1000);
		return randomNum;
		
	}
	
	public String getCurrentDateAndTime() {
		Date d = new Date();
		String date = d.toString().replace(" ", "_").replace(":", "_");
		return date;
	}
	public String getRequiredDate(int days) {
		Date d = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("dd-MM-yyyy");
		sim.format(d);
		Calendar cal= sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);
		
		return sim.format(cal.getTime());
	
	}
}
