You’re tasked to create a program that manages a pet adoption center, handling animals, staff, and customers.
It must include validation, interactions between objects, and file storage/retrieval.

# Class Descriptions

## Animal

Superclass (abstract)
Represents any adoptable pet.

Attributes:

`String name`

`int age`

`double adoptionFee`

Rules:

Age must be **non-negative**.

Adoption fee must be **≥ 100**.

`toCSV()` returns "AnimalType,name,age,adoptionFee"


Methods:

`String getType()` → abstract

`String toCSV()`

`String toString()` → "Dog(Name, Age=2, Fee=1200.0)"

## Dog (extends Animal)

`boolean trained`

Constructor validates that age ≥ 1 for trained dogs.

`getType()` → "Dog"

`toString()` → "Dog(Name, Age=3, Fee=1500.0, Trained=true)"

## Cat (extends Animal)

`boolean indoor`

Constructor validates that kittens (<1 year) must be indoor.

`getType()` → "Cat"

`toString()` → "Cat(Name, Age=0, Fee=900.0, Indoor=true)"

## Person

Represents either Customer or Staff.

Attributes:

`String name`

`int age`

Rules:

Age must be **≥ 18**.

No subclass here — it’s a base entity to check roles.

## Main

Contains all static management functions 

Methods to Implement:

`public static void adoptAnimal(List<Animal> animals, List<Person> people, String customerName, String animalName)`
Finds the customer and animal by name.

If animal not found → throw `NoSuchElementException("Animal <name> not found")`

If customer not found → throw `NoSuchElementException("<name> not found")`

If Person under 18 → `throw IllegalStateException("<name> is underaged to adopt")`

Removes the adopted animal from animals list.

`public static String interact(List<Person> people, String person1, String person2)`

If both people exist → return
"Hello <person2>! I'm <person1> from the adoption center."

If any name doesn’t exist → throw `NoSuchElementException("<name> does not exist")`

`public static void storeToFile(List<Animal> animals)`

Writes to "animals.txt" using format:

`Dog,Buddy,3,1500.0,true`

`Cat,Mittens,0,900.0,true`

`public static void retrieveFromFile(List<Animal> animals)`

Reads animals.txt and reconstructs objects accordingly.