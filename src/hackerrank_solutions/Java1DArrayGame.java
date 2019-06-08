package hackerrank_solutions;
/*
You are playing a game on your cell phone. You are given an array of length n, indexed 0 from to n - 1. Each element of the array is either 0 or 1. You can only move to an index which contains 0. At first, you are at the 0th position. In each move you can do one of the following things:

Walk one step forward or backward.
Make a jump of exactly length  m forward.
That means you can move from position x to x + 1 , x - 1 or  x + m in one move, but at least one of the following conditions must be true:

The new position contains 0.
The new position is greater than n - 1.
You can't move backward from position 0. If you move to any position greater than n - 1, you win the game.

Given the array and the length of the jump, you need to determine if it's possible to win the game or not.

Input Format

In the first line there will be an integer  T denoting the number of test cases. Each test case will consist of two lines. The first line will contain two integers, n and m. On the second line there will be  space-separated integers, each of which is either 0 or 1 .


Sample Input

4

5 3
0 0 0 0 0

6 5
0 0 0 1 1 1

6 3
0 0 1 1 1 0

3 1
0 1 0

Sample Output

YES
YES
NO
NO

Explanation

In the first case, you can just walk to reach the end of the array.
In the second case, you can walk to index 1 or 2 and jump from there. In the third case, jump length is too low,
 and you can't reach the end of the array. In the fourth case, jump length is 1,
 so it doesn't matter if you jump or walk, you can't reach the end.
*/

import java.util.Scanner;

public class Java1DArrayGame {

    public static void main(String[] args) {
        // read input stream from system 
        Scanner scan = new Scanner(System.in);
        
        // scoop the number of test cases from the very 1st line and advance cursor to new line
        int t = scan.nextInt();
        
       // loop on the number of test cases 
         for (int i = 0; i < t; i++){

            int n = scan.nextInt();// board length, cursor postion is advanced by 1 
            int m = scan.nextInt();// jump count, cursor postion is advanced by 1 and then to new line since this this is the end of this line

            //System.out.println( i + ", game length: " + n + ", jump: " + m );

            // for each test case crate an array of length matching the corresponding board length
            int[] a = new int[n];

            // loop on each test case 
             for (int j=0; j<n; j++){
                // populate the board set array postion with values from the line of space seperatee ints
                a[j] = scan.nextInt();// cursor postion is advanced by 1 and then to new line when j == n-1

                //System.out.println( "game data at index: " + j + ", value: " + a[j] );
             }

             boolean checker = checkPosPlay(0, m, a); 

             if(checker){
                System.out.println( "YES" );
             }else{
                System.out.println( "NO");
             }

        }
    }

private static boolean checkPosPlay(int pos, int jump, int[]  game){

        // 6, 3
        // exp : 0 0 0 1 1 1

    // no more moves... lost
         // System.out.println( "currPos : " + currPos + ", gameSet: " + gameSet.length );
         if (pos < 0 || game[pos] == 1) return false;

         // winner 
         if ((pos == game.length - 1) || (pos + jump > game.length - 1)) return true;
 

         // update the value at position currPos = 1 ( makred as played )
         game[pos] = 1;

         return checkPosPlay(pos + jump, jump, game) ||
                 checkPosPlay(pos + 1, jump, game) ||
                 checkPosPlay(pos - 1, jump, game);
    }
}



/*
sample and test log

4
5 3
0 0 0 0 0
6 5
0 0 0 1 1 1
6 3
0 0 1 1 1 0
3 1
0 1 0


0, game length: 5, jump: 3
    game data at : 0, value: 0
    game data at : 1, value: 0
    game data at : 2, value: 0
    game data at : 3, value: 0
    game data at : 4, value: 0

1, game length: 6, jump: 5
    game data at : 0, value: 0
    game data at : 1, value: 0
    game data at : 2, value: 0
    game data at : 3, value: 1
    game data at : 4, value: 1
    game data at : 5, value: 1

2, game length: 6, jump: 3
    game data at : 0, value: 0
    game data at : 1, value: 0
    game data at : 2, value: 1
    game data at : 3, value: 1
    game data at : 4, value: 1
    game data at : 5, value: 0

3, game length: 3, jump: 1
    game data at : 0, value: 0
    game data at : 1, value: 1
    game data at : 2, value: 0



 other solutions :-
private static boolean isSolvable(int m, int[] arr, int i) {
    if (i < 0 || arr[i] == 1) return false;
    if ((i == arr.length - 1) || i + m > arr.length - 1) return true;

    arr[i] = 1;
    return isSolvable(m, arr, i + 1) || isSolvable(m, arr, i - 1) || isSolvable(m, arr, i + m);
}

-------------------------------------------------

Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        for (int i=0;i<t;i++){
            int n = scan.nextInt();
            int m = scan.nextInt();
            int[] a = new int[n];
            boolean[] win = new boolean[n];
            for (int j=0;j<n;j++)
                a[j] = scan.nextInt();

            for (int j=n-1;j>=0;j--){
                if (a[j]==0&&(j+Math.max(1,m)>=n || win[j+1] || win[j+m])){
                    win[j] = true;
                    int k = j+1;
                    while (k<n-1&&a[k]==0&&!win[k]){
                        win[k] = true;
                        k++;
                    }
                }
            }

            System.out.println(win[0]?"YES":"NO");
        }
        scan.close();

*/