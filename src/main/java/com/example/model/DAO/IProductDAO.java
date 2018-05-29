package com.example.model.DAO;

import com.example.model.exeptions.PersonException;
import com.example.model.exeptions.UpdateException;
import com.example.model.pojos.Category;
import com.example.model.pojos.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;


public interface IProductDAO {


    void addNewProduct(Product product, int categoryId) throws UpdateException;

    void editProduct(Product product) throws UpdateException;

    void removeProduct(Product product) throws UpdateException;

    void restoreProduct(Product product) throws UpdateException;

    Set<Product> getAllProductsInCategory(int id) throws UpdateException;

    Product getProduct(String name) throws UpdateException;

    Set<Product> getRelatedProducts(int id) throws UpdateException;

    Set<Product> getAllInCategoryAndSub(int categoryId) throws UpdateException;

    Set<Product> getAllInCategoryAndSubWithChar(int categoryId, int characteristicId) throws UpdateException;

    Set<Product> sortByPriceAsc(Set<Product> products);

    Set<Product> sortByPriceDesc(Set<Product> products);
}
