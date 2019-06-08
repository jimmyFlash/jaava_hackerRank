package chapter2_Variables_types;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Date{

	private static int day;
	private static int date;
	private static int month;
	private static int year;

	private static Calendar calendar;


	//String[] daysStr = {"SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY"};

	private static SimpleDateFormat basicFormt = new SimpleDateFormat("EEEE dd/MM/yyyy");

	private static SimpleDateFormat usFormat = new SimpleDateFormat("EEEE, MMMM dd, yyyy");
	private static SimpleDateFormat euFormat = new SimpleDateFormat("EEEE dd MMMM, yyyy");

	public static void main (String[] args ){

		calendar = Calendar.getInstance();

		day = calendar.get(Calendar.DAY_OF_WEEK);

		date = calendar.get(Calendar.DAY_OF_MONTH);

		month = calendar.get(Calendar.MONTH);

		year = calendar.get(Calendar.YEAR);

		System.out.println(" the day of the week: " + day);

		System.out.println(" the day of the month: " + date);

		System.out.println(" the month: " + month + 1);

		System.out.println(" the year: " + year);


		// System.out.println("The date is: " + basicFormt.format(calendar.getTime()));

		System.out.print("The date in US format: ");

		System.out.println(usFormat.format(calendar.getTime()));


		System.out.print("The date in EU format: ");

		System.out.println(euFormat.format(calendar.getTime()));



	}
}