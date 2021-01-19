import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SolvingDailyJumble {


    private  Map<String, List<String>> wordMap;
    String[] wordsJumbled = {"cautla", "agileo", "mmlueb"};

    public static void main(String[] args) {

        SolvingDailyJumble solvingDailyJumble = new SolvingDailyJumble();

        solvingDailyJumble.jumbleJava();

    }

    public void jumbleJava() {
        try {
            wordMap = Files.lines(
                    Paths.get("src/res/words.txt"))
                    .filter(word ->  word.length() == 5 || word.length() == 6)
                    .collect(Collectors.groupingBy(this::word2key));

            System.out.println("words from word list: " +  wordMap.toString());

            for (int i = 0 ; i < wordsJumbled.length; i++)
                 System.out.println("solving for: " +  wordsJumbled[i] + " > " + solve(wordsJumbled[i]));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

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


    public String solve(String clue) {
        return wordMap.getOrDefault(word2key(clue),
                Collections.singletonList("")).get(0);
    }
}
