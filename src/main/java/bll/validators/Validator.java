package bll.validators;

/**
 * The Validator interface defines a contract for classes that perform validation.
 * It specifies a method to validate objects of a generic type.
 *
 * @param <T> The type of object to be validated.
 */
public interface Validator<T> {

    /**
     * Validates an object of type T.
     *
     * @param t The object to be validated.
     */
    public void validate(T t);
}
