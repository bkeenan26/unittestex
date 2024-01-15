package animals.petstore.pet.types;

import animals.AnimalType;
import animals.petstore.pet.attributes.Breed;
import animals.petstore.pet.attributes.Gender;
import animals.petstore.pet.attributes.PetType;
import animals.petstore.pet.attributes.Skin;

import java.math.BigDecimal;

/**
 * Cat attributes
 * @param <Pet>
 */
public class Cat<Pet> extends animals.petstore.pet.Pet implements PetImpl {

    public Cat(AnimalType animalType, Skin skinType, Gender gender, Breed breed)
    {
        super(PetType.CAT, new BigDecimal(0), gender);
        this.skinType = skinType;
        this.numberOfLegs = 4;
        this.animalType = animalType;
        this.breed = breed;
    }
    public Cat(AnimalType animalType, Skin skinType, Gender gender, Breed breed, BigDecimal cost)
    {
        super(PetType.CAT, cost, gender);
        this.skinType = skinType;
        this.numberOfLegs = 4;
        this.animalType = animalType;
        this.breed = breed;
    }
    public Cat(AnimalType animalType, Skin skinType, Gender gender, Breed breed, BigDecimal cost, int petStoreId)
    {
        super(PetType.CAT, cost, gender, petStoreId);
        this.skinType = skinType;
        this.numberOfLegs = 4;
        this.animalType = animalType;
        this.breed = breed;
    }
    private int numberOfLegs;
    private PetType type;
    private AnimalType animalType;
    // private Skin skinType;

    private Breed breed;

    private Skin skinType;

    public String catHyperAllergetic() {
        return super.isPetHyperAllergetic(this.skinType).replaceAll("pet", "cat");
    }

    /**
     * Depending if the cat is domestic, wild, or neither what can the say
     * @return
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

    @Override
    public Breed getBreed() {
        return this.breed;
    }

    public String typeOfPet() {
        return "The type of pet is " + type + "!";
    }

    public AnimalType getAnimalType() {
        return this.animalType;
    }
    @Override
    public String toString() {
        return super.toString() +
                "The cat is " + this.animalType + "!\n" +
                "The cat breed is " + this.getBreed() + "!\n" +
                this.catHyperAllergetic() + "!\n" +
                this.speak() + "\n" +
                this.numberOfLegs();
    }
}