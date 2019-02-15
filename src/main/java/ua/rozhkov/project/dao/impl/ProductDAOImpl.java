package ua.rozhkov.project.dao.impl;

import ua.rozhkov.project.dao.ProductDAO;
import ua.rozhkov.project.models.Product;

import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    private static volatile ProductDAOImpl instance;

    private ProductDAOImpl() {
    }

    public static ProductDAOImpl getInstance() {
        if (instance == null)
            synchronized (ProductDAOImpl.class) {
                if (instance == null)
                    instance = new ProductDAOImpl();
            }
        return instance;
    }

    @Override
    public long create(Product newEntity) {
        System.out.println("Product created! " + newEntity);
        return 0;
    }

    @Override
    public Product get(long idEntity) {
        System.out.println("Some product");
        return null;
    }

    @Override
    public List<Product> getAll() {
        System.out.println("Some product 1");
        System.out.println("Some product 2");
        System.out.println("Some product 3");
        return null;
    }

    @Override
    public boolean update(long idEntity, Product updatedEntity) {
        System.out.println("Product updated!");
        return true;
    }

    @Override
    public boolean delete(long idEntity) {
        System.out.println("Product deleted!");
        return true;
    }
}
