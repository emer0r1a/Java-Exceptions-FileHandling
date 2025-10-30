package GalaxySim.src.Galaxy;

public class TerrestrialPlanet extends Planet {
    private boolean habitable;

    public TerrestrialPlanet(String name, double mass, Star orbitingStar, boolean habitable) {
        super(name, mass, orbitingStar);
        this.habitable = habitable;
    }

    @Override
    public String getType() {
        return "TerrestrialPlanet";
    }

    public boolean isHabitable() {
        return habitable;
    }
}
