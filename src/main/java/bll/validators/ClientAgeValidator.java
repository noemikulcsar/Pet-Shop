package bll.validators;

import model.Client;

/**
 * The ClientAgeValidator class validates the age of a Client object.
 * It ensures that the age is within a specified range.
 */
public class ClientAgeValidator implements Validator<Client> {

    private static final int MIN_AGE = 18; // Minimum age allowed
    private static final int MAX_AGE = 100; // Maximum age allowed

    /**
     * Validates the age of a Client object.
     *
     * @param client the Client object to validate
     * @throws IllegalArgumentException if the age is not within the specified range
     */
    public void validate(Client client) {
        if (client.getAge() < MIN_AGE || client.getAge() > MAX_AGE) {
            throw new IllegalArgumentException("The Client Age limit is not respected!");
        }
    }
}
