package com.morrice.SingleSignOn.user.repository.model;

import java.sql.Timestamp;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.morrice.SingleSignOn.user.repository.IUser;

@Entity
@Table(name = "users")
@DynamicUpdate//Don't Work
public class User implements IUser  {

	private static final long serialVersionUID = 7692651870052707754L;

	@Id
	@GeneratedValue
	private Integer id;

	@Column(unique=true, name="username")
	private String login;

	@Column
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	@Column
	private Boolean enabled;
	
	@Column(updatable=false)
	@CreationTimestamp
	@JsonProperty(access = Access.WRITE_ONLY)
	private Timestamp createDateTime;

	@Column
	@UpdateTimestamp
	private Timestamp updateDateTime;

    @ManyToMany
    @JoinTable( 
        name = "users_roles", 
        joinColumns = @JoinColumn(
          name = "user_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(
          name = "role_id", referencedColumnName = "id")) 
    private Collection<Role> roles;
	
	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String getLogin() {
		return login;
	}

	@Override
	public void setLogin(String login) {
		this.login = login;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public Boolean getEnabled() {
		return enabled;
	}

	@Override
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public Timestamp getCreateDateTime() {
		return createDateTime;
	}

	@Override
	@CreationTimestamp
	public void setCreateDateTime(Timestamp createDateTime) {
		this.createDateTime = createDateTime;
	}

	@Override
	@UpdateTimestamp
	@Version
	public Timestamp getUpdateDateTime() {
		return updateDateTime;
	}

	@Override
	public void setUpdateDateTime(Timestamp updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

}
