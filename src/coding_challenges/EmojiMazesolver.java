package coding_challenges;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

class EmojiMazesolver {

    // remember emojis are represented by two characters, hence
    // a single emoji is actually (2 * char)
    // example 😀 is represented by the characters \uD83D & \uDE00 -> \uD83D\uDE00
    private static final String[] emojiArr = new String[] {
            "😁:😉:🎂:🍔, " +
            "🎂:🎉:🍗:🍔, " +
            "🍗:🍔:😁:😉, " +
            "🎉:🎊:😁:🍗",
            "😁😉🎂🍔, 🎂🍔🎊🍗, 🍗🍔🎊, 😁🎂🍗🍔🎉😉, 😁😉🎉🎊"
    };

    private static final String[] emojiMaze = new String[] {
            "😁:😁:🎂:🍔:🎉:🎁," +
            "🎂:😁:😁:🎈:🎈:🎁," +
            "🎈:🎈:🎈:😉:🎈:🎁," +
            "🎈:🎊:😁:😁:🍗:🎁," +
            "🎈:🎈:🎈:🎈:🎈:🎁," +
            "🎉:🎊:😁:😁:🎈:🎈",
            "🎈"
    };

    private static final String[] emojiMazeNonSolvable = new String[] {
            "😁:😁:🎂:🍔:🎉:🎁," +
            "🎂:😁:😁:🎈:🎈:🎁," +
            "🎈:🎈:🎈:😉:🎈:🎁," +
            "🎈:🎊:😁:😁:🍗:🎁," +
            "🎈:🎈:🎈:🎈:🎈:🎁," +
            "🎉:🎊:😁:😁:🎈:🎈",
            "🎈"
    };

    // the 2d matrix dimensions
    private static int ROW = 4;
    private static int COLUMN = 4;

    public static void main(String[] args) {

//        System.out.println(" > " + nonCharFunSearch(emojiArr));
//        checkIsUTF16EmojiPart('♣');
//        checkIsUTF16EmojiPart('\uD83D');
//        checkIsUTF16EmojiPart('\uDE00');

         ROW = 6;
         COLUMN = 6;

        emojiMazeSolver(emojiMaze);
    }


    // check that current location (i and j) is in matrix range
    // and position not visited before
    private static boolean isValidAndNotVisited(int i, int j, boolean[][] visited) {
        return (
                (i >= 0 && i < ROW) &&
                (j >= 0 && j < COLUMN) &&
                 !visited[i][j]
        );
    }

    /**
     * creates a 4X4 matrix from the given string
     * @param sts comma delimited string of words with length 4 per each
     * @return 4X4 (2d) char matrix of the words
     */
    private static String[][] splitToCharMatrix(String sts, String delimiter){
        String[] wordArr = sts.split(",");

        String[][] charMatrix = new String[ROW][COLUMN];
        for (int i = 0 ; i < wordArr.length; i++){
            String[] wordChar = wordArr[i].trim().split(delimiter); // converter each string to char array
            charMatrix[i] = wordChar; // store the char array in new row in the 2d matrix
            System.out.println("Matrix row: " + i + " >> " + Arrays.deepToString(wordChar));
        }
        return charMatrix;
    }

    private static class EmojiNode {

        private final String nodeId;
        private final int weight;
        private final List<EmojiNode> children;

        EmojiNode(String nodeId, int weight) {
            this.nodeId = nodeId;
            this.weight = weight;
            this.children = new ArrayList<>();
        }
    }

    private static void populateNodeTree(EmojiNode parent, String key){

        EmojiNode currParent = parent;
        int i = 0;
        while ( i < key.length() ) {
           // System.out.println("Is character a UTF 16 emoji? " + checkIsUTF16EmojiPart(key.charAt(i)));
            // each emoji is represented by 2 char values (UTF16)
            String strChar = String.valueOf(key.charAt(i)) + key.charAt(i + 1);
            EmojiNode nodeFound = checkNodeExistent(strChar, currParent.children, 0);
            if( nodeFound != null){
                currParent = nodeFound;
            }else{
                EmojiNode newChldNode = new EmojiNode(strChar, currParent.weight + 1);
                currParent.children.add(newChldNode);
                System.out.println("{"+currParent.nodeId+"}-->"+"branch-"+ strChar + " level^" + newChldNode.weight);
                currParent = newChldNode;
            }
            i+=2;
        }
    }

    private static EmojiNode checkNodeExistent(String strChar, List<EmojiNode> nodesList, int index){
        if(index == nodesList.size())
            return null;

        EmojiNode wordNodeItem = nodesList.get(index);
        if (strChar.equals(wordNodeItem.nodeId )){
            return wordNodeItem;
        }
        return checkNodeExistent(strChar, nodesList, ++index);
    }

    private static void searchCharInMatrix(EmojiNode node, String word, int i, int j,
                                           boolean[][] visitedPositions,
                                           String[][] charMatrix, List<String> checkerList){

        // reached the last node / leaf
        if(node.children.size() == 0){
//            System.out.println("FOUND WORD: " + word);
            checkerList.remove(word);
        }

        // check if position corresponding in the 2d matrix has been checked before for this character
        if(isValidAndNotVisited(i, j, visitedPositions) ) {

            // mark node position visited to prevent using same position twice
            visitedPositions[i][j] = true;

            // per WordNode node pass for recursive call check its children list of characters
            for (int k = 0; k < node.children.size(); k++) {

                EmojiNode wordChar = node.children.get(k);
                String ch = wordChar.nodeId;

                /*
                below if check will check the surrounding positions adjacent to the current character in
                2 matrix each time if the adjacent positions hasn't been checked before, and it has a character
                matching the current node character value, call recursion with this node's child WordNode
                children list and check its surrounding char values in the 2d matrix
                */
                // to the right and below
                if (isValidAndNotVisited(i + 1, j + 1, visitedPositions) && 
                        Objects.equals(charMatrix[i + 1][j + 1], ch)){
                    searchCharInMatrix(wordChar, word + ch,i+1,  j+1,
                            visitedPositions, charMatrix, checkerList);
                }
                // to the left and above
                if (isValidAndNotVisited(i - 1, j - 1, visitedPositions) && 
                        Objects.equals(charMatrix[i - 1][j - 1], ch)){
                    searchCharInMatrix(wordChar, word + ch,i-1,  j-1,
                            visitedPositions, charMatrix, checkerList);
                }
                // to the right and above
                if (isValidAndNotVisited(i - 1, j + 1, visitedPositions) && 
                        Objects.equals(charMatrix[i - 1][j + 1], ch)){
                    searchCharInMatrix(wordChar, word + ch,i-1,  j+1,
                            visitedPositions, charMatrix, checkerList);
                }
                // to the left and below
                if (isValidAndNotVisited(i + 1, j - 1, visitedPositions) && 
                        Objects.equals(charMatrix[i + 1][j - 1], ch)){
                    searchCharInMatrix(wordChar, word + ch,i+1,  j-1,
                            visitedPositions, charMatrix, checkerList);
                }
                // to the right same level
                if (isValidAndNotVisited(i , j+1 , visitedPositions) && 
                        Objects.equals(charMatrix[i][j + 1], ch)){
                    searchCharInMatrix(wordChar, word + ch,i,  j+1,
                            visitedPositions, charMatrix, checkerList);
                }
                // to the left same level
                if (isValidAndNotVisited(i , j-1 , visitedPositions) && 
                        Objects.equals(charMatrix[i][j - 1], ch)){
                    searchCharInMatrix(wordChar, word + ch, i, j-1,
                            visitedPositions, charMatrix, checkerList);
                }
                // below directly
                if (isValidAndNotVisited(i+1, j, visitedPositions) && 
                        Objects.equals(charMatrix[i + 1][j], ch)){
                    searchCharInMatrix(wordChar, word + ch, i+1,  j,
                            visitedPositions, charMatrix, checkerList);
                }
                // above directly
                if (isValidAndNotVisited(i-1, j, visitedPositions) && 
                        Objects.equals(charMatrix[i - 1][j], ch)){
                    searchCharInMatrix(wordChar, word + ch, i-1,  j,
                            visitedPositions, charMatrix, checkerList);
                }
            }

            // mark current element unvisited before next call from {wordMatrixChallenge} matrix loop
            // to enable search for next word
            visitedPositions[i][j] = false;
        }
    }

    private static String nonCharFunSearch(String[] characArr){
        // the visited matrix positions
        boolean[][] visited = new boolean[ROW][COLUMN];

        // creates the 2d char matrix from 1st comma delimited string set
        String[][] charMatrix =  splitToCharMatrix(characArr[0], ":");


        // create array of dictionary strings to search in matrix from second comme delimited string
        String[] charsToSearchArr = characArr[1].split(",");

        List<String> checkerList = Arrays.stream(charsToSearchArr)
                .map(String::trim)
                .collect(Collectors.toList());

        StringBuilder searchWordStr = new StringBuilder();
        
        EmojiNode charNode = new EmojiNode(".", 0);

        System.out.println("Dictionary: " + checkerList);


        // insert all words of dictionary into tree
        for (String s : charsToSearchArr) {
            populateNodeTree(charNode, s.trim());
        }

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLUMN; j++) {
                String charInMatrix = charMatrix[i][j];

                // check if the character at {charMatrix[i][j]} is found in the {root} children
                EmojiNode searchedCharNode = checkNodeExistent(charInMatrix, charNode.children, 0);
                if(searchedCharNode != null){
                    searchWordStr.append(charInMatrix);// append that character to search string
                    System.out.println(">>character " + charInMatrix + ", found @ " + i +  "," + j );

                    // start checking 2d char matrix elements for next valid character of current dictionary word
                    // will return the dictionary word if found or empty string
                    searchCharInMatrix(searchedCharNode, searchWordStr.toString(), i, j,
                            visited, charMatrix, checkerList);

                    // reset the search string after recursive call to {searchWordInMatrix} to prevent concatenation
                    // of incomplete searches with successful ones
                    searchWordStr = new StringBuilder();
                }
            }
        }
         return checkerList.size() > 0? "Sets not found: " + checkerList : "All Sets found";

    }

    private static boolean checkIsUTF16EmojiPart(char chatToTest){
        System.out.println("Is it a digit?: " + Character.isDigit(chatToTest) +
                "\n, is it a letter?: " + Character.isLetter(chatToTest) +
                "\n, is it unicode defined?: " +  Character.isDefined(chatToTest) +
                "\n, is it supplementary characters in the UTF-16 encoding?: " + Character.isHighSurrogate(chatToTest) +
                "\n, is it is an ignorable control character?: " + Character.isIdentifierIgnorable(chatToTest));
        if( Character.isDefined(chatToTest)){
            if(Character.isLetterOrDigit(chatToTest))
                return false;
            else
                return Character.isHighSurrogate(chatToTest) && !Character.isIdentifierIgnorable(chatToTest);
        }
        return false;
    }



    private static class EmojiMatrixNode {

        private final String nodeId;
        private  int row;
        private  int column;


        EmojiMatrixNode(String nodeId, int row, int column) {
            this.nodeId = nodeId;
            this.row = row;
            this.column = column;
        }

        public void setColumn(int column) {
            this.column = column;
        }
    }

    private static void emojiMazeSolver(String[] characArr){

        // the visited matrix positions
        boolean[][] visited = new boolean[ROW][COLUMN];

        // creates the 2d char matrix from 1st comma delimited string set
        String[][] emojiMatrix =  splitToCharMatrix(characArr[0], ":");


        String charsToSearchArr = characArr[1];

        boolean entryPointFound = false;
        List<EmojiMatrixNode> checkerList = new ArrayList<>();
        int i;
        for ( i  = 0; i < ROW; i++) {
            String emojiInMatrix = emojiMatrix[i][0];
            if (emojiInMatrix.equals(charsToSearchArr)) {
                EmojiMatrixNode matchEmoji = new EmojiMatrixNode(emojiInMatrix, i, 0);
                checkerList.add(matchEmoji);
                System.out.println("Entry found at row: " + i);
                entryPointFound = true;
                break;
            }
        }

        if(entryPointFound){
            boolean matrixSolvable = solveEmojiMatrix(
                    charsToSearchArr, i,  0, visited,  emojiMatrix,  checkerList);
            System.out.println("Solvable: " + matrixSolvable);
        }

    }

    private static boolean solveEmojiMatrix(String emoji,
                                         int i, int j,
                                         boolean[][] visitedPositions,
                                         String[][] emojiMatrix,
                                         List<EmojiMatrixNode> checkerList){

        System.out.println("CURRENT recursive iteration " + i + ", " + j + "," + Thread.currentThread().getName());



        // check if position corresponding in the 2d matrix has been checked before for this character
        if(isValidAndNotVisited(i, j, visitedPositions) ) {


            visitedPositions[i][j] = true;
            EmojiMatrixNode matchEmoji;
            if (isValidAndNotVisited(i + 1, j, visitedPositions) &&
                    Objects.equals(emojiMatrix[i + 1][j], emoji)){
                System.out.println("Found " + emojiMatrix[i + 1][j] + ", at " + "," + (i + 1) + ", " + (j));
                if (!checkMazeSolvedOrProceed(checkerList, emoji, i + 1, j ))
                    solveEmojiMatrix(emoji ,i+1, j, visitedPositions, emojiMatrix, checkerList);
            }


            if (isValidAndNotVisited(i, j + 1, visitedPositions) &&
                    Objects.equals(emojiMatrix[i ][j + 1], emoji)){
                System.out.println("Found " + emojiMatrix[i][j+1] + ", at " + "," + (i) + ", " + (j+1));
                if (!checkMazeSolvedOrProceed(checkerList, emoji, i, j + 1 ))
                    solveEmojiMatrix(emoji ,i, j + 1, visitedPositions, emojiMatrix, checkerList);
            }

            if (isValidAndNotVisited(i - 1, j , visitedPositions) &&
                    Objects.equals(emojiMatrix[i - 1][j], emoji)){
                System.out.println("Found " + emojiMatrix[i - 1][j] + ", at " + "," + (i - 1) + ", " + (j));
                if (!checkMazeSolvedOrProceed(checkerList, emoji, i - 1, j ))
                    solveEmojiMatrix(emoji ,i - 1, j, visitedPositions, emojiMatrix, checkerList);
            }

            if (isValidAndNotVisited(i, j - 1 , visitedPositions) &&
                    Objects.equals(emojiMatrix[i ][j - 1], emoji)){
                System.out.println("Found " + emojiMatrix[i ][j -1] + ", at " + "," + (i ) + ", " + (j-1));
                if (!checkMazeSolvedOrProceed(checkerList, emoji, i , j - 1 ))
                    solveEmojiMatrix(emoji ,i , j - 1, visitedPositions, emojiMatrix, checkerList);
            }
        }

        return checkerList.get(checkerList.size() - 1).column == COLUMN - 1;
    }

    private static boolean checkMazeSolvedOrProceed(List<EmojiMatrixNode> checkerList,
                                                    String emoji,
                                                    int i,
                                                    int j){

        if(checkerList.get(checkerList.size() - 1).column != COLUMN - 1){
            EmojiMatrixNode matchEmoji = new EmojiMatrixNode(emoji, i, j);
            checkerList.add(matchEmoji);
            return false;
        }
        return true;
    }
}
