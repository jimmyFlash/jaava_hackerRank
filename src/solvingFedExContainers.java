import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Romzo works at FedEx. FedEx uses advance containers of variable size (First item size + 6),
 * depending upon the first item inserted into it.
 * Suppose we inserted the first item of weight 5 then container can take items till weight 5 + 6, i.e 11.
 *
 * We are given items  1, 2, 3, 21, 7, 12, 14, 21, and we need to find the minimum number of containers
 * required to ship our consignment.
 */

public class solvingFedExContainers {


    public static void main(String[] args) {

        int[] itemWeights = {1, 2, 3, 21, 7, 12, 14, 21};
        int weight = 6;

        System.out.println(solveNormal( itemWeights, weight));
        System.out.println(solveUsingLambdaAndAtomic( itemWeights, weight));

    }

    private static int solveNormal (int[] itemWeights, int wt){
        int continerNeeded = 1;
        Arrays.sort(itemWeights); // sort items asc by weight
        int currWeight = itemWeights[0];
        for (int weight : itemWeights){

            if( !(weight <= currWeight + wt) ){

                continerNeeded++;
                currWeight = weight;

            }
        }
        return continerNeeded;

    }

    /**
     * using AtomicInteger makes sure the int value is updated atomically making it thread safe
     * using builtin thread safe methods like incrementAndGet, set()
     * @param itemWeights array of item weights
     * @param wt constant weight
     * @return the required container count
     */
    private static int solveUsingLambdaAndAtomic(int[] itemWeights, int wt){

        AtomicInteger continerNeeded = new AtomicInteger(0); // effective final
        final AtomicInteger t = new AtomicInteger(0); // final
        Arrays.sort(itemWeights); // sort items asc by weight

        IntStream.rangeClosed(0, itemWeights.length - 1).boxed()
                .map(itemWeight -> {
                    if(!(itemWeights[t.intValue()] + wt >= itemWeights[itemWeight] )){
                        continerNeeded.incrementAndGet();
                        t.set(itemWeight);
                    }
                    return continerNeeded;
                })
                .collect(Collectors.toList());

        return continerNeeded.incrementAndGet();
    }
}
