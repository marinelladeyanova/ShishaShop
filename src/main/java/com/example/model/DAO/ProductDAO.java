package com.example.model.DAO;

import com.example.model.exeptions.DatabaseExepton;
import com.example.model.exeptions.UpdateException;

import com.example.model.pojos.Characteristic;
import com.example.model.pojos.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.*;

import static com.example.model.DAO.PersonAbstractDAO.INVALID_UPDATE;

@Component
public class ProductDAO extends AbstractDAO implements IProductDAO {

    private static final String ADD_PRODUCT =
            "INSERT INTO products(name, descriotion, price, quantity, data, photo, category_id) VALUES (?, ?, ?, ?, DATE(NOW()), ?, ?);";
 //   private static final String FIND_QUANTITY = "SELECT quantity FROM products WHERE product_id = ?;";
    private static final String EDIT_PRODUCT =
         "UPDATE products SET name = ?, descriotion = ?, price = ?, quantity = ?, photo = ?, category_id = ? WHERE product_id = ?;";
    private static final String REMOVE_PRODUCT =
            "UPDATE products SET deleted = 1 WHERE product_id = ?;";
    private static final String RESTORE_PRODUCT =
            "UPDATE products SET deleted = NULL WHERE product_id = ?;";
    private static final String ALL_PRODUCTS =
            "SELECT product_id FROM products WHERE deleted IS NULL;";
    private static final String ALL_PRODUCTS_IN_CATEGORY =
            "SELECT product_id, name, descriotion, price, quantity, data, photo, discount FROM products WHERE deleted IS NULL AND category_id = ?;";
    private static final String GET_PRODUCT_BY_NAME =
            "SELECT product_id, descriotion, price, quantity, data, photo, category_id, discount FROM products WHERE deleted IS NULL AND name = ?;";
    private static final String GET_RELATED =
          "SELECT p.product_id, p.name, p.price, p.photo \n" +
                  "FROM products p \n" +
                  "\tINNER JOIN product_characteristics ch \n" +
                  "    ON p.product_id = ch.product_id \n" +
                  "\t\tWHERE ch.characteristic_id \n" +
                  "\t\t\tIN (SELECT characteristic_id FROM product_characteristics \n" +
                  "\t\t\t\tWHERE product_id = ?)\n" +
                  "\t\tAND p.product_id != ?\n" +
                  "\tLIMIT ?\n" +
                  ";";

    private static final String ALL_IN_CATEGORY_AND_SUBS =
            "SELECT product_id, name, descriotion, price, quantity, data, photo, category_id, discount FROM products WHERE category_id IN (SELECT category_id FROM categories WHERE category_id = ? OR parrent_category_id = ?) AND deleted IS NULL;";
    private static final String ALL_IN_CATEGORY_AND_SUBS_WITH_CHAR =
            "SELECT product_id, name, descriotion, price, quantity, data, photo, category_id, discount FROM products WHERE category_id IN (SELECT category_id FROM categories WHERE category_id = ? OR parrent_category_id = ?) AND deleted IS NULL AND product_id IN (SELECT product_id FROM product_characteristics WHERE characteristic_id = ?);";


    private  static final String GET_PHOTOS = "SELECT url FROM photos WHERE product_id = ?;";
    private  static final String GET_CHARACTERISTICS = "SELECT characteristic_id, name, value_text FROM characteristics WHERE characteristic_id IN (SELECT characteristic_id FROM product_characteristics WHERE product_id = ?);";
    private  static final String GET_CATEGORY_ID = "SELECT category_id FROM products WHERE product_id = ?;";
    private static final int RELATED_LIMIT = 4;


    @Autowired
    CategoryDAO categoryDAO;



    public ProductDAO() throws DatabaseExepton {
    }

    @Override
    public void addNewProduct(Product product, int categoryId) throws UpdateException {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(ADD_PRODUCT);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getQuantity());
            preparedStatement.setString(5, product.getPhotoUrl());
            preparedStatement.setInt(6, categoryId);

            int update = preparedStatement.executeUpdate();
            if (update == INVALID_UPDATE) throw new UpdateException("Invalid update");
        } catch (SQLException e) {
            throw new UpdateException("Invalid update. " + e.getMessage(), e);
        }
    }

    @Override
    public void editProduct(Product product) throws UpdateException {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(EDIT_PRODUCT);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getQuantity());
            preparedStatement.setString(5, product.getPhotoUrl());
            preparedStatement.setInt(6, product.getCategory().getId());
            preparedStatement.setInt(7, product.getId());

            int update = preparedStatement.executeUpdate();
            if (update == INVALID_UPDATE) throw new UpdateException("Invalid update");
        } catch (SQLException e) {
            throw new UpdateException("Invalid update. " + e.getMessage(), e);
        }
    }

    @Override
    public void removeProduct(Product product) throws UpdateException {
        PreparedStatement preparedStatement;
        manage(product, REMOVE_PRODUCT);
    }

    @Override
    public void restoreProduct(Product product) throws UpdateException {
        PreparedStatement preparedStatement;
        manage(product, RESTORE_PRODUCT);
    }

    @Override
    public Set<Product> getAllProductsInCategory(int id) throws UpdateException {
        Set<Product> products = new TreeSet<>(Comparator.comparing(Product::getId));
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(ALL_PRODUCTS_IN_CATEGORY);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                products.add(new Product(
                        resultSet.getInt(1),                   // id,
                        resultSet.getString(2),                // name
                        resultSet.getString(3),                // description
                        resultSet.getDouble(4),                // price
                        resultSet.getInt(5),                   // quantity
                        resultSet.getDate(6).toLocalDate(),    // LocalDate date
                        resultSet.getString(7),                // photoUrl
                        new CategoryDAO().getCategory(id),
                        resultSet.getString(8) == null ? null : new DiscountDAO().getDiscount(resultSet.getInt(8))
                ));
            }
        } catch (DatabaseExepton | SQLException e) {
            throw new UpdateException("Invalid operation" + e.getMessage(), e);
        }
        return products;
    }

    @Override
    public Product getProduct(String name) throws UpdateException {
        PreparedStatement preparedStatement;
        Product product;
        try {
            preparedStatement = connection.prepareStatement(GET_PRODUCT_BY_NAME);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            product = new Product(
                    resultSet.getInt(1),                   // id,
                    name,                                              // name
                    resultSet.getString(2),                // description
                    resultSet.getDouble(3),                // price
                    resultSet.getInt(4),                   // quantity
                    resultSet.getDate(5).toLocalDate(),    // LocalDate date
                    resultSet.getString(6),                // photoUrl
                    new CategoryDAO().getCategory(resultSet.getString(7)),
                    resultSet.getString(8) == null ? null : new DiscountDAO().getDiscount(resultSet.getInt(8))
            );


            preparedStatement = connection.prepareStatement(GET_PHOTOS);
            preparedStatement.setInt(1, product.getId());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                product.addToPhotos(resultSet.getString(1));
            }

            preparedStatement = connection.prepareStatement(GET_CHARACTERISTICS);
            preparedStatement.setInt(1, product.getId());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                product.addCharacteristic(
                        new Characteristic(
                            resultSet.getInt(1),
                                resultSet.getString(2),
                                resultSet.getString(3)
                        ));
            }

        } catch (DatabaseExepton | SQLException e) {
            throw new UpdateException("Invalid operation" + e.getMessage(), e);
        }
        return product;
    }


    @Override
    public Set<Product> getRelatedProducts(int id) throws UpdateException {
        PreparedStatement preparedStatement;
        Set<Product> products = new HashSet<>();
        try {
            preparedStatement = connection.prepareStatement(GET_RELATED);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, id);
            preparedStatement.setInt(3, RELATED_LIMIT);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getString(4)
                );
                if (product.getId() != id)
                    products.add(product);
            }

            for (Product product : products) {
                preparedStatement = connection.prepareStatement(GET_CATEGORY_ID);
                preparedStatement.setInt(1, id);
                resultSet = preparedStatement.executeQuery();
                resultSet.next();
                product.setCategory(categoryDAO.getCategory(resultSet.getInt(1)));
            }

        } catch (SQLException e) {
            throw new UpdateException("Invalid operation" + e.getMessage(), e);
        }
        return products;
    }

    private void manage(Product product, String restoreProduct) throws UpdateException {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(restoreProduct);
            preparedStatement.setInt(1, product.getId());

            int update = preparedStatement.executeUpdate();
            if (update == INVALID_UPDATE) throw new UpdateException("Invalid update");
        } catch (SQLException e) {
            throw new UpdateException("Invalid update. " + e.getMessage(), e);
        }
    }

    @Override
    public Set<Product> getAllInCategoryAndSub(int categoryId) throws UpdateException {
        Set<Product> products = new TreeSet<>(Comparator.comparing(Product::getId));
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(ALL_IN_CATEGORY_AND_SUBS);
            preparedStatement.setInt(1, categoryId);
            preparedStatement.setInt(2, categoryId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                products.add(new Product(
                        resultSet.getInt(1),                                     // id,
                        resultSet.getString(2),                                  // name
                        resultSet.getString(3),                                  // description
                        resultSet.getDouble(4),                                  // price
                        resultSet.getInt(5),                                     // quantity
                        resultSet.getDate(6).toLocalDate(),                      // LocalDate date
                        resultSet.getString(7),                                  // photoUrl
                        categoryDAO.getCategory(resultSet.getInt(8)),      // category id
                        resultSet.getString(9) == null ? null : new DiscountDAO().getDiscount(resultSet.getInt(9))
                ));
            }

        } catch (DatabaseExepton | SQLException | UpdateException e) {
            throw new UpdateException("Invalid operation" + e.getMessage(), e);
        }
        return products;
    }



    @Override
    public Set<Product> getAllInCategoryAndSubWithChar(int categoryId, int characteristicId) throws UpdateException {
        Set<Product> products = new TreeSet<>(Comparator.comparing(Product::getId));
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(ALL_IN_CATEGORY_AND_SUBS_WITH_CHAR);
            preparedStatement.setInt(1, categoryId);
            preparedStatement.setInt(2, categoryId);
            preparedStatement.setInt(3, characteristicId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                products.add(new Product(
                        resultSet.getInt(1),                   // id,
                        resultSet.getString(2),                // name
                        resultSet.getString(3),                // description
                        resultSet.getDouble(4),                // price
                        resultSet.getInt(5),                   // quantity
                        resultSet.getDate(6).toLocalDate(),    // LocalDate date
                        resultSet.getString(7),                // photoUrl
                        categoryDAO.getCategory(resultSet.getInt(8)),// category id
                        resultSet.getString(9) == null ? null : new DiscountDAO().getDiscount(resultSet.getInt(9))
                ));
            }
        } catch (DatabaseExepton | SQLException | UpdateException e) {
            throw new UpdateException("Invalid operation" + e.getMessage(), e);
        }
        return products;
    }

    @Override
    public Set<Product> sortByPriceAsc(Set<Product> products){
        Set<Product> productSet = new TreeSet<>((firstProduct, secondProduct) -> {
            if (firstProduct.getCurrentPrice() == secondProduct.getCurrentPrice()) return -1;
            return (int) (firstProduct.getCurrentPrice() - secondProduct.getCurrentPrice());
        });
        productSet.addAll(products);
        return productSet;
    }

    @Override
    public Set<Product> sortByPriceDesc(Set<Product> products){
        Set<Product> productSet = new TreeSet<>((firstProduct, secondProduct) -> {
            if (firstProduct.getCurrentPrice() == secondProduct.getCurrentPrice()) return -1;
            return (int) (secondProduct.getCurrentPrice() - firstProduct.getCurrentPrice());
        });
        productSet.addAll(products);
        return productSet;

    }



}
