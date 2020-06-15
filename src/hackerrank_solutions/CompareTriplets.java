package hackerrank_solutions;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class CompareTriplets {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(/*System.getenv("OUTPUT_PATH")*/ "../out/resources/FileWriterTest.txt"));

        List<Integer> a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> b = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> result = compareTriplets(a, b);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining(" "))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();

    }

    // Complete the compareTriplets function below.
    static List<Integer> compareTriplets(List<Integer> a, List<Integer> b) {

       Integer aliceScore = (a.get(0) > b.get(0)? 1 : 0) + (a.get(1) > b.get(1)? 1 : 0) + (a.get(2) > b.get(2)? 1 : 0);
       Integer bobScore = (a.get(0) < b.get(0)? 1 : 0) + (a.get(1) < b.get(1)? 1 : 0) + (a.get(2) < b.get(2)? 1 : 0);
        return Arrays.asList(aliceScore, bobScore);
    }
}
