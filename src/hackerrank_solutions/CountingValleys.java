package hackerrank_solutions;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CountingValleys {


    /*
     * Complete the 'countingValleys' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER steps
     *  2. STRING path
     */

    static int seaLevelDeviation = 0;
    static int valies = 0;
    //UDDDUDUU
    public static int countingValleys(int steps, String path) {

       /* List<String> pathList= Stream.of(path.split(""))
                .map(String::new)
                .collect(Collectors.toList());

        pathList.forEach(CountingValleys::pathCheck);*/
        int vallies = scanVallies(path, 0);
        System.out.println(vallies);
        return vallies;
    }

    private static void pathCheck(String str){
        if(str.equalsIgnoreCase("u")){
            seaLevelDeviation++;
        }else if(str.equalsIgnoreCase("d")){
            seaLevelDeviation--;
        }
        if(seaLevelDeviation == -1) {
            valies++;
        }
    }

    private static int scanVallies(String path, int cnt ){
        int currCnt = cnt;

        if(path.length() == 0) return currCnt;
        char curChar = path.charAt(0);
        if(curChar == 'U') {
            currCnt++;
        }else if (curChar == 'D'){
            currCnt--;
        }


        String updatedPath = path.substring(1);
        System.out.println(updatedPath + ", depth: " + currCnt);
        return scanVallies( updatedPath, currCnt);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
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
