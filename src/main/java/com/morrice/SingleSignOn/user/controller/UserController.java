package com.morrice.SingleSignOn.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.morrice.SingleSignOn.user.business.IUserBusiness;
import com.morrice.SingleSignOn.user.repository.IUser;
import com.morrice.SingleSignOn.user.repository.model.User;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private IUserBusiness userBusiness;	
	
	@PostMapping
	public IUser save(@RequestBody User user) {		
		return userBusiness.save(user);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public IUser findById(@PathVariable Integer id) {		
		return userBusiness.findById(id);		
	}
	
	@PutMapping("/{id}")
	public IUser update(@RequestBody User user, @PathVariable Integer id) {		
		return userBusiness.update(user, id);		
	}
	
	@DeleteMapping("/{id}")
	public IUser deletedById(@PathVariable Integer id) {		
		return userBusiness.findById(id);		
	}
	
}
