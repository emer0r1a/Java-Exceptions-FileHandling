package Adoption_Answer;

public abstract class Animal {
    private String name;
    private int age;
    private double adoptionFee;

    //change constructor
    public Animal(String name, int age, double adoptionFee) {
        this.name = name;
        setAge(age);
        setAdoptionFee(adoptionFee);
    }

    //implement setter methods
    public void setAdoptionFee(double adoptionFee) {
        if(adoptionFee >= 100) {
            this.adoptionFee = adoptionFee;
        } else throw new IllegalArgumentException("Adoption fee must be at least 100");
    }

    public void setAge(int age) {
        if(age >= 0) {
            this.age = age;
        } else throw new IllegalArgumentException("Age must be non-negative");
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getAdoptionFee() {
        return adoptionFee;
    }

    //implement method
    public abstract String toCSV();
    public abstract String toString();
    public abstract String getType();
}
