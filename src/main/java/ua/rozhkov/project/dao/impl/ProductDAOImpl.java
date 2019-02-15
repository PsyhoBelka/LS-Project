package ua.rozhkov.project.dao.impl;

import ua.rozhkov.project.dao.ProductDAO;
import ua.rozhkov.project.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    private static volatile ProductDAOImpl instance;

    private static List<Product> productsT = new ArrayList<>();
    private static long index = 0;

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
        newEntity.setId(index++);
        productsT.add(newEntity);
        return index - 1;
    }

    @Override
    public Product get(long idEntity) {
//        System.out.println("Some product");
        for (Product product : productsT) {
            if (product.getId() == idEntity) return product;
        }
        return null;
    }

    @Override
    public List<Product> getAll() {
//        System.out.println("Some product 1");
//        System.out.println("Some product 2");
//        System.out.println("Some product 3");
        return productsT;
    }

    @Override
    public boolean update(long idEntity, Product updatedEntity) {
//        System.out.println("Product updated!");
        Product tmp = get(idEntity);

        if (tmp != null) {
            updatedEntity.setId(tmp.getId());
            tmp = updatedEntity;
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(long idEntity) {
//        System.out.println("Product deleted!");
        Product tmp = get(idEntity);
        if (tmp != null) {
            productsT.remove(tmp);
            return true;
        }
        return false;
    }
}
