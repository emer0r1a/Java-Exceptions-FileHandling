package Adoption_Answer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class AdoptionTest {

    List<Adoption_Answer.Animal> animals;
    List<Adoption_Answer.Person> people;

    @BeforeEach
    void setup() {
        animals = new ArrayList<>();
        animals.add(new Adoption_Answer.Dog("Buddy", 3, 1500, true));
        animals.add(new Adoption_Answer.Cat("Mittens", 0, 900, true));
        animals.add(new Adoption_Answer.Dog("Rex", 1, 1000, false));

        people = new ArrayList<>();
        people.add(new Adoption_Answer.Person("Alice", 23));
        people.add(new Person("Bob", 17));
    }

    // -------------------------------
    // Animal Creation Tests
    // -------------------------------
    @Test
    void validDogCreation() {
        Adoption_Answer.Dog d = new Adoption_Answer.Dog("Rocky", 2, 1200, true);
        assertEquals("Dog", d.getType());
        assertTrue(d.toString().contains("Trained=true"));
    }

    @Test
    void invalidDogUnderageTrained() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new Adoption_Answer.Dog("Pup", 0, 800, true);
        });
        assertEquals("Trained dog must be at least 1 year old", e.getMessage());
    }

    @Test
    void invalidCatKittenOutdoor() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new Adoption_Answer.Cat("Tiny", 0, 700, false);
        });
        assertEquals("Kittens must be indoor cats", e.getMessage());
    }

    @Test
    void invalidNegativeAge() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new Dog("Ghost", -1, 500, false);
        });
        assertEquals("Age must be non-negative", e.getMessage());
    }

    @Test
    void invalidLowFee() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new Cat("Whiskers", 2, 50, true);
        });
        assertEquals("Adoption fee must be at least 100", e.getMessage());
    }

    // -------------------------------
    // Person & Adoption Tests
    // -------------------------------
    @Test
    void successfulAdoptionRemovesAnimal() {
        Adoption_Answer.Main.adoptAnimal(animals, people, "Alice", "Buddy");
        assertEquals(2, animals.size());
        assertTrue(animals.stream().noneMatch(a -> a.getName().equals("Buddy")));
    }

    @Test
    void underageAdopterThrowsError() {
        Exception e = assertThrows(IllegalStateException.class, () -> {
            Adoption_Answer.Main.adoptAnimal(animals, people, "Bob", "Rex");
        });
        assertEquals("Bob is underaged to adopt", e.getMessage());
    }

    @Test
    void missingAnimalThrowsError() {
        Exception e = assertThrows(NoSuchElementException.class, () -> {
            Adoption_Answer.Main.adoptAnimal(animals, people, "Alice", "Ghost");
        });
        assertEquals("Animal Ghost not found", e.getMessage());
    }

    @Test
    void missingPersonThrowsError() {
        Exception e = assertThrows(NoSuchElementException.class, () -> {
            Adoption_Answer.Main.adoptAnimal(animals, people, "Charlie", "Buddy");
        });
        assertEquals("Charlie not found", e.getMessage());
    }

    // -------------------------------
    // Interaction Tests
    // -------------------------------
    @Test
    void validInteraction() {
        String msg = Adoption_Answer.Main.interact(people, "Alice", "Bob");
        assertEquals("Hello Bob! I'm Alice from the adoption center.", msg);
    }

    @Test
    void invalidInteractionMissingPerson() {
        Exception e = assertThrows(NoSuchElementException.class, () -> {
            Adoption_Answer.Main.interact(people, "Alice", "Charlie");
        });
        assertEquals("Charlie does not exist", e.getMessage());
    }

    // -------------------------------
    // File Storage Tests
    // -------------------------------
    @Test
    void storeAndRetrieveFromFile() {
        Adoption_Answer.Main.storeToFile(animals);
        List<Animal> reloaded = new ArrayList<>();
        Main.retrieveFromFile(reloaded);
        assertEquals(animals.size(), reloaded.size());
        assertTrue(reloaded.get(0).toCSV().contains("Dog"));
    }

    @AfterEach
    void cleanup() {
        File f = new File("animals.txt");
        if (f.exists()) f.delete();
    }
}