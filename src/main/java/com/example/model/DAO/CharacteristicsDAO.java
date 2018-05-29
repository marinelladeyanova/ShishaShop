package com.example.model.DAO;

import com.example.model.exeptions.DatabaseExepton;
import com.example.model.exeptions.UpdateException;
import com.example.model.pojos.Characteristic;
import com.example.model.pojos.CharacteristicsValues;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.example.model.DAO.PersonAbstractDAO.INVALID_UPDATE;

@Component
public class CharacteristicsDAO extends AbstractDAO implements ICharacteristicsDAO {

    private static final String ADD_CHARACTERISTIC = "INSERT INTO characteristics(name, value_text) VALUES (?, ?)";
    private static final String ADD_CHAR_TO_PRODUCT = "INSERT INTO product_characteristics(product_id, characteristic_id) VALUES (?, ?);";
    private static final String GET_CHAR_NAMES_IN_CATEGORY = "SELECT DISTINCT ch.name FROM characteristics ch JOIN product_characteristics pch JOIN products p WHERE p.category_id = ?;";
    private static final String GET_CHAR_IN_CATEGORY = "SELECT c.characteristic_id, c.name, c.value_text FROM characteristics c JOIN product_characteristics pch JOIN products p WHERE  p.category_id = ? AND c.name = ? group by value_text order by c.characteristic_id;";



    public CharacteristicsDAO() throws DatabaseExepton {
    }


    @Override
    public void addCharacteristic(String name, String value) throws UpdateException {
        name = name.toLowerCase().trim();
        value = value.toLowerCase().trim();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(ADD_CHARACTERISTIC);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, value);

            int update = preparedStatement.executeUpdate();
            if (update == INVALID_UPDATE) throw new UpdateException("Invalid update");
        } catch (SQLException e) {
            throw new UpdateException("Invalid update! " + e.getMessage(), e);
        }
    }



    public void addCharToCategory(Characteristic characteristic, int categoryId) {

    }


    @Override
    public void addCharToProduct(int productId, int charId) throws UpdateException {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(ADD_CHAR_TO_PRODUCT);
            preparedStatement.setInt(1, productId);
            preparedStatement.setInt(2, charId);

            int update = preparedStatement.executeUpdate();
            if (update == INVALID_UPDATE) throw new UpdateException("Invalid update");
        } catch (SQLException e) {
            throw new UpdateException("Invalid update! " + e.getMessage(), e);
        }
    }


    @Override
    public  List<String> getCharacteristicsNamesForCategory(int categoryId){
        List<String> names = new ArrayList<>();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(GET_CHAR_NAMES_IN_CATEGORY);
            preparedStatement.setInt(1, categoryId);

            ResultSet result = preparedStatement.executeQuery();
            while (result.next()){
                names.add(result.getString(1));
            }
        } catch (SQLException e) {
           return null;
        }
        return names;
    }

    @Override
    public  Set<Characteristic> getValuesForCharInCategory(String charName, int categoryId){
        Set<Characteristic> characteristics = new TreeSet<>((o1, o2) -> {
           if (o1.getValue().equals(o2.getValue())) return -1;
           return o1.getValue().compareToIgnoreCase(o2.getValue());
        });
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(GET_CHAR_IN_CATEGORY);
            preparedStatement.setInt(1, categoryId);
            preparedStatement.setString(2, charName);

            ResultSet result = preparedStatement.executeQuery();
            while (result.next()){
                characteristics.add(new Characteristic(
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3)
                        ));
            }
        } catch (SQLException e) {
            return null;
        }
        return characteristics;
    }

    @Override
    public List<CharacteristicsValues> getAllCharacteristicsInCategory(int categoryId){
        List<CharacteristicsValues> characteristicsValues = new ArrayList<>();
        for (String s : getCharacteristicsNamesForCategory(categoryId)) {
            characteristicsValues.add(new CharacteristicsValues(s));
        }
        for (CharacteristicsValues characteristicsValue : characteristicsValues) {
            characteristicsValue.setValues(getValuesForCharInCategory(characteristicsValue.getName(), categoryId));
        }
        return characteristicsValues;
    }



}
