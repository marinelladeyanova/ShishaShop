package com.example.model.DAO;

import com.example.model.exeptions.DatabaseExepton;
import com.example.model.exeptions.PersonException;
import com.example.model.exeptions.ReviewExeption;
import com.example.model.pojos.Product;
import com.example.model.pojos.Rate;
import com.example.model.pojos.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Set;

import static com.example.model.DAO.PersonAbstractDAO.INVALID_UPDATE;
import static java.sql.Date.valueOf;

public class RateDAO extends PairAbstractDAO implements IRateDAO {

    private static final String ADD_RATE = "INSERT INTO rating(product_id, data, rating, users_id) VALUES (?, ?, ?, ?);";
    private static final String FIND_RATE_FROM_USER_FOR_PRODUCT = "SELECT rating_id FROM rating WHERE users_id = ? AND product_id = ?";

    private static final String ALL_RATES_FOR_PRODUCT = "SELECT rating_id FROM rating WHERE product_id = ?;";

    public RateDAO() throws DatabaseExepton {
    }


    @Override
    public boolean checkForExcistingRating(User user, Rate rate, Product product) throws PersonException, ReviewExeption {
        PreparedStatement preparedStatement;
        try {

            preparedStatement = connection.prepareStatement(FIND_RATE_FROM_USER_FOR_PRODUCT);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setInt(2, product.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
                return true;
            return false;
        } catch (SQLException e) {
            throw new PersonException("Invalid update! " + e.getMessage(), e);
        }
        }




    @Override
    public void addRating(User user, Rate rate, Product product) throws PersonException, ReviewExeption {
     PreparedStatement preparedStatement;
        try {
            if (checkForExcistingRating(user, rate, product))
                throw new ReviewExeption("Rating existing.");

            preparedStatement = connection.prepareStatement(ADD_RATE);
            preparedStatement.setInt(1, product.getId());
            preparedStatement.setDate(2, valueOf(LocalDate.now()));
            preparedStatement.setInt(3, rate.getRating());
            preparedStatement.setInt(4, user.getId());

            int update = preparedStatement.executeUpdate();
            if (update == INVALID_UPDATE) throw new PersonException("Invalid update");
        } catch (SQLException e) {
            throw new PersonException("Invalid update! " + e.getMessage(), e);
        }
    }


    @Override
    public Set<Integer> getAllRatingsForProduct(Product product) throws PersonException {
         return getAll(product, ALL_RATES_FOR_PRODUCT);
    }

}
