package com.shravya.flightreservation.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shravya.flightreservation.entities.User;
import com.shravya.flightreservation.repos.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepo;
	
	@RequestMapping("/home")
	public String showRegistrationPage() {
		return "login/registerUser";
	}
	
	@RequestMapping(value="/registerUser", method=RequestMethod.POST)
	public String register(@ModelAttribute("user") User user) {
		userRepo.save(user);
		return "login/login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@RequestParam("email") String email, @RequestParam("password") String password, ModelMap modelMap) {
		Optional<User> optionalUser = Optional.of(userRepo.findByEmail(email));
		if(optionalUser.isPresent()) {
			User user = optionalUser.get();
			if(user.getPassword().equals(password))
				return "findFlights";
			else {
				modelMap.addAttribute("msg", "Invalid Password! Please try again.");
				return "login/login";
			}
		}
		return "login/registerUser";
	}
	
//	@RequestMapping("/login")
//	public String login() {
//		return "login/login";
//	}
}
