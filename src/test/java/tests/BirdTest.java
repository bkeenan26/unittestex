package tests;

import animals.AnimalType;
import animals.petstore.pet.attributes.Breed;
import animals.petstore.pet.attributes.Gender;
import animals.petstore.pet.attributes.PetType;
import animals.petstore.pet.attributes.Skin;
import animals.petstore.pet.types.Bird;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class BirdTest {

    @Test
    public void testBirdCreation() {
        Bird bird = new Bird(AnimalType.DOMESTIC, Skin.FEATHERS, Gender.FEMALE, Breed.BLUE_JAY, new BigDecimal("150.00"), 1);

        assertEquals(Breed.BLUE_JAY, bird.getBreed());
        assertEquals(Gender.FEMALE, bird.getGender());
        assertEquals(Skin.FEATHERS, bird.getSkinType());
        assertEquals(AnimalType.DOMESTIC, bird.getAnimalType());
        assertEquals(PetType.BIRD, bird.getPetType());
        assertEquals(1, bird.getPetStoreId());
        assertEquals(2, bird.getNumberOfLegs());
    }

    @Test
    public void testBirdSpeak() {
        Bird domesticBird = new Bird(AnimalType.DOMESTIC, Skin.FEATHERS, Gender.MALE, Breed.CARDINAL, BigDecimal.ZERO, 2);
        Bird wildBird = new Bird(AnimalType.WILD, Skin.FEATHERS, Gender.FEMALE, Breed.SPARROW, BigDecimal.ZERO, 3);
        Bird unknownBird = new Bird(AnimalType.UNKNOWN, Skin.FEATHERS, Gender.UNKNOWN, Breed.HUMMING_BIRD, BigDecimal.ZERO, 4);

        assertEquals("The bird goes chirp! chirp!", domesticBird.speak());
        assertEquals("The bird goes chirp! chirp!", wildBird.speak());
        assertEquals("The bird goes chirp! chirp!", unknownBird.speak());
    }

    @Test
    public void testBirdHypoallergenic() {
        Bird birdFeathers = new Bird(AnimalType.DOMESTIC, Skin.FEATHERS, Gender.MALE, Breed.ROBIN, BigDecimal.ZERO, 5);
        Bird birdUnknownSkin = new Bird(AnimalType.DOMESTIC, Skin.UNKNOWN, Gender.FEMALE, Breed.PARROT, BigDecimal.ZERO, 6);

        assertEquals("The bird is not hyperallergetic!", birdFeathers.birdHypoallergenic());
        assertEquals("The pet skin is UNKNOWN at this time, so cannot determine if pet is hypoallergetic!", birdUnknownSkin.birdHypoallergenic());
    }

    @Test
    public void testBirdToString() {
        Bird bird = new Bird(AnimalType.DOMESTIC, Skin.FEATHERS, Gender.FEMALE, Breed.BALTIMORE_ORIOLE, new BigDecimal("200.00"), 7);
        String birdStr = bird.toString();

        assertTrue(birdStr.contains("The type of pet is BIRD"));
        assertTrue(birdStr.contains("The bird gender is FEMALE"));
        assertTrue(birdStr.contains("The bird pet store id is 7"));
        assertTrue(birdStr.contains("The bird breed is BALTIMORE_ORIOLE"));
        assertTrue(birdStr.contains("legs"));
    }
}
