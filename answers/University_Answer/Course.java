package University_Answer;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private final String code;
    private final String title;
    private Professor instructor;
    private List<Student> enrolled = new ArrayList<>();

    public Course(String title, String code, Professor instructor) {
        this.instructor = instructor;
        this.title = title;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public Professor getInstructor() {
        return instructor;
    }

    public List<Student> getEnrolled() {
        return enrolled;
    }

    //implement methods
    public void enroll(Student student) {
        enrolled.add(student);
    }

    public void drop(Student student) {
        enrolled.remove(student);
    }

    public String toString() {
        return "Course: "+code+" - "+title+" ("+enrolled.size()+"students)";
    }
}
