package animals.petstore.store;

/**
 * Exception if there are duplicate records in the pet store inventory list
 */
public class DuplicatePetStoreRecordException extends Exception {
    public DuplicatePetStoreRecordException(String errorMessage) {
        super(errorMessage);
    }}
