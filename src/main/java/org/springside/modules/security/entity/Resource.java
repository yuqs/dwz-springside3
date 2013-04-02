package org.springside.modules.security.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springside.modules.orm.IdEntity;

@Entity
@Table(name = "RBAC_RESOURCE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Resource extends IdEntity
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5503383469393219319L;
	
	public static final Integer TYPE_MENU = 0;
	
	public static final Integer TYPE_URL = 1;
	
	public static final Integer TYPE_FILEDIR = 2;
	
	public static final Long ROOT_MENU = 0L;
	
	private String name;
	
	private String source;
	
	private String rel;
	
	private Integer type;
	
	private String description;
	
	private Resource menu;
	
	private Integer selected;
	
	private Set<Resource> resources = new HashSet<Resource>();
	
	private Set<Authority> authorities = new HashSet<Authority>();

    @Column(name = "source", unique = false, nullable = true)
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
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

	@ManyToOne
	@JoinColumn(name="menu")
	public Resource getMenu() {
		return menu;
	}

	public void setMenu(Resource menu) {
		this.menu = menu;
	}

	@Column(name = "name", unique = true, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    @ManyToMany(mappedBy="resources")
	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

	@OneToMany(cascade = {CascadeType.REFRESH, CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="menu")
	@Fetch(FetchMode.SUBSELECT)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<Resource> getResources() {
		return resources;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

	@Transient
	public Integer getSelected() {
		return selected;
	}

	public void setSelected(Integer selected) {
		this.selected = selected;
	}

	@Column(name = "rel", unique = false, nullable = true)
	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

}
