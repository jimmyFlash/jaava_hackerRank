import java.util.*;
import java.util.stream.Collectors;

public class Assesment1 {

    static int count = 0;

    public static void main(String[] args) {

        Assesment1 assesment1 = new Assesment1();
        // Q1
        assesment1.listOfBooleans();

        /*
          Q2 how can you achieve polymorphism in java?
          answer : methode overriding
         */

        // Q3
        String strawberries  = "strawberries";
        System.out.println("\n" + strawberries.substring(2, 5));

        //Q4
        assesment1.listOfNames();

        //Q5
        Main2 main2 = new Main2();
        System.out.print("\n" + assesment1.message());
        System.out.print( main2.message());

         /*
            Q6: what method can be used t create instance of object
            Answer: constructor
         */

        // Q7
        //answer:  will NOT compile because second catch statement is unreachable/already caught (remove second catch block)
        exceptionPriority();

        // Q8
        // will not compile because (list) is abstract has to type associated with for it's elements
        abstractListError();


        //Q9
        //Answer: A and D because badMethod() throws Error not Exception so it won't catch or print "C"
//         catchErrorVsException();

        //Q10
        //answer: Exception in thread "main" java.lang.StringIndexOutOfBoundsException: String index out of range: -6
//        substrIOOB();

        // Q11
        // print "Hello world!" 3 times
//        recursion();

        // Q12
        // answer: Exception in thread "main" java.lang.StringIndexOutOfBoundsException: String index out of range: 5
        String message2 = "Hello";
        for (int i = 0; i < message2.length(); i++){
           // System.out.println(message2.charAt(i+1));
        }

        // Q13
        // will print number from 1 - 10
        for (int i = 0; i < 10; i = i++){
            i += 1;
            System.out.println(i);
        }

        //Q14
        // Answer: [5, 1, 10]
        LinkedList<Integer> list = new LinkedList<>();
        list.add(5);
        list.add(1);
        list.add(10);
        System.out.println(list);

        //Q15
        // answer: HelloHelloWorld!
        String message = "Hello";
        print(message);
        message += "World!";
        print(message);


    }

    private static void recursion(){
        if(count < 3){
            count++;
            main(null);
        }else{
            return;
        }
        System.out.println("Hello world!");
    }

    private static void substrIOOB(){
        String message = "Hello world!";
        String newMessage = message.substring(6, 12) +  message.substring(12, 6);
        System.out.println(newMessage);
    }

    private static void badMethod(){
        throw new Error();
    }

    private static void abstractListError(){
        // code commented out because it causes compilation error in ide
       /*
       List list = new List();
        list.add("Hello");
        list.add(2);
        System.out.print(list.get(0) instanceof Object);
        System.out.print(list.get(1) instanceof Integer);
        */
    }

    private static void catchErrorVsException(){
        try{
            System.out.println("\nA");
            badMethod();
            System.out.println("B");
        }catch (Exception e){
            System.out.println("C");
        }finally {
            System.out.println("D");
        }
    }

    private static void exceptionPriority(){
        // code commented out because it causes compilation error in ide
       /* try{
            System.out.println("\n" + "Hello World");
        }catch (Exception e){

            System.out.print(e.getMessage());
        }catch (ArithmeticException e){
            System.out.println(e.getMessage());
        }finally {

            System.out.println("!");
        }*/
    }

    private void listOfBooleans(){
        List<Boolean>  list = new ArrayList<>();
        list.add(true);
        list.add(Boolean.parseBoolean("FalSe")); // letter casing doesn't matter
        list.add(Boolean.TRUE);
        System.out.print(list.size());
        System.out.print(list.get(1) instanceof Boolean);

    }

    private void listOfNames(){
        List<String>  names = new ArrayList<>();
        names.add("jimm");
        names.add("aamez"); // letter casing doesn't matter
        names.add("shaker");
          // 1
//        list.sort(Comparator.comparing(String::toString));
//        System.out.println("\n" + list.toString());

          // 2
//        Collections.sort(names);
//        System.out.println("\n" + names.toString());

        // 3
        names = names.stream()
//                .sorted((s1, s2) -> s1.compareTo(s2))
                .sorted(String::compareTo)
                .collect(Collectors.toList());

        System.out.println("\n" + names.toString());

    }

    private Object message(){
        return "Hello!";
    }

    private static void print(String message){
        System.out.print(message);
    }


}

class Main2 extends Assesment1{
    String message(){
        return "World!";
    }
}
