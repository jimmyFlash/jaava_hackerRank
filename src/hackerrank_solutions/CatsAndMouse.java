package hackerrank_solutions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CatsAndMouse {

    private static final Scanner scanner = new Scanner("2\n" +
            "1 2 3\n" +
            "1 3 2");

    // Complete the catAndMouse function below.
    static String catAndMouse(int x, int y, int z) {

        int deltaCatAMouse = Math.abs(z - x);
        int deltaCatBMouse = Math.abs(z - y);

        String winner = deltaCatAMouse == deltaCatBMouse ? "Mouse C" :
                deltaCatAMouse > deltaCatBMouse ? "Cat B" : "Cat A";

        System.out.println(winner);
        return winner;
    }




    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(  new FileWriter("src/test/cats_mouse_test.txt"));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] xyz = scanner.nextLine().split(" ");

            int x = Integer.parseInt(xyz[0]);

            int y = Integer.parseInt(xyz[1]);

            int z = Integer.parseInt(xyz[2]);

            String result = catAndMouse(x, y, z);

//            bufferedWriter.write(result);
//            bufferedWriter.newLine();
        }

//        bufferedWriter.close();

        scanner.close();
    }
}
