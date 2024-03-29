package coding_challenges;

import java.util.Arrays;

public class AppleOrchard {

    /*
        test cases:-

            1- int[] : the array N of apple tress
            2- int : Alice's range of trees
            3- int : Bob's range of trees
     */
    private static final int[] A = new int[]{6, 1, 4, 6, 3, 2, 7, 4};
    private static final int K = 3;
    private static final int L = 2;

    private static final int[] B = new int[]{10, 19, 15};
    private static final int K2 = 2;
    private static final int L2 = 2;


    private static final int[] C = new int[]{10, 9, 15, 13 ,15 ,6 ,0 ,7 ,5 ,10 ,2 ,14  ,11 , 1};
    private static final int K3 = 4;
    private static final int L3 = 3;


    private static final int[] D = new int[]{10, 9, 15, 6, 1, 4, 6, 3, 2, 7, 4, 13 ,15 ,6 ,0 ,7, 10, 19,
            15 ,5 ,10 ,2 ,14  ,11 , 1};
    private static final int K4 = 3;
    private static final int L4 = 5;


    /**
     *
     * @param A  There are N apple trees stored in array A
     * @param K  Alice collects form K trees consecutive
     * @param L  Bob collects from L consecutive
     * @return max collected apples or -1
     *
     */
    public int solution(int[] A, int K, int L) {

        int[] aliceLargest = sliceAndSum (A, 0, K, -1, -1);
        int[] bobLargest = sliceAndSum (A, aliceLargest[1] + K, L, -1, -1);
        
        System.out.println("Alice highest: " + aliceLargest[0]+ ", index:  " + aliceLargest[1]);
        System.out.println("Bob highest: " + bobLargest[0]+ ", index:  " + bobLargest[1]);
        int modulo = 1000000007;
        int sum = -1;
        if(aliceLargest[0] > -1 && bobLargest[0] > -1 )
            sum = (aliceLargest[0] +  bobLargest[0]) % modulo;
        return sum;
    }

    /**
     * helper recursive methods that calculates max sum in a range from given array and returns it with index
     * @param tgtArray: the base array to check
     * @param index: current index to start from
     * @param range: the range to count after the start index
     * @param sum: the sum in the ranges
     * @param maxIndx: the index of max range count
     * @return a 2 element array containing max sum for given range, and it's index
     */
    private int[] sliceAndSum (int[] tgtArray, int index, int range, int sum, int maxIndx){

        int[] maxSumAndIndex = new int[2];
        // terminal condition
        if(tgtArray.length < range || (index + range) > tgtArray.length){
            maxSumAndIndex[0] = sum;
            maxSumAndIndex[1] = maxIndx;
            return maxSumAndIndex;
        }

        // create a copy array of ints from target array starting at index and up to start index + range count
        int[] newArr = Arrays.copyOfRange(tgtArray, index, index + range);

        // get the sum of int elements in the copy array
        Integer intSum = Arrays.stream(newArr)
                .boxed() // boxed because we are using array of int, needs to be boxed to Inetger for stream
                .reduce(0, Integer::sum);

        int maxSumIndx = maxIndx; // keep track of the max index
        int largestSum;
        // if the calculated sum of copy array is > the passed value of sum argument
        if(intSum > sum) {
            // assign the value of the largest calculated sum and save ref. to the current index as max index
            largestSum = intSum;
            maxSumIndx = index;
        }else{
            // hold the current sum as the still being the largest calculated
            largestSum = sum;
        }

        // recursive call the with index move one place
        return  sliceAndSum (tgtArray, index + 1, range, largestSum, maxSumIndx);
    }

    public static void main(String[] args) {

        AppleOrchard appleOrchard = new AppleOrchard();
        int sol1 = appleOrchard.solution(A, K, L);
        System.out.println(sol1);

        int sol2 = appleOrchard.solution(D, K2, L2);
        System.out.println(sol2);

        int sol3 = appleOrchard.solution(D, K3, L3);
        System.out.println(sol3);

        int sol4 = appleOrchard.solution(D, K4, L4);
        System.out.println(sol4);

    }
}
