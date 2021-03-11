package hackerrank_solutions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/*
9
10 20 20 10 10 30 50 10 20

8
1 2 1 2 1 3 2 1

6
1 2 1 2 1 1

5
1 2 1 2 1
 */

public class SockMerchant {

    private static final Scanner scanner = new Scanner(System.in);

    // Complete the sockMerchant function below.
    static int sockMerchant(int n, int[] ar) {

        int matchingPairs = 0;
        Map<Integer, List<Integer>> hash = Arrays.stream(ar).boxed()
                .collect(Collectors.groupingBy(Integer::intValue));

        for (Map.Entry<Integer, List<Integer>> key : hash.entrySet()){
            double pairsPerSet = Math.floor(key.getValue().size()/2.0);
            matchingPairs+=pairsPerSet;
        }
        System.out.println(hash.toString() + ", " + matchingPairs);
        return matchingPairs;
    }


    public static void main(String[] args) throws IOException {

        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] ar = new int[n];

        String[] arItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arItem = Integer.parseInt(arItems[i]);
            ar[i] = arItem;
        }

        int result = sockMerchant(n, ar);

//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();

//        bufferedWriter.close();

        scanner.close();
    }
}
