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

    private static Integer[] sockPairsCols = {1,2,1,2,1,3,2,1};
    private static Integer[] sockPairsCols1 = {1,2,1,2,1,1};
    private static Integer[] sockPairsCols2 = {1,2,1,2,1};


    public static void main(String[] args){

        Integer[] selectedArr  = sockPairsCols;
        SockPairs.SockProfiler sp = new SockProfiler(selectedArr);
        Map<Integer, ArrayList<Integer>> calcPairs = sp.calcuateSockSets();

        ArrayList<Integer> arrayToMutableList = new ArrayList<Integer>(Arrays.asList(selectedArr));

        HashSet<Integer> indeceisSet = sp.getPairIdsSet();
        System.out.println("Pairs found are: " + sp.getPairs() +
                " , at the following positions in the array " + sp.getPairIdsSet().toString());


        for (Integer index = 0 ; index <  arrayToMutableList.size(); index++) {

            if(!indeceisSet.contains(index)){
                System.out.println("orphan item @index: " + index + " = " + arrayToMutableList.get(index));
            }
        }


    }

    static class SockProfiler{

        Integer[] sockPairsCols;

        HashSet<Integer> getPairIdsSet() {
            return pairIdsSet;
        }

        HashSet<Integer> pairIdsSet = new HashSet<>();
        private  Map<Integer, ArrayList<Integer>> matchingPairs = new HashMap();

        int getPairs() {
            return pairs;
        }

        private int pairs = 0;

        SockProfiler(Integer[] sockPairsCols) {
            this.sockPairsCols = sockPairsCols;

        }

        Map<Integer, ArrayList<Integer>> calcuateSockSets(){
            for (int i = 0 ; i < sockPairsCols.length; i++ ){

                ArrayList<Integer> colorPr;

                int sockCol = sockPairsCols[i];
                if (matchingPairs.containsKey(sockCol)){
                    colorPr =  matchingPairs.get(sockCol);
                    colorPr.add(i);

                    if(colorPr.size() % 2 == 0){
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
