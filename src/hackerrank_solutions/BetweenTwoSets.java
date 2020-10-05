package hackerrank_solutions;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class BetweenTwoSets {

    /*
     * Complete the 'getTotalX' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY a
     *  2. INTEGER_ARRAY b
     *
     */

    public static int getTotalX(List<Integer> a, List<Integer> b) {


        List<Integer> cond_1_list = b.stream()
                .flatMap(n -> frstArrFactorOfNum(a, a.size(), n).stream())
                .distinct()
                .collect(toList());

        List<Integer> cond_2_list = cond_1_list.stream()
                .flatMap(n -> numberFactorOfAllSecArr(a, a.size(), n, true).stream())
                .distinct()
                .collect(toList());

        List<Integer> cond_3_list = cond_2_list.stream()
                .flatMap(n -> numberFactorOfAllSecArr(b, b.size(), n, false).stream())
                .distinct()
                .collect(toList());


        System.out.println( "condition 1:" + cond_1_list
                        +  ", condition 2:" + cond_2_list
                        +  ", condition 3:" + cond_3_list

                );
        return 0;

    }


    private static List<Integer> numberFactorOfAllSecArr(List<Integer> lst, int n, int k, boolean revrse)
    {
        List<Integer> filtered = new ArrayList<>();

        int i;
        for(i = 0; i < n; i++){
            int mod = lst.get(i) % k;
            if(revrse) mod = k % lst.get(i);
            if ( mod != 0 ) {
                break;
            }
        }
        if(i == n) filtered.add(k);
        return filtered;
    }

    private static List<Integer> frstArrFactorOfNum(List<Integer> lst, int n, int k)
    {
        List<Integer> filtered = new ArrayList<>();
        int i;

        for( i = 0; i < n; i++){
            if ( k % lst.get(i) == 0 ) {
                filtered.add(k/lst.get(i));
            }else{
                return new ArrayList<>();
            }
        }
        return filtered;
    }


    public static void main(String[] args) throws IOException {

/*
2 3
2 4
16 32 96

2 2
2 6
24 36
*/
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
