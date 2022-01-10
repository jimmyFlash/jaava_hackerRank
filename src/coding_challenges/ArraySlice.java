package coding_challenges;

/**
 * the below code, change one line in the (calculate) method : for loop
 * so that given the array (ar) with values and max_sum with default value of 2
 * have the main method return true
 */

public class ArraySlice {

    private static int max_sum = 2;
    private static int result = 13;
    private static int[] ar1 = {3, 4, 6, -2, 3, 4, 5, -8};
    private static int[] ar2 = {1, 2, -3, 4, 5, -6};
    private static int[] ar3 = {1, 2, -3, 4, -5, -4, 4, 7};
    private static int[] ar4 = {-1, -2, -3, -4, -5, -4, -4, -7};

    public static void main(String[] args) {

        System.out.println(calculate(ar1) == result);

    }

    private static int calculate (int[] ar){
        int current_sum = 0;
        boolean positive = false;
        int n = ar.length;
        for (int i = 0; i < n; ++i) {
            int item = ar[i];
            if (item < 0) {
                if (max_sum < current_sum
                ) {
                    max_sum = current_sum;
                    current_sum = 0;
                }
                System.out.println("i: " + i +"," + "item: " + item + " --current max " + max_sum + ", current_sum " + current_sum);
            } else {
                positive = true;
                current_sum += item;
                System.out.println( "i= " + i +"," + "item: " + item + " ++current max " + max_sum + ", current_sum " + current_sum);
            }
        }
        if (current_sum > max_sum) {
            max_sum = current_sum;
        }
        if (positive) {
            return max_sum;
        }
        return -1;
    }

}

