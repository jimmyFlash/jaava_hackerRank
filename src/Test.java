/**
 * the below code, change one line in the (calculate) method : for loop
 * so that given the array (ar) with values and max_sum with default value of 2
 * have the main method return true
 */

public class Test {

    private static int max_sum = 2;
    private static int result = 9;
    private static int[] ar = {2, -1, 3, 4, 2};

    public static void main(String[] args) {

        System.out.println(calculate(ar) == result);

    }

    private static int calculate (int[] ar){
        int current_sum = 0;
        boolean positive = false;
        int n = ar.length;
        for (int i = 0; i < n; ++i) {
            int item = ar[i];
            //System.out.println("Item " + item);
            if (item < 0) {
                if (max_sum <= current_sum) { /* if (max_sum < current_sum) { (error was here) */
                    max_sum = current_sum;
                    current_sum = 0;
                   // System.out.println("-current max " + max_sum + ", current_sum " + current_sum);
                }
            } else {
                positive = true;
                current_sum += item;
               // System.out.println("+current max " + max_sum + ", current_sum " + current_sum);
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

