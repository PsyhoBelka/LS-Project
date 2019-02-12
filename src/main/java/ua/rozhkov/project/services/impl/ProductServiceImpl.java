package ua.rozhkov.project.services.impl;

import ua.rozhkov.project.dao.ProductDAO;
import ua.rozhkov.project.dao.impl.ProductDAOImpl;
import ua.rozhkov.project.models.Product;
import ua.rozhkov.project.services.ProductService;

import java.math.BigDecimal;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final ProductDAO productDAO = new ProductDAOImpl();

    /**
     * Create new product
     *
     * @param name  name of new product
     * @param price price of new product
     * @return id of newly created product
     */
    @Override
    public long createProduct(String name, BigDecimal price) {
        Product newProduct = new Product(name, price);
        return productDAO.create(newProduct);
    }

    /**
     * Return product with specified id
     *
     * @param id id of product to be returned
     * @return instance of product with specified id
     */
    @Override
    public Product readProduct(long id) {
        productDAO.get(id);
        return null;
    }

    /**
     * Return all available products
     *
     * @return list of all available products
     */
    @Override
    public List<Product> readAllProducts() {
        return productDAO.getAll();
    }

    /**
     * Update some product with specified data
     *
     * @param id    id of product-to-update
     * @param name  new product's name
     * @param price new product's price
     * @return true, if operation successfully
     */
    @Override
    public boolean updateProduct(long id, String name, BigDecimal price) {
        Product updProduct = readProduct(id);
        if (!name.equals("")) updProduct.setName(name);
        if (price.equals(BigDecimal.ZERO)) updProduct.setPrice(price);
        return productDAO.update(id, updProduct);
    }

    /**
     * Delete specified product
     *
     * @param id id of product-to-delete
     * @return true, if operation successfully
     */
    @Override
    public boolean deleteProduct(long id) {
        return productDAO.delete(id);
    }
}
