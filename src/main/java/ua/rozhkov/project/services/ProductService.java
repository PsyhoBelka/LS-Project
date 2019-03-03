package ua.rozhkov.project.services;

import ua.rozhkov.project.models.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    /**
     * Create new product
     *
     * @param name  name of new product
     * @param price price of new product
     * @return true, if operation successfully
     */
    boolean createProduct(String name, BigDecimal price);

    /**
     * Return product with specified id
     *
     * @param id id of product to be returned
     * @return instance of product with specified id
     */
    Product getProduct(long id);

    /**
     * Return all available products
     *
     * @return list of all available products
     */
    List<Product> getAllProducts();

    /**
     * Update some product with specified data
     *
     * @param id    id of product-to-update
     * @param name  new product's name
     * @param price new product's price
     * @return true, if operation successfully
     */
    boolean updateProduct(long id, String name, BigDecimal price);

    /**
     * Delete specified product
     *
     * @param id id of product-to-delete
     * @return true, if operation successfully
     */
    boolean deleteProduct(long id);
}
