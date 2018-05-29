package pojos;

import com.example.model.exeptions.PersonException;
import com.example.model.exeptions.ValidationExeption;
import com.example.model.pojos.PersonInformation;
import com.example.model.pojos.User;
import org.junit.Test;

public class PersonInformationTest {

    @Test
    public void passwordTest() {
       /* try {
            PersonInformation p = new User("ghl", "wWWw123", "mail@as.sd");
        } catch (PersonException validationExeption) {
            System.out.println(validationExeption.getMessage());
        }
*/
    }


    @Test
    public void emailTest() {
        try {
        PersonInformation p = new User("Qqqqq", "000EeE33", "mail@as.sd");
            p.setEmail("asd.a.s@s.d");
        } catch (PersonException personException) {
            System.out.println(personException.getMessage());
        }
    }

    @Test
    public void nameTest(){
        try {
            PersonInformation p = new User("Choko", "000EeE33", "mail@as.sd");
            p.setFirstName("qv        Or");
            p.setLastName("Qvorov qvorov");
            System.out.println(p.getFirstName() + "\n" + p.getLastName() );
        } catch (PersonException  personException) {
            System.out.println(personException.getMessage());
        }


    }




}
