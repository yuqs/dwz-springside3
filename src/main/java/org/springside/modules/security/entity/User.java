package org.springside.modules.security.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springside.modules.orm.IdEntity;

@Entity
@Table(name = "RBAC_USER")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User extends IdEntity
{
	private static final long serialVersionUID = 7446802057673100315L;
	
	public static final Integer TYPE_ADMIN = 0;
	
	public static final Integer TYPE_GENERAL = 1;
	
	private String username;
	
	private String password;
	
	private String fullname;
	
	private Integer type;
	
	private String email;
	
	private String address;
	
	private Integer age;
	
	private String sex;
	
	private String enabled;
	
	private Org org;

	private Set<Role> roles = new HashSet<Role>();
	
	private Set<Authority> authorities = new HashSet<Authority>();

	@Column(name = "username", unique = true, nullable = false)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name = "password", unique = false, nullable = true)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "fullname", unique = false, nullable = true)
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Column(name = "email", unique = false, nullable = true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "address", unique = false, nullable = true)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "age", unique = false, nullable = true)
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Column(name = "sex", unique = false, nullable = true)
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "enabled", unique = false, nullable = true)
	public String getEnabled() {
		return enabled == null ? "1" : enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	@ManyToMany
	@JoinTable(name="RBAC_ROLE_USER",joinColumns={ @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	@Fetch(FetchMode.SUBSELECT)
	public Set<Role> getRoles()
	{
		if(roles == null)
		{
			roles = new HashSet<Role>();
		}
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@ManyToMany
	@JoinTable(name="RBAC_USER_AUTHORITY",joinColumns={ @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "AUTHORITY_ID") })
	@Fetch(FetchMode.SUBSELECT)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
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

	@ManyToOne
	@JoinColumn(name="org", nullable=true)
	public Org getOrg() {
		return org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}

	@Column(name = "type", unique = false, nullable = true)
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
