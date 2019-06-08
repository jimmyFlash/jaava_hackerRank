package chapter4_conditionals_and_recursion;

import com.sun.deploy.util.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jamal on 5/1/2017.
 */
public class TriangleTester {

    public static void main(String[] args){
        System.out.println("Sticks of length 1, 2, 3 can form a triangle: " + isTriangle(1, 2, 3));

        System.out.println("Sticks of length 1, 5, 3 can form a triangle: " + isTriangle(1, 5, 3));


        System.out.println("---------------------------------------------------------------------------------------");


        TriangleTester ts = new TriangleTester();
        Integer[] sets = {1, 2, 3};
        System.out.println("Sticks of length 1, 2, 3 can form a triangle: " + ts.isTriangleV2(0, sets));

        Integer[] sets2 = {1, 5, 3};
        System.out.println("Sticks of length 1, 5, 3 can form a triangle: " + ts.isTriangleV2(0, sets2));

    }

    public static boolean isTriangle(int l1, int l2, int l3){

        boolean canFormTriangle = true;

        int prevSum = 0;

        int[] sets = {l1, l2, l3};

       jimmy:  for(int i = 0 ; i < sets.length ; i++){

            int tot = 0;
            try {
                for (int j =  i + 1 ; j < sets.length  ; j ++){
                    tot += sets[j];
                }

               boolean test =  testLength (sets[i], prevSum + tot);

                prevSum +=  sets[i];

                if(!test){
                    return false;
                }

            } catch (Exception e) {
                e.printStackTrace();

                return  false;
            }
        }
        return canFormTriangle;

    }

    public boolean isTriangleV2(int itr, Integer[] lengths){

        ArrayList<Integer> ar = new ArrayList<>(Arrays.asList(lengths));
        int removed =0;
        try {
            removed = ar.remove(itr);
        } catch (Exception e) {

        }

        int tot = 0;
        for (Integer anAr : ar) {
            tot += anAr;
        }

        boolean makeTri = testLength (removed, tot);


        if(!makeTri){
            return  false;
        }

        return (itr + 1) >= lengths.length ? makeTri : isTriangleV2(itr + 1, lengths);
    }

    private static boolean testLength (int l1, int compare){



        if(l1 > compare){

            System.out.println("test length: " + l1 + ", other two length sum: "  + compare + "===> " + false);

            return false;
        }else{
            System.out.println("test length: " + l1 + ", other two length sum: " + compare + "===> " + true);
            return true;
        }

    }
}
