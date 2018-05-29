package com.example.model.DAO;

import com.example.model.exeptions.DatabaseExepton;
import com.example.model.exeptions.PersonException;
import com.example.model.pojos.PersonInformation;
import com.example.model.pojos.User;
import org.springframework.stereotype.Component;

@Component
public class UserDAO extends PersonAbstractDAO implements IUserDAO {

    private static final String LOGIN_USER = "SELECT user_id FROM users WHERE people_id = ?;";
    private static final String REGISTER_USER = "INSERT INTO users(people_id) VALUES (?)";


    private static final String MAKE_ORDER = "INSERT INTO orders ";

    public UserDAO() throws DatabaseExepton {
    }

    @Override
    public int registerUser(PersonInformation person) throws PersonException {
        if (person instanceof User) {
            int id =  register(person, REGISTER_USER);
            ((User)person).seUserId(id);
            return id;
        }
        else throw new PersonException("Registration for users only");
    }

    @Override
    public int loginUser(String username, String password) throws PersonException {
        return login(username, password, LOGIN_USER);
    }

    @Override
    public void makeOrder(PersonInformation person) throws PersonException {
    /*    Statement preparedStatement;
       try {
            preparedStatement = connection.prepareStatement(MAKE_ORDER);
            preparedStatement.setInt(1, person.getPersonId());
            preparedStatement.setInt(2, product.getPersonId());

            int update = preparedStatement.executeUpdate();
            if (update == INVALID_UPDATE) throw new PersonException("Invalid update");

        } catch (SQLException e) {
            throw new PersonException("Invalid update!", e);
        }*/

    }


}
