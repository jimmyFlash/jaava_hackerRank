package chapter4_conditionals_and_recursion;

/**
 * Created by jamal on 7/29/2017.
 *
 * // Pad with zeros and a width of 5 chars.
 String result = String.format("%1$05d %2$05d", i, i + 10);
 */
public class BeerBottleCounter {


    private static BeerBottleCounter ins;
    private static int totlaBottles = 100;

    private String endd = "No bottles of beer on the wall, no bottles of beer, ya’ can’t take one down,\n" +
            "ya’ can’t pass it around, ’cause there are no more bottles of beer on the\n" +
            "wall!";

    public String lyrics = "%1$d bottles of beer on the wall, %2$d bottles of beer, ya’ take one down, ya’ pass it around, %3$d bottles of beer on the wall.";

    public static void main(String[] args){
         ins = new BeerBottleCounter();

        ins.looper (--totlaBottles );



    }


    private String looper (int bottles ){


        if(bottles == 0){

            System.out.println(endd);

        }else{
            System.out.println(String.format(lyrics, bottles, bottles, bottles - 1));
        }

        return (bottles == 0) ? "" : looper (--bottles );
    }


}
