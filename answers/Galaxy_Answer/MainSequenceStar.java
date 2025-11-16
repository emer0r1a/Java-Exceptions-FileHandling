package Galaxy_Answer;

public class MainSequenceStar extends Star {
    private double temperature;

    public MainSequenceStar(String name, double mass, double luminosity, double temperature) {
        super(name, mass, luminosity);
        setTemperature(temperature);
    }

    //implement setTemperature(double)
    protected void setTemperature(double temperature) {
        if(temperature > 3000) {
            this.temperature = temperature;
        } else throw new IllegalArgumentException("Temperature too low for a main sequence star");
    }

    @Override
    public String getType() {
        return "MainSequenceStar";
    }

    public double getTemperature() {
        return temperature;
    }
}
