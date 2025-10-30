package GalaxySim.src.PetAdoptionSystem;

public abstract class Animal {
    private String name;
    private int age;
    private double adoptionFee;

    //change constructor
    public Animal(String name, int age, double adoptionFee) {
        this.name = name;
        this.age = age;
        this.adoptionFee = adoptionFee;
    }

    //implement setter methods

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
