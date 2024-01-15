package animals.petstore.store;

/**
 * Attempt to purchase an animal that is not present or a wild animal not part of the pet store
 */
public class PetNotFoundSaleException extends Exception {
    public PetNotFoundSaleException(String errorMessage) {
        super(errorMessage);
    }}
