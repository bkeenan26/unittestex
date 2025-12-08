package tests;

import animals.AnimalType;
import animals.petstore.pet.Pet;
import animals.petstore.pet.attributes.Breed;
import animals.petstore.pet.attributes.Gender;
import animals.petstore.pet.attributes.PetType;
import animals.petstore.pet.attributes.Skin;
import animals.petstore.pet.types.Bird;
import animals.petstore.pet.types.Cat;
import animals.petstore.pet.types.Dog;
import animals.petstore.store.DuplicatePetStoreRecordException;
import animals.petstore.store.PetNotFoundSaleException;
import animals.petstore.store.PetStore;
import number.Numbers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class PetStoreTest
{
    private static PetStore petStore;

    @BeforeEach
    public void loadThePetStoreInventory() {
        petStore = new PetStore();
        petStore.init();
    }

    @Test
    @DisplayName("Inventory Count Test")
    public void validateInventory() {
        // Arrange, Act, Assert
        assertEquals(6, petStore.getPetsForSale().size(),"Inventory counts are off!");
    }

    @Test
    @DisplayName("Print Inventory Test")
    public void printInventoryTest() {
        // Arrange, Act, Assert
        petStore.printInventory();
    }

    @Test
    @DisplayName("Add Bird to Inventory")
    public void addBirdToInventory() {
        // Arrange, Act
        petStore.addPetInventoryItem(new Bird(AnimalType.DOMESTIC, Skin.FEATHERS, Gender.MALE, Breed.CARDINAL,
        new BigDecimal("200.0"), 4));

        // Asesert
        assertEquals(7,  petStore.getPetsForSale().size(),"Inventory counts are off!");
    }

    @Test
    @DisplayName("Sale of Bird Remove Item Test")
    public void birdSoldTest() throws DuplicatePetStoreRecordException, PetNotFoundSaleException {
        // Arrange
        Pet bird = new Bird(AnimalType.DOMESTIC, Skin.FEATHERS, Gender.MALE, Breed.CARDINAL,
                new BigDecimal("200.0"), 4);

        // Act
        petStore.soldPetItem(bird);

        // Assert
        assertEquals(5,  petStore.getPetsForSale().size(),"Inventory counts are off!");
    }

    @Test
    @DisplayName("Bird Duplicate Record Exception Test")
    public void birdDupRecordExceptionTest() {
        // Arrange
        petStore.addPetInventoryItem(new Bird(AnimalType.DOMESTIC, Skin.FEATHERS, Gender.MALE, Breed.CARDINAL,
                new BigDecimal("200.0"), 4));
        Bird bird = new Bird(AnimalType.DOMESTIC, Skin.FEATHERS, Gender.MALE, Breed.CARDINAL,
                new BigDecimal("200.0"), 4);

        // Validation, Act, Assert
        String expectedMessage = "Duplicate Bird record store id [4]";
        Exception exception = assertThrows(DuplicatePetStoreRecordException.class, () ->{
            petStore.soldPetItem(bird);});
        assertEquals(expectedMessage, exception.getMessage(), "DuplicateRecordExceptionTest was NOT encountered!");

    }

    /**
     * Limitations to test factory as it does not instantiate before all
     * @return list of {@link DynamicNode} that contains the test results
     * @throws DuplicatePetStoreRecordException if duplicate pet record is found
     * @throws PetNotFoundSaleException if pet is not found
     */
    @TestFactory
    @DisplayName("Sale of Bird Remove Item Test2")
    public Stream<DynamicNode> birdSoldTest2() throws DuplicatePetStoreRecordException, PetNotFoundSaleException {
        // Arrange
        int inventorySize = petStore.getPetsForSale().size() - 1;

        Bird bird = new Bird(AnimalType.DOMESTIC, Skin.FEATHERS, Gender.MALE, Breed.CARDINAL,
                new BigDecimal("200.0"), 4);

        // Act
        Bird removedItem = (Bird) petStore.soldPetItem(bird);

        // Validation, Assert
        List<DynamicNode> nodes = new ArrayList<>();
        List<DynamicTest> dynamicTests = Arrays.asList(
                dynamicTest("Inventory Check Size Test ", () -> assertEquals(inventorySize,
                        petStore.getPetsForSale().size())),
                dynamicTest("The bird objects match ", () -> assertEquals(bird.toString(),
                        removedItem.toString()))
        );
        nodes.add(dynamicContainer("Bird Item 2 Test", dynamicTests));//dynamicNode("", dynamicContainers);

        return nodes.stream();
    }

    @Test
    @DisplayName("Sale of Poodle Remove Item Test")
    public void poodleSoldTest() throws DuplicatePetStoreRecordException, PetNotFoundSaleException {
        // Arrange
        int inventorySize = petStore.getPetsForSale().size() - 1;

        // Act
        Dog poodle = new Dog(AnimalType.DOMESTIC, Skin.FUR, Gender.MALE, Breed.POODLE,
                new BigDecimal("650.00"), 1);

        // Validation, Assert
        petStore.soldPetItem(poodle);
        assertEquals(inventorySize, petStore.getPetsForSale().size(), "Expected inventory does not match actual");
    }

    @Test
    @DisplayName("Poodle Duplicate Record Exception Test")
    public void poodleDupRecordExceptionTest() {
        // Arrange, Act
        petStore.addPetInventoryItem(new Dog(AnimalType.DOMESTIC, Skin.FUR, Gender.MALE, Breed.POODLE,
                new BigDecimal("650.00"), 1));
        Dog poodle = new Dog(AnimalType.DOMESTIC, Skin.FUR, Gender.MALE, Breed.POODLE,
                new BigDecimal("650.00"), 1);

        // Validation, Assert
        String expectedMessage = "Duplicate Dog record store id [1]";
        Exception exception = assertThrows(DuplicatePetStoreRecordException.class, () ->{
            petStore.soldPetItem(poodle);});
        assertEquals(expectedMessage, exception.getMessage(), "DuplicateRecordExceptionTest was NOT encountered!");

    }

    @Test
    @DisplayName("Sale of Sphynx Remove Item Test")
    public void sphynxSoldTest() throws DuplicatePetStoreRecordException, PetNotFoundSaleException {
        // Arrange
        int inventorySize = petStore.getPetsForSale().size() - 1;

        Cat sphynx = new Cat(AnimalType.DOMESTIC, Skin.UNKNOWN, Gender.FEMALE, Breed.SPHYNX,
                new BigDecimal("100.00"),2);

        // Act
        Cat removedItem = (Cat) petStore.soldPetItem(sphynx);

        // Validation, Assert
        assertEquals(inventorySize, petStore.getPetsForSale().size(), "Expected inventory does not match actual");
        assertEquals(sphynx.getPetStoreId(), removedItem.getPetStoreId(), "The cat items are identical");
    }

    /**
     * Limitations to test factory as it does not instantiate before all
     * @return list of {@link DynamicNode} that contains the test results
     * @throws DuplicatePetStoreRecordException if duplicate pet record is found
     * @throws PetNotFoundSaleException if pet is not found
     */
    @TestFactory
    @DisplayName("Sale of Sphynx Remove Item Test2")
    public Stream<DynamicNode> sphynxSoldTest2() throws DuplicatePetStoreRecordException, PetNotFoundSaleException {
        // Arrange
        int inventorySize = petStore.getPetsForSale().size() - 1;

        Cat sphynx = new Cat(AnimalType.DOMESTIC, Skin.UNKNOWN, Gender.FEMALE, Breed.SPHYNX,
                new BigDecimal("100.00"),2);

        // Act
        Cat removedItem = (Cat) petStore.soldPetItem(sphynx);

        // Validation, Assert
        List<DynamicNode> nodes = new ArrayList<>();
        List<DynamicTest> dynamicTests = Arrays.asList(
                dynamicTest("Inventory Check Size Test ", () -> assertEquals(inventorySize,
                        petStore.getPetsForSale().size())),
                dynamicTest("The cat objects match ", () -> assertEquals(sphynx.toString(),
                        removedItem.toString()))
                );
        nodes.add(dynamicContainer("Cat Item 2 Test", dynamicTests));//dynamicNode("", dynamicContainers);

        return nodes.stream();
    }

    /**
     * Example of parameterized test
     * @param number to be tested
     */
    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, -10, 128, Integer.MIN_VALUE}) // six numbers
    void isNumberEven(int number) {
        // Arrange, Act, Assert
        assertTrue(Numbers.isEven(number));
    }

    @Test
    @DisplayName("Add duplicate item via alternate method.")
    void initAddDuplicateItemTest() {
        // Arrange
        PetStore localPetStore = new PetStore();
        Pet pet = new Dog(AnimalType.DOMESTIC, Skin.FUR, Gender.MALE, Breed.MALTESE, new BigDecimal("750.00"), 3);

        // Act
        localPetStore.initAddDuplicateItem(pet);
        List<Pet> petsForSale = localPetStore.getPetsForSale();

        int count = 0;

        for(Pet p : petsForSale)
        {
            if(p.getPetType().equals(pet.getPetType()) &&
               p.petHypoallergenic(Skin.FUR).equals(pet.petHypoallergenic(Skin.FUR)) &&
               p.getGender().equals(pet.getGender()) &&
               p.getCost().equals(pet.getCost()))
            {
                count += 1;
            }
        }

        // Assert
        assertTrue(localPetStore.getPetsForSale().contains(pet));
        assertEquals(2, count);
    }

    @Test
    @DisplayName("Sold pet item test where store id equals 0.")
    void soldPetItemThrowsTest() throws PetNotFoundSaleException {
        // Arrange
        PetStore localPetStore = new PetStore();
        Pet pet = new Dog(AnimalType.DOMESTIC, Skin.FUR, Gender.MALE, Breed.RAGDOLL, new BigDecimal("750.00"), 0);

        // Act & Assert
        assertThrows(PetNotFoundSaleException.class, () ->
        {
            localPetStore.soldPetItem(pet);
        });
    }

    @Test
    @DisplayName("Duplicate item sold test.")
    void duplicateSoldPetItemThrowsTest() throws DuplicatePetStoreRecordException {
        // Arrange
        PetStore localPetStore = new PetStore();
        Pet pet = new Cat(AnimalType.DOMESTIC, Skin.HAIR, Gender.MALE, Breed.BURMESE, new BigDecimal("65.00"),1);

        petStore.initAddDuplicateItem(pet);

        // Act & Assert
        assertThrows(DuplicatePetStoreRecordException.class, () ->
        {
            localPetStore.soldPetItem(pet);
        });
    }
}
