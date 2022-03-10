package com.springsecurity.SpringSecurityJWTpractice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {

	@RequestMapping("/welcome")
	public String welcome() {
		return "Welcome to secured home page";
	}
	
	
}
