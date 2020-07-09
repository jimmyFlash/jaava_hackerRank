package hackerrank_solutions;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MiniMaxSum {
    // Complete the miniMaxSum function below.
    static void miniMaxSum(int[] arr) {
        int[] clone1 = Arrays.copyOf(arr, arr.length);
        Arrays.sort(clone1);
        int[] clone2 = Arrays.copyOf(clone1, arr.length);

        List<BigInteger> listAsc = Arrays.stream(clone1)
                .mapToObj(BigInteger::valueOf)
                .collect(Collectors.toList());
        listAsc.remove(0);

        List<BigInteger> listDsc = Arrays.stream(clone2)
                .mapToObj(BigInteger::valueOf)
                .collect(Collectors.toList());
        listDsc.remove(listDsc.size()-1);

        System.out.println(listDsc.stream().reduce(BigInteger.ZERO, BigInteger::add) +
                " " +
                listAsc.stream().reduce(BigInteger.ZERO, BigInteger::add));


    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int[] arr = new int[5];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < 5; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        miniMaxSum(arr);

        scanner.close();
    }
}
