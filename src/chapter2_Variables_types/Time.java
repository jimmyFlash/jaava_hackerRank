package chapter2_Variables_types;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Time{

	private static SimpleDateFormat usFormat = new SimpleDateFormat("HH:mm:ss");
	private static Calendar calendar;

	public static void main (String[] args){

		double hour, minute, seconds;

		calendar = Calendar.getInstance();

		hour = calendar.get(Calendar.HOUR_OF_DAY);

		minute = calendar.get(Calendar.MINUTE);

		seconds = calendar.get(Calendar.SECOND);

		System.out.println("The time now, in 24Hrs format now is: " + usFormat.format(calendar.getTime()));

		/*System.out.print ("Number of minutes since midnight: ");
		System.out.println (hour * 60 + minute);*/

		long minutessineMidnight = (long) (hour * 3600 + minute * 60 + seconds);
		long oneDay = 24 * 60 * 60;
		long remainingInDay = oneDay - minutessineMidnight;

		System.out.print ("Number of seconds since midnight: ");
		System.out.println (minutessineMidnight);

		System.out.print ("Number of seconds remaining in day: ");
		System.out.println (remainingInDay);

		System.out.print ("percentage of day passed : ");
		System.out.println (hour * 100 / 24 + " %");

		/*
		System.out.print ("Fraction of the hour that has passed: ");
		System.out.println (minute/60);
		System.out.print ("Percentage of the hour that has passed: ");
		System.out.println (minute * 100/60);*/
	}

}