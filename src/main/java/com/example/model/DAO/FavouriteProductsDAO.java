package com.example.model.DAO;

import com.example.model.exeptions.DatabaseExepton;
import com.example.model.exeptions.PersonException;
import com.example.model.pojos.Product;
import com.example.model.pojos.User;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class FavouriteProductsDAO extends PairAbstractDAO implements IFavouriteProductsDAO {

    private static final String ADD_FAVOURITE_PRODUCT = "INSERT INTO favourite_products(user_id, product_id) VALUES (?, ?)";
    private static final String REMOVE_FAVOURITE_PRODUCT = "DELETE FROM favourite_products WHERE (user_id = ? AND product_id = ?)";
    private static final String ALL_FAVOURITE_PRODUCT = "SELECT product_id FROM favourite_products WHERE user_id = ?;";
    private static final String ALL_FAVOURITE_USERS = "SELECT user_id FROM favourite_products WHERE product_id = ?;";


    public FavouriteProductsDAO() throws DatabaseExepton {
    }



    @Override
    public void addFavouriteProduct(User user, Product product) throws PersonException {
        manage(user, product, ADD_FAVOURITE_PRODUCT);
    }

    @Override
    public void removeFavouriteProduct(User user, Product product) throws PersonException {
        manage(user, product, REMOVE_FAVOURITE_PRODUCT);
    }

    @Override
    public Set<Integer> getAllFavouriteProducts(User user) throws PersonException {
        return getAll(user, ALL_FAVOURITE_PRODUCT);
    }

    @Override
    public Set<Integer> getAllFavoredUsers(User product) throws PersonException {
       return getAll(product, ALL_FAVOURITE_USERS);
    }


}
