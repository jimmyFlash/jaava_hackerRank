package hackerrank_solutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * challenge question found in: hackerrank_questions > java-list.pdf
 */
public class JavaLists_AddDelete {

    public static void main(String[] args) {

        workingSolution ();
    }


    private static void workingSolution (){
        Scanner sc=new Scanner(System.in);// scan system input
        int n=sc.nextInt();// read the next (token) integer in a line (1st line has only one token) the initial number of elements
        List<Integer> l= new ArrayList<>();
        for(int i=0;i<n;i++){// based on n
            int x=sc.nextInt(); //read every token in the current line as integer
            l.add(x);// add it to the list l
        }

        int operationno=sc.nextInt(); // read the next token integer indicating the number of operations

        for (int k=0;k<operationno;k++){
            String Oper=sc.next(); // scan the compete token string representing the operation type
            if (Oper.equals("Insert")) // if insert
            {
                int index=sc.nextInt(); // scan the next integer token for the index to insert at
                int value=sc.nextInt(); // scane the next integer token for the value to insert
                l.add(index,value);// add the value at the specified index to the l list
            }
            else if(Oper.equals("Delete")) // if it's delete
            {
                int index=sc.nextInt();// scan next integer token indicating the position to delete from
                l.remove(index);// remove item at index from list
            }

        }

        sc.close();// close the scanner
        int j=0;
        while(j<l.size()){ // loop through the list
            System.out.print(l.get(j)+" "); // print it's elements all in one line
            j++;
        }
    }

    private static List<Integer> intsArr;
    /**
     *
     */
    private static void mySolutionWithJavaStreams(){



        Pattern pattern = Pattern.compile("\\s");// pattern to match for space
        // read system input and store in read buffer
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try{
            int arrCount = Integer.parseInt(bufferedReader.readLine().trim());// read 1st line containing length of array
            // create an array of ints by splitting the line of array elements into a stream
            // applying map to convert each string element into integer
            // collect the stream into a list
            intsArr = pattern.splitAsStream(
                    bufferedReader.readLine().trim())
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());
            // read the number of operation line converting it's value to int
            int numberOfOps = Integer.parseInt(bufferedReader.readLine().trim());
            // System.out.println("num-ops: " + numberOfOps);
            // read the remaining lines of the read buffer into a stream
            Stream<String> linesStream = bufferedReader.lines();
            // collect lines into a list of strings
            List<String> opsList = linesStream.collect(Collectors.toList());

            /*
              using stream reduce take two consecutive elements in the list
              (operation & values) and apply them to the array of ints
              using helper method testLogig
             */
            opsList.stream().reduce(JavaLists_AddDelete::applyOperationOnList);
            // put the array of ints into stream converting each element to string though map
//            and collect the stream into a string joined/delimited by a space
            String joinedList = intsArr.stream().map(String::valueOf).collect(Collectors.joining(" "));
            System.out.println(joinedList);// print the string
            bufferedReader.close();// close buffer
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * helper method to apply insert/delete with given values to the array of ints
     * @param w the string representing the operation name
     * @param w2 the value(s) delimited by space to apply
     * @return a string representing the arguments used as Optional for the reduce
     */
    private static String applyOperationOnList(String w, String w2){
        //System.out.println("arg1: " + w + ", arg2: "+ w2);
        if(w.equals("Insert") ){ // if insert
            //System.out.println("operation: " + w + ", args: "+ w2);
            /*
            convert the space delimited line into array stream by splitting
            map each split element into integer representation
            collect the elements into a list
            apply reduce method that takes lambda that inserts b into the intsarr at position a
            returns an Optional (1) since reduce must return an Optional value
             */
            Arrays.stream(w2.split("\\s"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList())
                    .stream()
                    .reduce((a, b) -> {intsArr.add(a, b); return 1;});
        }else if(w.equals("Delete")){ // if delete
            //System.out.println("operation: " + w + ", args: "+ w2);
            intsArr.remove(Integer.parseInt(w2));
        }
        return w2;
    }
}
