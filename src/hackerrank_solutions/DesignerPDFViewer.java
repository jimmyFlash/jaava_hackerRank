package hackerrank_solutions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;


public class DesignerPDFViewer {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/test/print_pdf_sample.txt"));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

       for (int i = 0 ; i < 2 ; i++){
           List<Integer> h = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "")
                           .split(" "))
                   .map(Integer::parseInt)
                   .collect(toList());

           String word = bufferedReader.readLine();

           int result = DesignerResult.designerPdfViewer(h, word);
           System.out.println("The rect. area for word: \"" + word + "\", is = " + result);
       }

//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();

        bufferedReader.close();
//        bufferedWriter.close();

    }
}

 class DesignerResult {

    /*
     * Complete the 'designerPdfViewer' function below.
     * each word is highlighted independently :: split sentence using space
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY h : list of letter heights per word
     *  2. STRING word
     * all letters are 1 mm wide : total width = sum of letters
     * calculate the area of rectangle highlighting the word in mm2
     * height of rectangle = the biggest letter height
     * each word has max of 10 letters
     * 1 <= height <= 7
     * area = height * width
     * map each letter in letters array to a height to create a Map<String, Integer>
     */

    public static int designerPdfViewer(List<Integer> h, String word) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        char[] charArr = alphabet.toCharArray();
        char[] wordArr = word.toCharArray();
        int largestHeight = 0;
        Map<Character, Integer> charHeightsMap = new Hashtable<>();

        for (int i = 0 ; i < charArr.length; i++){
            charHeightsMap.put(charArr[i], h.get(i));
        }

        for (char c : wordArr) {
            largestHeight = Math.max(largestHeight, charHeightsMap.get(c));
        }
        System.out.println("dictionary mapping: " + charHeightsMap);

        return largestHeight * word.length();

    }

}