package com.example.model.DAO;

import com.example.model.exeptions.DatabaseExepton;
import com.example.model.exeptions.PersonException;
import com.example.model.pojos.Admin;
import com.example.model.pojos.PersonInformation;
import org.springframework.stereotype.Component;

@Component
public class AdminDAO extends PersonAbstractDAO implements IAdminDAO {
    private static final String REGISTER_ADMIN = "INSERT INTO admins(people_id) VALUES (?)";
    private static final String LOGIN_ADMIN = "SELECT admin_id FROM admins WHERE people_id = ?;";


    public AdminDAO() throws DatabaseExepton {
    }

    @Override
    public int registerAdmin(PersonInformation person) throws PersonException {
        if (person instanceof Admin){
            int id = register(person, REGISTER_ADMIN);
            ((Admin)person).setAdminId(id);
            return id;
        }
        else throw new PersonException("Registration for admins only");
    }


    @Override
    public int loginAdmin(String username, String password) throws PersonException {
        return login(username, password, LOGIN_ADMIN);
    }

}
