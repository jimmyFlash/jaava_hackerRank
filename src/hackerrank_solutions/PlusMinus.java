package hackerrank_solutions;

import java.text.DecimalFormat;
import java.util.Scanner;

public class PlusMinus {

    static void plusMinus(int[] arr) {

        double arrSize = arr.length;
        double zero = 0.0, pos = 0.0, neg = 0.0;
        for (int num : arr){
            if(num == 0){
                zero++;
            }else{
                if(num >= 1){
                    pos++;
                }else{
                    neg++;
                }
            }
        }
        double p =  pos/arrSize;
        double n = neg/arrSize;
        double z = zero/arrSize;
        DecimalFormat decimalFormat = new DecimalFormat("0.000000");
        System.out.println(decimalFormat.format(p));
        System.out.println(decimalFormat.format(n));
        System.out.println(decimalFormat.format(z));

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        plusMinus(arr);

        scanner.close();

    }
}
