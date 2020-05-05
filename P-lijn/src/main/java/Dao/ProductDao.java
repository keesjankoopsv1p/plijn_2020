package Dao;

import Domain.Product;

import java.util.List;

public interface ProductDao{

    List<Product> findAll();
    Product findById(long id);
    Product save(Product product);
    Product update(Product product);
    boolean delete(Product product);
    void closeConnection();

}
