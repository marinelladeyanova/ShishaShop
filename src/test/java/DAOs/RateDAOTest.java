package DAOs;

import com.example.model.DAO.RateDAO;
import com.example.model.exeptions.DatabaseExepton;
import com.example.model.exeptions.PersonException;
import com.example.model.exeptions.ReviewExeption;
import com.example.model.pojos.Product;
import com.example.model.pojos.Rate;
import com.example.model.pojos.User;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RateDAOTest {

    @Test
    public void addRateTest() throws PersonException, DatabaseExepton, ReviewExeption {
        new RateDAO().addRating(new User(1), new Rate(2, ""), new Product(2));
        new RateDAO().addRating(new User(2), new Rate(1, ""), new Product(2));
        new RateDAO().addRating(new User(3), new Rate(2, ""), new Product(2));
        new RateDAO().addRating(new User(4), new Rate(3, ""), new Product(2));
        new RateDAO().addRating(new User(5), new Rate(4, ""), new Product(2));
        new RateDAO().addRating(new User(1), new Rate(3, ""), new Product(1));
        new RateDAO().addRating(new User(2), new Rate(2, ""), new Product(1));
    }

    @Test
    public void checkRateTest() throws PersonException, DatabaseExepton, ReviewExeption {
        assertTrue(new RateDAO().checkForExcistingRating(new User(1), new Rate(2, ""), new Product(2)));
        assertFalse(new RateDAO().checkForExcistingRating(new User(5), new Rate(2, ""), new Product(1)));
    }

    @Test(expected = ReviewExeption.class)
    public void invalidRateTest() throws PersonException, DatabaseExepton, ReviewExeption {
        new RateDAO().addRating(new User(1), new Rate(2, ""), new Product(2));
    }

    @Test
    public void allCommentsPerProductTest() throws PersonException, DatabaseExepton {
        Set<Integer> set = new RateDAO().getAllRatingsForProduct(new Product(2));
        set.forEach(System.out::println);
    }

}
