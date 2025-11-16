package Galaxy;

public abstract class CelestialBody {
    protected String name;
    protected double mass;

    public CelestialBody(String name, double mass) {
        this.name = name;
    }

    //implement setMass(double)
    protected void setMass(double mass) {

    }

    public String getName() {
        return name;
    }

    public double getMass() {
        return mass;
    }

    public abstract String getType();
}
