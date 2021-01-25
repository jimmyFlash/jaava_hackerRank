package hackerrank_solutions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class DivisibleSumPairs {

    private static final String testInput1 = "6 3\r\n1 3 2 6 1 2";


    static int divisibleSumPairs(int n, int k, int[] ar) {

        int indexI = 0;
        int indexJ = indexI + 1;

        int pairsTotal = checkPair( indexI, indexJ, ar,  k, 0);

        System.out.println("number of pairs = " + pairsTotal);
        return pairsTotal;
    }

    /**
     *
     * @param indexI the 1st pair index
     * @param indexJ  the second pair index
     * @param ar array of elements
     * @param divider number the pair are equally divisible by
     * @param pairsFound divisible pairs count
     * @return the total of divisible pairs
     */
    private static int checkPair(int indexI, int indexJ, int[] ar, int divider, int pairsFound){


        if (indexJ == ar.length  ) {
            indexI++;
            indexJ = indexI + 1;

        }

        if (indexI == ar.length - 1 )
            return pairsFound;


        int pairCnt = pairsFound;
        if (indexI < indexJ && (ar[indexI] + ar[indexJ]) % divider == 0) {
            pairCnt++;
        }

        return checkPair( indexI, ++indexJ, ar,  divider, pairCnt);
    }

    private static final Scanner scanner = new Scanner(testInput1);

    public static void main(String[] args) throws IOException {
       // BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[] ar = new int[n];

        String[] arItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arItem = Integer.parseInt(arItems[i]);
            ar[i] = arItem;
        }

        int result = divisibleSumPairs(n, k, ar);
//
//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();
//
//        bufferedWriter.close();

        scanner.close();
    }
}
