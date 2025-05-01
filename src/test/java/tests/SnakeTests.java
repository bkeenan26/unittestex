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
    public static void createAnimals() {
        actualSnake = new Snake(AnimalType.DOMESTIC, Skin.SCALES, Gender.UNKNOWN, Breed.UNKNOWN);
    }

    @Test
    @Order(1)
    @DisplayName("Animal Test Type Tests Domestic")
    public void animalTypeTests() {
        assertEquals(AnimalType.DOMESTIC, actualSnake.getAnimalType(),
                "Animal Type Expected[" + AnimalType.DOMESTIC + "] Actual[" + actualSnake.getAnimalType() + "]");
    }

    @Test
    @Order(1)
    @DisplayName("Snake Speak Hiss Tests")
    public void snakeGoesHissTest() {
        assertEquals("The snake goes hisss! hisss!", actualSnake.speak(),
                "I was expecting hisss! hisss!");
    }

    @Test
    @Order(1)
    @DisplayName("Snake Scales is it Hyperallergetic")
    public void snakeHyperAllergeticTests() {
        assertEquals("The snake is hyperallergetic!", actualSnake.snakeHypoallergenic(),
                "Expected snake to be hyperallergetic!");
    }

    @Test
    @Order(1)
    @DisplayName("Snake has legs Test")
    public void legTests() {
        Assertions.assertEquals(0, actualSnake.getNumberOfLegs(), "Snakes should have 0 legs.");
    }

    @Test
    @Order(2)
    @DisplayName("Snake Gender Test Male")
    public void genderTestMale() {
        actualSnake = new Snake(AnimalType.WILD, Skin.UNKNOWN, Gender.MALE, Breed.UNKNOWN);
        assertEquals(Gender.MALE, actualSnake.getGender(), "Expecting Male Gender!");
    }

    @Test
    @Order(2)
    @DisplayName("Snake Breed Test Python")
    public void snakeBreedTest() {
        actualSnake = new Snake(AnimalType.WILD, Skin.UNKNOWN, Gender.FEMALE, Breed.BALL_PYTHON);
        assertEquals(Breed.BALL_PYTHON, actualSnake.getBreed(), "Expecting Breed Python!");
    }

    @Test
    @Order(2)
    @DisplayName("Snake Speak Sss Tests")
    public void snakeGoesSssTest() {
        actualSnake = new Snake(AnimalType.WILD, Skin.UNKNOWN, Gender.UNKNOWN, Breed.UNKNOWN);
        assertEquals("The snake goes ssssss!", actualSnake.speak(), "I was expecting ssssss!");
    }

    @Test
    @Order(2)
    @DisplayName("Snake Speak Bark Tests 1")
    public void snakeGoesBarkTest() {
        actualSnake = new Snake(AnimalType.UNKNOWN, Skin.UNKNOWN, Gender.UNKNOWN, Breed.UNKNOWN);
        assertEquals("The snake goes Bark! Bark!", actualSnake.speak(), "I was expecting Bark!");
    }
}
