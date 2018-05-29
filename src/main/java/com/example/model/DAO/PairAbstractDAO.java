package com.example.model.DAO;

import com.example.model.exeptions.DatabaseExepton;
import com.example.model.exeptions.PersonException;
import com.example.model.pojos.IIdentifiable;
import com.example.model.pojos.Product;
import com.example.model.pojos.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import static com.example.model.DAO.PersonAbstractDAO.INVALID_UPDATE;

public abstract class PairAbstractDAO extends AbstractDAO {

    public PairAbstractDAO() throws DatabaseExepton {
    }


    protected void manage(User user, Product product, String statement) throws PersonException {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setInt(2, product.getId());
            int update = preparedStatement.executeUpdate();
            if (update == INVALID_UPDATE) throw new PersonException("Invalid update");
        } catch (SQLException e) {
            throw new PersonException("Invalid update! " + e.getMessage(), e);
        }
    }

    protected Set<Integer> getAll(IIdentifiable iIdentifiable, String statement) throws PersonException {
        Set<Integer> productIndexsexs = new HashSet<Integer>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setInt(1, iIdentifiable.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                productIndexsexs.add(resultSet.getInt(1));
            }
            return productIndexsexs;
        } catch (SQLException e) {
            throw new PersonException("No favourite." + e.getMessage(), e);
        }
    }


}
