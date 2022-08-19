package coding_challenges;

import java.util.Arrays;

public class BasicChallenages {

    public static void main(String[] args) {


        Integer[] reverseArrTst = new Integer[]{1, 2, 3, 4, 5, 6};
        ArrayReverse<Integer> arrRev = new ArrayReverse<>(reverseArrTst);
        System.out.println(Arrays.deepToString(reverseArrTst) + ", Reversed: " +
                Arrays.deepToString(arrRev.applyReverse(0)));

        String palindromeTst1 = "jmj";
        String palindromeTst2 = "jmmj";
        String palindromeTst3 = "jmjmj";
        String palindromeTst4 = "jmjjmj";
        String palindromeTst5 = "montm";
        Palindrome plndrm = new Palindrome();
        System.out.println(palindromeTst1 + " is palindrome: " + plndrm.isItPalindrome(palindromeTst1));
        System.out.println(palindromeTst2 + " is palindrome: " +plndrm.isItPalindrome(palindromeTst2));
        System.out.println(palindromeTst3 + " is palindrome: " +plndrm.isItPalindrome(palindromeTst3));
        System.out.println(palindromeTst4 + " is palindrome: " +plndrm.isItPalindrome(palindromeTst4));
        System.out.println(palindromeTst5 + " is palindrome: " +plndrm.isItPalindrome(palindromeTst5));

        int factorialNum = 10;
        Factorial fac = new Factorial();
        System.out.println(factorialNum + "! = " + fac.factorial(factorialNum));

        int fib = 5;
        Fibonacci fb = new Fibonacci();
        fb.intArr(fib);
        System.out.println(fb.calculateFib(fb.intArr.length));


    }

    /**
     * reversal of an array is achieved by iterating through the array until the middle (array.length /2) :: int
     * have to variables 1 to hold the current value at index (i)
     * the second to hold the corresponding value at (array length - 1 - i)
     * then you swap the current value at i with value stored at (array length - 1 - i) and vise versa
     * @param <T> generic type
     */
    static class ArrayReverse<T>{
        T[] arr;
        public ArrayReverse(T[] arr_) {
            this.arr = arr_;
        }

        // recursive method for array reversal
        public T[] applyReverse(int count){

            if(count == arr.length / 2) // should always return int value whether length is odd or even
                return arr;

            int countDecrement = (arr.length - 1) - count ;
            T currentItem = arr[count];
            arr[count] = arr[countDecrement];
            arr[countDecrement] = currentItem;
            return applyReverse(++count);
        }
    }


    private static class Palindrome{

        boolean isItPalindrome(String palindromeStr){

            int lengthHalf = palindromeStr.length() / 2;
            int matches = 0;

            for (int i = 0 ; i < lengthHalf ;i++){
               if( palindromeStr.charAt(i) == palindromeStr.charAt(palindromeStr.length() - 1 - i)){
                   matches++;
               }
            }
            return matches == lengthHalf;
        }
    }

    // recursive factorial
    static class Factorial{
        int factorial(int n){
            if (n == 0)
                return 1;

            return(n * factorial(n-1));
        }

    }

    static class Fibonacci{

        int[] intArr;

        int[] intArr(int n){
            intArr =  new int[n + 1];
            intArr[1] = 1;
            intArr[2] = 1;

            return intArr;
        }
        // 0,1,2,3,4,5,6,7,8
        // 0,1,1,2,3,5,8,13,21
        int calculateFib(int n){
            if(n <= 2){
                return 1;
            }
            intArr[n] = calculateFib(n -1) + calculateFib(n -2);
            return intArr[n];
        }
    }
}
