package chapter3_methods;

/**
 * Created by jamal on 4/7/2017.
 */
public class Multadd {

    public Multadd() {
    }

    public static void main(String[] args){

        System.out.println( multaddLocal(1.0, 2.0, 3.0));
        System.out.println( multaddLocal(Math.sin(Math.PI/ 4), 1.0, Math.cos(Math.PI/ 4) / 2));
        System.out.println( multaddLocal(Math.log(10.0), 1.0, Math.log(20.0)));

    }

    // object method implementation
    public double multadd(Double d1, Double d2, Double d3){

        try {
            return d1 * d2 + d3;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // class method implementation
    private static double multaddLocal(Double d1, Double d2, Double d3){

        try {
            return d1 * d2 + d3;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public double yikes(double x){

        return multadd(x * Math.exp(-x), 1.0, Math.sqrt(1 - Math.exp(-x)));

    }

}
