package ua.rozhkov.project.services.impl;

import ua.rozhkov.project.dao.ProductDAO;
import ua.rozhkov.project.models.Product;
import ua.rozhkov.project.services.ProductService;

import java.math.BigDecimal;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private static volatile ProductService instance;
    private ProductDAO productDAO;

    private ProductServiceImpl() {
    }

    public static ProductService getInstance() {
        if (instance == null)
            synchronized (ProductServiceImpl.class) {
                if (instance == null)
                    instance = new ProductServiceImpl();
            }
        return instance;
    }

    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public long createProduct(String name, BigDecimal price) {
        Product newProduct = new Product(name, price);
        return productDAO.create(newProduct);
    }

    @Override
    public Product readProduct(long id) {
        productDAO.get(id);
        return null;
    }

    @Override
    public List<Product> readAllProducts() {
        return productDAO.getAll();
    }

    @Override
    public boolean updateProduct(long id, String name, BigDecimal price) {
        Product updProduct = readProduct(id);
        if (!name.isEmpty()) updProduct.setName(name);
        if (price.equals(BigDecimal.ZERO)) updProduct.setPrice(price);
        return productDAO.update(id, updProduct);
    }

    @Override
    public boolean deleteProduct(long id) {
        return productDAO.delete(id);
    }
}
