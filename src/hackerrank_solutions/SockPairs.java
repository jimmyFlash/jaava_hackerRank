package hackerrank_solutions;


import java.util.*;

/*
Given an array of integers representing the color of each sock, determine how many pairs of socks with matching
colors there are.
For example, there are 7 socks with colors [1,2,1,2,1,3,2] .
There is one pair of color 1 and one of color 2. There are three odd socks left, one of each color.
The number of pairs is 2.
*/
public class SockPairs {

    private static int[] sockPairsCols = {1,2,1,2,1,3,2,1};
    private static int[] sockPairsCols1 = {1,2,1,2,1,1};
    private static int[] sockPairsCols2 = {1,2,1,2,1};


    public static void main(String[] args){

        SockPairs.SockProfiler sp = new SockProfiler(sockPairsCols);
        Map<Integer, ArrayList<Integer>> calcPairs = sp.calcuateSockSets();

        System.out.println("Pairs found are: " + sp.getPairs() +
                " , at the following positions in the array " + sp.getPairIdsSet().toString());

    }

    static class SockProfiler{

        int[] sockPairsCols;

        public HashSet<Integer> getPairIdsSet() {
            return pairIdsSet;
        }

        HashSet<Integer> pairIdsSet = new HashSet<>();
        private  Map<Integer, ArrayList<Integer>> matchingPairs = new HashMap();

        int getPairs() {
            return pairs;
        }

        private int pairs = 0;

        SockProfiler(int[] sockPairsCols) {
            this.sockPairsCols = sockPairsCols;

        }

        Map<Integer, ArrayList<Integer>> calcuateSockSets(){
            for (int i = 0 ; i < sockPairsCols.length; i++ ){

                ArrayList<Integer> colorPr;

                int sockCol = sockPairsCols[i];  //1,2,1,2,1,1
                if (matchingPairs.containsKey(sockCol)){
                    colorPr =  matchingPairs.get(sockCol);
                    System.out.println("sockCol matching color found colorID: " + sockCol + " @index " + i);
                    colorPr.add(i);

                    if(colorPr.size() % 2 == 0){
                        System.out.println("adding @ % 2: " + sockCol + " @index " + i);
                        pairs++;
                        pairIdsSet.add(colorPr.get(colorPr.size() - 1));
                        pairIdsSet.add(colorPr.get(colorPr.size() - 2));
                    }
                }else{
                    colorPr = new ArrayList();
                    colorPr.add(i);
                    matchingPairs.put(sockCol, colorPr);
                }
            }
            return matchingPairs;
        }
    }

}
