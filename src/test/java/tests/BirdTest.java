package tests;

import animals.AnimalType;
import animals.petstore.pet.attributes.Breed;
import animals.petstore.pet.attributes.Gender;
import animals.petstore.pet.attributes.Skin;
import animals.petstore.pet.types.Bird;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BirdTest {

    @Test
    @DisplayName("Constructor without cost defaults to 0")
    void testConstructorWithoutCost() {
        Bird bird = new Bird(AnimalType.DOMESTIC, Skin.FEATHERS, Gender.MALE, Breed.SPARROW);
        assertEquals(BigDecimal.ZERO, bird.getCost());
        assertEquals(AnimalType.DOMESTIC, bird.getAnimalType());
        assertEquals(Skin.FEATHERS, bird.getSkinType());
        assertEquals(Gender.MALE, bird.getGender());
        assertEquals(Breed.SPARROW, bird.getBreed());
        assertEquals(2, bird.getNumberOfLegs());
    }

    @Test
    @DisplayName("Constructor with cost sets value")
    void testConstructorWithCost() {
        BigDecimal expectedCost = new BigDecimal("150.00");
        Bird bird = new Bird(AnimalType.WILD, Skin.FEATHERS, Gender.FEMALE, Breed.HAWK, expectedCost);
        assertEquals(expectedCost, bird.getCost());
        assertEquals(AnimalType.WILD, bird.getAnimalType());
        assertEquals(Gender.FEMALE, bird.getGender());
        assertEquals(Breed.HAWK, bird.getBreed());
    }

    @Test
    @DisplayName("Constructor with cost and petStoreId sets values")
    void testConstructorWithCostAndId() {
        Bird bird = new Bird(AnimalType.DOMESTIC, Skin.FEATHERS, Gender.UNKNOWN, Breed.ROBIN,
                new BigDecimal("75.00"), 42);
        assertEquals(new BigDecimal("75.00"), bird.getCost());
        assertEquals(42, bird.getPetStoreId());
        assertEquals(Breed.ROBIN, bird.getBreed());
    }

    @Test
    @DisplayName("Speak method covers DOMESTIC branch")
    void testSpeakDomestic() {
        Bird bird = new Bird(AnimalType.DOMESTIC, Skin.FEATHERS, Gender.UNKNOWN, Breed.UNKNOWN);
        assertEquals("The bird goes tweet!", bird.speak());
    }

    @Test
    @DisplayName("Speak method covers WILD branch")
    void testSpeakWild() {
        Bird bird = new Bird(AnimalType.WILD, Skin.FEATHERS, Gender.UNKNOWN, Breed.UNKNOWN);
        assertEquals("The bird goes screech!", bird.speak());
    }


    @Test
    @DisplayName("Fly method returns expected string")
    void testFly() {
        Bird bird = new Bird(AnimalType.DOMESTIC, Skin.FEATHERS, Gender.UNKNOWN, Breed.UNKNOWN);
        assertEquals("The bird spreads its wings and flies away!", bird.fly());
    }

    @Test
    @DisplayName("Hypoallergenic method replaces pet with bird")
    void testBirdHypoallergenic() {
        Bird bird = new Bird(AnimalType.DOMESTIC, Skin.FEATHERS, Gender.UNKNOWN, Breed.UNKNOWN);
        String result = bird.birdHypoallergenic();
        assertTrue(result.contains("bird"), "Expected hypoallergenic string to mention bird");
    }

    @Test
    @DisplayName("Getter and setter for numberOfLegs")
    void testLegsGetterSetter() {
        Bird bird = new Bird(AnimalType.DOMESTIC, Skin.FEATHERS, Gender.UNKNOWN, Breed.UNKNOWN);
        bird.setNumberOfLegs(1);
        assertEquals(1, bird.getNumberOfLegs());
    }

    @Test
    @DisplayName("TypeOfPet returns BIRD")
    void testTypeOfPet() {
        Bird bird = new Bird(AnimalType.DOMESTIC, Skin.FEATHERS, Gender.UNKNOWN, Breed.UNKNOWN);
        String result = bird.typeOfPet();
        System.out.println(result);
        assertEquals("The type of pet is BIRD!", result);
    }

    @Test
    @DisplayName("SkinType getter returns FEATHERS")
    void testGetSkinType() {
        Bird bird = new Bird(AnimalType.WILD, Skin.FEATHERS, Gender.MALE, Breed.CARDINAL);
        Skin skin = bird.getSkinType();
        System.out.println(skin);
        assertEquals(Skin.FEATHERS, skin);
    }


    @Test
    @DisplayName("ToString contains all details")
    void testToString() {
        Bird bird = new Bird(AnimalType.WILD, Skin.FEATHERS, Gender.MALE, Breed.CARDINAL,
                new BigDecimal("200.00"), 5);
        String result = bird.toString();
        assertTrue(result.contains("The bird is WILD!"));
        assertTrue(result.contains("The bird breed is CARDINAL!"));
        assertTrue(result.contains("Birds have 2 legs!"));
        assertTrue(result.contains("flies away!"));
    }


}