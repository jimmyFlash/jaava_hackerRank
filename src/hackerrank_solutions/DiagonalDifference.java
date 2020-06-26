package hackerrank_solutions;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class DiagonalDifference {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("../out/resources/FileWriterTest.txt"));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> arr = new ArrayList<>();

        IntStream.range(0, n).forEach(i -> {
            try {
                arr.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = Result.diagonalDifference(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

}


class Result {

    /*
     * Complete the 'diagonalDifference' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY arr as parameter.
     */

    static int i = 0;
    static int j = 0;
    static int sum_1 = 0;
    static boolean change = false;
    public static int diagonalDifference(List<List<Integer>> arr) {
        int a = arr.size();
        int totalSize = a * a;
        if(i == a && !change){
            change = true;
            j = 0;
        }

        if( i < a && !change){
            sum_1 += arr.get(i++).get(j++);
            diagonalDifference(arr);
        }

        if(i > 0 && change){
            sum_1 -= arr.get(--i).get(j++);
            diagonalDifference( arr);
        }

        return Math.abs(sum_1);

    }

}