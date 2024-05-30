package com.MySecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.MySecurity.dto.UserRegistrationDTO;
import com.MySecurity.service.MyUserService;

@Controller
public class RegistrationController {
	
	@Autowired
	private MyUserService userService;
	
	
	@GetMapping("/registration")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new UserRegistrationDTO());
		return "registration";
	}

	@PostMapping("/registration")
	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDTO userRegistrationDTO ) {
		userService.save(userRegistrationDTO);
		return "redirect:/SecretFile/registration?success";
	}
}
