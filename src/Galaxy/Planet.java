package GalaxySim.src.Galaxy;

public class Planet extends CelestialBody {
    protected Star orbitingStar;

    public Planet(String name, double mass, Star orbitingStar) {
        super(name, mass);
        this.orbitingStar = orbitingStar;
    }

    //implement setOrbitingStar
    protected void setOrbitingStar(Star orbitingStar) {

    }

    @Override
    public String getType() {
        return "Planet";
    }

    public CelestialBody getOrbitingStar() {
        return orbitingStar;
    }
}
