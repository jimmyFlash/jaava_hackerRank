package hackerrank_solutions;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class DayOfThrProgrammer {

    // Complete the dayOfProgrammer function below.
    static String dayOfProgrammer(int year) {

        int totlalDaysJanToAugNotLeap = 243;
        int dayOfDeveloper = 256;
        int monthOfDeveloper = 8;
        if(year == 1918){
            totlalDaysJanToAugNotLeap -= 13; // Feb 14th was day 32 of the year (14th - 28th)
        }

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, monthOfDeveloper);
        if(isitLeap (year))
            cal.set(Calendar.DAY_OF_MONTH, dayOfDeveloper - (totlalDaysJanToAugNotLeap + 1));
        else
            cal.set(Calendar.DAY_OF_MONTH, dayOfDeveloper - totlalDaysJanToAugNotLeap );

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        System.out.println(simpleDateFormat.format(cal.getTime()));
        return simpleDateFormat.format(cal.getTime());
    }

    static boolean isitGregorian (int year){
        return year > 1917;
    }

    static boolean isitLeap (int year){
        if(isitGregorian(year)){
            return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0) ;
        }else{

            return year % 4 == 0;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int year = Integer.parseInt(bufferedReader.readLine().trim());

        String result = dayOfProgrammer(year);

//        bufferedWriter.write(result);
//        bufferedWriter.newLine();

        bufferedReader.close();
//        bufferedWriter.close();
    }
}
