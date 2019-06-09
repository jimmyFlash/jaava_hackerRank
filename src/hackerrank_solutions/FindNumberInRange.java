package hackerrank_solutions; /**
 * input sample:
 * 5
 * 2
 * 4
 * 3
 * 1
 * 0
 * 2
 */


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class FindNumberInRange {



    // Complete the findNumber function below.
    static String findNumber(List<Integer> arr, int k) {
        if(arr.contains(k)){
            return "YES";
        }
        return "NO";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int arrCount = Integer.parseInt(bufferedReader.readLine().trim());// read 1st line containing array count

        List<String> arrTemp = new ArrayList<>();

        /*
        IntStream range(int startInclusive, int endExclusive) returns a sequential ordered IntStream from startInclusive (inclusive) to endExclusive (exclusive) by an incremental
        */
        IntStream.range(0, arrCount).forEach(i -> {
            try {
                arrTemp.add(bufferedReader.readLine().replaceAll("\\s+$", ""));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> arr = arrTemp.stream()
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        int k = Integer.parseInt(bufferedReader.readLine().trim());

        String res = findNumber(arr, k);

        bufferedWriter.write(res);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
