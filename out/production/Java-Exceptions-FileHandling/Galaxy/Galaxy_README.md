Create an OOP program that models celestial bodies (stars and planets) and performs file storage, retrieval, and operations on them.

The system must include inheritance, method overriding, and validation with exceptions — just like the Person version (Customer, Developer, Employee, Manager).

# CLASS STRUCTURE
## CelestialBody (abstract)

`protected String name`

`protected double mass`

Constructor: 
`public CelestialBody(String name, double mass)`


Throws `IllegalArgumentException("Mass must be positive")` if **mass <= 0**.


Methods:

`public String getName()`

`public double getMass()`

`public abstract String getType()`




## Star (extends CelestialBody)

`protected double luminosity`

Constructor:

`public Star(String name, double mass, double luminosity)`


Throws `IllegalArgumentException` if **luminosity < 0**.

Methods:

@Override `public String getType()` _// returns "Star"_

## MainSequenceStar (extends Star)

`private double temperature`

Constructor:

`public MainSequenceStar(String name, double mass, double luminosity, double temperature)`


Throws `IllegalArgumentException` if **temperature < 3000**.

Methods:

@Override
`public String getType()` // _returns "MainSequenceStar"_

## NeutronStar (extends Star)

`private double spinRate`

Constructor:

`public NeutronStar(String name, double mass, double luminosity, double spinRate)`


Throws `IllegalArgumentException` if **spinRate < 0**.

Methods:

@Override
`public String getType()` _// returns "NeutronStar"_

## Planet (extends CelestialBody)

`protected Star orbitingStar`

Constructor:

`public Planet(String name, double mass, Star orbitingStar)`


Throws `IllegalArgumentException` if **orbitingStar == null**.

Methods:

`public Star getOrbitingStar()`

@Override
`public String getType() `_// returns "Planet"_

## GasGiant (extends Planet)

`private int moonCount`

Constructor:

`public GasGiant(String name, double mass, Star orbitingStar, int moonCount)`


Methods:

@Override
`public String getType()` _// returns "GasGiant"_

## TerrestrialPlanet (extends Planet)

`private boolean habitable`

Constructor:

`public TerrestrialPlanet(String name, double mass, Star orbitingStar, boolean habitable)`


Methods:

@Override
`public String getType()` _// returns "TerrestrialPlanet"_

# GALAXY MAIN CLASS (Galaxy.java)

Implement these static methods:

## storeToFile(List<CelestialBody> bodies)

Saves all bodies into "galaxy.csv"

Format per line:

`Type,Name,Mass,Luminosity,Temperature,SpinRate,OrbitingStar,Habitable,MoonCount`


If a value doesn’t apply, write 0 or null or false.

## loadFromFile(List<CelestialBody> bodies)

Loads bodies from "galaxy.csv" back into the list.

Recreates the appropriate subclass object based on the first column (Type).

## assignOrbit(List<CelestialBody> bodies, String planetName, String starName)

Sets the planet’s orbiting star to the given star.

Throws
`ClassCastException` if the planet or star type is invalid.

`NoSuchElementException` if either name doesn’t exist.

`IllegalStateException` if the planet already orbits a star.

## compareMass(List<CelestialBody> bodies, String name1, String name2)

Returns:

"Equal mass"

"<name1> is heavier"

"<name2> is heavier"

Throws `NoSuchElementException` if any body not found.

## findBody(List<CelestialBody> bodies, String name)

Returns the CelestialBody with that name.

Throws `NoSuchElementException` if not found.

# EXCEPTIONS

`IllegalArgumentException`	Invalid mass, temperature, luminosity, or null orbit

`ClassCastException`	Assigning non-matching body types

`IllegalStateException`	Planet already has orbit

`NoSuchElementException`	Missing celestial body
