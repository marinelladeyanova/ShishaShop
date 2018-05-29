package com.example.model.DAO;

import com.example.model.exeptions.DatabaseExepton;
import com.example.model.exeptions.DiscountException;
import com.example.model.exeptions.UpdateException;
import com.example.model.pojos.Discount;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static java.sql.Date.valueOf;

public class DiscountDAO extends AbstractDAO {
    private static final String CREATE_DISCOUNT = "INSERT INTO discounts (`from`, `to`, percent) VALUES ( ?, ?, ?);";
    private static final String ADD_TO_PRODUCT = "UPDATE products SET discount = ? WHERE product_id = ?;";
    private static final String GET_BY_ID = "SELECT `from`, `to`, percent  FROM discounts WHERE discount_id = ?;";



    private static final int INVALID_UPDATE = 0;


    public DiscountDAO() throws DatabaseExepton {
    }

    public void createNewDiscount(LocalDate start, LocalDate end, double percent ) throws UpdateException {

        try {
            Discount discount = new Discount(start, end, percent);
        } catch (DiscountException discountException) {
            discountException.printStackTrace();
        }
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(CREATE_DISCOUNT);
            preparedStatement.setDate(1, valueOf(start));
            preparedStatement.setDate(2, valueOf(end));
            preparedStatement.setDouble(3, percent);

            int update = preparedStatement.executeUpdate();
            if (update == INVALID_UPDATE) throw new UpdateException("Invalid update");
        } catch (SQLException e) {
            throw new UpdateException("Invalid update! " + e.getMessage(), e);
        }
    }

    public void addDiscountToProduct(int discountid, int productId) throws UpdateException {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(ADD_TO_PRODUCT);
            preparedStatement.setInt(1, discountid);
            preparedStatement.setInt(2, productId);

            int update = preparedStatement.executeUpdate();
            if (update == INVALID_UPDATE) throw new UpdateException("Invalid update");
        } catch (SQLException e) {
            throw new UpdateException("Invalid update! " + e.getMessage(), e);
        }
    }


    public Discount getDiscount(int id) throws UpdateException {
        Discount discount;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(GET_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            discount = new Discount(
                    resultSet.getDate(1).toLocalDate(),
                    resultSet.getDate(2).toLocalDate(),
                    resultSet.getDouble(3));

            return discount;
        } catch (DiscountException | SQLException e) {
            throw new UpdateException("Invalid update." + e.getMessage(), e);
        }
    }




}
