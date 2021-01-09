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

    private static final Integer[] sockPairsCols = {1,2,1,2,1,3,2,1};
    private static final Integer[] sockPairsCols1 = {1,2,1,2,1,1};
    private static final Integer[] sockPairsCols2 = {1,2,1,2,1};


    public static void main(String[] args){

        Integer[] selectedArr  = sockPairsCols; // socks array to test
        ArrayList<Integer> arrayToMutableList = new ArrayList<>(Arrays.asList(selectedArr));

        SockPairs.SockProfiler sp = new SockProfiler(selectedArr);
        sp.calculateSockSets();

        HashSet<Integer> indicesSet = sp.getPairIdsSet(); // the hash set of pair color positions in array
        System.out.println("Pairs count found are: " + sp.getPairs() +
                " , at the following positions in the array " + indicesSet.toString());


        // loop through original array and compare to set entries to filter out orphan sock colors
        for (int index = 0; index <  arrayToMutableList.size(); index++) {

            if(!indicesSet.contains(index)){
                System.out.println("orphan item @index: " + index + " ,color: " + arrayToMutableList.get(index));
            }
        }


    }

    /**
     * checks arrays and creates array of pair and orphans
     */
    static class SockProfiler{

        private int pairs = 0; // socks pairs found
        Integer[] sockPairsCols; // stores socks pair found
        HashSet<Integer> pairIdsSet = new HashSet<>(); // hashset to store none duplicate sock ids
        private final Map<Integer, ArrayList<Integer>> matchingPairs = new HashMap<>(); // temp holder for pairs

        // getter for the socks pair ids hashset
        HashSet<Integer> getPairIdsSet() {
            return pairIdsSet;
        }

        int getPairs() {
            return pairs;
        }

        // constructor
        SockProfiler(Integer[] sockPairsCols) {
            this.sockPairsCols = sockPairsCols;
        }

        /**
         * loops through array of socks
         */
        void calculateSockSets(){

            for (int i = 0 ; i < sockPairsCols.length; i++ ){

                ArrayList<Integer> colorPair; // stores the list of matching color pairs

                int sockColorId = sockPairsCols[i]; // sock color id
                // check the arraylist for matches
                if (matchingPairs.containsKey(sockColorId)){ // check color id key in map
                    colorPair =  matchingPairs.get(sockColorId);// get the arraylist of color pairs
                    colorPair.add(i); // add the index to it

                    // check each color list if the length of the pairs array is divisible by 2
                    if(colorPair.size() % 2 == 0){
                        pairs++; // update pairs found count
                        // hashset will remove duplicate indices
                        pairIdsSet.add(colorPair.get(colorPair.size() - 1)); // add last index value
                        pairIdsSet.add(colorPair.get(colorPair.size() - 2)); // add index value before last
                    }
                }else{ // no previous color id stored
                    colorPair = new ArrayList<>(); // create new arraylist of id color
                    colorPair.add(i); // add index position of color
                    matchingPairs.put(sockColorId, colorPair); // add the sock color id as key and array of sock color spairs to hashmap
                }
            }
        }
    }

}
