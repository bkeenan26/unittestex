package animals.petstore.pet;

import animals.AnimalType;
import animals.petstore.pet.attributes.Gender;
import animals.petstore.pet.attributes.PetType;
import animals.petstore.pet.attributes.Skin;

/**
 * Common attributes of a pet
 */
public abstract class  AbstractPet {

    protected AnimalType animalType = AnimalType.UNKNOWN;
    protected PetType petType = PetType.UNKNOWN;
    protected Skin skinType = Skin.UNKNOWN;
    protected Gender gender = Gender.UNKNOWN;

    protected boolean hasLegs = true;
    protected boolean isMammal = false;

    /**
     * Get the pet's gender
     * @return {@link Gender}
     */
    public abstract Gender getGender();

    /**
     * Is the pet allergy friendly determined by skin type
     * @param skin type of skin the pet has
     * @return A message that tells if the pet is hypoallergenic
     */
    public String petHypoallergenic(Skin skin)
    {
        String petHypoallergenicStmt;
        switch(skin)
        {
            case FUR:
            case FEATHERS:
                petHypoallergenicStmt = "The pet is not hyperallergetic!";
                break;
            case HAIR:
            case SCALES:
                petHypoallergenicStmt = "The pet is hyperallergetic!";
                break;
            default:
                petHypoallergenicStmt = "The pet skin is UNKNOWN at this time, so cannot determine if pet is " +
                        "hypoallergetic!";
                break;
        }
        return petHypoallergenicStmt;
    }


}
