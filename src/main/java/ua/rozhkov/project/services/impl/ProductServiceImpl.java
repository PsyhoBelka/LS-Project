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
        //TODO fix here
        //        return productDAO.create(newProduct);
        return 1;
    }

    @Override
    public Product getProduct(long idProduct) {
        if (idProduct >= 0) {
            return productDAO.get(idProduct);
        }
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return productDAO.getAll();
    }

    @Override
    public boolean updateProduct(long idProduct, String name, BigDecimal price) {
        if (idProduct >= 0) {
            Product updProduct = getProduct(idProduct);
            if (!name.isEmpty())
                updProduct.setName(name);
            if (price.equals(BigDecimal.ZERO))
                updProduct.setPrice(price);
            return productDAO.update(idProduct, updProduct);
        }
        return false;
    }

    @Override
    public boolean deleteProduct(long idProduct) {
        if (idProduct >= 0)
            return productDAO.delete(idProduct);
        return false;
    }
}
