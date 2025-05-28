package com.cedge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.cedge.model.User;
import com.cedge.repo.UserRepository;
import com.cedge.security.JwtUtil;

@Service
public class AuthenticationService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	public String authenticate(String username, String password) throws Exception {

		// User user = userRepository.findByUsername(username).orElseThrow(() -> new
		// Exception("User not found"));

		try {
			//from 
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			
			
		} catch (AuthenticationException e) {
			throw new Exception("Invalid username or password");
		}

		User user = userRepository.findByUsername(username).orElseThrow(() -> new Exception("User not found"));
		System.out.println("User Name ==> " + user.getUsername());
		System.out.println("Password ==> " + user.getPassword());

		return jwtUtil.generateToken(user.getUsername());
	}

}
