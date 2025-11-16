package Galaxy_Answer;

public class Planet extends CelestialBody {
    protected Star orbitingStar;

    public Planet(String name, double mass, Star orbitingStar) {
        super(name, mass);
        setOrbitingStar(orbitingStar);
    }

    //implement setOrbitingStar
    protected void setOrbitingStar(Star orbitingStar) {
        if(orbitingStar != null) {
            this.orbitingStar = orbitingStar;
        } else throw new IllegalArgumentException("Planet must orbit a star");
    }

    @Override
    public String getType() {
        return "Planet";
    }

    public Star getOrbitingStar() {
        return orbitingStar;
    }
}
