package hackerrank_solutions; /**
 * input sample:
 * 5
 * 10
 */

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


public class FindOddNumberJava8 {

    // Complete the oddNumbers function below.
    private static List<Integer> oddNumbers(int l, int r) {
        List<Integer> list = new ArrayList<>();
        for (int i = l ; i <= r ; i++){
            if(i % 2 != 0){
                list.add(i);
            }
        }
        return list;
    }

    public static void main(String[] args) throws IOException {
        // InputStreamReader is a bridge from byte streams to character streams: It reads bytes and decodes them into characters using a specified charset 
        //BufferedReader also has a readLine() method which reads a whole line 
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int l = Integer.parseInt(bufferedReader.readLine().trim());// read 1st line containing left integer

        int r = Integer.parseInt(bufferedReader.readLine().trim());// read 2nd line containing right integer

        List<Integer> res = oddNumbers(l, r);

        bufferedWriter.write(
            //Stream API is used to process collections of objects
            res.stream()
                .map(Object::toString) // convter each object in the collection to string
                .collect(joining("\n")) // collect allows to perform mutable fold operations (repackaging elements)  on data elements held in a Stream instance in this case joining each item with a \n 
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
