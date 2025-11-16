package UniversityEnrollment;

public abstract class Person {
    private final String name;
    private int age;

    public Person(String name, int age) {
        this.age = age;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void birthday() {
        age++;
        System.out.println("Happy birthday, "+ name + "!");
    }

    public abstract void performTask();

    public String toString() {
        return name + " (" + age + ")";
    }
}
