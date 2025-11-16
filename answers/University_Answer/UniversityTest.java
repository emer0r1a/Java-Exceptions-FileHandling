package University_Answer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class UniversityTest {

    List<Person> people;
    List<Course> courses;

    @BeforeEach
    void setUp() {
        people = new ArrayList<>();
        courses = new ArrayList<>();
    }

    @AfterEach
    void tearDown() {
        people.clear();
        courses.clear();
    }

    // ----------------------------
    // 1. Constructor Validations
    // ----------------------------
    @Test
    void testPersonAgeInvalid() {
        assertThrows(IllegalArgumentException.class,
                () -> new Professor("Drake", -40, "Physics", 60000),
                "Age must be non-negative");
    }

    @Test
    void testProfessorSalaryInvalid() {
        assertThrows(IllegalArgumentException.class,
                () -> new Professor("Drake", 45, "Physics", 38000),
                "Salary must be at least 40000");
    }

    @Test
    void testStudentGpaInvalid() {
        assertThrows(IllegalArgumentException.class,
                () -> new Student("Caden", 19, "S001", 4.5),
                "GPA must be between 0.0 and 4.0");
    }

    // ----------------------------
    // 2. Valid Object Creation
    // ----------------------------
    @Test
    void testValidCreation() {
        assertDoesNotThrow(() -> {
            people.add(new Professor("Drake", 45, "Physics", 70000));
            people.add(new Professor("Mary", 51, "Mathematics", 85000));
            people.add(new Student("Caden", 19, "S001", 3.7));
            people.add(new Student("Aubrey", 21, "S002", 3.2));
        });

        assertEquals(4, people.size());

        assertAll(
                () -> assertEquals("Drake", people.get(0).getName()),
                () -> assertEquals(45, people.get(0).getAge()),
                () -> assertEquals("Mathematics", ((Professor) people.get(1)).getDepartment()),
                () -> assertEquals("S001", ((Student) people.get(2)).getStudentID()),
                () -> assertEquals(3.2, ((Student) people.get(3)).getGpa(), 0.001)
        );

        assertTrue(people.get(0) instanceof Person);
        assertTrue(people.get(2) instanceof Student);
        assertTrue(people.get(1) instanceof Professor);
        assertEquals("Professor(Name=Drake, Dept=Physics, Salary=70000.00)",
                people.get(0).toString());
        assertEquals("Student(Name=Caden, GPA=3.70, Advisor=NONE)",
                people.get(2).toString());
    }

    // ----------------------------
    // 3. Assign Advisor Logic
    // ----------------------------
    @Test
    void testAssignAdvisorValid() {
        people.add(new Professor("Drake", 45, "Physics", 70000));
        people.add(new Professor("Mary", 50, "Mathematics", 80000));
        people.add(new Student("Caden", 19, "S001", 3.7));
        people.add(new Student("Aubrey", 21, "S002", 3.2));

        assertDoesNotThrow(() -> {
            University.assignAdvisor(people, "Caden", "Drake");
            University.assignAdvisor(people, "Aubrey", "Mary");
        });

        Student caden = (Student) people.get(2);
        Student aubrey = (Student) people.get(3);

        assertEquals("Drake", caden.getAdvisor().getName());
        assertEquals("Mary", aubrey.getAdvisor().getName());
    }

    @Test
    void testAssignAdvisorAlreadyHas() {
        people.add(new Professor("Drake", 45, "Physics", 70000));
        people.add(new Student("Caden", 19, "S001", 3.7));

        assertDoesNotThrow(() -> University.assignAdvisor(people, "Caden", "Drake"));
        assertThrows(IllegalStateException.class,
                () -> University.assignAdvisor(people, "Caden", "Drake"));

        try {
            University.assignAdvisor(people, "Caden", "Drake");
            fail("Should be an exception");
        } catch (IllegalStateException e) {
            assertEquals("Caden already has an advisor: Drake", e.getMessage());
        }
    }

    @Test
    void testAssignAdvisorNotFound() {
        people.add(new Professor("Drake", 45, "Physics", 70000));
        people.add(new Student("Caden", 19, "S001", 3.7));

        assertThrows(NoSuchElementException.class,
                () -> University.assignAdvisor(people, "Aubrey", "Drake"));

        try {
            University.assignAdvisor(people, "Aubrey", "Drake");
            fail("Should be an exception");
        } catch (NoSuchElementException e) {
            assertEquals("Aubrey does not exist", e.getMessage());
        }
    }

    @Test
    void testAssignAdvisorWrongType() {
        people.add(new Professor("Drake", 45, "Physics", 70000));
        people.add(new Student("Caden", 19, "S001", 3.7));
        people.add(new Student("Aubrey", 21, "S002", 3.2));

        assertThrows(ClassCastException.class,
                () -> University.assignAdvisor(people, "Caden", "Aubrey"));

        try {
            University.assignAdvisor(people, "Caden", "Aubrey");
            fail("Should be an exception");
        } catch (ClassCastException e) {
            assertEquals("Aubrey is not a Professor", e.getMessage());
        }
    }

    // ----------------------------
    // 4. Enroll Student Logic
    // ----------------------------
    @Test
    void testEnrollStudentValid() {
        Professor prof = new Professor("Drake", 45, "Physics", 70000);
        Student student = new Student("Caden", 19, "S001", 3.7);
        Course astro = new Course("Astrophysics","PH101", prof);

        people.add(prof);
        people.add(student);
        courses.add(astro);

        assertDoesNotThrow(() -> University.enrollStudent(courses, people, "Caden", "PH101"));
        assertEquals(1, astro.getEnrolled().size());
        assertEquals("Caden", astro.getEnrolled().get(0).getName());
    }

    @Test
    void testEnrollStudentInvalidTypes() {
        Professor prof = new Professor("Drake", 45, "Physics", 70000);
        Student student = new Student("Caden", 19, "S001", 3.7);
        Course astro = new Course("PH101", "Astrophysics", prof);

        people.add(prof);
        people.add(student);
        courses.add(astro);

        assertThrows(NoSuchElementException.class,
                () -> University.enrollStudent(courses, people, "Aubrey", "PH101"));

        try {
            University.enrollStudent(courses, people, "Drake", "PH101");
            fail("Should be an exception");
        } catch (ClassCastException e) {
            assertEquals("Drake is not a Student", e.getMessage());
        }
    }

    // ----------------------------
    // 5. File Handling Tests
    // ----------------------------
    @Test
    void testStoreAndLoadPeople() {
        assertDoesNotThrow(() -> {
            people.add(new Professor("Drake", 45, "Physics", 70000));
            people.add(new Student("Caden", 19, "S001", 3.7));
            people.add(new Student("Aubrey", 21, "S002", 3.2));
            University.storePeopleToFile(people);

            BufferedReader br = new BufferedReader(new FileReader("people.csv"));
            assertEquals("Professor,Drake,45,Physics,70000.0", br.readLine());
            assertEquals("Student,Caden,19,S001,3.7,NONE", br.readLine());
            assertEquals("Student,Aubrey,21,S002,3.2,NONE", br.readLine());
            br.close();

            people.clear();
            University.loadPeopleFromFile(people);
            assertEquals(3, people.size());
            assertInstanceOf(Professor.class, people.get(0));
            assertInstanceOf(Student.class,people.get(1));
        });
    }

    @Test
    void testStoreAndLoadCourses() {
        assertDoesNotThrow(() -> {
            Professor prof = new Professor("Drake", 45, "Physics", 70000);
            Student student1 = new Student("Caden", 19, "S001", 3.7);
            Student student2 = new Student("Aubrey", 21, "S002", 3.2);
            Course astro = new Course("Astrophysics","PH101", prof);

            astro.enroll(student1);
            astro.enroll(student2);
            courses.add(astro);

            University.storeCoursesToFile(courses);
            BufferedReader br = new BufferedReader(new FileReader("courses.txt"));
            assertEquals("PH101,Astrophysics,Drake,2", br.readLine());
            br.close();
        });
    }
}

