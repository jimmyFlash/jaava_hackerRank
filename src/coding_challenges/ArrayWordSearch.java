package coding_challenges;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 1st element in input contains 4X4 matrix of words
 * 2nd long comma delimited string of words each at least 3 letters long each
 * in alphabetical order
 */
public class ArrayWordSearch {

    /*
        test cases : array of strings containing 2 string elements
        each element is a comma delimited string in itself
     */
    private static String[] strArr1 = new String[] {
            "aaey, rrum, tgmn, ball",
            "all, ball, mur, raeymnl, tall, true, trum"
    };

    private static String[] strArr2 = new String[] {
            "rbfg, ukop, fgub, mnry",
            "bog, bob, gup, fur, ruk"
    };

    private static String[] strArr3 = new String[] {
            "aaey, rrum, tgmn, ball",
            "all, ball, mur, raeymnl, rumk, tall, true, trum, yes"
    };

    private static String[] strArr4 = new String[] {
            "aaey, rrum, tgmn, ball",
            "ball, bar, born, brick" 
    };


    private static String[] winCharMapArr = new String[] {
            "♣♥♦♠, ♩♪♫♬, ♡♢♤♧, ☀☁☂☃",
            "♣♥♦♠, ♩♪♫♬, ♡♢♤♧, ☀☁☂☃, ♣♪♢♤☂♧, ♣♪♢♤☀"
    };

    private static String[] numeralsAndOperatorsArr = new String[] {
            "1234, 5679, 90-+, %&*!",
            "1234, 5679, 90-+, %&*!, 150-&*+973, 39+*&0"
    };


    // the 2d matrix dimensions
    private static final int ROW = 4;
    private static final int COLUMN = 4;

    // total number of letters in alphabet
    private static final int ENG_ALPHABET = 26;

    private enum LetterCasing{
        UPPER_CASE_LETTER,
        LOWER_CASE_LETTERS
    }

    public static void main(String[] args) {
        String[] testCase = strArr4;
        System.out.println(" > " + wordMatrixChallenge(testCase));
        System.out.println(" > " + nonCharFunSearch(testCase));

        testCase = winCharMapArr;
        System.out.println(" > " + nonCharFunSearch(testCase));

    }

    /**
     *
     * @param strArr the string array of characters to scan
     * @return the list of not found strings if any or empty string
     */
    private static String wordMatrixChallenge( String[] strArr){

        // the visited matrix positions
        boolean[][] visited = new boolean[ROW][COLUMN];

        // creates the 2d char matrix from 1st comma delimited string set
        char[][] charMatrix =  splitToCharMatrix(strArr[0]);

        // create array of dictionary strings to search in matrix from second comme delimited string
        String[] wordArr = strArr[1].split(",");

        StringBuilder searchStr = new StringBuilder();

        WordTree root = new WordTree("root"); // base tree node

        // remove any white space in dictionary words and collect in a list
        List<String> checkerList = Arrays.stream(wordArr)
                .map(String::trim)
                .collect(Collectors.toList());

        System.out.println("Dictionary: " + checkerList);

        // insert all words of dictionary into tree
        for (String s : wordArr) {
            insertNode(root, s.trim());
        }


        // traverse all 2d matrix characters
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLUMN; j++) {
                char charInMatrix = charMatrix[i][j];

                // check if the character at {charMatrix[i][j]} is found in the {root} children
                if (root.children[getCharOffsetAbstract(charInMatrix)] != null) {
                    searchStr.append(charInMatrix); // append that character to search string

                    System.out.println("character " + charMatrix[i][j] + ", found @ " + i +  "," + j );

                    // start checking 2d char matrix elements for next valid character of current dictionary word
                    // will return the dictionary word if found or empty string
                    searchWordInMatrix(root.children[getCharOffsetAbstract(charInMatrix)],
                            searchStr.toString(), i, j, visited, charMatrix, checkerList);

                    // reset the search string after recursive call to {searchWordInMatrix} to prevent concatenation
                    // of incomplete searches with successful ones
                    searchStr = new StringBuilder();
                }
            }
        }
        return checkerList.size() > 0? "Words not found: " + checkerList : "All words found";
    }

    /**
     * recursive search function given tree node for valid children form the matrix
     * @param node tree node to check
     * @param word current search word compiled so far
     * @param i row index in matrix of characters
     * @param j column index in matrix of characters
     * @param visitedPositions 2d array to mark visited potions in matrix per dictionary word
     * @param charMatrix the 2d character matrix to check for dictionary word
     */
    private static void searchWordInMatrix(WordTree node, String word, int i, int j,
                                           boolean[][] visitedPositions,
                                           char[][] charMatrix, List<String> checkerList){

        // found word in dictionary
        if(node.leaf){
//            System.out.println("FOUND WORD: " + word);
            checkerList.remove(word);
        }

        if( isValidAndNotVisited(i, j, visitedPositions) ){

            // mark node position visited to prevent using same position twice per
            visitedPositions[i][j] = true;

            for (int k = 0; k < ENG_ALPHABET; k++){

                if(node.children[k] != null) {

                    String nodeName = node.children[k].name;
                    char ch = nodeName.charAt(nodeName.length() - 1);
                    //System.out.println("Node [" + node.name + "] has Char->" + ch);
                    // to the right and below
                    if (isValidAndNotVisited(i + 1, j + 1, visitedPositions) && charMatrix[i + 1][j + 1] == ch){
                        searchWordInMatrix(node.children[k], word + ch,i+1,  j+1,
                                visitedPositions, charMatrix, checkerList);
                    }
                    // to the left and above
                    if (isValidAndNotVisited(i - 1, j - 1, visitedPositions) && charMatrix[i - 1][j - 1] == ch){
                        searchWordInMatrix(node.children[k], word + ch,i-1,  j-1,
                                visitedPositions, charMatrix, checkerList);
                    }
                    // to the right and above
                    if (isValidAndNotVisited(i - 1, j + 1, visitedPositions) && charMatrix[i - 1][j + 1] == ch){
                        searchWordInMatrix(node.children[k], word + ch,i-1,  j+1,
                                visitedPositions, charMatrix, checkerList);
                    }
                    // to the left and below
                    if (isValidAndNotVisited(i + 1, j - 1, visitedPositions) && charMatrix[i + 1][j - 1] == ch){
                        searchWordInMatrix(node.children[k], word + ch,i+1,  j-1,
                                visitedPositions, charMatrix, checkerList);
                    }
                    // to the right same level
                    if (isValidAndNotVisited(i , j+1 , visitedPositions) && charMatrix[i][j + 1] == ch){
                        searchWordInMatrix(node.children[k], word + ch,i,  j+1,
                                visitedPositions, charMatrix, checkerList);
                    }
                    // to the left same level
                    if (isValidAndNotVisited(i , j-1 , visitedPositions) && charMatrix[i][j-1] == ch){
                        searchWordInMatrix(node.children[k], word + ch, i, j-1,
                                visitedPositions, charMatrix, checkerList);
                    }
                    // below directly
                    if (isValidAndNotVisited(i+1, j, visitedPositions) && charMatrix[i+1][j] == ch){
                        searchWordInMatrix(node.children[k], word + ch, i+1,  j,
                                visitedPositions, charMatrix, checkerList);
                    }
                    // above directly
                    if (isValidAndNotVisited(i-1, j, visitedPositions) && charMatrix[i-1][j] == ch){
                        searchWordInMatrix(node.children[k], word + ch, i-1,  j,
                                visitedPositions, charMatrix, checkerList);
                    }

                }
            }
            // make current element unvisited before next call from {wordMatrixChallenge} matrix loop
            // to enable search for next word
            visitedPositions[i][j] = false;
        }
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
     * returns the offset character position in alphabet
     * Java uses UTF-16 characters
     * @param charValue the char value of character in string, ex. string.charAt(0)
     * @param letterCase enum value to determine if the offset is W.R.T lower casing or upper casing
     * @return offset value in english alphabet of the character
     */
    private static int getCharOffset(int charValue, LetterCasing letterCase) throws Exception {
        if(letterCase == LetterCasing.UPPER_CASE_LETTER){
            return charValue - 'A';
        }else if (letterCase == LetterCasing.LOWER_CASE_LETTERS){
            return charValue - 'a';
        }else{
            throw new Exception("Not supported");
        }
    }

    /**
     * get the index of character in the alphabet letters
     * @param charValue the character to look up
     * @return the offset index of the character w.r.t. alphabet letters
     */
    public static int getCharOffsetAbstract(char charValue){
        return "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toLowerCase().indexOf(charValue);
    }



    /**
     * creates a tree of nodes, each holding a character from a string passed as argument
     * each parent node has a number of empty (null) places matching the numbers of the alphabet (28)
     * some of these places will hold child characters each one at a position matching its index in alphabet -1
     * so a single parent could have all 28 places occupied with characters from the alphabet, some of them
     * or none depending on the number of words that have the parent character as common node for child words
     *
     * example: the (b) in [ball, born, blink]
     * (b) parent node will have children -> a(position = 0), o (position = 14) and l(position = 11)
     * the rest of the 28 child positions will have value of null
     */
    private static class WordTree {
        // leaf is true if the node represents end of a word
        boolean leaf;

        String name;

        // the tree span matches the number of english alphabet letters
        WordTree[] children = new WordTree[ENG_ALPHABET];
        // constructor
        public WordTree(String name_) {
            leaf = false;
//            Arrays.fill(children, null); // fill the children array with null values
            this.name = name_;
        }
    }

    /**
     * insert a new tree branch per dictionary word
     * each letter becomes a parent tree node and the following letter becomes its child branch
     * @param parent parent node binding new tree branches
     * @param key a word from the dictionary
     */
    private static void insertNode(WordTree parent, String key){
        int n = key.length(); // length of each word

        WordTree parentNode = parent; // parent node in the tree

        for (int i = 0; i < n; i++) {

            char character = key.charAt(i);

            // using character index number in alphabet as a unique identifier for each branch node (weight),
            // instead of using i, since the later would override children indices per dictionary word loop
            int index = getCharOffsetAbstract(character);

            if (parentNode.children[index] == null) { // if child node not found in current parent
                // create a new child node branch assigned to this node's children
                parentNode.children[index] = new WordTree("branch-" + character);
                //System.out.println(  "[" + parentNode.name + "]-->" + "branch-" + character + " @" + index);
            }

            // switch node ref to the current child node to populate its children array
            parentNode = parentNode.children[index];
        }
        // mark last  character node as leaf node
        parentNode.leaf = true;
    }


    /**
     * creates a 4X4 matrix from the given string
     * @param sts comma delimited string of words with length 4 per each
     * @return 4X4 (2d) char matrix of the words
     */
    private static char[][] splitToCharMatrix(String sts){
        String[] wordArr = sts.split(",");

        char[][] charMatrix = new char[ROW][COLUMN];
        for (int i = 0 ; i < wordArr.length; i++){
            char[] wordChar = wordArr[i].trim().toCharArray(); // converter each string to char array
            charMatrix[i] = wordChar; // store the char array in new row in the 2d matrix
            System.out.println("Matrix row: " + i + " >> " + String.valueOf(wordChar));
        }
        return charMatrix;
    }

    private static class WordNode{

        private final char nodeId;
        private final int weight;
        private final List<WordNode> children;

        WordNode(char nodeId, int weight) {
            this.nodeId = nodeId;
            this.weight = weight;
            this.children = new ArrayList<>();
        }
    }

    private static void populateNodeTree(WordNode parent, String key){

        WordNode currParent = parent;
        for (int i = 0; i < key.length(); i++) {
            char strChar = key.charAt(i);
            WordNode nodeFound = checkNodeExistent(strChar, currParent.children, 0);
            if( nodeFound != null){
                currParent = nodeFound;
            }else{
                WordNode newChldNode = new WordNode(strChar, (int) currParent.weight + 1);
                currParent.children.add(newChldNode);
                System.out.println("{"+currParent.nodeId+"}-->"+"branch-"+ strChar + " level^" + newChldNode.weight);
                currParent = newChldNode;
            }
        }
    }

    private static WordNode checkNodeExistent(char strChar, List<WordNode> nodesList, int index){
        if(index == nodesList.size())
            return null;

        WordNode wordNodeItem = nodesList.get(index);
        if (strChar == wordNodeItem.nodeId ){
            return wordNodeItem;
        }
        return checkNodeExistent(strChar, nodesList, ++index);
    }

    private static void searchCharInMatrix(WordNode node, String word, int i, int j,
                                           boolean[][] visitedPositions,
                                           char[][] charMatrix, List<String> checkerList){

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

                WordNode wordChar = node.children.get(k);
                char ch = wordChar.nodeId;

                /*
                below if check will check the surrounding positions adjacent to the current character in
                2 matrix each time if the adjacent positions hasn't been checked before, and it has a character
                matching the current node character value, call recursion with this node's child WordNode
                children list and check its surrounding char values in the 2d matrix
                */
                // to the right and below
                if (isValidAndNotVisited(i + 1, j + 1, visitedPositions) && charMatrix[i + 1][j + 1] == ch){
                    searchCharInMatrix(wordChar, word + ch,i+1,  j+1,
                            visitedPositions, charMatrix, checkerList);
                }
                // to the left and above
                if (isValidAndNotVisited(i - 1, j - 1, visitedPositions) && charMatrix[i - 1][j - 1] == ch){
                    searchCharInMatrix(wordChar, word + ch,i-1,  j-1,
                            visitedPositions, charMatrix, checkerList);
                }
                // to the right and above
                if (isValidAndNotVisited(i - 1, j + 1, visitedPositions) && charMatrix[i - 1][j + 1] == ch){
                    searchCharInMatrix(wordChar, word + ch,i-1,  j+1,
                            visitedPositions, charMatrix, checkerList);
                }
                // to the left and below
                if (isValidAndNotVisited(i + 1, j - 1, visitedPositions) && charMatrix[i + 1][j - 1] == ch){
                    searchCharInMatrix(wordChar, word + ch,i+1,  j-1,
                            visitedPositions, charMatrix, checkerList);
                }
                // to the right same level
                if (isValidAndNotVisited(i , j+1 , visitedPositions) && charMatrix[i][j + 1] == ch){
                    searchCharInMatrix(wordChar, word + ch,i,  j+1,
                            visitedPositions, charMatrix, checkerList);
                }
                // to the left same level
                if (isValidAndNotVisited(i , j-1 , visitedPositions) && charMatrix[i][j-1] == ch){
                    searchCharInMatrix(wordChar, word + ch, i, j-1,
                            visitedPositions, charMatrix, checkerList);
                }
                // below directly
                if (isValidAndNotVisited(i+1, j, visitedPositions) && charMatrix[i+1][j] == ch){
                    searchCharInMatrix(wordChar, word + ch, i+1,  j,
                            visitedPositions, charMatrix, checkerList);
                }
                // above directly
                if (isValidAndNotVisited(i-1, j, visitedPositions) && charMatrix[i-1][j] == ch){
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
        char[][] charMatrix =  splitToCharMatrix(characArr[0]);


        // create array of dictionary strings to search in matrix from second comme delimited string
        String[] charsToSearchArr = characArr[1].split(",");

        List<String> checkerList = Arrays.stream(charsToSearchArr)
                .map(String::trim)
                .collect(Collectors.toList());

        StringBuilder searchWordStr = new StringBuilder();

        WordNode charNode = new WordNode('.', 0);

//        System.out.println("Dictionary: " + checkerList);

        // insert all words of dictionary into tree
        for (String s : charsToSearchArr) {
            populateNodeTree(charNode, s.trim());
        }

        // traverse all 2d matrix characters
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLUMN; j++) {
                char charInMatrix = charMatrix[i][j];

                // check if the character at {charMatrix[i][j]} is found in the {root} children
                WordNode searchedCharNode = checkNodeExistent(charInMatrix, charNode.children, 0);
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

}
