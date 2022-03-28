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
        Palindrome plndrm = new Palindrome();
        System.out.println(palindromeTst1 + " is palindrome: " + plndrm.isItPalindrome(palindromeTst1));
        System.out.println(palindromeTst2 + " is palindrome: " +plndrm.isItPalindrome(palindromeTst2));
        System.out.println(palindromeTst3 + " is palindrome: " +plndrm.isItPalindrome(palindromeTst3));
        System.out.println(palindromeTst4 + " is palindrome: " +plndrm.isItPalindrome(palindromeTst4));

        int factorialNum = 10;
        Factorial fac = new Factorial();
        System.out.println(factorialNum + "! = " + fac.factorial(factorialNum));


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

            int lengthHalf = palindromeStr.length() / 2  ;
            int matches = 0;
            int offsetMidPoint = 0;

            if(!(palindromeStr.length() % 2 == 0))
                offsetMidPoint = 1;

            String[] set1 = palindromeStr.substring(0, lengthHalf).split("");
            String[] set2 = palindromeStr.substring(lengthHalf + offsetMidPoint).split("");


            for (int i = 0 ; i < set1.length; i++){
                if(set1[i].equalsIgnoreCase(set2[set2.length - 1 - i])){
                    matches++;
                }
            }

            return matches == set1.length && matches == set2.length;
        }
    }

    static class Factorial{

        int factorial(int n){
            if (n == 0)
                return 1;
            else
                return(n * factorial(n-1));
        }

    }
}
