package hackerrank_solutions;

import java.io.*;
import java.util.*;

/**
 * functional interface
 * an interface with only one method that takes in 1 parameter
 */
interface PerformOperation {
    boolean check(int a);
}

class MyMath {

    /**
     *  checks any method that implements the PerformOperation against the given
     *  parameter num
     * @param p function that implements the PerformOperation inertafce
     * @param num
     * @return boolean if operation is success or not
     */
    boolean checker(PerformOperation p, int num) {
        return p.check(num);
    }

    /**
     * check odd number using lambda expression that
     * @return an implementation of function interface PerformOperation
     */
    PerformOperation isOdd() {
        return (int num) -> num % 2 != 0;
    }

    /**
     * checks for prime numbers
     * @return an implementation of function interface PerformOperation
     */
     PerformOperation isPrime() {
        return (int n) -> java.math.BigInteger.valueOf(n).isProbablePrime(1);
    }

    /**
     * check for palindrome numbers i.e. if 121 is equal to it's reverse -> 121
     * @return an implementation of function interface PerformOperation
     */

     PerformOperation isPalindrome(){
        return (int n) -> Integer.toString(n).equals( new StringBuilder(Integer.toString(n)).reverse().toString() );
    }

}



public class LambdaExpressions {

    public static void main(String[] args) throws IOException {
        // instance of the MyMath class
        MyMath ob = new MyMath();
        // buffered reader reads the system input form console
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // read 1st integer indicating number of test cases
        PerformOperation op;
        boolean ret = false;
        String ans = null;
        // create a loop that goes from T to 0
        while (T--> 0) {
            String s = br.readLine().trim();// read each line from buffer (each test case containing operation type and number to check)
            StringTokenizer st = new StringTokenizer(s);
            int ch = Integer.parseInt(st.nextToken());// operation type number
            int num = Integer.parseInt(st.nextToken());// number to check

            if (ch == 1) {
                op = ob.isOdd();
                ret = ob.checker(op, num);
                ans = (ret) ? "ODD" : "EVEN";
            } else if (ch == 2) {
                op = ob.isPrime();
                ret = ob.checker(op, num);
                ans = (ret) ? "PRIME" : "COMPOSITE";
            } else if (ch == 3) {
                op = ob.isPalindrome();
                ret = ob.checker(op, num);
                ans = (ret) ? "PALINDROME" : "NOT PALINDROME";

            }
            System.out.println(ans);
        }
    }
}
