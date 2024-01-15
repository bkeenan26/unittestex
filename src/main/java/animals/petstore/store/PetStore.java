package animals.petstore.store;

import animals.AnimalType;
import animals.petstore.pet.Pet;
import animals.petstore.pet.attributes.Breed;
import animals.petstore.pet.attributes.Gender;
import animals.petstore.pet.attributes.PetType;
import animals.petstore.pet.attributes.Skin;
import animals.petstore.pet.types.Cat;
import animals.petstore.pet.types.Dog;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class PetStore
{
    private List<Pet> petsForSale;

    private List<Pet> petsSold;

    private int petStoreId;
    public PetStore()
    {
        petsForSale = new ArrayList<Pet>();
        petsSold = new ArrayList<Pet>();

    }

    /**
     * Initialize the pet inventory list
     */
    public void init()
    {
        this.addPetInventoryItem(new Dog(AnimalType.DOMESTIC, Skin.FUR, Gender.MALE, Breed.MALTESE,
                new BigDecimal("750.00"), 3));
        this.addPetInventoryItem(new Dog(AnimalType.DOMESTIC, Skin.FUR, Gender.MALE, Breed.POODLE,
                new BigDecimal("650.00"), 1));
        this.addPetInventoryItem(new Cat(AnimalType.DOMESTIC, Skin.HAIR, Gender.MALE, Breed.BURMESE,
                new BigDecimal("65.00"),1));
        this.addPetInventoryItem(new Dog(AnimalType.DOMESTIC, Skin.HAIR, Gender.MALE, Breed.GERMAN_SHEPARD,
                new BigDecimal("50.00"), 2));
        this.addPetInventoryItem(new Cat(AnimalType.DOMESTIC, Skin.UNKNOWN, Gender.FEMALE, Breed.SPHYNX,
                new BigDecimal("100.00"),2));


    }

    /**
     * Print the inventory left in the pets for sale list
     */
    public void printInventory()
    {
        Consumer<Pet> action = System.out::println;
        List sortedPets = this.petsForSale.stream()
                .sorted(Comparator.comparing(Pet::getPetType))
                .collect(Collectors.toList());
        sortedPets.stream()
                .forEach(action);
    }

    /**
     * Remove pet items from the petsForSale list
     * @param soldPet
     * @return
     * @throws DuplicatePetStoreRecordException
     * @throws PetNotFoundSaleException
     */
    public Pet soldPetItem(Pet soldPet) throws DuplicatePetStoreRecordException, PetNotFoundSaleException {
        if(soldPet.getPetStoreId()==0)
        {
            throw new PetNotFoundSaleException("The Pet is not part of the pet store!!");
        }
        if(soldPet instanceof Dog)
        {
            Dog foundDog = this.identifySoldDogFromInventory((Dog) soldPet);
            this.removePetFromInventoryByPetId(PetType.DOG, soldPet.getPetStoreId());
            return foundDog;
        }else {
            Cat foundCat = this.identifySoldCatFromInventory((Cat) soldPet);
            this.removePetFromInventoryByPetId(PetType.CAT, soldPet.getPetStoreId());
            return foundCat;
        }
    }

    /**
     * Add item to the inventory list
     * @param item
     */
    public void addPetInventoryItem(Pet item)
    {
        this.petsForSale.add(item);
    }

    /**
     * Remove the Pet from the pet store by id and type of pet
     * @param petType
     * @param petId
     */
    private void removePetFromInventoryByPetId(PetType petType, int petId)
    {
        List<Pet> otherPets = this.petsForSale.stream()
                .filter(p -> (p.getPetType() != petType))
                .collect(Collectors.toList());
        switch(petType)
        {
            case CAT:
                this.petsForSale = this.petsForSale.stream()
                        .filter(p -> ((p instanceof Cat)
                                && (p.getPetStoreId() != petId)))
                        .collect(Collectors.toList());
                break;
            default: //remove the dog
                this.petsForSale = this.petsForSale.stream()
                        .filter(p -> ((p instanceof Dog)
                                && (p.getPetStoreId() != petId)))
                        .collect(Collectors.toList());

                break;
        }
        this.petsForSale.addAll(otherPets);
    }

    /**
     * Identify the Dog to remove from the inventory list
     * @param soldDog
     * @return
     * @throws DuplicatePetStoreRecordException
     */
    private Dog identifySoldDogFromInventory(Dog soldDog) throws DuplicatePetStoreRecordException
    {
        List<Pet> dogPets = this.petsForSale.stream()
                .filter(p -> ((p instanceof Dog)
                        && (p.getPetStoreId() == soldDog.getPetStoreId())))
                .collect(Collectors.toList());

        if(dogPets.isEmpty())
        {
            return null;
        }
        if(dogPets.size()==1)
        {
            return (Dog) dogPets.get(0);
        }
        else {
            throw new DuplicatePetStoreRecordException ("Duplicate Dog record id[" + soldDog.getPetStoreId() + "]");
        }
    }
    /**
     * Identify the cat which was sold from the inventory list
     */
    private Cat identifySoldCatFromInventory(Cat soldCat) throws DuplicatePetStoreRecordException
    {
        List<Pet> catPets = this.petsForSale.stream()
                .filter(p -> ((p instanceof Cat)
                        && (p.getPetStoreId() == soldCat.getPetStoreId())))
                .collect(Collectors.toList());

        if(catPets.isEmpty())
        {
            return null;
        }
        if(catPets.size()==1)
        {
            return (Cat) catPets.get(0);
        }
        else {
            throw new DuplicatePetStoreRecordException ("Duplicate Cat record id[" + soldCat.getPetStoreId() + "]");
        }
    }
    public List<Pet> getPetsForSale()
    {
        return petsForSale;
    }

}
