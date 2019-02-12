package ua.rozhkov.project.services;

import ua.rozhkov.project.models.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    boolean createProduct(String name, BigDecimal price);

    Product readProduct(long id);

    List<Product> readAllProducts();

    boolean updateProduct(long id, String name, BigDecimal price);

    boolean deleteProduct(long id);
}
