package com.springsecurity.SpringSecurityJWTpractice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurity.SpringSecurityJWTpractice.TokensUtil.JwtUtil;
import com.springsecurity.SpringSecurityJWTpractice.model.JwtRequest;
import com.springsecurity.SpringSecurityJWTpractice.model.JwtResponse;
import com.springsecurity.SpringSecurityJWTpractice.service.CustomUserDetailsService;

@RestController
public class JwtController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil; 
	
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	
	@PostMapping("/token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		
		System.out.println(jwtRequest);
		
		//for authenticating request body username, password with the username password from userdetailservice implementation
		try {
			
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),jwtRequest.getPassword()));
			
		}catch (UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("Bad Cridentials");
		}
		catch (BadCredentialsException e) {
			e.printStackTrace();
			throw new Exception("Bad Cridentials");
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Any other exception");
		}
		
		
		
		
		//below code gets executed once authentication is done i.e happy flow
		
		UserDetails userDetails= this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
		
		String token=jwtUtil.generateToken(userDetails);
		
		System.out.println("JWT token : " + token);
		
		return ResponseEntity.ok(new JwtResponse(token));
		
	}
	
	
}
