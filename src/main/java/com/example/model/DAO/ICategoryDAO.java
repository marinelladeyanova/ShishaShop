package com.example.model.DAO;

import com.example.model.exeptions.PersonException;
import com.example.model.exeptions.UpdateException;
import com.example.model.pojos.Category;
import com.example.model.pojos.Comment;
import com.example.model.pojos.Product;
import com.example.model.pojos.User;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public interface ICategoryDAO {

    public void addCategory(Category category) throws UpdateException;

    public void addSubcategory(Category category, Category subcategory) throws UpdateException;

    public void editCategory(Category category) throws UpdateException;

    public void removeCategory(Category category) throws UpdateException;

    public void restoreCategory(Category category) throws UpdateException;

    public Set<Category> getAllCategories() throws UpdateException;

    public Category getCategory(String name) throws UpdateException;

    public Category getCategory(int id) throws UpdateException;

    public List<String> getAllCategoriesNames() throws UpdateException;

    public List<String> getAllSubcategoriesNamesForParent(Category category) throws UpdateException;


}
