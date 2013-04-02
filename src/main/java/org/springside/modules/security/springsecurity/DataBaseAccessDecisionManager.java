package org.springside.modules.security.springsecurity;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class DataBaseAccessDecisionManager implements AccessDecisionManager
{
	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException,
			InsufficientAuthenticationException 
	{
		if(configAttributes == null)
		{  
			return;  
		}  
		Iterator<ConfigAttribute> iterator = configAttributes.iterator();  
		while(iterator.hasNext()) 
		{  
			ConfigAttribute configAttribute = iterator.next();  
			String needPermission = configAttribute.getAttribute();
			for(GrantedAuthority grantedAuthority : authentication.getAuthorities())
			{
				if(needPermission.equals(grantedAuthority.getAuthority()))
				{  
					return;  
				}  
			}  
		}  
		throw new AccessDeniedException("Has no right to access to protected resources");
	}

	@Override
	public boolean supports(ConfigAttribute arg0) 
	{
		return true;
	}

	@Override
	public boolean supports(Class<?> arg0) 
	{
		return true;
	}

}
