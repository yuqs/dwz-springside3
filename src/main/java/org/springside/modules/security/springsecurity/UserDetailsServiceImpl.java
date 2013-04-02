package org.springside.modules.security.springsecurity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springside.modules.security.entity.Authority;
import org.springside.modules.security.entity.Role;
import org.springside.modules.security.entity.User;
import org.springside.modules.security.service.SecurityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Sets;

/**
 * 实现SpringSecurity的UserDetailsService接口,实现获取用户Detail信息的回调函数.
 * 
 * @author calvin
 * @modify blademaster
 */
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {

	private SecurityManager securityManager;

	/**
	 * 获取用户Details信息的回调函数.
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {

		User user = securityManager.findUserByName(username);
		if (user == null) {
			throw new UsernameNotFoundException("用户" + username + " 不存在");
		}

		UserContext context = new UserContext(user);
		
		List<Role> roles = new ArrayList<Role>();
		roles.addAll(user.getRoles());
		context.setRoles(roles);
		
		List<Authority> authorities = new ArrayList<Authority>();
		authorities.addAll(user.getAuthorities());
		
		for(Role role : roles)
		{
			authorities.addAll(role.getAuthorities());
		}
		context.setGrantedAuthorities(authorities);

		return context;
	}

	/**
	 * 获得用户所有角色的权限集合.
	 */
	@SuppressWarnings("unused")
	@Deprecated
	private Set<GrantedAuthority> obtainGrantedAuthorities(User user) {
		Set<GrantedAuthority> authSet = Sets.newHashSet();
		/**
		 * 支持对用户通过角色授权
		 */
		for (Role role : user.getRoles()) {
			for (Authority authority : role.getAuthorities()) {
				authSet.add(new GrantedAuthorityImpl(authority.getName()));
			}
		}
		/**
		 * 支持对用户直接授权
		 */
		for(Authority authority : user.getAuthorities()) {
			authSet.add(new GrantedAuthorityImpl(authority.getName()));
		}
		return authSet;
	}

	@Autowired
	public void setSecurityManager(SecurityManager securityManager) {
		this.securityManager = securityManager;
	}
}
