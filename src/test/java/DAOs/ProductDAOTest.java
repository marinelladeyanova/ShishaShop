package DAOs;


import com.example.model.DAO.ProductDAO;
import com.example.model.exeptions.*;
import com.example.model.pojos.*;
import org.junit.Test;

import java.util.Set;

public class ProductDAOTest {

    @Test
    public void addProductTest() throws DatabaseExepton, UpdateException {
        new ProductDAO().addNewProduct(new Product("product432", "Egyptian hookah", 45.2,  450, "") , 7);
        new ProductDAO().addNewProduct(new Product("product4211", "Egyptian hookah", 150,   450,  ""), 7);
        new ProductDAO().addNewProduct(new Product("product4221", "Egyptian hookah", 45.2, 450,   ""), 7);
        new ProductDAO().addNewProduct(new Product("product4231", "Egyptian hookah", 45.2, 450,   ""), 8);
        new ProductDAO().addNewProduct(new Product("product4241", "Egyptian hookah", 150, 450,    ""), 8);
        new ProductDAO().addNewProduct(new Product("product4251", "Egyptian hookah", 45.2, 450,   ""), 8);


    }

    @Test(expected = UpdateException.class)
    public void addDuplicateProductTest() throws DatabaseExepton, UpdateException {
        new ProductDAO().addNewProduct(new Product("Nammor Pharaoh Hookah", "30 inch Egyptian hookah with Nammor Hose upgrade", 45.2, 450, ""), 7);
    }

    @Test
    public void editProductTest() throws DatabaseExepton, UpdateException {
        new ProductDAO().editProduct(new Product(7, "Nammor Pharaoh ", "upgrade", 45.2, 450, ""));
    }

    @Test
    public void removeProductTest() throws DatabaseExepton, UpdateException {
        new ProductDAO().removeProduct(new Product(5));
    }

    @Test
    public void restoreProductTest() throws DatabaseExepton, UpdateException {
        new ProductDAO().restoreProduct(new Product(7));
    }

    @Test
    public void allProductsInCategoryTest() throws DatabaseExepton, UpdateException {
        Set<Product> products = new ProductDAO().getAllProductsInCategory(9);
        products.forEach(System.out::println);
    }

    @Test
    public void getProductByName() throws DatabaseExepton, UpdateException {
        Product product = new ProductDAO().getProduct("Nammor 1 ");
        System.out.println(product);
    }

    @Test
    public void getProductInCategoryAndSubs() {

        try {
            Set<Product> products = new ProductDAO().getAllInCategoryAndSub(6);
            products.forEach(s -> System.out.println(s.getId() + " - " + s.getName() + " - " + s.getCategory().getId()));
        } catch (UpdateException | DatabaseExepton e) {
            System.out.println(e.getMessage());
        }
    }


    @Test
    public void getProductCharacteristics() {
        try {
            Set<Characteristic> characteristics = new ProductDAO().getProduct("PRODUCT15").getCharacteristics();
            characteristics.forEach(s -> System.out.println(s.getId() + " - " + s.getName() + " - " + s.getValue()));
        } catch (UpdateException | DatabaseExepton e) {
            System.out.println(e.getMessage());
        }
    }






}
