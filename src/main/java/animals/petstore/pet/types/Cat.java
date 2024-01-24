package animals.petstore.pet.types;

import animals.AnimalType;
import animals.petstore.pet.Pet;
import animals.petstore.pet.attributes.Breed;
import animals.petstore.pet.attributes.Gender;
import animals.petstore.pet.attributes.PetType;
import animals.petstore.pet.attributes.Skin;

import java.math.BigDecimal;

/**
 * Cat attributes
 */
public class Cat extends Pet implements PetImpl {

    /* Properties */
    private int numberOfLegs;
    private Breed breed;

    /**
     * Constructor
     * @param animalType {@link AnimalType} that defines if it domesticated or wild cat
     * @param skinType The {@link Skin} of the cat
     * @param gender The {@link Gender} of the cat
     * @param breed The type of cat {@link Breed}
     */
    public Cat(AnimalType animalType, Skin skinType, Gender gender, Breed breed)
    {
        this(animalType, skinType, gender, breed, new BigDecimal(0));
    }

    /**
     * Constructor
     * @param animalType {@link AnimalType} that defines if it domesticated or wild cat
     * @param skinType The {@link Skin} of the cat
     * @param gender The {@link Gender} of the cat
     * @param breed The type of cat {@link Breed}
     * @param cost The cost of the cat
     */
    public Cat(AnimalType animalType, Skin skinType, Gender gender, Breed breed, BigDecimal cost)
    {
        this(animalType, skinType, gender, breed, cost, 0);
    }

    /**
     * Constructor
     * @param animalType {@link AnimalType} that defines if it domesticated or wild cat
     * @param skinType The {@link Skin} of the cat
     * @param gender The {@link Gender} of the cat
     * @param breed The type of cat {@link Breed}
     * @param cost The cost of the cat
     * @param petStoreId The pet store id
     */
    public Cat(AnimalType animalType, Skin skinType, Gender gender, Breed breed, BigDecimal cost, int petStoreId)
    {
        super(PetType.CAT, cost, gender, petStoreId);
        super.skinType = skinType;
        super.animalType = animalType;
        this.numberOfLegs = 4;
        this.breed = breed;
    }

    /**
     * Is the cat allergy friendly determined by skin type
     * @return A message that tells if the cat is hypoallergenic
     */
    public String catHypoallergenic() {
        return super.petHypoallergenic(this.skinType).replaceAll("pet", "cat");
    }

    /**
     * Depending if the cat is domestic, wild, or neither what can the say
     * @return what cats would speak
     */
    public String speak() {
        String language;
        switch (this.animalType) {
            case DOMESTIC:
                language = "The cat goes prr! prr!";
                break;
            case WILD:
                language = "The cat goes hiss! hiss!";
                break;
            default:
                language = "The cat goes " + super.getPetType().speak + "! " + super.getPetType().speak + "!";
                break;
        }
        return language;
    }

    private String numberOfLegs() {
        return "Cats have " + numberOfLegs + " legs!";
    }

    public int getNumberOfLegs() {
        return numberOfLegs;
    }

    public void setNumberOfLegs(int numberOfLegs) {
        this.numberOfLegs = numberOfLegs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Breed getBreed() {
        return this.breed;
    }

    public String typeOfPet() {
        return "The type of pet is " + petType + "!";
    }

    public AnimalType getAnimalType() {
        return super.animalType;
    }

    @Override
    public String toString() {
        return super.toString() +
                "The cat is " + this.animalType + "!\n" +
                "The cat breed is " + this.getBreed() + "!\n" +
                this.catHypoallergenic() + "!\n" +
                this.speak() + "\n" +
                this.numberOfLegs();
    }
}