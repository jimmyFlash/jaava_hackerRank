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
        int maxRange = sc.nextInt();
        boolean[] isPrime = new boolean[maxRange + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        ArrayList<Integer> primesFound = new ArrayList<>();

        for (int i = 2; i <= maxRange; i++) {

            System.out.println("checking i= " + i);
            if (isPrime[i]) {
                primesFound.add(i);
                int numSquare = i * i;

                while(numSquare <= maxRange){

                    isPrime[numSquare] = false;
                    numSquare += i;
                }
            }
        }
        System.out.println(primesFound);
    }
}
