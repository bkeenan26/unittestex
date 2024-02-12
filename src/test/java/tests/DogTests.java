package tests;

import org.junit.jupiter.api.*;
import animals.AnimalType;
import animals.petstore.pet.attributes.Breed;
import animals.petstore.pet.attributes.Gender;
import animals.petstore.pet.attributes.Skin;
import animals.petstore.pet.types.Dog;

import static org.junit.jupiter.api.Assertions.assertEquals;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DogTests {

    private static Dog actualDog;
    @BeforeAll
    public static void createAnimals()
    {
        actualDog = new Dog(AnimalType.DOMESTIC, Skin.FUR, Gender.UNKNOWN, Breed.UNKNOWN);
    }


    @Test
    @Order(1)
    @DisplayName("Animal Test Type Tests Domestic")
    public void animalTypeTests()
    {
        assertEquals(AnimalType.DOMESTIC, actualDog.getAnimalType(), "Animal Type Expected[" + AnimalType.DOMESTIC
            + "] Actual[" + actualDog.getAnimalType() + "]");
    }

    @Test
    @Order(1)
    @DisplayName("Dog Speak Woof Tests")
    public void dogGoesWoffTest()
    {
        assertEquals("The dog goes woof! woof!", actualDog.speak(), "I was expecting woof! woof!");
    }

    @Test
    @Order(1)
    @DisplayName("Dog Fur is it Hyperallergetic")
    public void dogHyperAllergeticTests()
    {
        assertEquals("The dog is not hyperallergetic!", actualDog.dogHypoallergenic(),
                "The dog is not hyperallergetic!");
    }

    @Test
    @Order(1)
    @DisplayName("Dog has legs Test")
    public void legTests()
    {
        Assertions.assertNotNull(actualDog.getNumberOfLegs());
    }

    @Test
    @Order(2)
    @DisplayName("Dog Gender Test Male")
    public void genderTestMale()
    {
        actualDog = new Dog(AnimalType.WILD, Skin.UNKNOWN,Gender.MALE, Breed.UNKNOWN);
        assertEquals(Gender.MALE, actualDog.getGender(), "Expecting Male Gender!");
    }

    @Test
    @Order(2)
    @DisplayName("Dog Breed Test Maltese")
    public void genderDogBreed() {
        actualDog = new Dog(AnimalType.WILD, Skin.UNKNOWN,Gender.FEMALE, Breed.MALTESE);
        assertEquals(Breed.MALTESE, actualDog.getBreed(), "Expecting Breed Maltese!");
    }

    @Test
    @Order(2)
    @DisplayName("Dog Speak Grr Tests")
    public void dogGoesGrrTest()
    {
        actualDog = new Dog(AnimalType.WILD, Skin.UNKNOWN,Gender.UNKNOWN, Breed.UNKNOWN);
        assertEquals("The dog goes Grr! Grr!", actualDog.speak(), "I was expecting Grr");
    }

    @Test
    @Order(2)
    @DisplayName("Dog Speak Bark Tests 1")
    public void dogGoesBarkTest()
    {
        actualDog = new Dog(AnimalType.UNKNOWN, Skin.UNKNOWN,Gender.UNKNOWN, Breed.UNKNOWN);
        assertEquals("The dog goes Bark! Bark!", actualDog.speak(), "I was expecting Bark");
    }
}
