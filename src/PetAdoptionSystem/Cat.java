package GalaxySim.src.PetAdoptionSystem;

public class Cat extends Animal {
    private boolean indoor;

    //change constructor
    public Cat(String name, int age, double adoptionFee, boolean indoor) {
        super(name, age, adoptionFee);
        this.indoor = indoor;
    }

    public boolean isIndoor() {
        return indoor;
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public String getType() {
        return "Cat";
    }
}
