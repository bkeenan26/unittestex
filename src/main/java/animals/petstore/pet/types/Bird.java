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
    private Skin skinType;
    private AnimalType animalType;

    public Bird(AnimalType animalType, Skin skinType, Gender gender, Breed breed) {
        this(animalType, skinType, gender, breed, BigDecimal.ZERO, 0);
    }

    public Bird(AnimalType animalType, Skin skinType, Gender gender, Breed breed, BigDecimal cost) {
        this(animalType, skinType, gender, breed, cost, 0);
    }

    public Bird(AnimalType animalType, Skin skinType, Gender gender, Breed breed, BigDecimal cost, int petStoreId) {
        super(PetType.BIRD, cost, gender, petStoreId); // only call existing Pet constructor
        this.skinType = skinType;
        this.animalType = animalType;
        this.numberOfLegs = 2;
        this.breed = breed;
    }

    // speak() is not in Pet, so do not use @Override
    public String speak() {
        return "The bird goes chirp! chirp!";
    }

    public String birdHypoallergenic() {
        if (this.skinType == Skin.FEATHERS) {
            return "The bird is not hyperallergetic!";
        } else if (this.skinType == Skin.UNKNOWN) {
            return "The pet skin is UNKNOWN at this time, so cannot determine if pet is hypoallergetic!";
        } else {
            return "Unknown skin type for hypoallergenic determination.";
        }
    }

    public Skin getSkinType() {
        return this.skinType;
    }

    public AnimalType getAnimalType() {
        return this.animalType;
    }

    @Override
    public Breed getBreed() { // only override if PetImpl requires it
        return this.breed;
    }

    public int getNumberOfLegs() {
        return this.numberOfLegs;
    }

    @Override
    public String toString() {
        return "The type of pet is " + getPetType() + "\n" +
                "The bird breed is " + getBreed() + "\n" +
                "The bird gender is " + getGender() + "\n" +
                "The bird pet store id is " + getPetStoreId() + "\n" +
                "It has " + getNumberOfLegs() + " legs.";
    }
}
