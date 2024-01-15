package tests;

import org.junit.jupiter.api.*;
import animals.AnimalType;
import animals.petstore.pet.attributes.Breed;
import animals.petstore.pet.attributes.Gender;
import animals.petstore.pet.attributes.Skin;
import animals.petstore.pet.types.Cat;

import static org.junit.jupiter.api.Assertions.assertEquals;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CatTests{
        private static Cat actualCat;
        @BeforeAll
        public static void createAnimals()
        {
            actualCat = new Cat(AnimalType.DOMESTIC, Skin.FUR, Gender.UNKNOWN, Breed.UNKNOWN);
        }


        @Test
        @Order(1)
        @DisplayName("Animal Test Type Tests Domestic")
        public void animalTypeTests()
        {
            assertEquals(AnimalType.DOMESTIC, actualCat.getAnimalType(), "Animal Type Expected[" + AnimalType.DOMESTIC
                    + "] Actual[" + actualCat.getAnimalType() + "]");
        }

        @Test
        @Order(1)
        @DisplayName("Cat Speak Prrr Tests")
        public void catGoesMeowTest()
        {
            assertEquals("The cat goes prr! prr!", actualCat.speak(), "I was expecting meow");
        }

        @Test
        @Order(1)
        @DisplayName("Cat Fur is it Hyperallergetic")
        public void catHyperAllergeticTests()
        {
            assertEquals("The cat is not hyperallergetic!", actualCat.catHyperAllergetic(),
                    "The cat is not hyperallergetic!");
        }
        @Test
        @Order(1)
        @DisplayName("Cat has legs Test")
        public void legTests()
        {
            Assertions.assertNotNull(actualCat.getNumberOfLegs());
        }

        @Test
        @Order(2)
        @DisplayName("Cat Gender Test FeMale")
        public void genderTestFeMale()
        {
            actualCat = new Cat(AnimalType.WILD, Skin.UNKNOWN,Gender.FEMALE, Breed.UNKNOWN);
            assertEquals(Gender.FEMALE, actualCat.getGender(), "Expecting Male Gender!");
        }

        @Test
        @Order(2)
        @DisplayName("Cat Breed Test BURMESE")
        public void genderCatBreed() {
        actualCat = new Cat(AnimalType.WILD, Skin.UNKNOWN,Gender.FEMALE, Breed.BURMESE);
        assertEquals(Breed.BURMESE, actualCat.getBreed(), "Expecting Breed Maltese!");
    }
        @Test
        @Order(2)
        @DisplayName("Cat Speak Hiss Tests")
        public void dogGoesGrrTest()
        {
            actualCat = new Cat(AnimalType.WILD, Skin.UNKNOWN,Gender.UNKNOWN, Breed.UNKNOWN);
            assertEquals("The cat goes Hiss! Hiss!", actualCat.speak(), "I was expecting hiss");
        }
        @Test
        @Order(2)
        @DisplayName("Cat Speak Prr Tests")
        public void catGoesPrrTest()
        {
            actualCat = new Cat(AnimalType.UNKNOWN, Skin.UNKNOWN,Gender.UNKNOWN, Breed.UNKNOWN);
            assertEquals("The cat goes Meow! Meow!", actualCat.speak(), "I was expecting Prr");
        }





    }
