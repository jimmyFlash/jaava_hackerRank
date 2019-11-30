package hackerrank_solutions;

import java.util.*;

public class JavaSort {

    public static void main(String[] args ){

        Scanner in = new Scanner(System.in);
        int testCases = Integer.parseInt(in.nextLine());
        ComparatorStudents comparatorStudents = new ComparatorStudents();

        List<Student> studentList = new ArrayList<>();
        while(testCases>0){
            int id = in.nextInt();
            String fname = in.next();
            double cgpa = in.nextDouble();

            Student st = new Student(id, fname, cgpa);
            studentList.add(st);

            testCases--;
        }

        studentList.sort(comparatorStudents);
        for(Student st: studentList){
            System.out.println(st.getFname());
        }
    }
}

class ComparatorStudents  implements Comparator<Student> {
    ComparatorStudents(){}

    @Override
    public int compare(Student p1, Student p2) {
        if(p1.getCgpa() != p2.getCgpa()){
            return Double.compare(p2.getCgpa(),  p1.getCgpa());
        }else {
            if(!p1.getFname().equals(p2.getFname())){
                return p1.getFname().compareTo(p2.getFname());
            }else{
                return Integer.compare(p2.getId(), p1.getId());
            }
        }
    }
}

class Student{
    private int id;
    private String fname;
    private double cgpa;
    public Student(int id, String fname, double cgpa) {
        super();
        this.id = id;
        this.fname = fname;
        this.cgpa = cgpa;
    }
    public int getId() {
        return id;
    }
    public String getFname() {
        return fname;
    }
    public double getCgpa() {
        return cgpa;
    }
}
