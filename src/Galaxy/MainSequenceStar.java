package Galaxy;

public class MainSequenceStar extends Star {
    private double temperature;

    public MainSequenceStar(String name, double mass, double luminosity, double temperature) {
        super(name, mass, luminosity);
    }

    //implement setTemperature(double)
    protected void setTemperature(double temperature) {

    }

    @Override
    public String getType() {
        return "MainSequenceStar";
    }

    public double getTemperature() {
        return temperature;
    }
}
