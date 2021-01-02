package hackerrank_solutions;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


/*

1
4
4 1
>> 1

5
1 2 1 3 2
3 2
>> 2


6
1 1 1 1 1 1
3 2
>> 0

19
2 5 1 3 4 4 3 5 1 1 2 1 4 1 3 3 4 2 1
18 7
>> 3
 */
public class SubArrayDivision {

    // Complete the birthday function below.
    static int birthday(List<Integer> s, int d, int m) {
        Integer tempVal = s.get(0);
        int result = 0;

        result += checkChunks(s, 0, d, m, 0);

        System.out.println("total pieces given = " + result);

        return result;
    }

    private static int checkChunks(List<Integer> lst, int start, int maxSum, int count, int totalMatches) {

        int matchesCnt = totalMatches;
        int subsetLength = start + count;
        if (subsetLength > lst.size())
            return matchesCnt;

        int updateInit = lst
                .subList(start, subsetLength)
                .stream()
                .reduce(0, Integer::sum);

        if (updateInit == maxSum)
            matchesCnt++;


        return checkChunks(lst, ++start, maxSum, count, matchesCnt);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> s = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        String[] dm = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int d = Integer.parseInt(dm[0]);

        int m = Integer.parseInt(dm[1]);

        int result = birthday(s, d, m);

//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();

        bufferedReader.close();
//        bufferedWriter.close();
    }
}
