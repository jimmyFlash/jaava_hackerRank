package hackerrank_solutions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Kangaroo {

    // Complete the kangaroo function below.
    static String kangaroo(int x1, int v1, int x2, int v2) {

        // distance travelled = offsetPosition + jumps*distancePerJump
        // p = x1 + j * v1, p = x2 + j * v2
        // x1 + j*v1 = x2 + j*v2
        // j= x1 - x2 / v2 - v1 => eliminate fractions => (x1 - x2) % (v2 - v1)
        if( (v2 > v1) || v2-v1 == 0){
            return "NO";
        }else{
            if((x1 - x2) % (v2 - v1) == 0){
                return "YES";
            }else{
                return "NO";
            }
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] x1V1X2V2 = scanner.nextLine().split(" ");

        int x1 = Integer.parseInt(x1V1X2V2[0]);

        int v1 = Integer.parseInt(x1V1X2V2[1]);

        int x2 = Integer.parseInt(x1V1X2V2[2]);

        int v2 = Integer.parseInt(x1V1X2V2[3]);

        String result = kangaroo(x1, v1, x2, v2);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }

}
