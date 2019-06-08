package hackerrank_solutions;

import java.util.Scanner;

public class CountPlygone {
    private static int recs = 0;
    private static int sqr = 0;
    private static int plo = 0;

    public static void main(String args[] ) throws Exception {
        Scanner scanner = new Scanner(System.in);
    
        while (scanner.hasNextLine()) {
           
            String line = scanner.nextLine();
            line.trim();
            String[] data = line.split("\\s");

            //System.out.println(Arrays.toString(data));
            int[] intData = new int[data.length];

            if(data.length == 4){
                checkPolygonType(Integer.parseInt(data[0]), Integer.parseInt(data[1]),
                   Integer.parseInt(data[2]), Integer.parseInt(data[3]));
            }
        }
        System.out.println(recs + " " + sqr + " " + plo);
    }   

    private static void checkPolygonType(Integer t, Integer r, Integer b, Integer l){

        if( t <= 0 || r <= 0 || b <= 0 || l <= 0){
            plo++;
        }else{
            //System.out.println(Arrays.toString(data));
            if( t.equals(b) && r.equals(l)){
                if(b.equals(r)){
                    //System.out.println("sqr");
                    sqr++;
                }else{
                    recs++;
                    //System.out.println("rec");
                }
            }else{
                //System.out.println("pol");
                plo++;
            }
        }

    }
}

/** 
 * input sample 
36 30 36 30       > rec
15 15 15 15       > sqr
46 96 90 100      > pol
86 86 86 86       > sqr
100 200 100 200   > rec
-100 200 -100 200 > pol 

* output sample
2 2 2
*/


