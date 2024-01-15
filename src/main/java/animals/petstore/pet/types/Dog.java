package animals.petstore.pet.types;

import animals.AnimalType;
import animals.petstore.pet.attributes.Breed;
import animals.petstore.pet.attributes.Gender;
import animals.petstore.pet.attributes.PetType;
import animals.petstore.pet.attributes.Skin;

import java.math.BigDecimal;


public class Dog<Pet> extends animals.petstore.pet.Pet implements PetImpl {

    public Dog(AnimalType animalType, Skin skinType, Gender gender, Breed breed)
    {
        super(PetType.DOG, new BigDecimal(0), gender);
        this.skinType = skinType;
        this.numberOfLegs = 4;
        this.animalType = animalType;
        this.breed = breed;
    }
    public Dog(AnimalType animalType, Skin skinType, Gender gender, Breed breed, BigDecimal cost)
    {
        super(PetType.DOG, cost, gender);
        this.skinType = skinType;
        this.numberOfLegs = 4;
        this.animalType = animalType;
        this.breed = breed;
    }
    public Dog(AnimalType animalType, Skin skinType, Gender gender, Breed breed, BigDecimal cost, int petStoreId)
    {
        super(PetType.DOG, cost, gender, petStoreId);
        this.skinType = skinType;
        this.numberOfLegs = 4;
        this.animalType = animalType;
        this.breed = breed;
    }
    private int numberOfLegs;
    private AnimalType animalType;
   // private Skin skinType;

    private Breed breed;

    private Skin skinType;

    public String dogHyperAllergetic()
    {
        return super.isPetHyperAllergetic(this.skinType).replaceAll("pet", "dog");
    }

    /**
     * What does the dog say depending on type animal type Domestic, Wild, Farm, or Unknown
     * @return
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
               "The dog is " +  this.animalType + "!\n" +
               "The dogs breed is " + this.getBreed() + "!\n" +
               this.dogHyperAllergetic() + "!\n" +
               this.speak() + "\n" +
               this.numberOfLegs();
    }


}
