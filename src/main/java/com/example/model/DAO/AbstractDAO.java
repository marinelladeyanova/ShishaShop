package com.example.model.DAO;

import com.example.model.database_connection.DataBaseConnection;
import com.example.model.exeptions.AddressException;
import com.example.model.exeptions.DatabaseExepton;
import com.example.model.exeptions.PersonException;
import com.example.model.exeptions.UpdateException;
import com.example.model.pojos.PersonInformation;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public abstract class AbstractDAO {

    protected static final int INVALID_UPDATE = 0;




    protected Connection connection;

    public AbstractDAO() throws DatabaseExepton {
        this.connection = DataBaseConnection.getInstance().getConnection();
    }




    void update (String name, String statement) throws UpdateException {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1, name);
            int update = preparedStatement.executeUpdate();
            if (update == INVALID_UPDATE) throw new UpdateException("Invalid update");
        } catch (SQLException e) {
            throw new UpdateException("Invalid update! " + e.getMessage(), e);
        }
    }


    void manage(String name, int id, String statement) throws UpdateException {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            int update = preparedStatement.executeUpdate();
            if (update == INVALID_UPDATE) throw new UpdateException("Invalid update");
        } catch (SQLException e) {
            throw new UpdateException("Invalid update! " + e.getMessage(), e);
        }
    }


    void manage (int id, String statement) throws UpdateException {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setInt(1, id);
            int update = preparedStatement.executeUpdate();
            if (update == INVALID_UPDATE) throw new UpdateException("Invalid update");
        } catch (SQLException e) {
            throw new UpdateException("Invalid update! " + e.getMessage(), e);
        }
    }



}