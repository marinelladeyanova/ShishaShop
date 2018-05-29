package com.example.model.DAO;

import com.example.model.exeptions.DatabaseExepton;
import com.example.model.exeptions.UpdateException;
import com.example.model.pojos.Category;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import static com.example.model.DAO.PersonAbstractDAO.INVALID_UPDATE;
import static java.sql.Date.valueOf;

@Component
public class CategoryDAO extends AbstractDAO implements ICategoryDAO {


    private static final String ADD_CATEGORY = "INSERT INTO categories(name) VALUES (?);";
    private static final String ADD_SUBCATEGORY = "INSERT INTO categories(name, parrent_category_id) VALUES (?, ?);";

    private static final String EDIT_CATEGORY = "UPDATE categories SET name = ? WHERE category_id = ?;";
    private static final String REMOVE_CATEGORY = "UPDATE categories SET deleted = 1 WHERE category_id = ?;";
    private static final String RESTORE_CATEGORY = "UPDATE categories SET deleted = null WHERE category_id = ?;";


    private static final String ALL_CATEGORIES_NAMES = "SELECT name FROM categories WHERE deleted IS NULL AND parrent_category_id IS NULL;";
    private static final String ALL_SUBCATEGORIES_NAMES = "SELECT name FROM categories WHERE deleted IS NULL AND parrent_category_id = ?;";

    private static final String ALL_CATEGORIES = "SELECT category_id, name FROM categories WHERE deleted IS NULL AND parrent_category_id IS NULL;";
    private static final String ALL_SUBCATEGORIES = "SELECT category_id, name FROM categories WHERE deleted IS NULL AND parrent_category_id = ?;";


    private static final String GET_SUBCATEGORY = "SELECT category_id FROM categories WHERE name = ? ;";


    public CategoryDAO() throws DatabaseExepton, UpdateException {
    }

    @Override
    public void addCategory(Category category) throws UpdateException {
        update(category.getName(), ADD_CATEGORY);
    }


    @Override
    public void addSubcategory(Category category, Category subcategory) throws UpdateException {
        manage(subcategory.getName(), category.getId(), ADD_SUBCATEGORY);
    }

    @Override
    public void editCategory(Category category) throws UpdateException {
        manage(category.getName(), category.getId(), EDIT_CATEGORY);
    }



    @Override
    public void removeCategory(Category category) throws UpdateException {
        manage(category.getId(), REMOVE_CATEGORY);
    }

    @Override
    public void restoreCategory(Category category) throws UpdateException {
        manage(category.getId(), RESTORE_CATEGORY);
    }

    @Override
    public Set<Category> getAllCategories() throws UpdateException {
       Set<Category> categories = new TreeSet<>(Comparator.comparingInt(Category::getId));
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(ALL_CATEGORIES);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                categories.add(new Category(resultSet.getInt(1),resultSet.getString(2)));
            }

            for (Category category : categories) {
                preparedStatement = connection.prepareStatement(ALL_SUBCATEGORIES);
                preparedStatement.setInt(1, category.getId());
                resultSet = preparedStatement.executeQuery();

                while(resultSet.next()) {
                    category.addSubcategory(new Category(resultSet.getInt(1),resultSet.getString(2)));
                }
            }
        } catch (SQLException e) {
            throw new UpdateException("Invalid update!", e);
        }
        return categories;
    }

    @Override
    public Category getCategory(String name) throws UpdateException {
        for (Category category : getAllCategories()) {
            if (category.getName().toLowerCase().equals(name.toLowerCase()))
                return category;
            for (Category category1 : category.getSubcategories()) {
                if (category1.getName().toLowerCase().equals(name.toLowerCase()))
                    return category1;
            }
        }
        return null;
    }
/*

    public Category getSubcategory(String name) throws UpdateException {
        try {
            for (Category category : getAllCategories()) {
                for (Category category1 : category.getSubcategories()) {
                    if (category1.getName().toLowerCase().equals(name.toLowerCase()))
                        return category1;
                }
            }
        } catch (UpdateException e) {
            throw new UpdateException("Invalid operation" + e.getMessage(), e);
        }
        return null;
    }
*/

    @Override
    public Category getCategory(int id) throws UpdateException {
        for (Category category : getAllCategories()) {
            if (category.getId() == id) {
                return category;
            }
            for (Category category1 : category.getSubcategories()){
                    if (category1.getId() == id) return category1;
            }
        }
        return null;
    }



   /* @Override
    public Map<Category, List<Category>> getAllCategories() throws UpdateException {
        Map<Category, List<Category>> categories = new TreeMap<>(Comparator.comparingInt(Category::getId));
        PreparedStatement preparedStatement;
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(ALL_CATEGORIES);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                categories.put(new Category(resultSet.getInt(1),resultSet.getString(2)), new LinkedList<>());
            }

            for (Category category : categories.keySet()) {
                preparedStatement = connection.prepareStatement(ALL_SUBCATEGORIES);
                preparedStatement.setInt(1, category.getId());
                resultSet = preparedStatement.executeQuery();

                while(resultSet.next()) {
                    categories
                            .get(category)
                                    .add(new Category(resultSet.getInt(1),resultSet.getString(2)));
                }
            }
        } catch (SQLException e) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    throw new UpdateException("Invalid update!", e1);
                }
                throw new UpdateException("Invalid update!", e);
            } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new UpdateException("Invalid operation!", e);
            }
        }
        return categories;
    }

*/


    @Override
    public List<String> getAllCategoriesNames() throws UpdateException {
        List<String> names = new LinkedList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(ALL_CATEGORIES_NAMES);
            while (resultSet.next()) {
                names.add(resultSet.getString(1));
            }
            return names;
        } catch (SQLException e) {
           throw  new UpdateException("Invalid update", e);
        }
    }

    @Override
    public List<String> getAllSubcategoriesNamesForParent(Category category) throws UpdateException {
        List<String> names = new LinkedList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ALL_SUBCATEGORIES_NAMES);
            preparedStatement.setInt(1, category.getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                names.add(resultSet.getString(1));
            }
            return names;
        } catch (SQLException e) {
            throw  new UpdateException("Invalid update", e);
        }
    }



}
