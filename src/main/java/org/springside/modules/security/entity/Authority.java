package org.springside.modules.security.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springside.modules.orm.IdEntity;

@Entity
@Table(name = "RBAC_AUTHORITY")
public class Authority extends IdEntity
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8349705525996917628L;

	private String name;
	
	private String display;
	
	private String description;
	
	private Integer type;
	
	private Integer selected;
	
	private Set<Resource> resources = new HashSet<Resource>();
	
	private Set<Role> roles = new HashSet<Role>();
    
    private Set<User> users = new HashSet<User>();
	
    @Column(name = "name", unique = true, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "display", unique = false, nullable = true)
	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	@Column(name = "type", unique = false, nullable = true)
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "description", unique = false, nullable = true)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@JoinTable(name="RBAC_AUTHORITY_RESOURCE",joinColumns={ @JoinColumn(name = "AUTHORITY_ID") }, inverseJoinColumns = { @JoinColumn(name = "RESOURCE_ID") })
	public Set<Resource> getResources()
	{
		if(resources == null)
		{
			resources = new HashSet<Resource>();
		}
		return resources;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

	@ManyToMany(mappedBy="authorities")
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

    @ManyToMany(mappedBy="authorities")
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Transient
	public Integer getSelected() {
		return selected;
	}

	public void setSelected(Integer selected) {
		this.selected = selected;
	}
}
