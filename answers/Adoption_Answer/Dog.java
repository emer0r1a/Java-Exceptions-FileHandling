package Adoption_Answer;

public class Dog extends Animal {
    private boolean trained;

    //change constructor
    public Dog(String name, int age, double adoptionFee, boolean trained) {
        super(name, age, adoptionFee);
        this.trained = trained;
        if(trained && age <= 0) throw new IllegalArgumentException("Trained dog must be at least 1 year old");
    }

    public boolean isTrained() {
        return trained;
    }

    @Override
    public String getType() {
        return "Dog";
    }

    @Override
    public String toCSV() {
        return "Dog,"+getName()+","+getAge()+","+String.format("%.1f", getAdoptionFee())+","+isTrained();
    }

    @Override
    public String toString() {
        return "Dog("+getName()+", Age="+getAge()+", Fee="+String.format("%.1f", getAdoptionFee())+", Trained="+isTrained();
    }
}
