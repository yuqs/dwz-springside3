package org.springside.modules.security.web;

import java.util.List;
import java.util.Set;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import org.springside.modules.security.entity.Authority;
import org.springside.modules.security.entity.Role;
import org.springside.modules.security.service.SecurityManager;
import org.springside.modules.service.ServiceException;
import org.springside.modules.web.CrudActionSupport;

@Namespace("/security")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "role.action", type = "redirect") })
public class RoleAction extends CrudActionSupport<Role> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7831900786247842095L;
	private SecurityManager securityManager;
	//-- 页面属性 --//
	private Long id;
	private Role entity;
	private Page<Role> page = new Page<Role>();
	private Page<Authority> authPage = new Page<Authority>();
	private String orderIndexs;
	
	@Override
	public Role getModel() {
		return entity;
	}

	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.ASC);
		}
		page = securityManager.searchRole(page, filters);
		return executeCommand();
	}

	@Override
	public String input() throws Exception {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
		//设置默认排序方式
		if (!authPage.isOrderBySetted()) {
			authPage.setOrderBy("id");
			authPage.setOrder(Page.ASC);
		}
		authPage.setPageSize(Page.NON_PAGE);
		authPage = securityManager.searchAuthority(authPage, filters);
		
		List<Authority> auths = authPage.getResult();
		Set<Authority> authss = entity.getAuthorities();
		for(Authority auth : auths) {
			for(Authority selAuth : authss) {
				if(auth.getId().longValue() == selAuth.getId().longValue())
				{
					auth.setSelected(1);
				}
				if(auth.getSelected() == null)
				{
					auth.setSelected(0);
				}
			}
		}
		return INPUT;
	}

	@Override
	public String save() throws Exception {
		entity.getAuthorities().clear();
		if (orderIndexs != null && orderIndexs.length() > 0 )
		{
			orderIndexs = orderIndexs.trim();
			String[] ids = orderIndexs.split(",");
			String id = "";
			for(int i = 0; i < ids.length; i++)
			{
				id = ids[i].trim();
				if (id.length() > 0)
				{
					Authority auth = new Authority();
					auth.setId(Long.valueOf(id));
					entity.getAuthorities().add(auth);
				}
			}
		}
		securityManager.saveRole(entity);
		addActionMessage("保存角色成功");
		return executeCommand();
	}

	@Override
	public String delete() throws Exception {
		try {
			if(id != null && id.longValue() > 0) {
				Role role = securityManager.getRole(id);
				System.out.println(role.getUsers());
				securityManager.deleteRole(id);
				addActionMessage("删除角色成功");
			}
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage("删除角色失败");
		}
		return executeCommand();
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			entity = securityManager.getRole(id);
		} else {
			entity = new Role();
		}
	}

	@Autowired
	public void setSecurityManager(SecurityManager securityManager) {
		this.securityManager = securityManager;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Page<Role> getPage() {
		return page;
	}

	public Long getId() {
		return id;
	}

	public Page<Authority> getAuthPage() {
		return authPage;
	}

	public String getOrderIndexs() {
		return orderIndexs;
	}

	public void setOrderIndexs(String orderIndexs) {
		this.orderIndexs = orderIndexs;
	}

}
