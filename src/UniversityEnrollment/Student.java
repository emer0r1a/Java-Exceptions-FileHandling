package UniversityEnrollment;

public class Student extends Person {
    private String studentID;
    private double gpa;
    private Professor advisor;

    public Student(String name, int age, String studentID, double gpa) {
        super(name, age);
        this.studentID = studentID;
        this.gpa = gpa;
        this.advisor = advisor;
    }

    public String getStudentID() {
        return studentID;
    }

    public double getGpa() {
        return gpa;
    }

    public Professor getAdvisor() {
        return advisor;
    }

    public void removeAdvisor() {
        advisor = null;
    }

    @Override
    public void performTask() {
        System.out.println(getName() + " is studying");
    }

    @Override
    public String toString() {
        return "Student " + getName() + " (" + getAge() + ") - GPA: " + gpa +
                (advisor != null ? "[Advisor: " + advisor.getName() + "]" : "");
    }
}
