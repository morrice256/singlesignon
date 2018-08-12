package com.morrice.SingleSignOn.user.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.morrice.SingleSignOn.user.business.IUserBusiness;
import com.morrice.SingleSignOn.user.repository.IUser;
import com.morrice.SingleSignOn.user.repository.crud.UserRepository;
import com.morrice.SingleSignOn.user.repository.model.User;

@Service
public class UserBusiness implements IUserBusiness{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public IUser save(User user) {
		return userRepository.save(user);
	}

	@Override
	public IUser findById(Integer id) {
		User user = userRepository.findOne(id);
		return user;	
	}
	
	@Override
	public IUser update(User user, Integer id) {
		//TODO: this block exists because @DynamicUpdate in entity not work correctly 
		User userOld = userRepository.findOne(id);
		if(user.getPassword() == null) {
			user.setPassword(userOld.getPassword());
		}
		user.setCreateDateTime( user.getCreateDateTime() );
		user.setId(id);
		return userRepository.save(user);
	}
	

	@Override
	public void deleteById(Integer id) {
		userRepository.delete(id);	
	}
	
	
	
}
