package DAOs;

import com.example.model.DAO.CategoryDAO;
import com.example.model.exeptions.DatabaseExepton;
import com.example.model.exeptions.PersonException;
import com.example.model.exeptions.UpdateException;
import com.example.model.pojos.Category;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

public class CategoryDAOTest {

    @Autowired
    private CategoryDAO categoryDAO;


    @Test
    public void addCategoryTest() throws PersonException, DatabaseExepton, UpdateException {
        new CategoryDAO().addCategory(new Category("Hookahs"));
        new CategoryDAO().addCategory(new Category("Charcoal"));
        new CategoryDAO().addCategory(new Category("Accessories"));
    }

    // TODO characteristics

    @Test(expected = UpdateException.class)
    public void addDuplicateCategoryTest() throws DatabaseExepton, UpdateException {
        new CategoryDAO().addCategory(new Category("Hookahs"));
    }

    @Test
    public void editCategoryTest() throws DatabaseExepton, UpdateException {
        new CategoryDAO().editCategory(new Category(13,"Hookah Charcoal"));
    }

    @Test
    public void addSubcategoryTest() throws PersonException, DatabaseExepton, UpdateException {
        new CategoryDAO().addSubcategory(new Category(6), new Category("Royal Collection"));
        new CategoryDAO().addSubcategory(new Category(6), new Category("Classic"));
        new CategoryDAO().addSubcategory(new Category(6), new Category("Small Hookah"));
        new CategoryDAO().addSubcategory(new Category(6), new Category("Large Hookah"));
    }

    @Test
    public void getAllCategoriesNamesTest() throws DatabaseExepton, UpdateException {
        List<String> names = new CategoryDAO().getAllCategoriesNames();
        names.forEach(System.out::println);
    }

    @Test
    public void getAllSubcategoriesNamesTest() throws DatabaseExepton, UpdateException {
        List<String> names = new CategoryDAO().getAllSubcategoriesNamesForParent(new Category(18));
        names.forEach(System.out::println);
    }


    @Test
    public void deleteCategoryTest() throws DatabaseExepton, UpdateException {
        new CategoryDAO().removeCategory(new Category(20));
    }

    @Test
    public void restoreCategoryTest() throws DatabaseExepton, UpdateException {
        new CategoryDAO().restoreCategory(new Category(6));
    }

    @Test
    public void getAllCategoriesTest() throws DatabaseExepton, UpdateException {
        Set<Category> categories = new CategoryDAO().getAllCategories();
        for (Category category : categories) {
            System.out.println(category.getId() + " " + category.getName());
            for (Category subcategory : category.getSubcategories()) {
                System.out.println(subcategory.getId() + " - " + subcategory.getName());
            }
        }
    }

    @Test
    public void getCategoryTest() throws DatabaseExepton, UpdateException {
        Category category = new CategoryDAO().getCategory("Hookahs");
        System.out.println(category.getId() + " " + category.getName());
        if (category.getSubcategories() != null) {
            category.getSubcategories().forEach(e -> System.out.println(e.getName()));
        }
    }


    @Test
    public void getSubcategoryTest() throws DatabaseExepton, UpdateException {
        Category category = new CategoryDAO().getCategory("Royal Collection");
        System.out.println(category.getId() + " " + category.getName());

    }




}
