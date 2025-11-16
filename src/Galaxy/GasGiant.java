package Galaxy;

public class GasGiant extends Planet {
    private int moonCount;

    public GasGiant(String name, double mass, Star orbitingStar, int moonCount) {
        super(name, mass, orbitingStar);
        this.moonCount = moonCount;
    }

    @Override
    public String getType() {
        return "GasGiant";
    }

    public int getMoonCount() {
        return moonCount;
    }
}
