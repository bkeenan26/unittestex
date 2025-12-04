package animals.petstore.pet.types;

import animals.AnimalType;
import animals.petstore.pet.Pet;
import animals.petstore.pet.attributes.Breed;
import animals.petstore.pet.attributes.Gender;
import animals.petstore.pet.attributes.PetType;
import animals.petstore.pet.attributes.Skin;

import java.math.BigDecimal;

public class Bird extends Pet implements PetImpl {
    private int numberOfLegs;
    private Breed breed;

    public Bird(AnimalType animalType, Skin skinType, Gender gender, Breed breed)
    {
        this(animalType, skinType, gender, breed, new BigDecimal(0));
    }


    public Bird(AnimalType animalType, Skin skinType, Gender gender, Breed breed, BigDecimal cost)
    {
        this(animalType, skinType, gender, breed, cost, 0);
    }


    public Bird(AnimalType animalType, Skin skinType, Gender gender, Breed breed, BigDecimal cost, int petStoreId)
    {
        super(PetType.BIRD, cost, gender, petStoreId);
        super.skinType = skinType;
        super.animalType = animalType;
        this.numberOfLegs = 2;
        this.breed = breed;
    }

    public String speak()
    {
        String language;
        switch(animalType){
            case DOMESTIC:
                language = "The bird goes chirp! chirp!";
                break;
            case WILD:
                language = "The bird goes tweet! tweet!";
                break;
            default:
                language = "The bird goes " + super.getPetType().speak + "! " + super.getPetType().speak + "!";
        }
        return language;
    }

    private String numberOfLegs() {
        return "Birds have " + numberOfLegs + " legs!";
    }

    public int getNumberOfLegs() {
        return numberOfLegs;
    }

    public void setNumberOfLegs(int numberOfLegs) {
        this.numberOfLegs = numberOfLegs;
    }

    public String typeOfPet() {
        return "The type of pet is " + petType + "!";
    }

    public AnimalType getAnimalType() {
        return super.animalType;
    }

    @Override
    public Breed getBreed() {
        return null;
    }

    @Override
    public String toString() {
        return super.toString() +
                "The bird is " + this.animalType + "!\n" +
                "The bird breed is " + this.getBreed() + "!\n" +
                this.speak() + "\n" +
                this.numberOfLegs();
    }
}
