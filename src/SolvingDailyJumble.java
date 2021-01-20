import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * you have alist  jumbled words and you need to figure out what each word means actually
 */
public class SolvingDailyJumble {


    private  Map<String, List<String>> wordMap; // store key of sorted word characters and list of words that match
    String[] wordsJumbled = {"cautla", "agileo", "mmlueb"}; // random jumbled words

    public static void main(String[] args) {

        SolvingDailyJumble solvingDailyJumble = new SolvingDailyJumble();

        solvingDailyJumble.jumbleJava(6);

    }

    /**
     * load and external word dictionary
     * and c create hashmap of each sorted characters set and their corresponding words
     */
    public void jumbleJava(int filter) {
        try {
            wordMap = Files.lines(
                    Paths.get("src/res/words.txt"))
                    .map(String::toLowerCase) // convert all to lowercase
                    .filter(word ->  word.length() <= filter) // filter words with 5 to 6 character (optional)
                    .collect(Collectors.groupingBy(this::word2key));// group sorted letter and matching words

            System.out.println("words from word list: " +  wordMap.toString());

            // solve and print out each word match
            for (String s : wordsJumbled)
                System.out.println("solving for: " + s + " > " + solve(s.toLowerCase()));

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /**
     * convter a string into sorted string of characters
     * @param word string of word
     * @return a string of sorted characters
     */
    private String word2key(String word) {
        return Arrays.stream(word.split(""))
                .sorted()
                .collect(Collectors.joining());
    }

    public List<String> parallelSolve(String... clues) {
        return Arrays.stream(clues)
                .parallel()  // totally not necessary
                .map(this::solve)
                .collect(Collectors.toList());
    }


    /**
     * returns  a word form the dictionary matching the jumbled word
     * @param clue the jumbled word to search
     * @return the matching correct word form the dictionary
     */
    public String solve(String clue) {
        // Returns the value to which the specified key is mapped, or defaultValue if this map contains no mapping for the key
        return wordMap.getOrDefault(word2key(clue), Collections.singletonList(""))
                .get(0); // get 1st item form list
    }
}
