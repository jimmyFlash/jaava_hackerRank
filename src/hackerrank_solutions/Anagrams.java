package hackerrank_solutions;

import java.util.*;
import java.util.stream.Collectors;

public class Anagrams {

    private static String[] anagrams = {
            "pear",
            "amleth",
            "dormitory",
            "tinsel",
            "dirty room",
            "hamlet",
            "door me try",
            "listen"};

    // class for each word of duplicate array
    static class Word {
        String str; // to store word itself
        int index; // index of the word in the original array

        // constructor
        Word(String str, int index) {
            this.str = str;
            this.index = index;
        }
    }

    // class to represent duplicate array.
    static class DupArray {
        Word[] array; // Array of words
        int size; // Size of array

        // constructor
        DupArray(String[] str, int size){
            this.size = size;
            array = new Word[size];
            // One by one copy words from the
            // given wordArray to dupArray

            for (int i = 0; i < size; ++i) {
                // create a word Object with the
                // str[i] as str and index as i
                array[i] = new Word(str[i], i);
            }
        }
    }

    // Compare two words. Used in Arrays.sort() for
    // sorting an array of words
    static class CompStr implements Comparator<Word> {
        public int compare(Word a, Word b){
            return a.str.compareTo(b.str);
        }
    }

    // Given a list of words in originalWordArr[],
    private static void printAnagramsTogether(String[] originalWordArr, int size){
        System.out.println("--Solution 1 >>>>>>> ");
        int i;
        // Step 1: Create a copy of all words present
        // in given originalWordArr. The copy will also have
        // original indexes of words
        DupArray dupArray = new DupArray(originalWordArr, size);

        // Step 2: Iterate through all words in
        // dupArray and sort individual words.
        for ( i = 0; i < size; ++i) {
            String dupArrStr = dupArray.array[i].str.replaceAll("\\s","");// remove spaces within a word
//            System.out.println("trimmed string: " + dupArrStr);
            char[] char_arr = dupArrStr.toCharArray();
            Arrays.sort(char_arr);
            dupArray.array[i].str = new String(char_arr);// reassign them back to the duplicates array
        }

        // Step 3: Now sort the array of words in
        // dupArray
        Arrays.sort(dupArray.array, new CompStr());
        System.out.println("sorted array of sorted words: " + Arrays.stream(dupArray.array)
                .map(word -> word.str)
                .collect(Collectors.joining(",")) + "\n");

        // Step 4: Now all words in dupArray are together,
        // but these words are changed. Use the index
        // member of word struct to get the corresponding
        // original word
        String currStr;
        String prevStr = "";
        StringBuilder strBuilder;


        for (i = 0; i < size; i++) {
            currStr = dupArray.array[i].str; // pick a string form the array of dups in order
            if(!currStr.equals(prevStr)){ // compare for mismatch between current word and last previously saved word
                // create a new string set
                // add a word from original array to it matching the index of current selected word
                strBuilder = new StringBuilder();
                strBuilder.append(originalWordArr[dupArray.array[i].index]);

                // create a nested loop with start index = current index + 1 to avoid duplicates
                for (int j = i + 1 ; j < size ; j++) {
                    // check if the current string is equal to the value of var currStr
                    if(dupArray.array[j].str.equals(currStr)){
                        // append a comma delimiter and the original string from originalWordArr with matching index property
                        strBuilder.append(", ");
                        strBuilder.append(originalWordArr[dupArray.array[j].index]);
                        // keep refrence to the current string in dups array
                        prevStr = dupArray.array[j].str;
                    }
                }

                System.out.println("anagram group set >>>>>>> " + strBuilder );

            }

           // System.out.print(originalWordArr[dupArray.array[i].index] + " ");
        }
        System.out.println("--Solution 1 <<<<<<< \n");
    }

    public static void main(String[] args) {
        //BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        //Stream<String> linesStream = bufferedReader.lines();
        //List<String> collect = linesStream.collect(Collectors.toList()); List<String> collect = Arrays.asList(anagrams);

        int size = anagrams.length;
        printAnagramsTogether(anagrams, size);

        printAnagramsUsingHashmap(anagrams);


        //bufferedReader.close();

    }

    /**
     * secodn solution using hashmaps
     * @param arr array of strings containing anagrams
     */
    private static void printAnagramsUsingHashmap(String[] arr) {

        System.out.println("--Solution 2 >>>>>>>> ");
        HashMap<String, List<String>> map = new HashMap<>();

        System.out.println("serialized array: " + String.join(",", arr) + "\n");

        // loop over all words
        for (String word : arr) {

            String removeWhiteSpace = word.replaceAll("\\s", "");// remove spaces within a word
            // convert to char array, sort and
            // then re-convert to string
            char[] letters = removeWhiteSpace.toCharArray();
            Arrays.sort(letters);
            String newWord = new String(letters);

            if (map.containsKey(newWord)) {

                map.get(newWord).add(word);
            } else {

                // This is the first time we are
                // adding a word
                List<String> words = new ArrayList<>();
                words.add(word);
                map.put(newWord, words);
            }
        }


        Set<Map.Entry<String, List<String>>> entries = map.entrySet();

        // Now let's sort HashMap by keys first
        // all you need to do is create a TreeMap with mappings of HashMap
        // TreeMap keeps all entries in sorted order
        TreeMap<String, List<String>> sorted = new TreeMap<>(map);
        Set<Map.Entry<String, List<String>>> mappings = sorted.entrySet();
        for(Map.Entry<String, List<String>> mapping : mappings){
           // System.out.println(mapping.getKey() + " ==> " + mapping.getValue());
            if (mapping.getValue().size() > 1) { // anagrams
                System.out.println("anagrams: " + mapping.getValue().toString().replaceAll("[\\[\\]]", ""));
            } else if (mapping.getValue().size() == 1) {// non-anagrams
                System.out.println("non-anagrams: " + mapping.getValue().toString().replaceAll("[\\[\\]]", ""));
            }
        }
        System.out.println("--Solution 2 <<<<<<< ");
    }
}
