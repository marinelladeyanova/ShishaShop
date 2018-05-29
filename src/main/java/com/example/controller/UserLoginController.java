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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/login")
public class UserLoginController {

	@Autowired
	UserDAO userDAO;

	@RequestMapping(method = RequestMethod.GET)
	public String showLogin(@ModelAttribute("user") User user, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null) {
			return "redirect:index";
		}
		return "userLogin";
	}


	@RequestMapping(method = RequestMethod.POST, params = {"login"})
	protected String login(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user") User user, Model model) {
		int id;
		try {
			id = userDAO.loginUser(user.getUsername(), user.getPassword());
		} catch (PersonException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "userLogin";
		}
		request.getSession().setAttribute("user", user);
		request.getSession().setAttribute("id", id);

		return "index";
	}

	@RequestMapping(method = RequestMethod.GET, params = {"register"})
	protected String sendToRegister(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "userRegistration";
	}




}