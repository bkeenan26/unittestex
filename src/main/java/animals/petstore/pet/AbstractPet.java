package animals.petstore.pet;

import animals.AnimalType;
import animals.petstore.pet.attributes.Gender;
import animals.petstore.pet.attributes.PetType;
import animals.petstore.pet.attributes.Skin;

/**
 * Common attributes of a pet
 */
public abstract class  AbstractPet {
    public AbstractPet()
    {

    }
    private AnimalType animalType = AnimalType.UNKNOWN;
    private PetType petType = PetType.UNKNOWN;
    private static Skin skinType = Skin.UNKNOWN;
    private Gender gender = Gender.UNKNOWN;

    boolean hasLegs = true;
    boolean isMammal = false;
    public abstract Gender getGender();

    /**
     * Is the pet allergy friendly determined by skin type
     * @param skin
     * @return
     */
    public String isPetHyperAllergetic(Skin skin)
    {
        String isPetHyperAllergeticStmt;
        switch(skin)
        {
            case FUR:
            case FEATHERS:
                isPetHyperAllergeticStmt = "The pet is not hyperallergetic!";
                break;
            case HAIR:
            case SCALES:
                isPetHyperAllergeticStmt = "The pet is hyperallergetic!";
                break;
            default:
                isPetHyperAllergeticStmt = "The pet skin is UNKNOWN at this time, so cannot determine if pet is " +
                        "hypoallergetic!";
                break;
        }
        return isPetHyperAllergeticStmt;
    }


}
