package animals.petstore.pet.types;

import animals.AnimalType;
import animals.petstore.pet.Pet;
import animals.petstore.pet.attributes.Breed;
import animals.petstore.pet.attributes.Gender;
import animals.petstore.pet.attributes.PetType;
import animals.petstore.pet.attributes.Skin;

import java.math.BigDecimal;

/**
 * Snake attributes
 */
public class Snake extends Pet implements PetImpl
{
    /* Properties */
    private int numberOfLegs;
    private Breed breed;
    /**
    //     * Constructor
    //     * @param animalType {@link AnimalType} that defines if it domesticated or wild Snake
    //     * @param skinType The {@link Skin} of the Snake
    //     * @param gender The {@link Gender} of the Snake
    //     * @param breed The type of Snake {@link Breed}
    //     */

    public Snake(AnimalType animalType, Skin skinType, Gender gender, Breed breed)
    {
        this(animalType, skinType, gender, breed, new BigDecimal(0));
    }

    /**
     * Constructor
     * @param animalType {@link AnimalType} that defines if it domesticated or wild Snake
     * @param skinType The {@link Skin} of the Snake
     * @param gender The {@link Gender} of the Snake
     * @param breed The type of Snake {@link Breed}
     * @param cost The cost of the Snake
     */
    public Snake(AnimalType animalType, Skin skinType, Gender gender, Breed breed, BigDecimal cost)
    {
        this(animalType, skinType, gender, breed, cost, 0);
    }

    /**
     * Constructor
     * @param animalType {@link AnimalType} that defines if it domesticated or wild Snake
     * @param skinType The {@link Skin} of the Snake
     * @param gender The {@link Gender} of the Snake
     * @param breed The type of Snake {@link Breed}
     * @param cost The cost of the Snake
     * @param petStoreId The pet store id
     */
    public Snake(AnimalType animalType, Skin skinType, Gender gender, Breed breed, BigDecimal cost, int petStoreId)
    {
        super(PetType.SNAKE, cost, gender, petStoreId);
        super.skinType = skinType;
        super.animalType = animalType;
        this.numberOfLegs = 0;
        this.breed = breed;
    }

    /**
     * Is the Snake allergy friendly determined by skin type
     * @return A message that tells if the Snake is hypoallergenic
     */
    public String SnakeHypoallergenic()
    {
        return super.petHypoallergenic(this.skinType).replaceAll("pet", "Snake");
    }

    /**
     * What does the Snake say depends on {@link AnimalType} - Domestic, Wild, Farm, or Unknown
     * @return what Snake would speak
     */
    public String speak()
    {
        String language;
        switch(animalType){
            case DOMESTIC:
                language = "The Snake goes ssss! ssss!";
                break;
            case WILD:
                language = "The Snake goes hsss! hsss!";
                break;
            default:
                language = "The Snake goes " + super.getPetType().speak + "! " + super.getPetType().speak + "!";
        }
        return language;
    }


    private String numberOfLegs()
    {
        return "Snake have " + numberOfLegs + " legs!";
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

    public AnimalType getAnimalType() {
        return this.animalType;
    }

    @Override
    public String toString()
    {
        return super.toString() +
                "The dog is " +  super.animalType + "!\n" +
                "The dogs breed is " + this.getBreed() + "!\n" +
                this.SnakeHypoallergenic() + "!\n" +
                this.speak() + "\n" +
                this.numberOfLegs();
    }

}