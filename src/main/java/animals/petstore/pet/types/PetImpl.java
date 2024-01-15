package animals.petstore.pet.types;

import animals.petstore.pet.attributes.Breed;

/**
 *
 */
public interface PetImpl {
    Breed breed = Breed.UNKNOWN;
    public Breed getBreed();
}
