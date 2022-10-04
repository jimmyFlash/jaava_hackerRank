package hackerrank_solutions;

import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class HurdleRace {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine()
                .replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> height = Stream.of(bufferedReader.readLine()
                .replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = HurdleRaceResult.hurdleRace(k, height);

//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();

        bufferedReader.close();
//        bufferedWriter.close();
    }
}


class HurdleRaceResult {


    /*
     * Complete the 'hurdleRace' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER k
     *  2. INTEGER_ARRAY height
     */

    /*
5 4
1 6 3 5 2

5 7
2 5 4 5 2

5 1
1 2 3 3 2
     */

    public static int hurdleRace(int k, List<Integer> height) {
        Optional<Integer> maxHurdle = height.stream()
                .max(Integer::compareTo);

        int maxDose = maxHurdle.orElse(k);

        int neededDosage = (k >= maxDose)? 0 :maxDose - k;
        System.out.println(neededDosage);
        return neededDosage;
    }

}

