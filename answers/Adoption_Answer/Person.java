package Adoption_Answer;

public class Person {
    private String name;
    private int age;

    //change constructor
    public Person(String name, int age) {
        setAge(age);
        this.name = name;
    }

    private void setAge(int age) {
        if(age > 0) this.age = age;
        else throw new IllegalArgumentException("Age must be non-negative");
    }


    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
