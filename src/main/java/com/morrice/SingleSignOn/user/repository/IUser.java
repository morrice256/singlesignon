package com.morrice.SingleSignOn.user.repository;

import com.morrice.SingleSignOn.foundation.model.IModel;

public interface IUser extends IModel{

	void setLogin(String login);

	String getPassword();

	void setPassword(String password);

	String getLogin();

	Boolean getEnabled();

	void setEnabled(Boolean enabled);
	
}
