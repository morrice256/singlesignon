package com.morrice.SingleSignOn.user.business;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.morrice.SingleSignOn.foundation.exceptions.NotFoundException;
import com.morrice.SingleSignOn.user.repository.IUser;
import com.morrice.SingleSignOn.user.repository.model.User;

public interface IUserBusiness {

	IUser save(User user);

	IUser findById(Integer id) throws NotFoundException;

	void deleteById(Integer id);

	IUser update(User user, Integer id);

	PasswordEncoder passwordEncoder();

}
