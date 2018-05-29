package com.example.model.DAO;

import com.example.model.exeptions.UpdateException;
import com.example.model.pojos.Characteristic;
import com.example.model.pojos.CharacteristicsValues;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface ICharacteristicsDAO {

    void addCharacteristic(String name, String value) throws UpdateException;

    void addCharToProduct(int productId, int charId) throws UpdateException;

    List<String> getCharacteristicsNamesForCategory(int categoryId);

    Set<Characteristic> getValuesForCharInCategory(String charName, int categoryId);

    List<CharacteristicsValues> getAllCharacteristicsInCategory(int categoryId);


}