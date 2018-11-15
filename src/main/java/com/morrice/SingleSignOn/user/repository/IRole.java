package com.morrice.SingleSignOn.user.repository;

import java.util.Collection;

import com.morrice.SingleSignOn.foundation.model.IModel;
import com.morrice.SingleSignOn.user.repository.model.Privilege;
import com.morrice.SingleSignOn.user.repository.model.User;

public interface IRole  extends IModel{
	
	public String getName();
	
	public void setName(String name);

	public Collection<User> getUsers();

	public void setUsers(Collection<User> users);

	public Collection<Privilege> getPrivileges();

	public void setPrivileges(Collection<Privilege> privileges);

}
