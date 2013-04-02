package org.springside.modules.security.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springside.modules.orm.IdEntity;

@Entity
@Table(name = "RBAC_ROLE")
public class Role extends IdEntity
{
	private static final long serialVersionUID = 2041148498753846675L;
	
    public static final int ROLE_TYPE_SYSTEM = 0;

    public static final int ROLE_TYPE_OTHER = 1;

    public static final int ROLE_TYPE_MODULE = 2;
    
    private String name;
    
    private String fullname;
    
    private String type;
    
    private String description;
    
    private Integer selected;
    
    private Set<Authority> authorities = new HashSet<Authority>();
    
    private Set<User> users = new HashSet<User>();

    @Column(name = "name", unique = true, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "fullname", unique = false, nullable = true)
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Column(name = "type", unique = false, nullable = true)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "description", unique = false, nullable = true)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToMany
	@JoinTable(name="RBAC_ROLE_AUTHORITY",joinColumns={ @JoinColumn(name = "ROLE_ID") }, inverseJoinColumns = { @JoinColumn(name = "AUTHORITY_ID") })
	@Fetch(FetchMode.SUBSELECT)
	public Set<Authority> getAuthorities()
	{
		if(authorities == null)
		{
			authorities = new HashSet<Authority>();
		}
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

    @ManyToMany(mappedBy="roles")
	public Set<User> getUsers() 
	{
		if(users == null)
		{
			users = new HashSet<User>();
		}
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
