package com.MySecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
//	@GetMapping("/login/after")
//	public String loginAfter() {
//		return "SecretFile";
//	}

}
