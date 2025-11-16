package PetAdoptionSystem;

public class Dog extends Animal {
    private boolean trained;

    //change constructor
    public Dog(String name, int age, double adoptionFee, boolean trained) {
        super(name, age, adoptionFee);
        this.trained = trained;
    }

    public boolean isTrained() {
        return trained;
    }

    @Override
    public String getType() {
        return "Dog";
    }

    @Override
    public String toString() {
        return "";
    }
}
