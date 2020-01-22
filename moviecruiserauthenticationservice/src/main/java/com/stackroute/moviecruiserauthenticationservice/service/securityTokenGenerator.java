package com.stackroute.moviecruiserauthenticationservice.service;

import java.util.Map;

import com.stackroute.moviecruiserauthenticationservice.domain.User;

public interface securityTokenGenerator {
	
	Map<String, String> generateToken(User user);

}
