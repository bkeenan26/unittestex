package tests;

import animals.AnimalType;
import animals.petstore.pet.attributes.Breed;
import animals.petstore.pet.attributes.Gender;
import animals.petstore.pet.attributes.Skin;
import animals.petstore.pet.types.Snake;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SnakeTests {

    private static Snake aliveSnake;

    @BeforeAll
    public static void createAnimals() {
        aliveSnake = new Snake(AnimalType.DOMESTIC, Skin.SCALES, Gender.UNKNOWN, Breed.UNKNOWN);
    }

    @Test
    @Order(1)
    @DisplayName("Animal Test Type Tests Domestic")
    public void animalTypeTests()
    {
        assertEquals(AnimalType.DOMESTIC, aliveSnake.getAnimalType(), "Animal Type Expected[" + AnimalType.DOMESTIC
                + "] Actual[" + aliveSnake.getAnimalType() + "]");
    }

    @Test
    @Order(1)
    @DisplayName("Snake Speak pss Tests")
    public void snakeGoesPssTest() {
        assertEquals("The snake goes pss! pss!", aliveSnake.speak(), "I was expecting pss");
    }

    @Test
    @Order(1)
    @DisplayName("Snake has NO legs Test")
    public void legTests() {
        Assertions.assertNotNull(aliveSnake.getNumberOfLegs());
    }

    @Test
    @Order(2)
    @DisplayName("Snake Breed Test MILK")
    public void genderSnakeBreed() {
        aliveSnake = new Snake(AnimalType.WILD, Skin.SCALES, Gender.FEMALE, Breed.MILK);
        assertEquals(Breed.MILK, aliveSnake.getBreed(), "Expecting Breed Milk!");
    }

    @Test
    @Order(2)
    @DisplayName("Snake Speak Hiss Tests")
    public void snakeGoesHissTest() {
        aliveSnake = new Snake(AnimalType.WILD, Skin.SCALES, Gender.UNKNOWN, Breed.UNKNOWN);
        assertEquals("The snake goes hiss! hiss!", aliveSnake.speak(), "I was expecting hiss");
    }

    @Test
    @Order(2)
    @DisplayName("Snake Speak Pss Tests")
    public void snakeGoesPsssTest() {
        aliveSnake = new Snake(AnimalType.UNKNOWN, Skin.UNKNOWN, Gender.UNKNOWN, Breed.UNKNOWN);
        assertEquals("The snake goes Psss! Psss!", aliveSnake.speak(), "I was expecting Psss");
    }
}
