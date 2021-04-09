package hackerrank_solutions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FlippingBook {

    private static final Scanner scanner = new Scanner(System.in);

    /*
     * Complete the pageCount function below.
     */
    static int pageCount(int n, int p) {
        //6 2
        //5 4
        //6 5
        int pagesStartToEnd = (n % 2 == 0)? n + 1 : n;
        int shortestFlips = Math.min(p, pagesStartToEnd - p);
        System.out.println( ((shortestFlips == 1) ? 0 : shortestFlips/ 2) + " ----> " + shortestFlips/ 2 );
        return (shortestFlips == 1) ? 0 : shortestFlips/ 2;// 1, 0, 1

    }

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

        int p = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

        int result = pageCount(n, p);

//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();
//
//        bufferedWriter.close();

        scanner.close();
    }
}
