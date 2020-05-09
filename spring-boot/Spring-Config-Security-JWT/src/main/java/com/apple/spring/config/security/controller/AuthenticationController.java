package com.apple.spring.config.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.apple.spring.config.security.dto.AuthenicationResponse;
import com.apple.spring.config.security.dto.AuthenticationRequest;
import com.apple.spring.config.security.service.MyUserDetailsService;
import com.apple.spring.config.security.utils.JwtUtils;

@RestController
public class AuthenticationController {
	
	@Autowired
	AuthenticationManager authenticationManager; 
	
	@Autowired
	MyUserDetailsService userDetailsService;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@RequestMapping("/hello")
	public String getGreeting() {
		return "Good Afteroon";
	}
	
	@RequestMapping(value="/authenticate",method=RequestMethod.POST)
	public ResponseEntity userAuthenticate(@RequestBody AuthenticationRequest request) throws Exception{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
		}catch(Exception e) {
			throw new Exception("Incorrect Username & Password");
		}
		
		final UserDetails userDetails=userDetailsService.loadUserByUsername(request.getUsername());
		
		String token=jwtUtils.generateToken(userDetails);
		System.out.println("Token:"+token);
		return  ResponseEntity.ok(new AuthenicationResponse(token));
	}
}
