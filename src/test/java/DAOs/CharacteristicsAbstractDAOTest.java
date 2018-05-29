package DAOs;

import com.example.model.DAO.CharacteristicsDAO;
import com.example.model.exeptions.DatabaseExepton;
import com.example.model.exeptions.UpdateException;
import com.example.model.pojos.Characteristic;
import com.example.model.pojos.CharacteristicsValues;
import org.junit.Test;

import java.util.List;
import java.util.Set;

public class CharacteristicsAbstractDAOTest {
    @Test
    public void newCharStringTest() {
        try {
            new CharacteristicsDAO().addCharacteristic("manufacturer", "aladin");
            new CharacteristicsDAO().addCharacteristic("manufacturer", "MASHISHA");
            new CharacteristicsDAO().addCharacteristic("manufacturer", "APPLE ON TOP");
            new CharacteristicsDAO().addCharacteristic("manufacturer", "MONDAY MORNING GMBH");
            new CharacteristicsDAO().addCharacteristic("manufacturer", "HOOKAH STARTER PRO");
            new CharacteristicsDAO().addCharacteristic("manufacturer", "El-Badia");
        } catch (UpdateException | DatabaseExepton e) {
            e.printStackTrace();
        }
    }

    @Test
    public void newCharSIntTest() {
        try {
            new CharacteristicsDAO().addCharacteristic("size", "13");
            new CharacteristicsDAO().addCharacteristic("size", "15");

        } catch (UpdateException | DatabaseExepton e) {
            e.printStackTrace();
        }
    }


    @Test
    public void addToProductTest() {
        try {
            new CharacteristicsDAO().addCharToProduct(2, 1);
            new CharacteristicsDAO().addCharToProduct(2, 2);
            new CharacteristicsDAO().addCharToProduct(2, 3);
            new CharacteristicsDAO().addCharToProduct(3, 1);
            new CharacteristicsDAO().addCharToProduct(3, 2);
            new CharacteristicsDAO().addCharToProduct(3, 3);

        } catch (UpdateException | DatabaseExepton e) {
            e.printStackTrace();
        }
    }


    @Test
    public void getCharNamesTest() {
        try {
            List<String> names = new CharacteristicsDAO().getCharacteristicsNamesForCategory(6);
            names.forEach(System.out::println);

        } catch (DatabaseExepton databaseExepton) {
            databaseExepton.printStackTrace();
        }
    }


    @Test
    public void getCharForCategoryTest() {
        try {
            Set<Characteristic> characteristics = new CharacteristicsDAO().getValuesForCharInCategory("manufacturer", 6);
            characteristics.forEach(System.out::println);
        } catch (DatabaseExepton databaseExepton) {
            System.out.println(databaseExepton.getMessage());
        }
    }



    @Test
    public void getAllTest() {
        try {
            List<CharacteristicsValues> values = new CharacteristicsDAO().getAllCharacteristicsInCategory(6);
            values.forEach(System.out::println);

        } catch (DatabaseExepton databaseExepton) {
            databaseExepton.printStackTrace();
        }

    }

}
