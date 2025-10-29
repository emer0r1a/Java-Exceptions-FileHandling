package GalaxySim.src.Galaxy;

public class Star extends CelestialBody {
    protected double luminosity;

    public Star(String name, double mass, double luminosity) {
        super(name, mass);
    }

    //implement setLuminosity(double)
    protected void setLuminosity(double luminosity) {

    }

    @Override
    public String getType() {
        return "Star";
    }

    public double getLuminosity() {
        return luminosity;
    }
}
