package hackerrank_solutions;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

// array of space separated integers
// range from 1 to 100
// find the sub array with max number of integers where the abs difference between each integer is 0 or 1
// we don't need to list the elements in the array, just the size of the array with max number of elements


public class PickingNumbers {

    /*
     * Complete the 'pickingNumbers' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY as parameter.
     */

    public static int pickingNumbers(List<Integer> a) {

        //4 6 5 3 3 1 -> ans: 3
        //1 2 2 3 1 2 -> ans: 5
        //98 3 99 1 97 2 -> ans: 2

        int sol1 = solution1(a);
        System.out.println("Solution1 - Max sub array length: " + sol1);
        solution2(a);
        return sol1;

    }

    private static int solution1(List<Integer> a){
        int maxSumOfArraySubset = 0; // counter for max length of sub array
        // given, our integer values will range from 2 to 100
        int[] elementsRepeatFrequencyArr = new int[100];
        // for each integer value update the count/frequency of occurrences in
        a.forEach(i -> elementsRepeatFrequencyArr[i]++);
        // loop though array of occurrence frequency
        for (int i = 0 ; i < elementsRepeatFrequencyArr.length - 1; i++){
            // find the max between the current value of maxSumOfArraySubset and the sum of each two adjacent elements
            // update maxSumOfArraySubset value
            maxSumOfArraySubset = Math.max(maxSumOfArraySubset,
                    elementsRepeatFrequencyArr[i] + elementsRepeatFrequencyArr[i + 1]);
        }
        return maxSumOfArraySubset;
    }

    /**
     * this solution doesn't care about the min/max range of the integers in list
     * it also prints the content fo the max sub list of numbers
     * the complexity here is higher than that of solution 1
     * @param a
     * @return
     */
    private static int solution2(List<Integer> a){
        List<List<Integer>> maxSubset = new ArrayList<>();

        // sort the list of integers
        //4 6 5 3 3 1 - > 1 3 3 4 5 6
        a.sort(Comparator.comparing(Integer::intValue));
        System.out.println("Sorted: " + a);


        main : for (int i = 0 ; i < a.size(); i++){
            // new sublist list t hold all integers that have diff <=1 with current iteration
            List<Integer> diffList = new ArrayList<>();
            diffList.add(a.get(i)); // add the current integer to the list
            for (int j = i + 1 ; j < a.size(); j++){ // loop from next item to the current till end of list

                // get the difference between both iterations
                if(classAbsDiff(a.get(i), a.get(j)) != -1) {
                    diffList.add(a.get(j)); // add to current sublist
                }else{
                    // add the current list to top level maxSubset
                    maxSubset.add(diffList);
                    continue main; // skip the loop on main loop
                }
            }
        }

        int maxSubsetLength = 0;
        List<Integer> maxSubsetList = null;
        // loop top level list maxSubset sub lists to determine the max sublist
        for (List<Integer> i : maxSubset){
            if(i.size() > maxSubsetLength) {
                maxSubsetLength = i.size();
                maxSubsetList = i;
            }
        }
        System.out.println("Solution2 - subset max length: " + maxSubsetLength + ", elements : "  + maxSubsetList);
        return maxSubsetLength;
    }

    private static int classAbsDiff(int n1, int n2){

        int result = Math.abs(n1 - n2);
        if(result <= 1)
            return result;

        return -1;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/test/pickingNumber.txt")); // 22
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> a = Stream.of(bufferedReader.readLine()
                .replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = pickingNumbers(a);

//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();

        bufferedReader.close();
//        bufferedWriter.close();
    }
}
