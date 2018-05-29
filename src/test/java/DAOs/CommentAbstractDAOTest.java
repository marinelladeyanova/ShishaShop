package DAOs;

import com.example.model.DAO.CommentDAO;
import com.example.model.exeptions.DatabaseExepton;
import com.example.model.exeptions.PersonException;
import com.example.model.exeptions.ReviewExeption;
import com.example.model.pojos.Comment;
import com.example.model.pojos.Product;
import com.example.model.pojos.User;
import org.junit.Test;

import java.util.Set;

public class CommentAbstractDAOTest {


    @Test
    public void addCommentTest() throws PersonException, DatabaseExepton, ReviewExeption {
        new CommentDAO().addComment(new User(2), new Comment("text"), new Product(2));
        new CommentDAO().addComment(new User(2), new Comment("text"), new Product(1));
        new CommentDAO().addComment(new User(2), new Comment("text"), new Product(1));
        new CommentDAO().addComment(new User(1), new Comment("text"), new Product(1));
        new CommentDAO().addComment(new User(1), new Comment("text"), new Product(1));
        new CommentDAO().addComment(new User(1), new Comment("text"), new Product(2));
    }

    @Test
    public void allCommentsPerProductTest() throws PersonException, DatabaseExepton {
        Set<Integer> set = new CommentDAO().getAllCommentsForProduct(new Product(1));
        set.forEach(System.out::println);
    }


    @Test
    public void removeCommentTest() throws PersonException, DatabaseExepton, ReviewExeption {
        new CommentDAO().removeComment(new Comment(5));
    }

}
