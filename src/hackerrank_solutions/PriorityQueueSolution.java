package hackerrank_solutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class PriorityQueueSolution {

    private List<String> events = new ArrayList<>();

    private static final String ENTER = "ENTER";

    public static void main (String[] args) throws IOException {
        PriorityQueueSolution pq = new PriorityQueueSolution();

        // Create a buffering character-input stream that uses a default-sized input buffer.
        // Creates an InputStreamReader that uses the default charset to read system input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int numberOfEvents = Integer.parseInt(br.readLine());

        IntStream.range(0, numberOfEvents).forEach( eventStr -> {
            try {
                pq.events.add(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        List<StudentEntry> list = pq.getStudents(pq.events);

       // System.out.println(list.toString());

        br.close();


    }

    private ComparatorStudentsEvents initComp(){

        return new ComparatorStudentsEvents();
    }

    List<StudentEntry> getStudents(List<String> events){

        // PriorityQueue<StudentEntry> pq = new PriorityQueue<>(new ComparatorStudentsEvents());

        PriorityQueue<StudentEntry> pq =  new PriorityQueue<StudentEntry>(
                Comparator.comparing(StudentEntry::getCGPA).reversed()
                        .thenComparing(StudentEntry::getName)
                        .thenComparing(StudentEntry::getID)
        );

//        System.out.println("events : " +  events.size());
        for (String event : events){
            if(event.contains(ENTER)){
                String[] studentData = event.split("\\s");
                String studenNm = studentData[1].trim();
                Integer studenID = Integer.valueOf(studentData[3].trim());
                Double studenGpc = Double.valueOf(studentData[2].trim());

                StudentEntry stuEntry = new StudentEntry(studenID, studenNm, studenGpc);
                pq.add(stuEntry);
                //System.out.println("peek : " +  pq.peek());
            }else{
               pq.poll();
            }
        }

        List<StudentEntry> listy_=  new ArrayList<>();
        while(!pq.isEmpty()){
            StudentEntry sortedStudent = pq.poll();
            listy_.add(sortedStudent);
            System.out.println(sortedStudent.getName());
        }

        return listy_;
    }

    class ComparatorStudentsEvents  implements Comparator<StudentEntry> {
        ComparatorStudentsEvents() {}

        @Override
        public int compare(StudentEntry p1, StudentEntry p2) {
            if(!p2.getCGPA().equals(p1.getCGPA())){
                return Double.compare(p2.getCGPA(), p1.getCGPA());
            }else {
                if(!p1.getName().equals(p2.getName())){
                    return p1.getName().compareTo(p2.getName());
                }else{
                    return Integer.compare(p2.getID(), p1.getID());
                }
            }
        }
    }
}

class StudentEntry{

    private Integer id;
    private String name;
    private Double cgpa;

    StudentEntry(Integer id, String name, Double cgpa) {
        this.id = id;
        this.name = name;
        this.cgpa = cgpa;
    }

    Integer getID() {
        return id;
    }

    String getName() {
        return name;
    }

    Double getCGPA() {
        return cgpa;
    }

}
