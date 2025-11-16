package University_Answer;

public class Student extends Person {
    private String studentID;
    private double gpa;
    private Professor advisor;

    public Student(String name, int age, String studentID, double gpa) {
        super(name, age);
        this.studentID = studentID;
        setGpa(gpa);
    }

    protected void setGpa(double gpa) {
        if(gpa >= 0 && gpa <= 4) {
            this.gpa = gpa;
        } else throw new IllegalArgumentException("GPA must be between 0.0 and 4.0");
    }

    protected void setAdvisor(Professor prof) {
        if(advisor == null) advisor = prof;
        else throw new IllegalStateException(getName()+" already has an advisor: "+getAdvisor().getName());
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
        return "Student(Name="+getName()+", GPA="+String.format("%.2f",getGpa())+", Advisor=" +
                (advisor != null ? advisor.getName() + ")" : "NONE)");
    }
}

