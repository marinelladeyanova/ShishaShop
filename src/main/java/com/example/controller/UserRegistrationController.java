package com.example.controller;

import com.example.model.DAO.UserDAO;
import com.example.model.exeptions.PersonException;
import com.example.model.pojos.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping(value = "/registration")
public class UserRegistrationController {

	@Autowired
	private UserDAO userDao;

	@RequestMapping(method = RequestMethod.GET)
	public String doGet(@ModelAttribute("newUser") User newUser, Model model, HttpServletRequest request) {

        if (request.getSession().getAttribute("user") != null) {
            return "redirect:index";
        }
		model.addAttribute("newUser", new User());
		return "userRegistration";
	}


	@RequestMapping(method = RequestMethod.POST, params = {"register"})
	public String register(Model model, @ModelAttribute("newUser") User newUser, HttpServletRequest request) {

		/*model.addAttribute("user", new User());*/
       // User theUser = new User(newUser.getUsername(), newUser.getEmail(), newUser.getPassword(), newUser.getConfirmPassword());
		try {
		    newUser.validate();
            userDao.registerUser(newUser);
		} catch (PersonException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "userRegistration";
		}

		request.getSession().setAttribute("user", newUser);
        request.getSession().setAttribute("id", newUser.getId());

        return "index";
	}

}

	
