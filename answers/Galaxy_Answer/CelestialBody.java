package Galaxy_Answer;

public abstract class CelestialBody {
    protected String name;
    protected double mass;

    public CelestialBody(String name, double mass) {
        this.name = name;
        setMass(mass);
    }

    //implement setMass(double)
    protected void setMass(double mass) {
        if(mass <= 0) {
            throw new IllegalArgumentException("Mass must be positive");
        } else {
            this.mass = mass;
        }
    }

    public String getName() {
        return name;
    }

    public double getMass() {
        return mass;
    }

    public abstract String getType();
}
