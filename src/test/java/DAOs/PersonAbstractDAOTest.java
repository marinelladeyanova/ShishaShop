package DAOs;

import com.example.model.DAO.AdminDAO;
import com.example.model.DAO.PersonAbstractDAO;
import com.example.model.DAO.UserDAO;
import com.example.model.exeptions.AddressException;
import com.example.model.exeptions.DatabaseExepton;
import com.example.model.exeptions.PersonException;
import com.example.model.exeptions.ValidationExeption;
import com.example.model.pojos.Address;
import com.example.model.pojos.Admin;
import com.example.model.pojos.User;
import org.junit.Test;

import java.time.LocalDate;

public class PersonAbstractDAOTest {

    @Test
    public void validUserRegistrationTest() throws ValidationExeption, PersonException, DatabaseExepton {
        new UserDAO().registerUser(new User("testuser5", "Ww123456", "mail@as.sd"));
        new UserDAO().registerUser(new User("testuser2", "Ww123456", "mail@as.sd"));
        new UserDAO().registerUser(new User("testuser3", "Ww123456", "mail@as.sd"));
        new UserDAO().registerUser(new User("testuser4", "Ww123456", "mail@as.sd"));
    }

    @Test(expected = PersonException.class)
    public void invalidAdminRegistrationTest() throws ValidationExeption, PersonException, DatabaseExepton {
        new AdminDAO().registerAdmin(new User("testadmin2", "Ww123456", "mail@as.sd"));
        new AdminDAO().registerAdmin(new User("testadmin1", "Ww123456", "mail@as.sd"));
        new AdminDAO().registerAdmin(new User("testadmin3", "Ww123456", "mail@as.sd"));
    }

    @Test
    public void validAdminRegistrationTest() throws ValidationExeption, PersonException, DatabaseExepton {
        new AdminDAO().registerAdmin(new Admin("testadmin1", "Ww123456", "mail@as.sd"));
    }

    @Test(expected = PersonException.class)
    public void invalidUserRegistrationTest() throws ValidationExeption, PersonException, DatabaseExepton {
        new UserDAO().registerUser(new Admin("testadmin2", "Ww123456", "mail@as.sd"));
    }

    @Test(expected = PersonException.class)
    public void duplicateNameRegistrationTest() throws ValidationExeption, PersonException, DatabaseExepton {
        new UserDAO().registerUser(new User("testuser2", "Ww1234563", "maild@as.sd"));
    }


    @Test
    public void registerTest() throws ValidationExeption, PersonException, DatabaseExepton {
        new UserDAO().registerUser(new User("testuser51", "Ww123456", "mail@as.sd"));
        try {
            new AdminDAO().registerAdmin(new User("testuser52", "Ww123456", "mail@as.sd"));
        } catch (PersonException e) {
            System.out.println(e.getMessage());
        }
        new AdminDAO().registerAdmin(new Admin("testuser53", "Ww123456", "mail@as.sd"));
    }

    @Test
    public void loginTest() throws ValidationExeption, PersonException, DatabaseExepton {
       new UserDAO().loginUser("testuser31", "Ww123456");
       new AdminDAO().loginAdmin("testuser53", "Ww123456");
    }


   @Test
    public void editProfileTest() throws ValidationExeption, PersonException, DatabaseExepton, AddressException {
        new PersonAbstractDAO().editProfile(
                new User(
                3, "testuser1","Ww123456", "Joro", "Purvi", new Address(3, "BigCity", "LongStreet", 3, "FarAway"),
                "0894567895", LocalDate.of(1990, 5, 4), "mail@as.sd"), 4);
    }

    @Test
    public void editProfileNullableTest() throws ValidationExeption, PersonException, DatabaseExepton, AddressException {
        new PersonAbstractDAO().editProfile(
                new User(
                        3, "testuser1","Ww123456", "Bobo",
                        "Vtori", null, "0894567895", null, "mail@as.sd"), 5);
    }


}
