package bll;

import java.util.ArrayList;
import java.util.List;

import bll.validators.ProductStockValidator;
import bll.validators.Validator;
import dao.ProductDAO;
import model.Product;

/**
 * The ProductBLL class provides business logic operations for managing products.
 */
public class ProductBLL {
    private final List<Validator<Product>> validators;

    /**
     * Constructs a new ProductBLL object with default validators.
     */
    public ProductBLL() {
        validators = new ArrayList<>();
        validators.add(new ProductStockValidator());
    }

    /**
     * Finds a product by its ID.
     *
     * @param id The ID of the product to find.
     * @return The product with the specified ID, or null if not found.
     */
    public Product findProductById(int id) {
        ProductDAO productDAO = new ProductDAO();
        return productDAO.findById(id);
    }

    /**
     * Inserts a new product into the database.
     *
     * @param product The product object to insert.
     * @return The ID of the inserted product.
     */
    public int insertProduct(Product product) {
        // Validate product data
        for (Validator<Product> v : validators){
            v.validate(product);
        }

        ProductDAO productDAO = new ProductDAO();
        Product insertedProduct = productDAO.insert(product);
        return insertedProduct.getId();
    }

    /**
     * Updates an existing product in the database.
     *
     * @param product The product object to update.
     * @return The updated product object.
     */
    public Product update(Product product)
    {
        // Validate product data
        for(Validator<Product> v : validators){
            v.validate(product);
        }

        ProductDAO productDAO = new ProductDAO();
        return productDAO.update(product);
    }

    /**
     * Deletes a product from the database.
     *
     * @param id The ID of the product to delete.
     */
    public void delete(int id)
    {
        ProductDAO productDAO = new ProductDAO();
        productDAO.delete(id);
    }
}
