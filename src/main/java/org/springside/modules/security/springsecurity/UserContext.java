package org.springside.modules.security.springsecurity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

import org.springside.modules.security.entity.Authority;
import org.springside.modules.security.entity.Org;
import org.springside.modules.security.entity.Role;
import org.springside.modules.security.entity.User;

public class UserContext implements UserDetails 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2808836292194492292L;
	
	private User user;
	
	private List<Authority> grantedAuthorities;
	
	private List<Role> roles;
	
	public UserContext(User user)
	{
		String username = user.getUsername();
		if (((username == null) || "".equals(username)) || (user.getPassword() == null) || user.getPassword().equals("")) 
		{
            throw new BadCredentialsException("用户名或密码不能为空");
        }
		this.user = user;
	}
	
	@Override
	public Collection<GrantedAuthority> getAuthorities() 
	{
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		Authority authority = null;
		if(grantedAuthorities != null)
		{
			for(int i = 0; i < grantedAuthorities.size(); i++)
			{
				authority = grantedAuthorities.get(i);
				GrantedAuthorityImpl grantedAuthority = new GrantedAuthorityImpl(authority.getName());
				authorities.add(grantedAuthority);
			}
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		if(user.getEnabled() != null && "1".equalsIgnoreCase(user.getEnabled()))
		{
			return true;
		}
		return false;
	}

	public List<Authority> getGrantedAuthorities() {
		return grantedAuthorities;
	}

	public void setGrantedAuthorities(List<Authority> grantedAuthorities) {
		this.grantedAuthorities = grantedAuthorities;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public User getUser() {
		return user;
	}
	
	public Integer getUserId() {
		return user.getId().intValue();
	}
	
	public String getUserName()
	{
		return user.getFullname();
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Org getOrg()
	{
		return user.getOrg();
	}
	
	public String getOrgName()
	{
		Org org = getOrg();
		if(org != null)
		{
			return org.getName();
		}
		return "";
	}
}
