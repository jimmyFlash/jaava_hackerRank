package hackerrank_solutions;

import java.util.Scanner;

public class StairCase {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        staircase(n);

        scanner.close();

    }

    private static void staircase(int n) {

        for (int i =0 ;i < n ; i++){
            StringBuilder hash = new StringBuilder();
            int v = (n-1) - i;
            //System.out.println(v);
            for (int j = 0 ;j < n ; j++){

                if(j < v ){
                    hash.append(" ");
                }else{
                    hash.append("#");
                }
            }
            System.out.println(hash);
        }
    }
}
