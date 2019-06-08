import chapter2_Variables_types.Date;
import chapter2_Variables_types.Time;
import chapter3_methods.Multadd;

/**
 * Created by jamal on 2/24/2017.
 */
public class MainClass {

    public static void main (String[] args){

        System.out.println("Application started");

        System.out.println();

       /* System.out.println("Exercise 1 displaying date in different formats");

        Date.main(null);

        System.out.println();

        System.out.println("Exercise 2 time calculation");
        Time.main(null);*/

        Multadd mAdd = new Multadd();

        System.out.println(mAdd.multadd(50.0, 120.0, 9000.0));
        System.out.println(mAdd.yikes(10.0));

    }


}
