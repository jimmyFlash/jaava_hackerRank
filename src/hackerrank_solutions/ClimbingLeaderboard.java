package hackerrank_solutions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/*
arcade game, player, climb leaderboard and track rank
- game uses (dense ranking) :
- player with the highest score is ranked number 1
- players with same score receive same rank number others follow
- with each game play the player score updates the board
- new scores replace and push the score below it affecting the player rank as he progresses
- n : number of players on the board
- m : number of games the player plays

- The existing leaderboard is in [descending] order
- The player's scores are in [ascending] order.


example:

if leaderboard score is [100, 90, 90, 80] -> 1, 2,2, 3, & player scores are [70, 80, 105] rank returned will be [4, 3, 1]
and score board will be [105, 100, 90, 90]
 */
public class ClimbingLeaderboard {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/test/climbingLeaderBoard.txt"));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int rankedCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> ranked = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "")
                .split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int playerCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> player = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> result = ResultClimbingLeaderboard.climbingLeaderboard(ranked, player);

       /* bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );*/

        bufferedReader.close();
        //bufferedWriter.close();
    }
}

class ResultClimbingLeaderboard {

    /*
     * Complete the 'climbingLeaderboard' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY ranked
     *  2. INTEGER_ARRAY player
     */

    public static List<Integer> climbingLeaderboard(List<Integer> ranked, List<Integer> player) {

        ArrayList<Integer> playerRanks = new ArrayList<>();
        HashMap<Integer, Integer> scoresRankingMap = new HashMap<>();
        int rank = 1; // the rank of the score, starting with 1

        // using hashMap maps the board scores to ranks
        for (Integer integer : ranked) {
            if (!scoresRankingMap.containsKey(integer)) {
                scoresRankingMap.put(integer, rank++);
            }
        }

        int scoresSpan = ranked.size() - 1; // scores range limit to scan eac time to avoid scanning array again eachtime
        for (int playerScore : player){ // iterate through player scores
            for ( int i = scoresSpan; i >= 0; i--){ // iterate through score in ascending manner
                if(playerScore >= ranked.get(i) ){ // is the current player score >= score in board
                    // get that score rank from ranks map and assign in to rank variable
                    rank = scoresRankingMap.get(ranked.get(i));
                    // update the score range limit making is shorter
                    scoresSpan = i - 1;
                }else{// exit loop is the score is less than board score, no point comparing further
                    break;
                }
            }
            // update player ranking list
            playerRanks.add(rank);
        }

        System.out.println("Current player ranks : " + playerRanks);
      // List<Integer> filtered = ranked.stream().distinct().collect(toList());
        return playerRanks;
    }

    /**
     * @param playerScore the score to check
     * @param scores      current array of scores to check against
     * @param size        the size limit of the array to traverse through
     * @return an index of the score
     */
    private static int binarySearch(int playerScore, List<Integer> scores, int size){

       int upperLimitSearch = size;
       int lowerLimitSearch = 0;

        while (lowerLimitSearch <= upperLimitSearch) {

            int middPointWithOffset = lowerLimitSearch + (upperLimitSearch - lowerLimitSearch) / 2;

            // player score matches one of the stored scores from another player
            if (playerScore == scores.get(middPointWithOffset)) {
                return scores.get(middPointWithOffset);
            }

            // player score is > current midpoint value and < than the previous score value to the left
            if (playerScore > scores.get(middPointWithOffset) && playerScore < scores.get(middPointWithOffset - 1)) {
                return scores.get(middPointWithOffset); // return the score value at midpoint

            } else if (playerScore < scores.get(middPointWithOffset) // player score is < current midpoint
                    && playerScore > scores.get(middPointWithOffset + 1)) { // but > next score value to the right
                return scores.get(middPointWithOffset + 1); // return the score value to the right

            } else if (playerScore < scores.get(middPointWithOffset)) { // player score is < midpoint
                lowerLimitSearch = middPointWithOffset + 1; // offset lower bound search with midpoint value + 1
            } else {
                upperLimitSearch = middPointWithOffset - 1; // offset upper bound search with midpoint value - 1
            }
        }
        return -1; // nothing found return default
    }


    /**
     * helper method to remove duplicates from typed list
     * @param listWithDuplicates list containing duplicate items
     * @param <T> generic types
     * @return and array list without the duplicates
     */
    public static <T> ArrayList<T> removeDuplicates(List<T> listWithDuplicates){
        // return the list
        return new ArrayList<>( new LinkedHashSet<>(listWithDuplicates));
    }


}
