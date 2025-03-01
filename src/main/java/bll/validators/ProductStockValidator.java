package bll.validators;

import model.Product;

/**
 * The ProductStockValidator class validates the stock of a Product object.
 * It ensures that the stock quantity is not below a certain minimum threshold.
 */
public class ProductStockValidator implements Validator<Product>
{
    // Minimum allowed stock quantity
    private static final int MIN_STOCK = 0;

    /**
     * Validates the stock quantity of a Product object.
     *
     * @param t The Product object whose stock needs to be validated.
     * @throws IllegalArgumentException if the stock quantity is below the minimum threshold.
     */
    public void validate(Product t)
    {
        if (t.getStock() < MIN_STOCK){
            throw new IllegalArgumentException("The stock limit is not respected!");
        }
    }
}
