package tests;

import animals.AnimalType;
import animals.petstore.pet.attributes.Breed;
import animals.petstore.pet.attributes.Gender;
import animals.petstore.pet.attributes.Skin;
import animals.petstore.pet.types.Cat;
import animals.petstore.pet.types.Dog;
import animals.petstore.pet.types.Bird;
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
        assertEquals(5, petStore.getPetsForSale().size(),"Inventory counts are off!");
    }

    @Test
    @DisplayName("Print Inventory Test")
    public void printInventoryTest()
    {
        petStore.printInventory();
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

    @Test
    @DisplayName("Add Pet Inventory Happy Path Test")
    public void addPetSuccessTest() {
        int sizeBefore = petStore.getPetsForSale().size();

        Dog beagle = new Dog(AnimalType.DOMESTIC, Skin.FUR, Gender.FEMALE, Breed.BEAGLE,
                new BigDecimal("300.00"), 50);

        petStore.addPetInventoryItem(beagle);

        assertEquals(sizeBefore + 1, petStore.getPetsForSale().size());
    }

    @Test
    @DisplayName("Sold Pet Should Match Returned Pet Test")
    public void soldPetMatchesReturnedTest() throws Exception {
        Cat cat = new Cat(AnimalType.DOMESTIC, Skin.UNKNOWN, Gender.FEMALE, Breed.SPHYNX,
                new BigDecimal("100.00"), 2);

        Cat returned = (Cat) petStore.soldPetItem(cat);

        assertEquals(cat.getPetStoreId(), returned.getPetStoreId());
    }
    @Test
    @DisplayName("Print Inventory When Empty Test")
    public void printEmptyInventoryTest() {
        PetStore store = new PetStore(); // new, no init()
        store.printInventory(); // Just ensure no exceptions
    }
    @Test
    @DisplayName("Init Method Loads 5 Pets")
    public void initLoadsFivePets() {
        PetStore store = new PetStore();
        store.init();
        assertEquals(5, store.getPetsForSale().size());
    }
    @Test
    @DisplayName("Add and Sell Birds for All Valid Breeds")
    public void addSellBirdsTest() throws Exception {
        Breed[] birdBreeds = {
                Breed.BLUE_JAY, Breed.CARDINAL, Breed.SPARROW, Breed.HAWK,
                Breed.HUMMING_BIRD, Breed.ROBIN, Breed.BALTIMORE_ORIOLE, Breed.PARROT
        };

        for (int i = 0; i < birdBreeds.length; i++) {
            Bird bird = new Bird(
                    AnimalType.DOMESTIC,
                    Skin.FEATHERS,
                    Gender.FEMALE,
                    birdBreeds[i],
                    new BigDecimal("200.00"),
                    100 + i
            );

            petStore.addPetInventoryItem(bird);
            Bird soldBird = (Bird) petStore.soldPetItem(bird);

            assertEquals(bird.getPetStoreId(), soldBird.getPetStoreId());
        }
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
