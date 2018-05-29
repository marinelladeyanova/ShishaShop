package com.example.controller;

import com.example.model.DAO.CategoryDAO;
import com.example.model.DAO.CharacteristicsDAO;
import com.example.model.DAO.ProductDAO;
import com.example.model.exeptions.UpdateException;
import com.example.model.pojos.Category;
import com.example.model.pojos.CharacteristicsValues;
import com.example.model.pojos.Product;
import com.example.model.pojos.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

@Controller
public class CategoriesController {

	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	private ProductDAO productDAO;
    @Autowired
    private CharacteristicsDAO charDAO;


    @RequestMapping(method=RequestMethod.GET, value="/categories/{name}")
    public ModelAndView viewCategories(@PathVariable("name") String name, HttpServletRequest request) {

        ModelAndView model = new ModelAndView("categories");
        Category category;

        try {
            category = categoryDAO.getCategory(name);

            Set<Category> categories = categoryDAO.getAllCategories();
            model.addObject("categories", categories);
            Set<Product> products;


            model.addObject(category);

            String subName = request.getParameter("sub");
            String characteristicId = request.getParameter("characteristic");

            if (subName != null) {
                Category subcategory = categoryDAO.getCategory(subName);
                products = productDAO.getAllProductsInCategory(subcategory.getId());
            } else {
                if (characteristicId != null) {
                  products = productDAO.getAllInCategoryAndSubWithChar(category.getId(), Integer.parseInt(characteristicId));
                } else {
                    products = productDAO.getAllInCategoryAndSub(category.getId());
                }
            }


            if (request.getParameter("sort") != null) {
                if (request.getParameter("sort").equalsIgnoreCase("priceASC")) {
                    products = productDAO.sortByPriceAsc(products);
                }
                if (request.getParameter("sort").equalsIgnoreCase("priceDESC")) {
                    products = productDAO.sortByPriceDesc(products);
                }
            }
            model.addObject("products", products);


            List<CharacteristicsValues> characteristics = charDAO.getAllCharacteristicsInCategory(category.getId());
            model.addObject("characteristics", characteristics);

        } catch (UpdateException e) {
            return model;
        }
        return model;
    }


}
