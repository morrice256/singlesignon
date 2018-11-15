package com.morrice.SingleSignOn.user.repository;

import java.util.Collection;

import com.morrice.SingleSignOn.foundation.model.IModel;
import com.morrice.SingleSignOn.user.repository.model.Role;

public interface IPrivilege  extends IModel{

	public String getName();

	public void setName(String name);

	public Collection<Role> getRoles();

	public void setRoles(Collection<Role> roles);

}
