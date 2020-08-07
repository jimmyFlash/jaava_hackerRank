package hackerrank_solutions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TimeConversion {
    /*
     * Complete the timeConversion function below.
     */
    static String timeConversion(String s) {
        LocalTime inputTime = LocalTime.parse(s, DateTimeFormatter.ofPattern("hh:mm:ssa") );
        return inputTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    private static final Scanner scan = new Scanner("07:05:45PM");

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("E:\\projects\\java\\javaSE\\javalessons\\out\\FileWriterTest.txt"));

        String s = scan.nextLine();

        String result = timeConversion(s);


        bw.write(result);
        bw.newLine();

        bw.close();
    }
}
