import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Prime numbers are numbers that are only divisible by 1 or themselves
 * 2 is the only even prime number
 *
 */
public class PrimeNumberScanner {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int maxRange = sc.nextInt(); // read the maxRange of numbers
        boolean[] isPrime = new boolean[maxRange + 1]; // create and array of booleans from 0 - range + 1
        Arrays.fill(isPrime, true); // fill array with with values of true
        // set the 1st and second indices to false
        isPrime[0] = false; // 0 not prime
        isPrime[1] = false; // 1 not prime

        // arraylist to store prime numbers
        ArrayList<Integer> primesFound = new ArrayList<>();

        for (int i = 2; i <= maxRange; i++) { // start loop from 2

            System.out.println("checking i= " + i);
            if (isPrime[i]) { // check prime flag
                primesFound.add(i); // add to primes arraylist
                int numSquare = i * i; // get the square root of the current number
                // as long as the numSquare is less than the max range
                while(numSquare < maxRange){
                    // the square of the value means that 3*3 = 9 so 9 is divisible by 3 making it not prime number
                    isPrime[numSquare] = false; // mark corresponding index in booleans array with false
                    numSquare += i; // increment the number by current iteration value
                }
            }
        }
        System.out.println(primesFound);
    }
}
