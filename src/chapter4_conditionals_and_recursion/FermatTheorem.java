package chapter4_conditionals_and_recursion;

public class FermatTheorem {

    public static void main (String[] args){

        int a = 3, b=4, c= 5, n = 2;
        System.out.println("a^n = " + raiseToPow (a, n));
        System.out.println("b^n = " + raiseToPow (b, n));
        System.out.println("c^n = " + raiseToPow (c, n));
        System.out.println("a^n + b^n = " + (raiseToPow (a, n) + raiseToPow (b, n)));
        System.out.println(checkFermat (a, b, c, n));
    }

    private static String checkFermat(int a, int b, int c, int n){
        int cn = (int) Math.pow(c, n);
        int equation = (int) (Math.pow(a, n) +  Math.pow(b, n));

        System.out.println(equation + " == " + cn +  " >> " +(equation == cn ));
        if( n > 2 && equation == cn){
            return "Holy smokes, Fermat was wrong!";
        }else{
            return "No, that doesn’t work.";
        }
    }

    private static int raiseToPow (int x, int y){
        return (int) Math.pow(x, y);
    }
}
