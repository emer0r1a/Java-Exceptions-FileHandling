package Adoption_Answer;

import java.io.*;
import java.util.*;

public class Main {
    //implement static methods

    public static void adoptAnimal(List<Animal> animals, List<Person> people, String customerName, String animalName) {
        boolean anExist = false, perExist = false;
        int ind = -1;

        for(Animal a : animals) {
            if(a.getName().equalsIgnoreCase(animalName)) {
                anExist = true;
                for(Person p : people) {
                    if(p.getName().equalsIgnoreCase(customerName)) {
                        if(p.getAge() < 18) throw new IllegalStateException(customerName+" is underaged to adopt");
                        ind = animals.indexOf(a);
                        perExist = true;
                    }
                }
            }
        }

        if(anExist && perExist) animals.remove(ind);
        if(!anExist) throw new NoSuchElementException("Animal "+animalName+" not found");
        if(!perExist) throw new NoSuchElementException(customerName +" not found");

    }

    public static String interact(List<Person> people, String person1, String person2) {
        boolean exist1 = false, exist2 = false;

        for(Person p : people) {
            if(p.getName().equalsIgnoreCase(person1)) exist1 = true;
            else if(p.getName().equalsIgnoreCase(person2)) exist2 = true;
        }

        if(exist1 && exist2) return "Hello "+person2+"! I'm "+person1+" from the adoption center.";
        else if(exist2) throw new NoSuchElementException(person1+" does not exist");
        else throw new NoSuchElementException(person2+" does not exist");
    }

    public static void storeToFile(List<Animal> animals) {
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter("animals.txt"));
            String line;
            for(Animal a : animals) {
                if(a instanceof Dog d) {
                    bw.write(d.toCSV());
                    bw.newLine();
                }
                else if(a instanceof Cat c) {
                    bw.write(c.toCSV());
                    bw.newLine();
                }
            }
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void retrieveFromFile(List<Animal> animals) {
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader("animals.txt"));
            String line;
            while((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if(parts[0].equalsIgnoreCase("Dog")) animals.add(new Dog(parts[1],
                        Integer.parseInt(parts[2]), Double.parseDouble(parts[3]), Boolean.parseBoolean(parts[4])));
                else if (parts[0].equalsIgnoreCase("Cat")) {
                    animals.add(new Cat(parts[1], Integer.parseInt(parts[2]), Double.parseDouble(parts[3]), Boolean.parseBoolean(parts[4])));
                }
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
