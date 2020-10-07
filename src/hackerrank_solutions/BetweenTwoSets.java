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

        Integer lcmFirstList = lcmList(a);
        Integer gcdScndList = gcdList(b);

        int i = lcmFirstList, j = 2 /* 0 => % 0 Arth. error, 1 => % 1 same value*/, count = 0;
        while(i <= gcdScndList){
           System.out.println(i + "-" + j);
           if(gcdScndList % i == 0){ count++;}
           i = lcmFirstList * j;
           j++;
        }
        return count;
    }

    static Integer lcmList(List<Integer> ls){
       Integer lcmSetResult = ls.get(0);
       for (int i = 1 ; i < ls.size() ; i++){
           lcmSetResult =  lcm(lcmSetResult, ls.get(i ));
       }
       return lcmSetResult;
    }

    static  Integer gcdList(List<Integer> ls){
        Integer gcdSetResult = ls.get(0);
        for (int i = 1 ; i < ls.size() ; i++){
            gcdSetResult= gcd(gcdSetResult, ls.get(i));
        }
        return gcdSetResult;
    }

    // Recursive method to return gcd of a and b
    private static int gcd(int a, int b)
    {
        if (a == 0)
            return b;
        int gcd_ = gcd(b % a, a);
        System.out.println("Greatest common divisor " + a + "," + b + " : " + gcd_);
        return gcd_;
    }

    // method to return LCM of two numbers
    static int lcm(int a, int b) {
        int lcm = (a / gcd(a, b)) * b;
        System.out.println("Lowest common multiple of " + a + "," + b + " : " + lcm);
        return (a / gcd(a, b)) * b;
    }



    public static void main(String[] args) throws IOException {

/*
2 3
2 4
16 32 96

2 2
2 6
24 36

2 2
3 4
24 48 (8 , 16)
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
