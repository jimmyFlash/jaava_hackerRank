package hackerrank_solutions;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class ElectronicsShop {

    /*
     * Complete the getMoneySpent function below.
     */
    static int getMoneySpent(int[] keyboards, int[] drives, int b) {

        int[] sets = new int[2];
        int sum = -1;
        int closest = b;
        for (int i = keyboards.length - 1; i >= 0; i--) {
            if (keyboards[i] >= b)
                continue;
            for (int j = drives.length - 1; j >= 0; j--) {
                if (drives[j] >= b || (drives[j] + keyboards[i]) > b)
                    continue;
                if (keyboards[i] + drives[j] <= b) {
                    if (b - (keyboards[i] + drives[j]) < closest) {
                        closest = b - (keyboards[i] + drives[j]);
                        sum = keyboards[i] + drives[j];
                        sets[0] = keyboards[i];
                        sets[1] = drives[j];
                    }
                }
            }
        }
        System.out.println(sum + " = " + sets[0] + "," + sets[1]);
        return sum;
    }




    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(new File("src/test/electronics_shop_test.txt")); // 374625
       // BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] bnm = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");


        int b = Integer.parseInt(bnm[0]);

        int n = Integer.parseInt(bnm[1]);

        int m = Integer.parseInt(bnm[2]);

        int[] keyboards = new int[n];

        String[] keyboardsItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

        for (int keyboardsItr = 0; keyboardsItr < n; keyboardsItr++) {
            int keyboardsItem = Integer.parseInt(keyboardsItems[keyboardsItr]);
            keyboards[keyboardsItr] = keyboardsItem;
        }

        int[] drives = new int[m];

        String[] drivesItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

        for (int drivesItr = 0; drivesItr < m; drivesItr++) {
            int drivesItem = Integer.parseInt(drivesItems[drivesItr]);
            drives[drivesItr] = drivesItem;
        }

        /*
         * The maximum amount of money she can spend on a keyboard and USB drive, or -1 if she can't purchase both items
         */

        int moneySpent = getMoneySpent(keyboards, drives, b);

//        bufferedWriter.write(String.valueOf(moneySpent));
//        bufferedWriter.newLine();
//
//        bufferedWriter.close();

        scanner.close();
    }
}
