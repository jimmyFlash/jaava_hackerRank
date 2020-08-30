package hackerrank_solutions;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import static java.util.stream.Collectors.toList;

/*
Sample Input 0

7 11
5 15
3 2
-2 2 1
5 -6
Sample Output 0

1
1
 */
public class ApplesAndOranges {

    private static final Scanner scanner = new Scanner(System.in);

    // Complete the countApplesAndOranges function below.
    static void countApplesAndOranges(int s, // house range start
                                      int t, // house range end
                                      int a, // apple tree (left)
                                      int b, // orange tree (right)
                                      int[] apples, // fallen apple distances array
                                      int[] oranges) // fallen orange distances array
    {

        List<Integer> applesInHouse = Arrays.stream(apples).boxed()
                .map(apple -> a + apple)
                .filter(apple -> apple >= s && apple <= t)
                .collect(toList());

        List<Integer> orangesInHouse = Arrays.stream(oranges).boxed()
                .map(orange -> b + orange)
                .filter(orange -> orange >= s && orange <= t)
                .collect(toList());

        System.out.println(applesInHouse.size());
        System.out.println(orangesInHouse.size());


    }

    public static void main(String[] args){

        String[] st = scanner.nextLine().split(" ");

        int s = Integer.parseInt(st[0]);

        int t = Integer.parseInt(st[1]);

        String[] ab = scanner.nextLine().split(" ");

        int a = Integer.parseInt(ab[0]);

        int b = Integer.parseInt(ab[1]);

        String[] mn = scanner.nextLine().split(" ");

        int m = Integer.parseInt(mn[0]);

        int n = Integer.parseInt(mn[1]);

        int[] apples = new int[m];

        String[] applesItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < m; i++) {
            int applesItem = Integer.parseInt(applesItems[i]);
            apples[i] = applesItem;
        }

        int[] oranges = new int[n];

        String[] orangesItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int orangesItem = Integer.parseInt(orangesItems[i]);
            oranges[i] = orangesItem;
        }

        countApplesAndOranges(s, t, a, b, apples, oranges);

        scanner.close();
    }
}
