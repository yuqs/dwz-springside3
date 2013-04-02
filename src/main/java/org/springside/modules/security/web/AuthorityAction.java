package org.springside.modules.security.web;

import java.util.List;
import java.util.Set;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.security.entity.Authority;
import org.springside.modules.security.entity.Resource;
import org.springside.modules.security.entity.Role;
import org.springside.modules.utils.web.struts2.Struts2Utils;
import org.springside.modules.web.CrudActionSupport;
import org.springside.modules.security.service.SecurityManager;
import org.springside.modules.service.ServiceException;

@Namespace("/security")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "authority.action", type = "redirect") })
public class AuthorityAction extends CrudActionSupport<Authority> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7831900786247842095L;
	private SecurityManager securityManager;
	//-- 页面属性 --//
	private Long id;
	private Authority entity;
	private Page<Authority> page = new Page<Authority>();
	private Page<Resource> resPage = new Page<Resource>();
	private String orderIndexs;
	@Override
	public Authority getModel() {
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
		page = securityManager.searchAuthority(page, filters);
		return executeCommand();
	}

	@Override
	public String input() throws Exception {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
		if (!resPage.isOrderBySetted()) {
			resPage.setOrderBy("id");
			resPage.setOrder(Page.ASC);
		}
		resPage.setPageSize(Page.NON_PAGE);
		resPage = securityManager.searchResource(resPage, filters);
		
		List<Resource> ress = resPage.getResult();
		Set<Resource> resss = entity.getResources();
		for(Resource res : ress) {
			for(Resource selRes : resss) {
				if(res.getId().longValue() == selRes.getId().longValue())
				{
					res.setSelected(1);
				}
				if(res.getSelected() == null)
				{
					res.setSelected(0);
				}
			}
		}
		return INPUT;
	}

	@Override
	public String save() throws Exception {
		entity.getResources().clear();
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
					Resource res = new Resource();
					res.setId(Long.valueOf(id));
					entity.getResources().add(res);
				}
			}
		}
		securityManager.saveAuthority(entity);
		addActionMessage("保存权限成功");
		return executeCommand();
	}

	@Override
	public String delete() throws Exception {
		try {
			if(id != null && id.longValue() > 0) {
				List<Role> roles = securityManager.getAllRole();
				for(Role role : roles) {
					for(Authority auth : role.getAuthorities()) {
						if(auth.getId().longValue() == id.longValue()) {
							role.getAuthorities().remove(auth);
							securityManager.saveRole(role);
							break;
						}
					}
				}
				securityManager.deleteAuthority(id);
				addActionMessage("删除权限成功");
			}
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage("删除权限失败");
		}
		return executeCommand();
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			entity = securityManager.getAuthority(id);
		} else {
			entity = new Authority();
		}
	}

	@Autowired
	public void setSecurityManager(SecurityManager securityManager) {
		this.securityManager = securityManager;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Page<Authority> getPage() {
		return page;
	}

	public Long getId() {
		return id;
	}

	public Page<Resource> getResPage() {
		return resPage;
	}

	public String getOrderIndexs() {
		return orderIndexs;
	}

	public void setOrderIndexs(String orderIndexs) {
		this.orderIndexs = orderIndexs;
	}

}
