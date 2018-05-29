package com.example.controller;

import com.example.model.DAO.CategoryDAO;
import com.example.model.DAO.CharacteristicsDAO;
import com.example.model.DAO.ProductDAO;
import com.example.model.exeptions.UpdateException;
import com.example.model.pojos.Category;
import com.example.model.pojos.CharacteristicsValues;
import com.example.model.pojos.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

@Controller
public class ProductController {

    @Autowired
	private ProductDAO productDAO;


    @RequestMapping(method=RequestMethod.GET, value="/categories/{name}/{name}")
    public String viewProduct(Model model, @PathVariable("name") String name) {
        try {
            Product product = productDAO.getProduct(name);
            model.addAttribute("product", product);
            model.addAttribute("related", productDAO.getRelatedProducts(product.getId()));
        } catch (UpdateException e) {
            return "index";
        }
        return "productView";
    }


}
