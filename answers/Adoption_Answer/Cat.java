package GalaxySim.src.PetAdoptionSystem;

public class Cat extends Animal {
    private boolean indoor;

    //change constructor
    public Cat(String name, int age, double adoptionFee, boolean indoor) {
        super(name, age, adoptionFee);
        setIndoor(age, indoor);
    }

    public void setIndoor(int age, boolean indoor) {
        if(age < 1 && indoor) this.indoor = true;
        else if(age < 1 && !indoor) throw new IllegalArgumentException("Kittens must be indoor cats");
    }

    public boolean isIndoor() {
        return indoor;
    }

    @Override
    public String toString() {
        return "Cat("+getName()+", Age="+getAge()+", Fee="+getAdoptionFee()+", Indoor="+getIndoor();
    }

    @Override
    public String getType() {
        return "Cat";
    }


}
