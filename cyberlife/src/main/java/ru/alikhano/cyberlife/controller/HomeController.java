package ru.alikhano.cyberlife.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	// test controller, this method will actually redirect to a home page
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	 @RequestMapping(value = "/login", method = RequestMethod.GET)
	    public String login(Model model, String error, String logout) {
	        if (error != null) {
	            model.addAttribute("error", "Username or password is incorrect.");
	        }

	        if (logout != null) {
	            model.addAttribute("message", "Logged out successfully.");
	        }

	        return "login";
	    }

}
