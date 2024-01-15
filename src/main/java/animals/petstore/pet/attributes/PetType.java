package animals.petstore.pet.attributes;

/**
 * The different types of PETS that are available
 */
public enum PetType  {
    BIRD("Chirp"),
    CAT("Meow"),
    DOG("Bark"),
    SNAKE("Psss"),
    UNKNOWN("");

    public final String speak;

    private PetType(String speak) {
        this.speak = speak;
    }
}
