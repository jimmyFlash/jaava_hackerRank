package hackerrank_solutions;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/*
Sample Input

5 4
AND 1 2
SET 1 4
FLIP 2 2
OR 2 1

Sample Output

0 0
1 0
1 1
1 2

*/

public class BitSetSolution {


    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        int numObBits = sc.nextInt();
        int numOperations = sc.nextInt();
        List<String> op = new ArrayList<>(
                Arrays.asList(
                        "AND",
                        "OR",
                        "XOR"));

        List<String> sets = new ArrayList<>(
                Arrays.asList(
                        "FLIP",
                        "SET" ));

        HashMap<Integer, BitseOpration> hsMa= new HashMap<>();
        hsMa.put(1, new BitseOpration(numObBits));
        hsMa.put(2, new BitseOpration(numObBits));

        //System.out.println("numObBits: " + numObBits + " , numOperations: " + numOperations);
        while(sc.hasNextLine()){
            String ln= sc.nextLine();
            String[]  data = ln.split("\\s");
            if(data.length > 1){
                Integer  arg1 = Integer.valueOf(data[1]);
                Integer  arg2 = Integer.valueOf(data[2]);
                String oprationType = data[0];

                try {
                    if(op.contains(oprationType)){
                       hsMa.get(arg1).callMethodByNameWithArgs(oprationType.toLowerCase(),  hsMa.get(arg2).getBs());

                    }else if(sets.contains(oprationType)){
                       hsMa.get(arg1).callMethodByNameWithArgs(oprationType.toLowerCase(),  arg2);
                    }
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {

                    e.printStackTrace();
                }

                System.out.println(
                        hsMa.get(1).getBs().cardinality()
                        + " " +
                        hsMa.get(2).getBs().cardinality()
                );
            }
        }
    }

    static class BitseOpration{


        BitSet bs;
         BitseOpration(int size) {
            bs = new BitSet(size);
        }

         BitSet getBs() {
            return bs;
        }

        void setBs(BitSet bs) {
            this.bs = bs;
        }

         void callMethodByNameWithArgs(String methodName, Object obj)
                throws IllegalAccessException,
                InvocationTargetException,
                NoSuchMethodException {

            if(obj instanceof Integer){
                bs.getClass().getDeclaredMethod(methodName, int.class).invoke(bs, obj);
            }else if (obj instanceof BitSet){
                bs.getClass().getDeclaredMethod(methodName, BitSet.class).invoke(bs, obj);
            }


        }

    }


}
