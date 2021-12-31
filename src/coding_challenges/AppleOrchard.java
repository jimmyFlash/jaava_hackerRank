package coding_challenges;

import java.util.Arrays;

public class AppleOrchard {

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

    private int[] sliceAndSum (int[] tgtArray, int index, int range, int sum, int maxIndx){

        int[] maxSumAndIndex = new int[2];
        if(tgtArray.length < range || (index + range) > tgtArray.length){
            maxSumAndIndex[0] = sum;
            maxSumAndIndex[1] = maxIndx;
            return maxSumAndIndex;
        }

        int[] newArr = Arrays.copyOfRange(tgtArray, index, index + range);

        Integer intSum = Arrays.stream(newArr)
                .boxed()
                .reduce(0, Integer::sum);

        int maxSumIndx = maxIndx;
        int largestSum;
        if(intSum > sum) {
            largestSum = intSum;
            maxSumIndx = index;
        }else{
            largestSum = sum;
        }

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
