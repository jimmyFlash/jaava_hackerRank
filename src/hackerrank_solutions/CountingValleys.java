package hackerrank_solutions;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CountingValleys<priavte> {


    /*
     * Complete the 'countingValleys' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER steps
     *  2. STRING path
     */
    public static int countingValleys(int steps, String path) {

       /* List<String> pathList= Stream.of(path.split(""))
                .map(String::new)
                .collect(Collectors.toList());

        */

        int currCnt = 0;
        int totalVally = 0;
        int cnt;
        for (int i = 0 ; i < steps; i++){
            char curChar = path.charAt(i);
            cnt = currCnt;
            if(curChar == 'U') {
                currCnt++;
            }else if (curChar == 'D'){
                currCnt--;
            }

            if(cnt == 0 && currCnt == -1) totalVally++;
        }
        System.out.println("number valleys: " + totalVally);
        return totalVally;
    }


    public static void main(String[] args) throws IOException {
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/test/vally_count_input-718.txt"));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int steps = Integer.parseInt(bufferedReader.readLine().trim());

        String path = bufferedReader.readLine();

        int result = countingValleys(steps, path);

//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();

        bufferedReader.close();
//        bufferedWriter.close();
    }
}
