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
public class UserEditController {

	@Autowired
	private UserDAO userDao;



	@RequestMapping(value = "/edit", method = RequestMethod.GET)
		public String doGet(Model model, HttpServletRequest request) {

		if (request.getSession().getAttribute("user") != null) {
			return "userEdit";
		}
		return "userLogin";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editUser(@ModelAttribute("user") User user, Model model,  HttpServletRequest request) {

		User editUser = (User) request.getSession().getAttribute("user");

		try {
			new User(user.getUsername(), user.getPassword(), user.getEmail());
			userDao.editProfile(user, editUser.getId());
		} catch (PersonException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "userEdit";
		}
        return "index";
	}



}

	
