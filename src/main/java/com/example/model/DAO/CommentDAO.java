package com.example.model.DAO;

import com.example.model.exeptions.DatabaseExepton;
import com.example.model.exeptions.PersonException;
import com.example.model.exeptions.ReviewExeption;
import com.example.model.pojos.Comment;
import com.example.model.pojos.Product;
import com.example.model.pojos.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Set;

import static com.example.model.DAO.PersonAbstractDAO.INVALID_UPDATE;
import static java.sql.Date.valueOf;

public class CommentDAO extends PairAbstractDAO implements ICommentDAO {

    private static final String ADD_COMMENT = "INSERT INTO comments(date, text, user_id, product_id) VALUES (?, ?, ?, ?);";
    private static final String REMOVE_COMMENT = "DELETE FROM comments WHERE comment_id = ?";

   private static final String ALL_COMMENTS_FOR_PRODUCT = "SELECT comment_id FROM comments WHERE product_id = ?;";


    public CommentDAO() throws DatabaseExepton {
    }



    @Override
    public void addComment(User user, Comment comment, Product product) throws ReviewExeption {
         PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(ADD_COMMENT);
            preparedStatement.setDate(1, valueOf(LocalDate.now()));
            preparedStatement.setString(2, comment.getText());
            preparedStatement.setInt(3, user.getId());
            preparedStatement.setInt(4, product.getId());

            int update = preparedStatement.executeUpdate();
            if (update == INVALID_UPDATE) throw new ReviewExeption("Invalid update");
        } catch (SQLException e) {
            throw new ReviewExeption("Invalid update! " + e.getMessage(), e);
        }
    }


    @Override
    public void removeComment(Comment comment) throws ReviewExeption {
        //proverka dali e na user-a
       PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(REMOVE_COMMENT);
            preparedStatement.setInt(1, comment.getId());
            int update = preparedStatement.executeUpdate();
            if (update == INVALID_UPDATE) throw new ReviewExeption("Invalid update");
        } catch (SQLException e) {
            throw new ReviewExeption("Invalid update! " + e.getMessage(), e);
        }
    }


    @Override
    public Set<Integer> getAllCommentsForProduct(Product product) throws PersonException {
         return getAll(product, ALL_COMMENTS_FOR_PRODUCT);
    }
}
