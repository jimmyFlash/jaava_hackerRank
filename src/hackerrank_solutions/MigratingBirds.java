package hackerrank_solutions;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class MigratingBirds {

    /*
6
1 4 4 4 5 3

11
1 2 3 4 5 4 3 2 1 3 4

6
4 4 1 1 5 3
     */
    private static final String testInput1 = "6\r\n1 4 4 4 5 3";
    private static final String testInput2 = "11\r\n1 2 3 4 5 4 3 2 1 3 4";

    // Complete the migratoryBirds function below.
    static int migratoryBirds(List<Integer> arr) {

        Map<Integer, Integer> birdsCatMap = new HashMap<>();
//        List<Integer> listWithoutDuplicates = new ArrayList<>(new HashSet<>(arr));
        
        for (Integer birdId : arr){
            if(birdsCatMap.get(birdId) == null){
                birdsCatMap.put(birdId, 0);
            }else{
                Integer cnt = birdsCatMap.get(birdId);
                birdsCatMap.put(birdId, ++cnt);
            }
        }
        return compareHashValues(birdsCatMap);
    }

    private static int compareHashValues(Map<Integer, Integer> birdsCatMap){

        int largestKey = 0;
        int largestVal = 0;
        for (Map.Entry<Integer, Integer> entry : birdsCatMap.entrySet()){
            if(entry.getValue() > largestVal){
                largestVal = entry.getValue();
                largestKey = entry.getKey();
            }else if (entry.getValue() == largestVal && entry.getKey() < largestKey){
                largestVal = entry.getValue();
                largestKey = entry.getKey();
            }
        }
        return largestKey;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(System.in)
        );
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int arrCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "")
                .split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = migratoryBirds(arr);

        System.out.println("highest bird id: " + result);

//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();

        bufferedReader.close();
//        bufferedWriter.close();
    }
}
