package org.springside.modules.security.springsecurity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import org.springside.modules.security.entity.Authority;
import org.springside.modules.security.entity.Resource;
import org.springside.modules.security.service.SecurityManager;

public class DataBaseFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource 
{
	private SecurityManager securityManager = null;
	
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
	
    private boolean stripQueryStringFromUrls;
	
	public DataBaseFilterInvocationSecurityMetadataSource(SecurityManager securityManager)
	{
		this.securityManager = securityManager;
		loadRegisteredResource();
	}
	
	private void loadRegisteredResource()
	{
		if(resourceMap == null) 
		{  
			resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
			List<Authority> authorities = securityManager.getAllAuthority();
			for(Authority authority : authorities)
			{
				Set<Resource> resources = authority.getResources();
				if(resources == null || resources.isEmpty())
				{
					continue;
				}
				ConfigAttribute configAttribute = new SecurityConfig(authority.getName());  
				
				Iterator<Resource> resIt = resources.iterator();
				while(resIt.hasNext())
				{
					Resource resource = resIt.next();
					if(resourceMap.containsKey(resource.getSource()))
					{
						Collection<ConfigAttribute> configAttributes = resourceMap.get(resource.getSource());
						configAttributes.add(configAttribute);
						resourceMap.put(resource.getSource(), configAttributes);  
					}
					else
					{
						Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
						configAttributes.add(configAttribute);
						resourceMap.put(resource.getSource(), configAttributes);  
					}
				}
			}
		}  
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes()
	{
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException 
	{
        String url = ((FilterInvocation) object).getRequestUrl();

        return lookupAttributes(url);
	}
	
	public final Collection<ConfigAttribute> lookupAttributes(String url) 
	{
        if (stripQueryStringFromUrls) {
            // Strip anything after a question mark symbol, as per SEC-161. See also SEC-321
            int firstQuestionMarkIndex = url.indexOf("?");

            if (firstQuestionMarkIndex != -1) {
                url = url.substring(0, firstQuestionMarkIndex);
            }
        }

		if(resourceMap == null) 
		{  
			loadRegisteredResource();  
		}
		return resourceMap.get(url); 
    }

	@Override
	public boolean supports(Class<?> arg0) 
	{
		return true;
	}

	public boolean isStripQueryStringFromUrls() {
		return stripQueryStringFromUrls;
	}

	public void setStripQueryStringFromUrls(boolean stripQueryStringFromUrls) {
		this.stripQueryStringFromUrls = stripQueryStringFromUrls;
	}

}
