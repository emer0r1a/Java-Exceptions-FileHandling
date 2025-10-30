package GalaxySim.src.Galaxy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class CelestialTest {

    List<CelestialBody> bodies;

    @BeforeEach
    void setUp() {
        bodies = new ArrayList<>();
    }

    @AfterEach
    void tearDown() {
        bodies.clear();
    }

    @Test
    void testValidCreation() {
        assertDoesNotThrow(() -> {
            bodies.add(new MainSequenceStar("Sun", 1.989e30, 3.828e26, 5778));
            bodies.add(new NeutronStar("Pulsar", 2.8e30, 1.2e24, 716));
            bodies.add(new GasGiant("Jupiter", 1.898e27, new MainSequenceStar("Sun", 1.989e30, 3.828e26, 5778), 79));
            bodies.add(new TerrestrialPlanet("Earth", 5.972e24, new MainSequenceStar("Sun", 1.989e30, 3.828e26, 5778), true));
        });
        assertEquals("Sun", bodies.get(0).getName());
        assertEquals(5.972e24, bodies.get(3).getMass());
    }

    @Test
    void testInvalidMass() {
        assertThrows(IllegalArgumentException.class, () ->
                bodies.add(new MainSequenceStar("Tiny", -1.0, 3.0, 3000)));
        try {
            bodies.add(new MainSequenceStar("Tiny", -1.0, 3.0, 3000));
            assert false : "Should throw exception";
        } catch (IllegalArgumentException e) {
            assertEquals("Mass must be positive", e.getMessage());
        }
    }

    @Test
    void testInvalidLuminosity() {
        assertThrows(IllegalArgumentException.class, () ->
                bodies.add(new MainSequenceStar("Star", 1.0, -5.0, 4000)));
        try {
            bodies.add(new MainSequenceStar("Star", 1.0, -5.0, 4000));
            assert false;
        } catch (IllegalArgumentException e) {
            assertEquals("Luminosity must be non-negative", e.getMessage());
        }
    }

    @Test
    void testInvalidTemperature() {
        assertThrows(IllegalArgumentException.class, () ->
                bodies.add(new MainSequenceStar("Cold", 1e30, 3e26, 2000)));
        try {
            bodies.add(new MainSequenceStar("Cold", 1e30, 3e26, 2000));
            assert false;
        } catch (IllegalArgumentException e) {
            assertEquals("Temperature too low for a main sequence star", e.getMessage());
        }
    }

    @Test
    void testInvalidSpinRate() {
        assertThrows(IllegalArgumentException.class, () ->
                bodies.add(new NeutronStar("Slow", 1e30, 1e25, -5)));
        try {
            bodies.add(new NeutronStar("Slow", 1e30, 1e25, -5));
            assert false;
        } catch (IllegalArgumentException e) {
            assertEquals("Spin rate cannot be negative", e.getMessage());
        }
    }

    @Test
    void testInvalidOrbit() {
        assertThrows(IllegalArgumentException.class, () ->
                bodies.add(new TerrestrialPlanet("Mars", 6.3e23, null, false)));
        try {
            bodies.add(new TerrestrialPlanet("Mars", 6.3e23, null, false));
            assert false;
        } catch (IllegalArgumentException e) {
            assertEquals("Planet must orbit a star", e.getMessage());
        }
    }

    @Test
    void testAssignOrbitValid() {
        bodies.add(new MainSequenceStar("Sun", 1.989e30, 3.8e26, 5800));
        bodies.add(new MainSequenceStar("AlphaCentauri", 2.188e30, 5.1e26, 6200));
        bodies.add(new TerrestrialPlanet("Earth", 5.972e24, (Star) bodies.get(0), true));
        bodies.add(new GasGiant("Jupiter", 1.898e27, (Star) bodies.get(0), 79));
        bodies.add(new TerrestrialPlanet("ProximaB", 1.27e25, (Star) bodies.get(1), false));

        assertDoesNotThrow(() -> Galaxy.assignOrbit(bodies, "ProximaB", "Sun"));
        assertEquals("Sun", ((Planet) bodies.get(4)).getOrbitingStar().getName(),
                "ProximaB should now orbit the Sun");

        assertDoesNotThrow(() -> Galaxy.assignOrbit(bodies, "Jupiter", "AlphaCentauri"));
        assertEquals("AlphaCentauri", ((Planet) bodies.get(3)).getOrbitingStar().getName(),
                "Jupiter should now orbit AlphaCentauri");

        assertThrows(ClassCastException.class,
                () -> Galaxy.assignOrbit(bodies, "Sun", "Earth"),
                "A star cannot orbit a planet");

        assertThrows(NoSuchElementException.class,
                () -> Galaxy.assignOrbit(bodies, "Pluto", "Sun"),
                "Should throw when the target body does not exist");

        assertThrows(IllegalArgumentException.class,
                () -> Galaxy.assignOrbit(bodies, "Earth", "Earth"),
                "A body cannot orbit itself");

        assertThrows(ClassCastException.class,
                () -> Galaxy.assignOrbit(bodies, "Earth", "Jupiter"),
                "A planet cannot orbit another planet");

        assertEquals("Sun", ((Planet) bodies.get(4)).getOrbitingStar().getName(),
                "After invalid operations, ProximaB should still orbit the Sun");
    }

    @Test
    void testAssignOrbitInvalidType() {
        bodies.add(new NeutronStar("Pulsar", 2e30, 1e25, 600));
        bodies.add(new NeutronStar("Core", 1.5e30, 1e25, 500));
        assertThrows(ClassCastException.class, () ->
                Galaxy.assignOrbit(bodies, "Core", "Pulsar"));
        try {
            Galaxy.assignOrbit(bodies, "Core", "Pulsar");
            assert false;
        } catch (ClassCastException e) {
            assertEquals("Core is not a Planet", e.getMessage());
        }
    }

    @Test
    void testAssignOrbitMissing() {
        bodies.add(new MainSequenceStar("Sun", 1.989e30, 3.8e26, 5800));
        assertThrows(NoSuchElementException.class, () ->
                Galaxy.assignOrbit(bodies, "Neptune", "Sun"));
        try {
            Galaxy.assignOrbit(bodies, "Neptune", "Sun");
            assert false;
        } catch (NoSuchElementException e) {
            assertEquals("Body Neptune not found", e.getMessage());
        }
    }

    @Test
    void testCompareMass() {
        bodies.add(new MainSequenceStar("Sun", 1.989e30, 3.8e26, 5800));
        bodies.add(new GasGiant("Jupiter", 1.898e27, (Star) bodies.get(0), 79));
        assertEquals("Sun is heavier", Galaxy.compareMass(bodies, "Sun", "Jupiter"));
        assertFalse(
                Galaxy.compareMass(bodies, "Jupiter", "Sun")
                        .equalsIgnoreCase("Jupiter is heavier")
        );
        bodies.add(new TerrestrialPlanet("Equal", 1.898e27, (Star) bodies.get(0), false));
        assertEquals("Equal mass", Galaxy.compareMass(bodies, "Jupiter", "Equal"));
    }

    @Test
    void testFindBody() {
        bodies.add(new MainSequenceStar("Sun", 1.989e30, 3.8e26, 5800));
        assertDoesNotThrow(() -> {
            CelestialBody found = Galaxy.findBody(bodies, "Sun");
            assertEquals("Sun", found.getName());
        });
        assertThrows(NoSuchElementException.class, () ->
                Galaxy.findBody(bodies, "Pluto"));
    }

    @Test
    void testStoreToFile() {
        assertDoesNotThrow(() -> {
            bodies.add(new MainSequenceStar("Sun", 1.989e30, 3.8e26, 5800));
            bodies.add(new NeutronStar("Pulsar", 2.8e30, 1.2e24, 716));
            bodies.add(new GasGiant("Jupiter", 1.898e27, new MainSequenceStar("Sun", 1.989e30, 3.8e26, 5800), 79));
            bodies.add(new TerrestrialPlanet("Earth", 5.972e24, new MainSequenceStar("Sun", 1.989e30, 3.8e26, 5800), true));
            Galaxy.storeToFile(bodies);

            BufferedReader br = new BufferedReader(new FileReader("galaxy.csv"));
            assertEquals("MainSequenceStar,Sun,1.989E30,3.8E26,5800.0,0.0,null,false,0", br.readLine());
            assertEquals("NeutronStar,Pulsar,2.8E30,1.2E24,0.0,716.0,null,false,0", br.readLine());
            assertEquals("GasGiant,Jupiter,1.898E27,0.0,0.0,0.0,Sun,false,79", br.readLine());
            assertEquals("TerrestrialPlanet,Earth,5.972E24,0.0,0.0,0.0,Sun,true,0", br.readLine());
            assertNull(br.readLine());
        });
    }

    @Test
    void testRetrieveFromFile() {
        assertDoesNotThrow(() -> {
            bodies.add(new MainSequenceStar("Sun", 1.989e30, 3.8e26, 5800));
            bodies.add(new NeutronStar("Pulsar", 2.8e30, 1.2e24, 716));
            Galaxy.storeToFile(bodies);
            bodies.clear();
            Galaxy.loadFromFile(bodies);
            assertEquals(2, bodies.size());
            assertEquals("Sun", bodies.get(0).getName());
            assertEquals("Pulsar", bodies.get(1).getName());
        });
    }
}
