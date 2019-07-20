package hackerrank_solutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
problem question : java-arraylist_java8Streams.pdf

sample input
    55
    41 77 74 22 44
    1 12
    4 37 34 36 52
    03
    20 22 33
    51
    3
    3 4
    3 1
    4 3
    5 5

 */

public class JavaArrayStreams {

    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            Stream<String> linesStream = bufferedReader.lines();
            List<String> collect = linesStream.collect(Collectors.toList());
            //collect.forEach(System.out::println);
            int problems = Integer.parseInt(collect.get(0));
            //int queries = Integer.parseInt(collect.get(problems + 1));
            // System.out.println(problems + "/" + queries + "->" + collect.get(problems + 2));
            IntStream.range(problems + 2 , collect.size() ).forEach(i -> {
                String[] splitQuerySet = collect.get(i).split(" ");
                int linNo = Integer.parseInt(splitQuerySet[0]);
                int elementNo = Integer.parseInt(splitQuerySet[1]);
                String[] rowSetarr = collect.get(linNo).split(" ");

                // System.out.println("line: " +linNo + "/ number: " + elementNo+ "> " + Arrays.toString(rowSetarr));
                if(rowSetarr.length-1 < elementNo){
                    System.out.println("ERROR!");
                }else{
                    System.out.println(rowSetarr[elementNo]);
                }

            });

            bufferedReader.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }
}
