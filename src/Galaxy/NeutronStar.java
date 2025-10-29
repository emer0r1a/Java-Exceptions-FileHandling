package GalaxySim.src.Galaxy;

public class NeutronStar extends Star {
    private double spinRate;

    public NeutronStar(String name, double mass, double luminosity, double spinRate) {
        super(name, mass, luminosity);
    }

    //implement setSpinRate
    protected void setSpinRate(double spinRate) {

    }

    @Override
    public String getType() {
        return "NeutronStar";
    }

    public double getSpinRate() {
        return spinRate;
    }
}
