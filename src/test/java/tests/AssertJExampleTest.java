package tests;

import animals.AnimalType;
import animals.petstore.pet.Pet;
import animals.petstore.pet.attributes.Breed;
import animals.petstore.pet.attributes.Gender;
import animals.petstore.pet.attributes.Skin;
import animals.petstore.pet.types.Dog;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class example to show the power of assertJ
 */
public class AssertJExampleTest
{
    private List<Pet> dListActual = Arrays.asList(
            new Dog(AnimalType.DOMESTIC, Skin.FUR, Gender.MALE, Breed.MALTESE,
                    new BigDecimal("750.00"), 1),
            new Dog(AnimalType.DOMESTIC, Skin.FUR, Gender.MALE, Breed.POODLE,
                    new BigDecimal("750.00"), 2),
            new Dog(AnimalType.DOMESTIC, Skin.HAIR, Gender.FEMALE, Breed.GERMAN_SHEPARD,
                    new BigDecimal("750.00"), 2));

    private List<Pet> dListExpected = Arrays.asList(
            new Dog(AnimalType.DOMESTIC, Skin.FUR, Gender.MALE, Breed.MALTESE,
                    new BigDecimal("750.00"), 1),
            new Dog(AnimalType.DOMESTIC, Skin.FUR, Gender.MALE, Breed.POODLE,
                    new BigDecimal("750.00"), 2),
            new Dog(AnimalType.DOMESTIC, Skin.HAIR, Gender.FEMALE, Breed.CARDINAL,
                    new BigDecimal("750.00"), 2)
    );

    @Test
    @DisplayName("ABC test")
    public void abcTest()
    {
        assertThat("abc").isEqualTo(123);

    }

    @Test
    @DisplayName("Empty String test")
    public void emptyStringTest()
    {
        assertThat("".isEmpty()).isTrue();

    }

    @Test
    @DisplayName("Collection Test not null and not empty")
    public void dogCollectionTest()
    {
       assertThat(dListActual)
               .isNotEmpty()
               .doesNotContainNull();
    }

    @Test
    @DisplayName("Dog Collection  Match Tests1")
    public void dogCollectionMatch()
    {
        assertThat(dListActual).isEqualTo(dListExpected)
                .isNotEmpty()
                .doesNotContainNull();
    }

    @Test
    @DisplayName("Dog Collection  Match Tests2")
    public void dogCollectionSameListTest()
    {
        assertThat(dListActual).isEqualTo(dListActual)
                .isNotEmpty()
                .doesNotContainNull();
    }
}
