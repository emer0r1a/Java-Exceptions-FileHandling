package GalaxySim.src.Galaxy;

import java.io.*;
import java.util.List;
import java.util.NoSuchElementException;

public class Galaxy {

    //implement static methods
    public static void storeToFile(List<CelestialBody> bodies) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("galaxy.csv"))) {
            for (CelestialBody b : bodies) {
                String type = b.getType();
                String name = b.getName();
                double mass = b.getMass();

                double luminosity = 0;
                double temperature = 0;
                double spinRate = 0;
                String orbitingStar = "null";
                boolean habitable = false;
                int moonCount = 0;

                if (b instanceof NeutronStar n) {
                    luminosity = n.getLuminosity();
                    spinRate = n.getSpinRate();
                }
                else if (b instanceof MainSequenceStar m) {
                    luminosity = m.getLuminosity();
                    temperature = m.getTemperature();
                }
                else if (b instanceof Star s) {
                    luminosity = s.getLuminosity();
                }
                else if (b instanceof TerrestrialPlanet t) {
                    orbitingStar = (t.getOrbitingStar() != null) ? t.getOrbitingStar().getName() : "null";
                    habitable = t.isHabitable();
                }
                else if (b instanceof GasGiant g) {
                    orbitingStar = (g.getOrbitingStar() != null) ? g.getOrbitingStar().getName() : "null";
                    moonCount = g.getMoonCount();
                }
                else if (b instanceof Planet p) {
                    orbitingStar = (p.getOrbitingStar() != null) ? p.getOrbitingStar().getName() : "null";
                }

                bw.write(String.join(",",
                        type,
                        name,
                        String.valueOf(mass),
                        String.valueOf(luminosity),
                        String.valueOf(temperature),
                        String.valueOf(spinRate),
                        orbitingStar,
                        String.valueOf(habitable),
                        String.valueOf(moonCount)
                ));
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void loadFromFile(List<CelestialBody> bodies) throws IOException {
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader("galaxy.csv"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String line;
        while ((line = br.readLine()) != null) {
            String[] arr = line.split(",");
            if(arr[0].equalsIgnoreCase("Star")) bodies.add(new Star(arr[1], Double.parseDouble(arr[2]), Double.parseDouble(arr[3])));
            else if(arr[0].equalsIgnoreCase("NeutronStar")) bodies.add(new NeutronStar(arr[1], Double.parseDouble(arr[2]), Double.parseDouble(arr[3]), Double.parseDouble(arr[5])));
            else if(arr[0].equalsIgnoreCase("MainSequenceStar")) bodies.add(new MainSequenceStar(arr[1], Double.parseDouble(arr[2]), Double.parseDouble(arr[3]), Double.parseDouble(arr[4])));
            else if(arr[0].equalsIgnoreCase("GasGiant")) {
                Star orbiting = findStar(bodies, arr[6]);
                bodies.add(new GasGiant(arr[1], Double.parseDouble(arr[2]), orbiting, Integer.parseInt(arr[8])));
            }
            else if(arr[0].equalsIgnoreCase("Planet")) {
                Star orbiting = findStar(bodies, arr[6]);
                bodies.add(new Planet(arr[1], Double.parseDouble(arr[2]), orbiting));
            }
            else {
                Star orbiting = findStar(bodies, arr[6]);
                bodies.add(new TerrestrialPlanet(arr[1], Double.parseDouble(arr[2]), orbiting, Boolean.parseBoolean(arr[7])));
            }
        }
        br.close();
    }

    //Star finder for orbiting star
    private static Star findStar(List<CelestialBody> bodies, String starName) {
        for(CelestialBody b : bodies) {
            if(b instanceof Star && b.getName().equalsIgnoreCase(starName)) {
                return (Star) b;
            }
        }
        return null;
    }

    public static void assignOrbit(List<CelestialBody> bodies, String planetName, String starName) {
        if(planetName.equalsIgnoreCase(starName)) throw new IllegalArgumentException();
        Star orbit = null;
        Planet d = null;
        boolean found1 = false, found2 = false;
        for(CelestialBody b : bodies) {
            if(b.getName().equalsIgnoreCase(planetName)) {
                if(b instanceof Planet) {
                    d = (Planet) b;
                    found1 = true;
                } else throw new ClassCastException(planetName+" is not a Planet");
            } else if(b.getName().equalsIgnoreCase(starName)) {
                if(b instanceof Star) {
                    orbit = (Star) b;
                    found2 = true;
                } else throw new ClassCastException(starName + " is not a Star");
            }
        }
        if(found1 && found2) d.setOrbitingStar(orbit);
        else if(!found1) throw new NoSuchElementException("Body "+planetName+" not found");
        else throw new NoSuchElementException("Body "+starName+" not found");
    }

    public static String compareMass(List<CelestialBody> bodies, String name1, String name2) {
        double a = 0, c = 0;
        for(CelestialBody b : bodies) {
            if(b.getName().equalsIgnoreCase(name1)) {
                a = b.getMass();
            } else if(b.getName().equalsIgnoreCase(name2)) {
                c = b.getMass();
            }
        }
        if(a == c) return "Equal mass";
        else if(a > c) return name1 + " is heavier";
        else return name2 + " is heavier";
    }

    public static CelestialBody findBody(List<CelestialBody> bodies, String name) {
        for(CelestialBody b : bodies) {
            if(b.getName().equalsIgnoreCase(name)) {
                return b;
            }
        }
        throw new NoSuchElementException();
    }
}
