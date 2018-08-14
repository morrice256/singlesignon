package com.morrice.SingleSignOn.user.repository.crud;

import org.springframework.data.repository.CrudRepository;

import com.morrice.SingleSignOn.user.repository.model.User;

public interface UserRepository extends CrudRepository<User, Integer>{

	User findByLogin(String login);

}
