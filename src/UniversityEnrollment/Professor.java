package UniversityEnrollment;

public class Professor extends Person {
    private double salary;
    private final String department;

    public Professor(String name, int age, String department, double salary) {
        super(name, age);
        this.department = department;
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public String getDepartment() {
        return department;
    }

    public void performTask() {
        System.out.println(getName() + " is teaching in " + department);
    }
}
