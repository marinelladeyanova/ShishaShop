package com.example.model.DAO;

import com.example.model.exeptions.AddressException;
import com.example.model.exeptions.DatabaseExepton;
import com.example.model.exeptions.PersonException;
import com.example.model.pojos.PersonInformation;
import org.springframework.stereotype.Component;


import java.sql.*;
@Component
public class PersonAbstractDAO extends AbstractDAO implements IPersonDAO {

    private static final String REGISTER = "INSERT INTO people(username, password, email) VALUES (?, sha1(?), ?)";
    private static final String ADD_ADDRESS = "INSERT INTO address(people_id) VALUES(?)";

    private static final String LOGIN = "SELECT people_id FROM people WHERE username = ? AND password = sha1(?) AND is_deleted IS NULL;";

    private static final String UPDATE_PROFILE = "UPDATE people SET first_name = ?, last_name = ?, email = ?, phone = ?, birthdate = ? WHERE people_id = ?";
    private static final String UPDATE_ADDRESS = "UPDATE address SET city = ?, street = ?, street_number = ?, country = ? WHERE people_id = ?";

    private static final String DELETE_PROFILE = "UPDATE people SET deleted = 1 WHERE people_id = ?";
//    private static final String DELETE_ADDRESS = "DELETE FROM address WHERE address_id = ?";
    private static final String IS_USER = "SELECT user_id FROM users WHERE person_id = ?";

    private static final String DELETE_REVIEWS = "UPDATE reviews SET user_id = (SELECT id FROM users WHERE username = 'deleted profile') WHERE user_id = ? ";
//    private static final String DELETE_FAVOURITE_PRODUCTS = "DELETE FROM fauvite_products WHERE users_id = ?";


    public PersonAbstractDAO() throws DatabaseExepton {
    }

    @Override
    public int register(PersonInformation person, String statemnet) throws PersonException {
        PreparedStatement preparedStatement;
        try {
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(REGISTER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, person.getUsername());
            preparedStatement.setString(2, person.getPassword());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            int peopleIndex = 0;
            if (resultSet.next()) {
                peopleIndex = resultSet.getInt(1);
                person.setPersonId(peopleIndex);
            }

            preparedStatement = connection.prepareStatement(ADD_ADDRESS);
            preparedStatement.setInt(1, peopleIndex);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(statemnet, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, peopleIndex);

            int updateUser = preparedStatement.executeUpdate();
            if (updateUser == INVALID_UPDATE) throw new PersonException("Invalid update");

            return peopleIndex;
        } catch (SQLException e) {
            throw new PersonException("The username already exists!", e);
        }  finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new PersonException("Invalid update!", e);
            }
        }

    }

    @Override
    public int login(String username, String password, String statement) throws PersonException {
        PreparedStatement preparedStatement;
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(LOGIN);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            int peopleIndex;
            if (resultSet.next()) {
                peopleIndex = resultSet.getInt(1);
                System.out.println("peop"+peopleIndex);
            } else throw new PersonException("Wrong username or password.");

            preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setInt(1, peopleIndex);
            resultSet = preparedStatement.executeQuery();

            int index;
            if (resultSet.next()) {
                index = resultSet.getInt(1);
                return index;
            } else {
                throw new PersonException("Wrong username or password");
            }
        } catch (SQLException e) {
            throw new PersonException("Something went wrong" + e.getMessage(), e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new PersonException("Invalid operation!", e);
            }
        }
    }

    @Override
    public void editProfile(PersonInformation person, int id ) throws PersonException {
        PreparedStatement preparedStatement;
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(UPDATE_PROFILE);
            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.setString(4, person.getPhoneNumber());
            preparedStatement.setDate(5, person.getBirthday() == null ? null : Date.valueOf(person.getBirthday()));
            preparedStatement.setInt(6, id);

            if (person.getAddress() != null) {
                int update = preparedStatement.executeUpdate();
                if (update == INVALID_UPDATE) throw new PersonException("Invalid update");

                preparedStatement = connection.prepareStatement(UPDATE_ADDRESS);
                preparedStatement.setString(1, person.getAddress().getCity());
                preparedStatement.setString(2, person.getAddress().getStreet());
                preparedStatement.setInt(3, person.getAddress().getStretNumber());
                preparedStatement.setString(4, person.getAddress().getCountry());
                preparedStatement.setInt(5, person.getPersonId());

                int updateAddress = preparedStatement.executeUpdate();
                if (updateAddress == INVALID_UPDATE) throw new PersonException("Invalid update");
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new PersonException("Invalid update!", e1);
            }
            throw new PersonException("Invalid update!" + e.getMessage(), e);
        } catch (AddressException e) {
            throw new PersonException("Invalid update! " + e.getMessage(), e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new PersonException("Invalid operation!", e);
            }
        }
    }

    @Override
    public void removeProfile(PersonInformation person) throws PersonException {
        PreparedStatement preparedStatement;
        try {
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(DELETE_PROFILE);
            preparedStatement.setInt(1, person.getPersonId());
            int update = preparedStatement.executeUpdate();
            if (update == INVALID_UPDATE) throw new PersonException("Invalid update");

            preparedStatement = connection.prepareStatement(IS_USER);
            preparedStatement.setInt(1, person.getPersonId());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                preparedStatement = connection.prepareStatement(DELETE_REVIEWS);
                preparedStatement.setInt(1, person.getPersonId());
                preparedStatement.executeUpdate();

   /*             preparedStatement = connection.prepareStatement(DELETE_FAVOURITE_PRODUCTS);
                preparedStatement.setInt(1, person.getAddress().getId());
                preparedStatement.executeUpdate();
   */         }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new PersonException("Invalid update!", e1);
            }
            throw new PersonException("Invalid update!", e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new PersonException("Invalid operation!", e);
            }
        }
    }


}