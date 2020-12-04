package hackerrank_solutions;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/*

9
10 5 20 20 4 5 2 25 1

2 4

10
3 4 21 36 10 28 35 5 24 42

4 0


 */
public class BreakingRecords {

    private static final Scanner scanner = new Scanner(System.in);

    static int lowestScoreCount = 0;
    static int highestScoreCount = 0;
    static int lowestRecord, highestRecord = 0;

    static int[] breakingRecords(int[] scores) {

        lowestRecord = highestRecord = scores[0];
         Integer result = Arrays.stream(scores).boxed()
                 .reduce(scores[0], BreakingRecords::calRecordBreakers);
         System.out.println("highest count: " + highestScoreCount + ", lowest count: " + lowestScoreCount);
        return new int[]{highestScoreCount, lowestScoreCount};
    }

    private static Integer calRecordBreakers(Integer currScore, Integer nextScore){
        System.out.println(currScore + "," + nextScore);
        if(currScore < nextScore){
            if(nextScore > highestRecord){
                highestRecord = nextScore;
                System.out.println("^");
                highestScoreCount++;
            }
        }else if(currScore > nextScore){
            if(nextScore < lowestRecord ){
                lowestRecord = nextScore;
                System.out.println("v");
                lowestScoreCount++;
            }
        }
        return nextScore;
    }

    public static void main(String[] args) throws IOException {

       // BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] scores = new int[n];

        String[] scoresItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int scoresItem = Integer.parseInt(scoresItems[i]);
            scores[i] = scoresItem;
        }

        int[] result = breakingRecords(scores);

      /*  for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(String.valueOf(result[i]));

            if (i != result.length - 1) {
                bufferedWriter.write(" ");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();*/

        scanner.close();
    }
}
