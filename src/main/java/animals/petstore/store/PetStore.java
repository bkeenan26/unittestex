package animals.petstore.store;

import animals.AnimalType;
import animals.petstore.pet.Pet;
import animals.petstore.pet.attributes.Breed;
import animals.petstore.pet.attributes.Gender;
import animals.petstore.pet.attributes.PetType;
import animals.petstore.pet.attributes.Skin;
import animals.petstore.pet.types.Cat;
import animals.petstore.pet.types.Dog;
import animals.petstore.pet.types.Snake;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class PetStore {
    private List<Pet> petsForSale;
    private List<Pet> petsSold;

    public PetStore() {
        petsForSale = new ArrayList<>();
        petsSold = new ArrayList<>();
    }

    public void init() {
        this.addPetInventoryItem(new Dog(AnimalType.DOMESTIC, Skin.FUR, Gender.MALE, Breed.MALTESE,
                new BigDecimal("750.00"), 3));
        this.addPetInventoryItem(new Dog(AnimalType.DOMESTIC, Skin.FUR, Gender.MALE, Breed.POODLE,
                new BigDecimal("650.00"), 1));
        this.addPetInventoryItem(new Cat(AnimalType.DOMESTIC, Skin.HAIR, Gender.MALE, Breed.BURMESE,
                new BigDecimal("65.00"), 1));
        this.addPetInventoryItem(new Dog(AnimalType.DOMESTIC, Skin.HAIR, Gender.MALE, Breed.GERMAN_SHEPARD,
                new BigDecimal("50.00"), 2));
        this.addPetInventoryItem(new Cat(AnimalType.DOMESTIC, Skin.UNKNOWN, Gender.FEMALE, Breed.SPHYNX,
                new BigDecimal("100.00"), 2));
        this.addPetInventoryItem(new Snake(AnimalType.DOMESTIC, Skin.SCALES, Gender.FEMALE, Breed.BALL_PYTHON,
                new BigDecimal("150.00"), 5));
    }

    public void initAddDuplicateItem(Pet addPet) {
        this.init();
        this.addPetInventoryItem(addPet);
    }

    public void printInventory() {
        Consumer<Pet> action = System.out::println;
        List sortedPets = this.petsForSale.stream()
                .sorted(Comparator.comparing(Pet::getPetType))
                .collect(Collectors.toList());
        sortedPets.stream().forEach(action);
    }

    public Pet soldPetItem(Pet soldPet) throws DuplicatePetStoreRecordException, PetNotFoundSaleException {
        if (soldPet.getPetStoreId() == 0) {
            throw new PetNotFoundSaleException("The Pet is not part of the pet store!!");
        } else if (soldPet instanceof Dog) {
            Dog foundDog = this.identifySoldDogFromInventory((Dog) soldPet);
            this.removePetFromInventoryByPetId(PetType.DOG, soldPet.getPetStoreId());
            return foundDog;
        } else if (soldPet instanceof Cat) {
            Cat foundCat = this.identifySoldCatFromInventory((Cat) soldPet);
            this.removePetFromInventoryByPetId(PetType.CAT, soldPet.getPetStoreId());
            return foundCat;
        } else if (soldPet instanceof Snake) {
            Snake foundSnake = this.identifySoldSnakeFromInventory((Snake) soldPet);
            this.removePetFromInventoryByPetId(PetType.SNAKE, soldPet.getPetStoreId());
            return foundSnake;
        } else {
            throw new PetNotFoundSaleException("Unknown pet type!");
        }
    }

    public void addPetInventoryItem(Pet pet) {
        this.petsForSale.add(pet);
    }

    private void removePetFromInventoryByPetId(PetType petType, int petStoreId) {
        List<Pet> otherPets = this.petsForSale.stream()
                .filter(p -> (p.getPetType() != petType))
                .collect(Collectors.toList());
        switch (petType) {
            case CAT:
                this.petsForSale = this.petsForSale.stream()
                        .filter(p -> !(p instanceof Cat && p.getPetStoreId() == petStoreId))
                        .collect(Collectors.toList());
                break;
            case DOG:
                this.petsForSale = this.petsForSale.stream()
                        .filter(p -> !(p instanceof Dog && p.getPetStoreId() == petStoreId))
                        .collect(Collectors.toList());
                break;
            case SNAKE:
                this.petsForSale = this.petsForSale.stream()
                        .filter(p -> !(p instanceof Snake && p.getPetStoreId() == petStoreId))
                        .collect(Collectors.toList());
                break;
        }
        this.petsForSale.addAll(otherPets);
    }

    private Dog identifySoldDogFromInventory(Dog soldDog) throws DuplicatePetStoreRecordException {
        List<Pet> dogPets = this.petsForSale.stream()
                .filter(p -> (p instanceof Dog && p.getPetStoreId() == soldDog.getPetStoreId()))
                .collect(Collectors.toList());
        if (dogPets.size() == 1) {
            return (Dog) dogPets.get(0);
        } else {
            throw new DuplicatePetStoreRecordException("Duplicate Dog record store id [" + soldDog.getPetStoreId() + "]");
        }
    }

    private Cat identifySoldCatFromInventory(Cat soldCat) throws DuplicatePetStoreRecordException {
        List<Pet> catPets = this.petsForSale.stream()
                .filter(p -> (p instanceof Cat && p.getPetStoreId() == soldCat.getPetStoreId()))
                .collect(Collectors.toList());
        if (catPets.size() == 1) {
            return (Cat) catPets.get(0);
        } else {
            throw new DuplicatePetStoreRecordException("Duplicate Cat record store id [" + soldCat.getPetStoreId() + "]");
        }
    }

    private Snake identifySoldSnakeFromInventory(Snake soldSnake) throws DuplicatePetStoreRecordException {
        List<Pet> snakePets = this.petsForSale.stream()
                .filter(p -> (p instanceof Snake && p.getPetStoreId() == soldSnake.getPetStoreId()))
                .collect(Collectors.toList());
        if (snakePets.size() == 1) {
            return (Snake) snakePets.get(0);
        } else {
            throw new DuplicatePetStoreRecordException("Duplicate Snake record store id [" + soldSnake.getPetStoreId() + "]");
        }
    }

    public List<Pet> getPetsForSale() {
        return petsForSale;
    }
}
