package tests;

import animals.AnimalType;
import animals.petstore.pet.Pet;
import animals.petstore.pet.attributes.Breed;
import animals.petstore.pet.attributes.Gender;
import animals.petstore.pet.attributes.Skin;
import animals.petstore.pet.types.Bird;
import animals.petstore.pet.types.Cat;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BirdTests {

    private static Bird actualBird;

    @BeforeAll
    public static void createAnimals()
    {
        actualBird = new Bird(AnimalType.DOMESTIC, Skin.FEATHERS, Gender.MALE, Breed.CARDINAL,
            new BigDecimal("200.0"), 4);
    }

    @Test
    @Order(1)
    @DisplayName("Test Bird Constructor 1")
    public void testBirdConstructorOne()
    {
        // Arrange
        AnimalType type = AnimalType.DOMESTIC;
        Skin skin = Skin.FEATHERS;
        Gender gender = Gender.MALE;
        Breed breed = Breed.COPPERHEAD;

        // Act
        Bird bird = new Bird(type, skin, gender, breed);

        // Assert
        assertEquals(type, bird.getAnimalType());
        assertEquals(gender, bird.getGender());
        assertEquals(breed, bird.getBreed());
        assertEquals(new BigDecimal(0), bird.getCost());
    }

    @Test
    @Order(1)
    @DisplayName("Test Bird Constructor 2")
    public void testBirdConstructorTwo()
    {
        // Arrange
        AnimalType type = AnimalType.DOMESTIC;
        Skin skin = Skin.FEATHERS;
        Gender gender = Gender.MALE;
        Breed breed = Breed.COPPERHEAD;
        BigDecimal cost = new BigDecimal(123.0);
        int petStoreId = 0;

        // Act
        Bird bird = new Bird(type, skin, gender, breed, cost);

        // Assert
        assertEquals(type, bird.getAnimalType());
        assertEquals(gender, bird.getGender());
        assertEquals(breed, bird.getBreed());
        assertEquals(cost, bird.getCost());
        assertEquals(petStoreId, bird.getPetStoreId());
    }

    @Test
    @Order(1)
    @DisplayName("Animal Test Type Tests Domestic")
    public void animalTypeTests()
    {
        // Arrange, Act, Assert
        assertEquals(AnimalType.DOMESTIC, actualBird.getAnimalType(), "Animal Type Expected[" + AnimalType.DOMESTIC
                + "] Actual[" + actualBird.getAnimalType() + "]");
    }

    @Test
    @Order(1)
    @DisplayName("Bird Test is it Hyperallergetic")
    public void birdHyperAllergeticTests()
    {
        // Arrange, Act, Assert
        assertEquals("The bird is not hyperallergetic!", actualBird.birdHypoallergenic(),
                "The bird is not hyperallergetic!");
    }

    @Test
    @Order(1)
    @DisplayName("Bird has legs Test")
    public void legTests()
    {
        // Arrange, Act, Assert
        Assertions.assertNotNull(actualBird.getNumberOfLegs());
    }

    @Test
    @Order(2)
    @DisplayName("Bird Gender Test Female")
    public void genderTestFeMale()
    {
        // Arrange, Act, Assert
        actualBird = new Bird(AnimalType.DOMESTIC, Skin.FEATHERS, Gender.MALE, Breed.CARDINAL,
                new BigDecimal("200.0"), 4);
    }

    @Test
    @Order(2)
    @DisplayName("Bird Breed Test Cardinal")
    public void genderCatBreed()
    {
        // Arrange, Act, Assert
        actualBird = new Bird(AnimalType.DOMESTIC, Skin.FEATHERS, Gender.MALE, Breed.CARDINAL,
                new BigDecimal("200.0"), 4);
    }

    @Test
    @Order(2)
    @DisplayName("Bird Domestic Speak Test")
    public void birdDomesticSpeakTest()
    {
        // Arrange & Act
        actualBird = new Bird(AnimalType.DOMESTIC, Skin.FEATHERS, Gender.MALE, Breed.CARDINAL,
                new BigDecimal("200.0"), 4);

        // Assert
        assertEquals("The bird goes chirp! chirp!", actualBird.speak(), "I was expecting hiss");
    }

    @Test
    @Order(2)
    @DisplayName("Bird Wild Speak Test")
    public void birdWildSpeakest()
    {
        // Arrange & Act
        actualBird = new Bird(AnimalType.WILD, Skin.FEATHERS, Gender.MALE, Breed.CARDINAL,
                new BigDecimal("200.0"), 4);

        // Assert
        assertEquals("The bird goes grr! grr!", actualBird.speak(), "I was expecting Prr");
    }

    @Test
    @Order(2)
    @DisplayName("Bird Default Speak Test")
    public void birdDefaultSpeakest()
    {
        // Arrange & Act
        actualBird = new Bird(AnimalType.UNKNOWN, Skin.FEATHERS, Gender.MALE, Breed.CARDINAL,
                new BigDecimal("200.0"), 4);

        Pet bird = actualBird;

        // Assert
        assertEquals("The bird goes " + bird.getPetType().speak + "! " + bird.getPetType().speak + "!", actualBird.speak(), "I was expecting Prr");
    }

    @Test
    @Order(2)
    @DisplayName("Bird Test Setting Number of Legs")
    public void birdTestSetNumberLegs()
    {
        // Arrange
        actualBird = new Bird(AnimalType.UNKNOWN, Skin.FEATHERS, Gender.MALE, Breed.CARDINAL,
                new BigDecimal("200.0"), 4);

        // Act
        actualBird.setNumberOfLegs(4);

        // Assert
        assertEquals(4, actualBird.getNumberOfLegs());
    }
}