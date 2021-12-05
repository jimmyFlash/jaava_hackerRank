package hackerrank_solutions;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/*
5 3 4
1 5 8
6 4 2
answer = 7

4 9 2
3 5 7
8 1 5
answer = 1

8 3 5
1 5 8
6 4 2

4 8 2
4 5 7
6 1 6

2 9 8
4 2 7
5 6 7
answer = 21

4 5 8
2 4 1
1 9 7
answer = 14

6 1 6
2 9 8
5 6 7
 */
public class MagicSquare {

    static class Result {

        /*
         * Complete the 'formingMagicSquare' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts 2D_INTEGER_ARRAY s as parameter.
         */

        public static int formingMagicSquare(List<List<Integer>> s) {

            int[][] magicSqrThree = generateMagicSquarePermutations(3);
            System.out.println("Generating base magic 3 array > magicSqrThree");
            printArray(magicSqrThree);
            int[][] transformReflectVertical1 = mirrorArrayVertical(magicSqrThree);
            System.out.println("Apply transform vertical reflect on magicSqrThree > transformReflectVertical1");
            printArray(transformReflectVertical1);
            int[][] transformReflectHorizontal1 = mirrorArrayHorizontal(transformReflectVertical1);
            System.out.println("Apply transform horizontal reflect on transformReflectVertical1 > transformReflectHorizontal1");
            printArray(transformReflectHorizontal1);
            int[][] transformReflectVertical2 = mirrorArrayVertical(transformReflectHorizontal1);
            System.out.println("Apply transform vertical reflect on transformReflectHorizontal1 > transformReflectVertical2");
            printArray(transformReflectVertical2);
            int[][] rotateCounterClockWise1= rotateArrayCCW(transformReflectVertical2);
            System.out.println("Apply transform rotate CCW  on transformReflectVertical2 > rotateCounterClockWise1");
            printArray(rotateCounterClockWise1);
            int[][] rotateCounterClockWise2= rotateArrayCCW(magicSqrThree);
            System.out.println("Apply transform rotate CCW  on magicSqrThree > rotateCounterClockWise2");
            printArray(rotateCounterClockWise2);
            int[][] rotateClockWise1= rotateArray(transformReflectVertical2);
            System.out.println("Apply transform rotate on transformReflectVertical2 > rotateClockWise1");
            printArray(rotateClockWise1);
            int[][] rotateCounterClockWise3= rotateArrayCCW(transformReflectHorizontal1);
            System.out.println("Apply transform rotate CCW on transformReflectHorizontal1 > rotateCounterClockWise3");
            printArray(rotateCounterClockWise3);

             int[][][] allPermutations = new int[][][]{magicSqrThree, transformReflectVertical1,
                    transformReflectHorizontal1, transformReflectVertical2,
                    rotateCounterClockWise1, rotateCounterClockWise2,
                    rotateClockWise1, rotateCounterClockWise3
            };

          /*  precalculated

          int[][][] allPermutations =  {
                    {{4, 9, 2}, {3, 5, 7}, {8, 1, 6}},
                    {{2, 9, 4}, {7, 5, 3}, {6, 1, 8}},
                    {{6, 1, 8}, {7, 5, 3}, {2, 9, 4}},
                    {{8, 1, 6}, {3, 5, 7}, {4, 9, 2}},
                    {{6, 7, 2}, {1, 5, 9}, {8, 3, 4}},
                    {{2, 7, 6}, {9, 5, 1}, {4, 3, 8}},
                    {{4, 3, 8}, {9, 5, 1}, {2, 7, 6}},
                    {{8, 3, 4}, {1, 5, 9}, {6, 7, 2}}
            };
        */


            int minDebtfound = findMin(allPermutations, s);

            System.out.println("Least cost : " + minDebtfound);

            return minDebtfound;
        }

        private static int findMin(int[][][] allPermutations, List<List<Integer>> givenMagicSqure){
            int sumDepts, lastStoredsum = Integer.MAX_VALUE;
            int[][] squareId = new int[3][3];

            for (int[][] magicsquare : allPermutations){
                convert2DToList2D(magicsquare);
                System.out.println("\nComparing: >>>");
                printArray(magicsquare);
                System.out.println("TO: >>> " + givenMagicSqure);
                sumDepts = 0;
                for(int i = 0 ; i <  magicsquare.length ; i++){
                    for(int j = 0 ; j < magicsquare[i].length; j++){
                        sumDepts += Math.abs(magicsquare[i][j] - givenMagicSqure.get(i).get(j));

                    }
                }
                if (lastStoredsum > sumDepts) {
                    lastStoredsum = sumDepts;
                    squareId = magicsquare;
                }
                System.out.println("Sum found is: " + sumDepts);
            }
            System.out.println("\n\n<<< Your best bet is permutation >>> ");
            printArray(squareId);
            return lastStoredsum;
        }


        private static List<List<Integer>> convert2DToList2D(int[][] twoDArr){
            List<List<Integer>> twoDlist = Arrays.stream(twoDArr)
                    .map(internalArray -> Arrays.stream(internalArray).boxed().collect(Collectors.toList()))
                    .collect(Collectors.toList());
            System.out.println("\nThe magic square permutation 2d List representation: " + twoDlist);
            return twoDlist;
        }

        /**
         * Generates a magic square of order n. A magic squares is an n-by-n
         * matrix of the integers 1 to n^2, such that all row, column, and
         * diagonal sums are equal.
         *
         * One way to generate a magic square when n is odd is to assign
         * the integers 1 to n^2 in ascending order, starting at the
         * bottom, middle cell. Repeatedly assign the next integer to the
         * cell adjacent diagonally to the right and down. If this cell
         * has already been assigned another integer, instead use the
         * cell adjacently above. Use wrap-around to handle border cases.
         *
         * @param n number of columns * rows matrix to create
         * @return the magic square matrix
         */
        private static int[][] generateMagicSquarePermutations(int n){

            if (n % 2 == 0)
                throw new RuntimeException("n must be odd");

            int[][] magic = new int[n][n];

            int row = n-1; // bottom row
            int col = n/2; // middle column
            magic[row][col] = 1;  // assign 1 as our starting number

            for (int i = 2; i <= n * n; i++) {  // starting from number 2 and  ascending all the way to n square
                // check if the item on the next row & next column is empty ( 0 value )
                if (magic[(row + 1) % n][(col + 1) % n] == 0) {
                    row = (row + 1) % n;
                    col = (col + 1) % n;
                }else { // cell has value > 0
                    row = (row - 1 + n) % n; // move back one row then add n calculate % n
                    // don't change col
                }
                magic[row][col] = i; // assign
            }

            return magic;
        }
    }

    private static int[][] rotateArray(int[][] matrix){
        int size = matrix.length;
        int[][] rotated = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                rotated[i][j] = matrix[size - 1 - j][i];
            }
        }
        return rotated;
    }

    private static int[][] rotateArrayCCW(int[][] matrix){
        int size = matrix.length;
        int[][] rotated = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                rotated[i][j] = matrix[j][size - 1 - i];
            }
        }
        return rotated;
    }

    private static int[][] mirrorArrayHorizontal(int[][] matrix){
        int size = matrix.length;
        int[][] mirrored = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                mirrored[i][j] = matrix[size - 1 - i][j];
            }
        }
        return mirrored;
    }

    private static int[][] mirrorArrayVertical(int[][] matrix){
        int size = matrix.length;
        int[][] mirrored = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                mirrored[i][j] = matrix[i][size - 1 - j];
            }
        }
        return mirrored;
    }

    private static void printArray(int[][] arr){
        // print results
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i][j] < 10)
                    System.out.print(" ");  // for alignment
                if (arr[i][j] < 100)
                    System.out.print(" ");  // for alignment
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("\n");
    }

    public static void main(String[] args) throws IOException {
        String str = "4 9 2\n" +
                "3 5 7\n" +
                "8 1 5";
        ByteArrayInputStream bais = new ByteArrayInputStream(str.getBytes());
        System.setIn(bais);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        List<List<Integer>> s = new ArrayList<>();

        IntStream.range(0, 3).forEach(i -> {
            try {
                s.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = Result.formingMagicSquare(s);

//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();

        bufferedReader.close();
//        bufferedWriter.close();

    }
}
