package animals.petstore.pet;

import animals.petstore.pet.attributes.Gender;
import animals.petstore.pet.attributes.PetType;

import java.math.BigDecimal;

/**
 * Pet class is a type really for Domestic Animals
 */
public class Pet extends AbstractPet
{
    private BigDecimal cost;

    protected int petStoreId; //id will be populated if animal type is PET

    /**
     * Constructor
     * @param petType The {@link PetType}
     * @param cost The cost of the pet
     * @param gender The {@link Gender} of the cat
     */
    public Pet(PetType petType, BigDecimal cost, Gender gender)
    {
        this(petType, cost, gender, 0);
     }

    /**
     * Constructor
     * @param petType The {@link PetType}
     * @param cost The cost of the pet
     * @param gender The {@link Gender} of the cat
     * @param petStoreId The pet store id
     */
    public Pet(PetType petType, BigDecimal cost, Gender gender, int petStoreId)
    {

        this.gender = gender;
        this.petType = petType;
        this.cost = cost;
        this.petStoreId=petStoreId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Gender getGender() {
        return this.gender;
    }

    public BigDecimal getCost()
    {
        return this.cost;
    }

    public PetType getPetType() {
        return this.petType;
    }

    public void setPetStoreId(int storeId)
    {
       this.petStoreId = storeId;
    }

    public int getPetStoreId()
    {
        return this.petStoreId;
    }

    public String toString()
    {
        if(this.getPetStoreId() == 0)
        {
            return "The type of pet is " + this.petType + "!\n" +
                    "The "+ this.petType + " gender is " + this.gender + "!\n" +
                    "The "+ this.petType + " cost is $" + this.cost + "!\n";
        }
        else {
            return "The type of pet is " + this.petType + "!\n" +
                    "The "+ this.petType + " pet store id is " + this.petStoreId + "!\n" +
                    "The "+ this.petType + " gender is " + this.gender + "!\n" +
                    "The "+ this.petType + " cost is $" + this.cost + "!\n";
        }

    }

}
