package com.user.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.user.entity.Employee;
import com.user.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	/*
	 * method to authenticate the user when user inputs their employee number and password
	 * and presses the login button
	 * users inputs from screen stored in model "user"
	 * if the employee is authenticated successfully then we are taken to the "Menu" screen 
	 * which has the employees details stored in the "employee" object
	 * message on whether employees login is successful in "message" object
	 * if login not successful, we stay on the login screen 
	 * INFO: NOT YET TESTED
	 */
	@RequestMapping("/login")
	public ModelAndView employeeLogin(@ModelAttribute("user") Employee user,
			HttpSession session) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		Employee emp = userService.authEmpCheck(user.getEmpNo(), user.getPass()).getEmp();
		String authStatus = userService.authEmpCheck(user.getEmpNo(), user.getPass()).getAuthStatus();
		
		//change the return of the authEmpCheck- bring through the employee details 
		if(authStatus.equals("Authenticated User")) {
			session.setAttribute("employee", emp);
			modelAndView.addObject("employee", emp);
			modelAndView.setViewName("Menu");
		}else {
			modelAndView.setViewName("Login");
			modelAndView.addObject("user", new Employee());
		}
		//gives the message on whether emp has successfully logged in or not- on the appropriate screen
		//menu screen if authenticated user and Login screen if not
		modelAndView.addObject("message", authStatus);
		
		return modelAndView;
	}

}
