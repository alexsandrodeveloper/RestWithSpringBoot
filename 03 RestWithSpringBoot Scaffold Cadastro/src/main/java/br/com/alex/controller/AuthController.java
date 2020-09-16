package br.com.alex.controller;

import static org.springframework.http.ResponseEntity.ok;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alex.data.model.User;
import br.com.alex.repository.UserRepository;
import br.com.alex.security.AccountCredentialsVO;
import br.com.alex.security.jwt.JwtTokenProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "AuthenticationEndpoint")
@RestController
@RequestMapping(value = "/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private UserRepository userRepository;
	
	@ApiOperation(value = "Autentica um User")
	@PostMapping(value = "/signin", consumes = { "application/json", "application/xml", "application/x-yaml" }, produces = {
			"application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<Map<Object , Object>> signin(@RequestBody AccountCredentialsVO data) {
		
		var username = data.getUsername();
		var password = data.getPassword();
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username , password));
		} catch (AuthenticationException e) {
			throw new BadCredentialsException("Invalid username/password supplied");
		}		
		
		User user = this.userRepository.findByUserName(username);
		
		var token = "";
		
		if(user != null) {
			token = jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
		}else {
			throw new UsernameNotFoundException("Username " + username + "not found");
		}
		
		Map<Object , Object> map = new HashMap<>();
		map.put("username", username);
		map.put("token", token);
		
		return ok(map);			
	}
}
