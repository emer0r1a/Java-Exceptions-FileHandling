package University_Answer;

public abstract class Person {
    private final String name;
    private int age;

    public Person(String name, int age) {
        setAge(age);
        this.name = name;
    }

    public void setAge(int age) {
        if(age > 0) {
            this.age = age;
        } else throw new IllegalArgumentException("Age must be non-negative");
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
