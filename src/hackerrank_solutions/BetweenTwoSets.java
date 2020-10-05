package hackerrank_solutions;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class BetweenTwoSets {

    /*
     * Complete the 'getTotalX' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY a
     *  2. INTEGER_ARRAY b
     *
     * [2,4] -> [0,4] -> [4]
     * [6, 4, 3] -> [6, 0, 0] -> [6]
     */

    public static int getTotalX(List<Integer> a, List<Integer> b) {


        List<Integer> cond_2_list = b.stream()
                .flatMap(n -> calcCount2(a, a.size(), n).stream())
                .distinct()
                .collect(toList());

        List<Integer> cond_3_list = cond_2_list.stream()
                .flatMap(n -> calcCount(b, b.size(), n).stream())
                .distinct()
                .collect(toList());


        System.out.println( "condition 1:" + cond_2_list
                        +  ", condition 2:" + cond_3_list

                );
        return 0;

    }


    private static List<Integer> calcCount(List<Integer> lst, int n, int k)
    {
        List<Integer> filtered = new ArrayList<>();

        int i;
        // Loop to consider every
        // element of array
        for(i = 0; i < n; i++){
            if ( lst.get(i) % k != 0 ) {
                break;
            }
        }
        if(i == n) filtered.add(k);
        return filtered;
    }

    private static List<Integer> calcCount2(List<Integer> lst, int n, int k)
    {
        List<Integer> filtered = new ArrayList<>();

        // Loop to consider every
        // element of array
        for(int i = 0; i < n; i++){
            if (k != 0 && lst.get(i) != 0 && k % lst.get(i) == 0 && k != lst.get(i)) {
                filtered.add(k/lst.get(i));
            }
        }
        return filtered;
    }




    public static void main(String[] args) throws IOException {

        String s = "2 3\n" +
                "2 4\n" +
                "16 32 96";

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> brr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int total =  getTotalX(arr, brr);

       // bufferedWriter.write(String.valueOf(total));
        //bufferedWriter.newLine();

        bufferedReader.close();
       // bufferedWriter.close();

    }
}
