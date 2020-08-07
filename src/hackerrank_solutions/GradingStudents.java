package hackerrank_solutions;

import java.io.*;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class GradingStudents {

    private static Integer gradeRounder(Integer num){
        int baseFive = 5;
        int failMinGrade = 38;
        double nextMultiplier = Math.ceil(num.floatValue() / (float) baseFive) * baseFive;
        int multipVal = (int) (nextMultiplier - num);
        return (multipVal < 3 && num >= failMinGrade)? (int) nextMultiplier : num;

    }

    public static List<Integer> gradingStudents(List<Integer> grades) {

        List<Integer> updatedGrades = grades.stream()
                .map(GradingStudents::gradeRounder)
                .collect(toList());

        return updatedGrades;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("E:\\projects\\java\\javaSE\\javalessons\\out\\FileWriterTest.txt"));

        int gradesCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> grades = IntStream.range(0, gradesCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> result = GradingStudents.gradingStudents(grades);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
