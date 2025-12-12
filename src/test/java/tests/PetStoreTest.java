package tests;

import animals.AnimalType;
import animals.petstore.pet.attributes.Breed;
import animals.petstore.pet.attributes.Gender;
import animals.petstore.pet.attributes.Skin;
import animals.petstore.pet.types.Cat;
import animals.petstore.pet.types.Dog;
import animals.petstore.pet.types.Snake;
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
    public void loadThePetStoreInventory()
    {
        petStore = new PetStore();
        petStore.init();
    }

    @Test
    @DisplayName("Inventory Count Test")
    public void validateInventory()
    {
        assertEquals(7, petStore.getPetsForSale().size(),"Inventory counts are off!");
    }

    @Test
    @DisplayName("Print Inventory Test")
    public void printInventoryTest()
    {
        petStore.printInventory();
    }

    @Test
    @DisplayName("sold of MILK Remove Item Test")
    public void MILKSoldTest() throws DuplicatePetStoreRecordException, PetNotFoundSaleException {
        int inventorySize = petStore.getPetsForSale().size() - 1;
        Snake MILK = new Snake(AnimalType.DOMESTIC, Skin.SCALES, Gender.MALE, Breed.COPPERHEAD,
                new BigDecimal("300.00"), 2);

        // Validation
        petStore.soldPetItem(MILK);
        assertEquals(inventorySize, petStore.getPetsForSale().size(), "Expected inventory does not match actual");
    }

    @Test
    public void getCost()
    {
        int inventorySize = petStore.getPetsForSale().size() - 1;
        Snake BALL_PYTHON = new Snake(AnimalType.DOMESTIC, Skin.SCALES, Gender.MALE, Breed.BALL_PYTHON,
                new BigDecimal("300.00"), 1);
        Snake removedItem = BALL_PYTHON;
        assertEquals(BALL_PYTHON.getCost(), removedItem.getCost(), "The Cost is Different For Same Item");
    }

    @Test
    @DisplayName("Sale of copperhead Remove Item Test")
    public void milkSoldTest() throws DuplicatePetStoreRecordException, PetNotFoundSaleException {
        int inventorySize = petStore.getPetsForSale().size() - 1;

        Snake copperhead = new Snake(AnimalType.DOMESTIC, Skin.SCALES, Gender.FEMALE, Breed.COPPERHEAD,
                new BigDecimal("650.00"),2);
        Snake removedItem = (Snake) petStore.soldPetItem(copperhead);

        // Validation
        assertEquals(inventorySize, petStore.getPetsForSale().size(), "Expected inventory does not match actual");
        assertEquals(copperhead.getPetStoreId(), removedItem.getPetStoreId(), "The Snake items are identical");
    }



    /**
     * Limitations to test factory as it does not instantiate before all
     * @return list of {@link DynamicNode} that contains the test results
     * @throws DuplicatePetStoreRecordException if duplicate pet record is found
     * @throws PetNotFoundSaleException if pet is not found
     */
    @TestFactory
    @DisplayName("Sale of milk Remove Item Test2")
    public Stream<DynamicNode> milkSoldTest2() throws DuplicatePetStoreRecordException, PetNotFoundSaleException {
        int inventorySize = petStore.getPetsForSale().size() - 1;

        Snake milk = new Snake(AnimalType.DOMESTIC, Skin.SCALES, Gender.MALE, Breed.MILK,
                new BigDecimal("300.00"),1);
        Snake removedItem = (Snake) petStore.soldPetItem(milk);

        // Validation
        List<DynamicNode> nodes = new ArrayList<>();
        List<DynamicTest> dynamicTests = Arrays.asList(
                dynamicTest("Inventory Check Size Test ", () -> assertEquals(inventorySize,
                        petStore.getPetsForSale().size())),
                dynamicTest("The snake objects match ", () -> assertEquals(milk.toString(),
                        removedItem.toString()))
        );
        nodes.add(dynamicContainer("snake Item 2 Test", dynamicTests));//dynamicNode("", dynamicContainers);
        return nodes.stream();
    }

    @Test
    @DisplayName("milk Duplicate Record Exception Test")
    public void milkDupRecordExceptionTest() {
        petStore.addPetInventoryItem(new Snake(AnimalType.DOMESTIC, Skin.SCALES, Gender.MALE, Breed.MILK,
                new BigDecimal("650.00"), 1));
        Snake milk = new Snake(AnimalType.DOMESTIC, Skin.SCALES, Gender.MALE, Breed.MILK,
                new BigDecimal("650.00"), 1);

        // Validation
        String expectedMessage = "Duplicate Snake record store id [1]";
        Exception exception = assertThrows(DuplicatePetStoreRecordException.class, () ->{
            petStore.soldPetItem(milk);});
        assertEquals(expectedMessage, exception.getMessage(), "DuplicateRecordExceptionTest was NOT encountered!");

    }


    @Test
    @DisplayName("Sale of Poodle Remove Item Test")
    public void poodleSoldTest() throws DuplicatePetStoreRecordException, PetNotFoundSaleException {
        int inventorySize = petStore.getPetsForSale().size() - 1;
        Dog poodle = new Dog(AnimalType.DOMESTIC, Skin.FUR, Gender.MALE, Breed.POODLE,
                new BigDecimal("650.00"), 1);

        // Validation
        petStore.soldPetItem(poodle);
        assertEquals(inventorySize, petStore.getPetsForSale().size(), "Expected inventory does not match actual");
    }


    /**
     * Limitations to test factory as it does not instantiate before all
     * @return list of {@link DynamicNode} that contains the test results
     * @throws DuplicatePetStoreRecordException if duplicate pet record is found
     * @throws PetNotFoundSaleException if pet is not found
     */
    @TestFactory
    @DisplayName("Sale of poodle Remove Item Test2")
    public Stream<DynamicNode> poodleSoldTest2() throws DuplicatePetStoreRecordException, PetNotFoundSaleException {
        int inventorySize = petStore.getPetsForSale().size() - 1;

        Dog GERMANSHEPARD = new Dog(AnimalType.DOMESTIC, Skin.HAIR, Gender.MALE, Breed.GERMAN_SHEPARD,
                new BigDecimal("50.00"),2);
        Dog removedItem = (Dog) petStore.soldPetItem(GERMANSHEPARD);

        // Validation
        List<DynamicNode> nodes = new ArrayList<>();
        List<DynamicTest> dynamicTests = Arrays.asList(
                dynamicTest("Inventory Check Size Test ", () -> assertEquals(inventorySize,
                        petStore.getPetsForSale().size())),
                dynamicTest("The dog objects match ", () -> assertEquals(GERMANSHEPARD.toString(),
                        removedItem.toString()))
        );
        nodes.add(dynamicContainer("Dog Item 2 Test", dynamicTests));//dynamicNode("", dynamicContainers);
        return nodes.stream();
    }



    @Test
    @DisplayName("Poodle Duplicate Record Exception Test")
    public void poodleDupRecordExceptionTest() {
        petStore.addPetInventoryItem(new Dog(AnimalType.DOMESTIC, Skin.FUR, Gender.MALE, Breed.POODLE,
                new BigDecimal("650.00"), 1));
        Dog poodle = new Dog(AnimalType.DOMESTIC, Skin.FUR, Gender.MALE, Breed.POODLE,
                new BigDecimal("650.00"), 1);

        // Validation
        String expectedMessage = "Duplicate Dog record store id [1]";
        Exception exception = assertThrows(DuplicatePetStoreRecordException.class, () ->{
            petStore.soldPetItem(poodle);});
        assertEquals(expectedMessage, exception.getMessage(), "DuplicateRecordExceptionTest was NOT encountered!");

    }


    @Test
    @DisplayName("Sale of Sphynx Remove Item Test")
    public void sphynxSoldTest() throws DuplicatePetStoreRecordException, PetNotFoundSaleException {
        int inventorySize = petStore.getPetsForSale().size() - 1;

        Cat sphynx = new Cat(AnimalType.DOMESTIC, Skin.UNKNOWN, Gender.FEMALE, Breed.SPHYNX,
                new BigDecimal("100.00"),2);
        Cat removedItem = (Cat) petStore.soldPetItem(sphynx);

        // Validation
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
        int inventorySize = petStore.getPetsForSale().size() - 1;

        Cat sphynx = new Cat(AnimalType.DOMESTIC, Skin.UNKNOWN, Gender.FEMALE, Breed.SPHYNX,
                new BigDecimal("100.00"),2);
        Cat removedItem = (Cat) petStore.soldPetItem(sphynx);

        // Validation
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


    @Test
    @DisplayName("Testing Adding a Duplicate Cat with Constructor")
    public void addDuplicateCatConstructor(){
        int totalPetsForSale = petStore.getPetsForSale().size();
        Cat maine = new Cat(AnimalType.DOMESTIC, Skin.UNKNOWN, Gender.FEMALE, Breed.MAINE,
                new BigDecimal("150.00"),1);
        petStore.initAddDuplicateItem(maine);

        assertEquals((2 * totalPetsForSale) + 1, petStore.getPetsForSale().size());
    }


    @Test
    @DisplayName("Sphynx Duplicate Record Exception Test")
    public void SphynxDupRecordExceptionTest() {
        petStore.addPetInventoryItem(new Cat(AnimalType.DOMESTIC, Skin.FUR, Gender.MALE, Breed.SPHYNX,
                new BigDecimal("650.00"), 1));
        Cat Sphynx = new Cat(AnimalType.DOMESTIC, Skin.FUR, Gender.MALE, Breed.SPHYNX,
                new BigDecimal("650.00"), 1);

        // Validation
        String expectedMessage = "Duplicate Cat record store id [1]";
        Exception exception = assertThrows(DuplicatePetStoreRecordException.class, () ->{
            petStore.soldPetItem(Sphynx);});
        assertEquals(expectedMessage, exception.getMessage(), "DuplicateRecordExceptionTest was NOT encountered!");

    }



    /**
     * Example of parameterized test
     * @param number to be tested
     */
    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, -10, 128, Integer.MIN_VALUE}) // six numbers
    void isNumberEven(int number)
    {
        assertTrue(Numbers.isEven(number));
    }


}
