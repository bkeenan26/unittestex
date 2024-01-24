package animals.petstore.pet.types;

import animals.AnimalType;
import animals.petstore.pet.Pet;
import animals.petstore.pet.attributes.Breed;
import animals.petstore.pet.attributes.Gender;
import animals.petstore.pet.attributes.PetType;
import animals.petstore.pet.attributes.Skin;

import java.math.BigDecimal;


public class Dog extends Pet implements PetImpl {

    /* Properties */
    private int numberOfLegs;
    private Breed breed;

    /**
     * Constructor
     * @param animalType {@link AnimalType} that defines if it domesticated or wild dog
     * @param skinType The {@link Skin} of the dog
     * @param gender The {@link Gender} of the dog
     * @param breed The type of dog {@link Breed}
     */
    public Dog(AnimalType animalType, Skin skinType, Gender gender, Breed breed)
    {
        this(animalType, skinType, gender, breed, new BigDecimal(0));
    }

    /**
     * Constructor
     * @param animalType {@link AnimalType} that defines if it domesticated or wild dog
     * @param skinType The {@link Skin} of the dog
     * @param gender The {@link Gender} of the dog
     * @param breed The type of dog {@link Breed}
     * @param cost The cost of the dog
     */
    public Dog(AnimalType animalType, Skin skinType, Gender gender, Breed breed, BigDecimal cost)
    {
        this(animalType, skinType, gender, breed, cost, 0);
    }

    /**
     * Constructor
     * @param animalType {@link AnimalType} that defines if it domesticated or wild dog
     * @param skinType The {@link Skin} of the dog
     * @param gender The {@link Gender} of the dog
     * @param breed The type of dog {@link Breed}
     * @param cost The cost of the dog
     * @param petStoreId  The pet store id
     */
    public Dog(AnimalType animalType, Skin skinType, Gender gender, Breed breed, BigDecimal cost, int petStoreId)
    {
        super(PetType.DOG, cost, gender, petStoreId);
        super.skinType = skinType;
        super.animalType = animalType;
        this.numberOfLegs = 4;
        this.breed = breed;
    }

    /**
     * Is the dog allergy friendly determined by skin type
     * @return A message that tells if the cat is hypoallergenic
     */
    public String dogHypoallergenic()
    {
        return super.petHypoallergenic(this.skinType).replaceAll("pet", "dog");
    }

    /**
     * What does the dog say depends on {@link AnimalType} - Domestic, Wild, Farm, or Unknown
     * @return what dogs would speak
     */
    public String speak()
    {
        String language;
        switch(animalType){
            case DOMESTIC:
                language = "The dog goes woof! woof!";
                break;
            case WILD:
                language = "The dog goes grr! grr!";
                break;
            default:
                language = "The dog goes " + super.getPetType().speak + "! " + super.getPetType().speak + "!";
        }
       return language;
    }

    private String numberOfLegs()
    {
        return "Dogs have " + numberOfLegs + " legs!";
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
               this.dogHypoallergenic() + "!\n" +
               this.speak() + "\n" +
               this.numberOfLegs();
    }


}
