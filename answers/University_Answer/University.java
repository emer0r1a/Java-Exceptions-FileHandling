package University_Answer;

import java.io.*;
import java.util.List;
import java.util.NoSuchElementException;

public class University {
    public static void assignAdvisor(List<Person> people, String studentName, String professorName) {
        boolean studFound = false, profFound = false;
        Student s = null;
        Professor pr = null;

        for(Person p : people) {
            if(p.getName().equalsIgnoreCase(studentName)) {
                if(p instanceof Student) {
                    studFound = true;
                    s = (Student) p;
                }
                else throw new ClassCastException(studentName+" is not a Student");
            }
            else if(p.getName().equalsIgnoreCase(professorName)) {
                if(p instanceof Professor) {
                    profFound = true;
                    pr = (Professor) p;
                }
                else throw new ClassCastException(professorName+" is not a Professor");
            }
        }

        if(!studFound) throw new NoSuchElementException(studentName+" does not exist");
        else if(!profFound) throw new NoSuchElementException(professorName+" does not exist");
        s.setAdvisor(pr);
    }

    public static void enrollStudent(List<Course> courses, List<Person> people, String studentName, String courseCode) {
        boolean studFound = false, courFound = false;
        Student s = null;
        Course co = null;

        for(Person p : people) {
            if(p.getName().equalsIgnoreCase(studentName)) {
                if(p instanceof Student) {
                    studFound = true;
                    s = (Student) p;
                }
                else throw new ClassCastException(studentName+" is not a Student");
            }
        }

        for(Course c : courses) {
            if(c.getCode().equalsIgnoreCase(courseCode)) {
                courFound = true;
                co = (Course) c;
            }
        }

        if(!studFound) throw new NoSuchElementException(studentName+" does not exist");
        else if(!courFound) throw new NoSuchElementException(courseCode+" does not exist");
        co.enroll(s);
    }

    public static void storePeopleToFile(List<Person> people) {
        BufferedWriter bw;

        try {
            bw = new BufferedWriter(new FileWriter("people.csv"));

            for(Person p : people) {
                if(p instanceof Student s) {
                    bw.write("Student,"+s.getName()+","+s.getAge()+","+s.getStudentID()+","+ s.getGpa()+","+
                            (s.getAdvisor() == null ? "NONE" : s.getAdvisor().getName()));
                    bw.newLine();
                }
                else {
                    if (p instanceof Professor pr) {
                        bw.write("Professor,"+pr.getName()+","+pr.getAge()+","+pr.getDepartment()+","+
                                String.format("%.1f",pr.getSalary()));
                        bw.newLine();
                    }
                }
            }
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void loadPeopleFromFile(List<Person> people) throws FileNotFoundException {
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader("people.csv"));
            String line;
            while((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if(parts[0].equalsIgnoreCase("Student")) {
                    people.add(new Student(parts[1], Integer.parseInt(parts[2]), parts[3], Double.parseDouble(parts[4])));
                } else if(parts[0].equalsIgnoreCase("Professor")) {
                    people.add(new Professor(parts[1], Integer.parseInt(parts[2]), parts[3], Double.parseDouble(parts[4])));
                }
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void storeCoursesToFile(List<Course> courses) {
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter("courses.txt"));
            for (Course c : courses) {
                bw.write(c.getCode()+","+ c.getTitle()+","+c.getInstructor().getName()+","+c.getEnrolled().size());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
