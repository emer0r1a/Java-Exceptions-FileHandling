package University_Answer;

public class Professor extends Person {
    private double salary;
    private final String department;

    public Professor(String name, int age, String department, double salary) {
        super(name, age);
        setSalary(salary);

        if(department == null) throw new IllegalArgumentException("Department required");
        else this.department = department;
    }

    protected void setSalary(double salary) {
        if(salary >= 40000) {
            this.salary = salary;
        } else throw new IllegalArgumentException("Salary must be at least 40000");
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

    @Override
    public String toString() {
        return "Professor(Name="+ getName() + ", Dept=" +department+", Salary="+String.format("%.2f)", getSalary());
    }
}
