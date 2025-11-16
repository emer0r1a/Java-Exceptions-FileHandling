package Galaxy_Answer;

public class Star extends CelestialBody {
    protected double luminosity;

    public Star(String name, double mass, double luminosity) {
        super(name, mass);
        setLuminosity(luminosity);
    }

    //implement setLuminosity(double)
    protected void setLuminosity(double luminosity) {
        if(luminosity > 0) {
            this.luminosity = luminosity;
        } else throw new IllegalArgumentException("Luminosity must be non-negative");
    }

    @Override
    public String getType() {
        return "Star";
    }

    public double getLuminosity() {
        return luminosity;
    }
}
