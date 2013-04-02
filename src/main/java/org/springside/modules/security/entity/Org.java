package org.springside.modules.security.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springside.modules.orm.IdEntity;

@Entity
@Table(name = "RBAC_ORG")
public class Org extends IdEntity
{
	private static final long serialVersionUID = 7297765946510001885L;
	
	public static final Long ROOT_ORG_ID = 0l;
	
    private Org parentOrg;

    private String name;

    private String active;

    private String fullname;

    private String description;
    
    private String type;
    
    private Set<User> users = new HashSet<User>();
    
    private Set<Org> orgs = new HashSet<Org>();

    @Column(name = "name", unique = false, nullable = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "active", unique = false, nullable = true)
	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	@Column(name = "fullname", unique = false, nullable = true)
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Column(name = "description", unique = false, nullable = true)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "type", unique = false, nullable = true)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@OneToMany(mappedBy = "org",cascade = {CascadeType.REFRESH, CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REMOVE})
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@OneToMany(mappedBy = "parentOrg",cascade = CascadeType.ALL)
	public Set<Org> getOrgs() {
		return orgs;
	}

	public void setOrgs(Set<Org> orgs) {
		this.orgs = orgs;
	}

	@ManyToOne
	@JoinColumn(name="parentOrg", nullable=true)
	public Org getParentOrg() {
		return parentOrg;
	}

	public void setParentOrg(Org parentOrg) {
		this.parentOrg = parentOrg;
	}
}
