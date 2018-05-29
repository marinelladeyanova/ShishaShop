package DAOs;

import com.example.model.DAO.FavouriteProductsDAO;
import com.example.model.exeptions.DatabaseExepton;
import com.example.model.exeptions.PersonException;
import com.example.model.exeptions.ValidationExeption;
import com.example.model.pojos.Product;
import com.example.model.pojos.User;
import org.junit.Test;

import java.util.Set;

public class FavouriteProductsDAOTest {

    @Test
    public void addFavouriteTest() throws ValidationExeption, PersonException, DatabaseExepton {
        new FavouriteProductsDAO().addFavouriteProduct(new User(2), new Product(3));
    }

    @Test
    public void allFavouriteTest() throws ValidationExeption, PersonException, DatabaseExepton {
        Set<Integer> set = new FavouriteProductsDAO().getAllFavouriteProducts(new User(2));
        set.forEach(System.out::println);
    }

    @Test
    public void removeFavouriteTest() throws ValidationExeption, PersonException, DatabaseExepton {
        new FavouriteProductsDAO().removeFavouriteProduct(new User(2), new Product(4));
    }

}
