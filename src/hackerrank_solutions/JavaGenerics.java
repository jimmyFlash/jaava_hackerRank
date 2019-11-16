package hackerrank_solutions;

import java.lang.reflect.Method;
import java.util.Arrays;

class Printer {
    <T> void printArray(T[] objArray) {

        Arrays.stream(objArray).forEach(System.out::println);

        /*
        //OR using for loop if you're using java7

            for (T item : objArray){
                System.out.println(item.toString());
            }
         */
    }
}

public class JavaGenerics {

    public static void main( String args[] ) {
        Printer myPrinter = new Printer();
        Integer[] intArray = { 1, 2, 3 };
        String[] stringArray = {"Hello", "World"};
        myPrinter.printArray(intArray);
        myPrinter.printArray(stringArray);
        int count = 0;

        for (Method method : Printer.class.getDeclaredMethods()) {
            String name = method.getName();

            if(name.equals("printArray"))
                count++;
        }

        if(count > 1)System.out.println("Method overloading is not allowed!");

    }
}