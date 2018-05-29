package DAOs;

import com.example.model.DAO.DiscountDAO;
import com.example.model.exeptions.DatabaseExepton;
import com.example.model.exeptions.UpdateException;
import org.junit.Test;

import java.time.LocalDate;

public class DiscountsAbstractDAOTest {

    @Test
    public void createDiscount() throws DatabaseExepton, UpdateException {
        new DiscountDAO().createNewDiscount(LocalDate.of(2018, 04, 15),
                LocalDate.of(2018, 06, 10), 40);



    }


}
