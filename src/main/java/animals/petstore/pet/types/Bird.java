package animals.petstore.pet.types;

import animals.AnimalType;
import animals.petstore.pet.Pet;
import animals.petstore.pet.attributes.Breed;
import animals.petstore.pet.attributes.Gender;
import animals.petstore.pet.attributes.PetType;
import animals.petstore.pet.attributes.Skin;

import java.math.BigDecimal;

/**
 * Bird attributes
 */
public class Bird extends Pet implements PetImpl {

    private int numberOfLegs;
    private Breed breed;

    public Bird(AnimalType animalType, Skin skinType, Gender gender, Breed breed) {
        this(animalType, skinType, gender, breed, new BigDecimal(0));
    }

    public Bird(AnimalType animalType, Skin skinType, Gender gender, Breed breed, BigDecimal cost) {
        this(animalType, skinType, gender, breed, cost, 0);
    }

    public Bird(AnimalType animalType, Skin skinType, Gender gender, Breed breed, BigDecimal cost, int petStoreId) {
        super(PetType.BIRD, cost, gender, petStoreId);
        super.skinType = skinType;
        super.animalType = animalType;
        this.numberOfLegs = 2;
        this.breed = breed;
    }

    public String birdHypoallergenic() {
        return super.petHypoallergenic(this.skinType).replaceAll("pet", "bird");
    }

    public String speak() {
        switch (this.animalType) {
            case DOMESTIC:
                return "The bird goes tweet!";
            case WILD:
                return "The bird goes screech!";
            default:
                return "The bird goes " + super.getPetType().speak + "! " + super.getPetType().speak + "!";
        }
    }

    public String fly() {
        return "The bird spreads its wings and flies away!";
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
        return "The type of pet is " + petType + "!";
    }

    public Skin getSkinType() {
        return super.skinType;
    }



    @Override
    public String toString() {
        return super.toString() +
                "The bird is " + this.animalType + "!\n" +
                "The bird breed is " + this.getBreed() + "!\n" +
                this.birdHypoallergenic() + "!\n" +
                this.speak() + "\n" +
                this.fly() + "\n" +
                "Birds have " + numberOfLegs + " legs!";
    }

    public AnimalType getAnimalType() {
        return super.animalType;
    }
}
