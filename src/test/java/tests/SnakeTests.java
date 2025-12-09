package tests;

import org.junit.jupiter.api.*;
import animals.AnimalType;
import animals.petstore.pet.attributes.Breed;
import animals.petstore.pet.attributes.Gender;
import animals.petstore.pet.attributes.Skin;
import animals.petstore.pet.types.Snake;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SnakeTests {

    private static Snake actualSnake;

    @BeforeAll
    public static void createAnimals()
    {
        actualSnake = new Snake(AnimalType.DOMESTIC, Skin.FUR, Gender.UNKNOWN, Breed.UNKNOWN);
    }


    @Test
    @Order(1)
    @DisplayName("Animal Test Type Tests Domestic")
    public void animalTypeTests()
    {
        assertEquals(AnimalType.DOMESTIC, actualSnake.getAnimalType(), "Animal Type Expected[" + AnimalType.DOMESTIC
                + "] Actual[" + actualSnake.getAnimalType() + "]");
    }

    @Test
    @Order(1)
    @DisplayName("Snake Speak ssss Tests")
    public void SnakeGoesMeowTest()
    {
        assertEquals("The Snake goes ssss! ssss!", actualSnake.speak(), "I was expecting ssss!");
    }

    @Test
    @Order(1)
    @DisplayName("Snake Scale is it Hyperallergetic")
    public void SnakeHyperAllergeticTests()
    {
        assertEquals("The Snake is not hyperallergetic!", actualSnake.SnakeHypoallergenic(),
                "The Snake is hyperallergetic!");
    }

    @Test
    @Order(1)
    @DisplayName("Snake has 0 legs Test")
    public void legTests()
    {
        Assertions.assertNotNull(actualSnake.getNumberOfLegs());
    }

    @Test
    @Order(2)
    @DisplayName("Snake Gender Test FeMale")
    public void genderTestFeMale()
    {
        actualSnake = new Snake(AnimalType.WILD, Skin.UNKNOWN,Gender.FEMALE, Breed.UNKNOWN);
        assertEquals(Gender.FEMALE, actualSnake.getGender(), "Expecting Female Gender!");
    }

    @Test
    @Order(2)
    @DisplayName("Snake Breed Test COPPERHEAD")
    public void genderSnakeBreed()
    {
        actualSnake = new Snake(AnimalType.WILD, Skin.UNKNOWN,Gender.FEMALE, Breed.COPPERHEAD);
        assertEquals(Breed.COPPERHEAD, actualSnake.getBreed(), "Expecting COPPERHEAD!");
    }

    @Test
    @Order(2)
    @DisplayName("Snake Speak hsss Tests")
    public void dogGoesGrrTest()
    {
        actualSnake = new Snake(AnimalType.WILD, Skin.UNKNOWN,Gender.UNKNOWN, Breed.UNKNOWN);
        assertEquals("The Snake goes hsss! hsss!", actualSnake.speak(), "I was expecting hsss");
    }

    @Test
    @Order(2)
    @DisplayName("Snake Speak Psss Tests")
    public void SnakeGoesPrrTest()
    {
        actualSnake = new Snake(AnimalType.UNKNOWN, Skin.UNKNOWN,Gender.UNKNOWN, Breed.UNKNOWN);
        assertEquals("The Snake goes Psss! Psss!", actualSnake.speak(), "I was expecting Psss");
    }
}
