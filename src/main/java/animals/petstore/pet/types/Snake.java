package animals.petstore.pet.types;

import animals.AnimalType;
import animals.petstore.pet.Pet;
import animals.petstore.pet.attributes.Breed;
import animals.petstore.pet.attributes.Gender;
import animals.petstore.pet.attributes.PetType;
import animals.petstore.pet.attributes.Skin;

import java.math.BigDecimal;

/**
 * Represents a Snake in the pet store system.
 */
public class Snake extends Pet implements PetImpl {

    /* Fields */
    private int numberOfLegs; // Snakes naturally have no legs, but this field remains for consistency across pets.
    private Breed breed;

    /**
     * Basic constructor.
     *
     * @param animalType Indicates whether the snake is domestic or wild.
     * @param skinType The snake’s skin type.
     * @param gender The snake’s gender.
     * @param breed The specific snake breed.
     */
    public Snake(AnimalType animalType, Skin skinType, Gender gender, Breed breed)
    {
        this(animalType, skinType, gender, breed, new BigDecimal(0));
    }

    /**
     * Constructor with a defined cost.
     *
     * @param animalType Indicates whether the snake is domestic or wild.
     * @param skinType The snake’s skin type.
     * @param gender The snake’s gender.
     * @param breed The specific snake breed.
     * @param cost Cost of the snake.
     */
    public Snake(AnimalType animalType, Skin skinType, Gender gender, Breed breed, BigDecimal cost)
    {
        this(animalType, skinType, gender, breed, cost, 0);
    }

    /**
     * Full constructor including store ID.
     *
     * @param animalType Indicates whether the snake is domestic or wild.
     * @param skinType The snake’s skin type.
     * @param gender The snake’s gender.
     * @param breed The specific snake breed.
     * @param cost Cost of the snake.
     * @param petStoreId Unique ID of the snake inside the pet store.
     */
    public Snake(AnimalType animalType, Skin skinType, Gender gender, Breed breed, BigDecimal cost, int petStoreId)
    {
        super(PetType.SNAKE, cost, gender, petStoreId);
        super.skinType = skinType;
        super.animalType = animalType;
        this.numberOfLegs = 4; // Carried over from the abstract base—snakes still “inherit” the field.
        this.breed = breed;
    }

    /**
     * Determines whether the snake is hypoallergenic based on its skin type.
     *
     * @return A modified message specifying if the snake is hypoallergenic.
     */
    public String snakeHypoallergenic() {
        return super.petHypoallergenic(this.skinType).replaceAll("pet", "snake");
    }

    /**
     * Returns an appropriate "sound" depending on whether the snake is wild or domestic.
     *
     * @return A string describing the sound the snake makes.
     */
    public String speak() {
        String language;
        switch (this.animalType) {
            case DOMESTIC:
                language = "The snake goes pss! pss!";
                break;
            case WILD:
                language = "The snake goes hiss! hiss!";
                break;
            default:
                language = "The snake goes " + super.getPetType().speak + "! " + super.getPetType().speak + "!";
                break;
        }
        return language;
    }

    // Included for consistency with the Pet hierarchy even though snakes naturally have no legs.
    private String numberOfLegs() {
        return "Snakes have " + numberOfLegs + " legs!";
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
                "The snake is " + this.animalType + "!\n" +
                "The snake breed is " + this.getBreed() + "!\n" +
                this.snakeHypoallergenic() + "!\n" +
                this.speak() + "\n" +
                this.numberOfLegs();
    }
}