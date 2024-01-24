package animals.petstore.pet.types;

import animals.petstore.pet.attributes.Breed;

/**
 *
 */
public interface PetImpl {
    Breed breed = Breed.UNKNOWN;

    /**
     * Get the pet breed type
     * @return {@link Breed}
     */
    Breed getBreed();
}
