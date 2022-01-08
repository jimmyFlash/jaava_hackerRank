package coding_challenges;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 1st element in input contains 4X4 matrix of words
 * 2nd long comma delimited string of words each at least 3 letters long each
 * in alphabetical order
 */
public class ArrayWordSearch {

    private static String[] strArr1 = new String[] {"aaey, rrum, tgmn, ball",
            "all, ball, mur, raeymnl, tall, true, trum"};

    private static String[] strArr2 = new String[] {"rbfg, ukop, fgub, mnry",
            "bog, bob, gup, fur, ruk"};

    private static String[] strArr3 = new String[] {"aaey, rrum, tgmn, ball",
            "all, ball, mur, raeymnl, rumk, tall, true, trum, yes"};

    private static final int ROW = 4;
    private static final int COLUMN = 4;
    private static final int ENG_ALPHABET = 26;

    private enum LetterCasing{
        UPPER_CASE_LETTER,
        LOWER_CASE_LETTERS
    }

    private static String wordMatrixChallenge( String[] strArr){

        boolean[][] visited = new boolean[ROW][COLUMN];

        char[][] charMatrix =  splitToCharMatrix(strArr[0]); // creates the char 4 x4 matrix

        // create array of dictionary strings to search in matrix
        String[] wordArr = strArr[1].split(",");

        StringBuilder searchStr = new StringBuilder();

        WordTree root = new WordTree("root"); // base tree node

        List<String> checkerList = Arrays.stream(wordArr)
                .map(String::trim)
                .collect(Collectors.toList());

        System.out.println("Dictionary: " + checkerList);

        // insert all words of dictionary into tree
        for (String s : wordArr) {
            insertNode(root, s.trim());
        }


        // traverse all matrix elements
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLUMN; j++) {

                // check if the character at {charMatrix[i][j]} is found in the {root} children
                if (root.children[getCharOffsetAbstract(charMatrix[i][j])] != null) {
                    searchStr.append(charMatrix[i][j]); // append that character to search string

//                    System.out.println("character " + charMatrix[i][j] + ", found @ " + i +  "," + j );

                    // start checking 2d char matrix elements for next valid character of current dictionary word
                    // will return the dictionary word if found or empty string
                    searchWordInMatrix(root.children[getCharOffsetAbstract(charMatrix[i][j])],
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
     * creates a tree of nodes and branch children per each node
     */
    private static class WordTree {
        // leaf is true if the node represents end of a word
        boolean leaf;

        String name;

        WordTree[] children = new WordTree[ENG_ALPHABET];
        // constructor
        public WordTree(String name_) {
            leaf = false;
            Arrays.fill(children, null);
            this.name = name_;
        }
    }

    /**
     * insert a new tree branch per dictionary word
     * @param parent parent node binding new tree branches
     * @param key a word form the dictionary
     */
    private static void insertNode(WordTree parent, String key){
        int n = key.length(); // length of each word

        WordTree node = parent; // parent node in the tree

//        StringBuilder space = new StringBuilder();
        for (int i = 0; i < n; i++) {

//            space.append("_");
            char character = key.charAt(i);

            // using [index] as a unique index identifier for each branch node (weight), instead of using i,
            // since the later would override children indices per dictionary word loop,
            // another working option would be to use HashMap instead with key being [index] but that would be more
            // resource consuming
            int index = getCharOffsetAbstract(character);

            if (node.children[index] == null) { // if child node not found in current parent
                // create a new child node branch assigned to this node's children
                node.children[index] = new WordTree("branch-" + character);
//                System.out.println(space + "[" + node.name + "]-->" + "branch-" + character + " @" + index);
            }

            // switch node ref to the current child node
            node = node.children[index];
        }
        // make last node as leaf node
        node.leaf = true;
    }


    /**
     * creates a 4X4 matrix from the given string
     * @param sts comma delimited string of words with length 4 per each
     * @return 4X4 char matrix of the words
     */
    private static char[][] splitToCharMatrix(String sts){
        String[] wordArr = sts.split(",");

        char[][] charMatrix = new char[ROW][COLUMN];
        for (int i = 0 ; i < wordArr.length; i++){
            char[] wordChar = wordArr[i].trim().toCharArray();
            charMatrix[i] = wordChar;
        }
        System.out.println("Char matrix >> " + Arrays.deepToString(charMatrix));
        return charMatrix;
    }

    public static void main(String[] args) {

        System.out.println(" > " + wordMatrixChallenge(strArr1));
        System.out.println(" > " + wordMatrixChallenge(strArr2));
        System.out.println(" > " + wordMatrixChallenge(strArr3));

    }
}
