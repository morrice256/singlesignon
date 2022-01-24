package com.morrice.SingleSignOn.user.repository.model;

import java.sql.Timestamp;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.morrice.SingleSignOn.user.repository.IPrivilege;

@Entity
@Table(name = "privileges")
public class Privilege implements IPrivilege{
  
	private static final long serialVersionUID = -4608090966747458411L;

	@Id
    @GeneratedValue
    private Integer id;
 
    private String name;
    
    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;

	@Column(updatable=false)
	@CreationTimestamp
	@JsonProperty(access = Access.WRITE_ONLY)
	private Timestamp createDateTime;

	@Column
	@UpdateTimestamp
	private Timestamp updateDateTime;
	
    @Override
	public Integer getId() {
		return id;
	}

    @Override
	public void setId(Integer id) {
		this.id = id;
	}

    @Override
	public String getName() {
		return name;
	}

    @Override
	public void setName(String name) {
		this.name = name;
	}

    @Override
	public Collection<Role> getRoles() {
		return roles;
	}

    @Override
	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
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