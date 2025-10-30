package GalaxySim.src.Galaxy;

public class NeutronStar extends Star {
    private double spinRate;

    public NeutronStar(String name, double mass, double luminosity, double spinRate) {
        super(name, mass, luminosity);
        setSpinRate(spinRate);
    }

    //implement setSpinRate
    protected void setSpinRate(double spinRate) {
        if(spinRate > 0) {
            this.spinRate = spinRate;
        } else throw new IllegalArgumentException("Spin rate cannot be negative");
    }

    @Override
    public String getType() {
        return "NeutronStar";
    }

    public double getSpinRate() {
        return spinRate;
    }
}
