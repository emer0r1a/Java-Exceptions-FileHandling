You are tasked to implement a University Enrollment System that manages People, Students, Professors, and Courses.
Each class must enforce validation rules, throw appropriate exceptions, and support data storage and retrieval via files.

# The Classes

## Person

All Person objects have:

`private final String name`

`private int age`

Constructor Validation:

name cannot be empty

age cannot be negative — throw `IllegalArgumentException("Age must be non-negative")`

Methods:

`public String getName()`

`public int getAge()`

`public void birthday()` → increments age and prints "Happy birthday, [name]!"

`public abstract void performTask()` → implemented by subclasses

`public String toString()` → returns name + " (" + age + ")"

## Professor

Extends Person and adds:

`private double salary`

`private final String department`

Constructor Validation:

Salary must be **≥ 40,000.0** → otherwise throw `IllegalArgumentException("Salary must be at least 40000")`

Department cannot be empty → otherwise throw IllegalArgumentException("Department required")

Methods:

`public double getSalary()`

`protected void setSalary(double salary)` → must check the same 40,000.0 rule

`public String getDepartment()`

`public void performTask()` → prints: "[name] is teaching in [department]"

## Student

Extends Person and adds:

`private String studentId`

`private double gpa`

`private Professor advisor` (can be null)

Constructor Validation:

GPA must be between 0.0 and 4.0 inclusive

studentId cannot be empty

Methods:

`public String getStudentId()`

`public double getGpa()`

`protected void setGpa(double gpa)` → validate range 0–4

`public Professor getAdvisor()`

`protected void setAdvisor(Professor prof)`

If advisor is **already set**, throw `IllegalStateException("[name] already has an advisor: [advisor name]")`

Extra Behavior:

`public void removeAdvisor()` → sets advisor to null

`public void performTask()` → prints **"[name] is studying"**

## Course

Represents a class offered by a professor.

Fields:

`private final String code`

`private final String title`

`private Professor instructor`

`private List<Student> enrolled`

Constructor Validation:

code and title must not be empty

instructor cannot be null

Methods:

`public String getCode()`

`public String getTitle()`

`public Professor getInstructor()`

`public void enroll(Student student)`

If student already enrolled, print **"Student already enrolled"**

Otherwise add to list and print "[student name] has enrolled in [code]"

`public void drop(Student student)` → removes from list

`public List<Student> getEnrolled()`

## Class: University

Contains static management methods to handle logic involving Person, Course, and File I/O.

`public static void assignAdvisor(List<Person> people, String studentName, String professorName)`

Find both names in the list.

If not found → throw `NoSuchElementException("[name] does not exist")`

If either is not the correct type → throw `ClassCastException("[name] is not a Student/Professor")`

If valid, set professor as the student’s advisor using the setter.

`public static void enrollStudent(List<Course> courses, List<Person> people, String studentName, String courseCode)`

Find course by code and student by name.

Validate both; throw `NoSuchElementException` or `ClassCastException` when appropriate.

Call course.enroll(student)

`public static void storePeopleToFile(List<Person> people)`

Writes to people.csv

Columns: Type,Name,Age,Extra...

Student → Student,Name,Age,GPA,AdvisorName(or NONE)

Professor → Professor,Name,Age,Department,Salary

`public static void loadPeopleFromFile(List<Person> people)`

Reads from people.csv

Recreates Person objects and adds them to list.

If file not found → throw `FileNotFoundException("people.csv not found")`

`public static void storeCoursesToFile(List<Course> courses)`

Writes to courses.txt

Each line: Code,Title,InstructorName,EnrolledCount