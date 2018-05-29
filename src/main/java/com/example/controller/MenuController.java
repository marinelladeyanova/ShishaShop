package com.example.controller;

import com.example.model.DAO.CategoryDAO;
import com.example.model.DAO.CharacteristicsDAO;
import com.example.model.DAO.ProductDAO;
import com.example.model.exeptions.UpdateException;
import com.example.model.pojos.*;
import com.example.model.pojos.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class MenuController {

	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	private ProductDAO productDAO;


    @RequestMapping(method=RequestMethod.GET, value="/index")
	public String allCategories(Model model, HttpServletResponse response, HttpServletRequest request) {
		model.addAttribute("categories");
		try {
			Set<Category> categories = categoryDAO.getAllCategories();
			model.addAttribute("categories", categories);
		} catch (UpdateException e) {
			return "index";
		}

		return "index";
	}

/*


    @RequestMapping(method=RequestMethod.GET, value="/categories/{name}")
      public String viewCategories(Model model, @PathVariable("name") String name) {

      Category category;

      model.addAttribute("categories");
      model.addAttribute("products");
      model.addAttribute("charNames");
      try {
          Set<Category> categories = categoryDAO.getAllCategories();
          model.addAttribute("categories", categories);

          category = categoryDAO.getCategory(name);
          model.addAttribute(category);
          model.addAttribute("products", productDAO.getAllProductsInCategory(category.getId()));

          List<CharacteristicsValues> characteristics = charDAO.getAllCharacteristicsInCategory(category.getId());
          model.addAttribute("characteristics", characteristics);

      } catch (UpdateException e) {
          return "index";
      }
      return "categories";
  }

*/



    @RequestMapping(value={"userProfile/"}, method=RequestMethod.GET)
    public String userProfile(HttpSession session, Model model) {

        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "index";
        }

        model.addAttribute("user", user);
        model.addAttribute("cart", user.getCart());
        return "userProfile";
    }

  @RequestMapping(value={"userProfile/${product.name}"}, method=RequestMethod.POST)
    public String addProductToCart(HttpSession session, @PathVariable("product.name") String name, Model model) {

      Product product = null;
      try {
          product = productDAO.getProduct(name);
      } catch (UpdateException e) {
          return "index";
      }
      User user = (User) session.getAttribute("user");
      if (user == null) {
          return "index";
      }
      try {
          product = productDAO.getProduct(name);
          user.getCart().addProduct(product);
          model.addAttribute("cart", user.getCart());
      } catch (UpdateException e) {
          return "index";
      }

      return "userProfile";
  }

}
