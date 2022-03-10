package com.springsecurity.SpringSecurityJWTpractice.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	//for authenticating provided username in Front end is same as provided here or not
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//here we are just hardcoding for single user amrit for logging in with given password
		//in real time we authenticate user from Database
		//also this new arraylist is for roles for users but here currently its empty
		if(username.equals("Amrit"))
			return new User("Amrit", "Amrit123", new ArrayList<>());
		else
			throw new UsernameNotFoundException("User not found !!");
		
	}

}
