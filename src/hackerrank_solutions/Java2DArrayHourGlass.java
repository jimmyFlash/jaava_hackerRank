package hackerrank_solutions;

import java.util.Scanner;


/**
 input
 0  1  2  3  4  5
 -----------------
 1  1  1  0  0  0

      0  1  0               0  0  0
 1       1      1           0  0  0
      0  9  2              -4 -4  0

 0  0  0 -2  0  0
 0  0 -1 -2 -4  0

 output
 13


 */
public class Java2DArrayHourGlass {

    private static final Scanner scanner = new Scanner(
            "1 1 1 0 0 0\n" +
            "0 1 0 0 0 0\n" +
            "1 1 1 0 0 0\n" +
            "0 9 2 -4 -4 0\n" +
            "0 0 0 -2 0 0\n" +
            "0 0 -1 -2 -4 0"
    );

    private static int[][] arr  = new int[6][6];

    private static int largetSum = Integer.MIN_VALUE;

    public static void main(String[] args) {

//        mySolution();

        optimalSoultion();
        System.out.println(largetSum);

        scanner.close();
    }


    private static void optimalSoultion(){
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++){
                arr[i][j] = scanner.nextInt();
                if(i > 1 && j > 1){
                    int sum = (arr[i][j]  + arr[i][j - 1] +  arr[i][j - 2]
                            +arr[i - 1 ][j - 1]+
                            arr[i-2][j]  + arr[i-2][j - 1] +  arr[i-2][j - 2]);
                    if(sum > largetSum){
                        System.out.println("largest sum @i: " + i+ ", j: " + j + ", sum = "+ sum);
                        largetSum = sum;
                    }
                }
            }
        }
    }

    private static void mySolution(){
        for (int i = 0; i < 6; i++) {
            String[] arrRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            for (int j = 0; j < 6; j++) {
                int arrItem = Integer.parseInt(arrRowItems[j]);
                arr[i][j] = arrItem;
            }
        }

        for(int i = 0; i < arr.length - 2; i++){
            for (int j = 0; j < arr[i].length - 2; j++){

                int sum = (arr[i][j]  + arr[i][j + 1] +  arr[i][j + 2]
                        +arr[i + 1 ][j + 1]+
                        arr[i+2][j]  + arr[i+2][j + 1] +  arr[i+2][j + 2]);
                if(sum > largetSum){
                    System.out.println("largest sum @ " + i+ "," + j + " sum = "+ sum);
                    largetSum = sum;
                }

            }
        }
    }
}


