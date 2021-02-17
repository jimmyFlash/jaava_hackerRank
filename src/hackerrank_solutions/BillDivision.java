package hackerrank_solutions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/*
4 1
3 10 2 9
7


4 1
3 10 2 9
12

// output : 4009
 */
public class BillDivision {

    // Complete the bonAppetit function below.
    static void bonAppetit(List<Integer> bill, int k, int b) {
        bill.remove(k);
        double sum = bill.stream()
//                .filter(i -> !i.equals(bill.get(k)))
                .mapToDouble(Integer::doubleValue)
                .sum();
        double annaShouldGet = (double) b - (sum / 2);

       System.out.println((sum / 2) == b ? "Bon Appetit" : Math.round(annaShouldGet));

    }

    public static void main(String[] args) throws IOException {

        File file = new File("src/test/testcase.txt");
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/test/testcase.txt"));

        String[] nk = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        List<Integer> bill = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int b = Integer.parseInt(bufferedReader.readLine().trim());

        bonAppetit(bill, k, b);

        bufferedReader.close();
    }

}
