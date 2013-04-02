package org.springside.modules.security.web;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import org.springside.modules.security.entity.Authority;
import org.springside.modules.security.entity.Resource;
import org.springside.modules.security.service.SecurityManager;
import org.springside.modules.service.ServiceException;
import org.springside.modules.web.CrudActionSupport;

@Namespace("/security")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "resource.action", type = "redirect") })
public class ResourceAction extends CrudActionSupport<Resource> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7831900786247842095L;
	private SecurityManager securityManager;
	//-- 页面属性 --//
	private Long id;
	private Resource entity;
	private Page<Resource> page = new Page<Resource>();
	private Long menuId;
	
	@Override
	public Resource getModel() {
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
		page = securityManager.searchResource(page, filters);
		return executeCommand();
	}

	@Override
	public String input() throws Exception {
		return INPUT;
	}

	@Override
	public String save() throws Exception {
		if(menuId != null && menuId.longValue() > 0) {
			Resource menu = new Resource();
			menu.setId(menuId);
			entity.setMenu(menu);
		}
		securityManager.saveResource(entity);
		addActionMessage("保存资源成功");
		return executeCommand();
	}

	@Override
	public String delete() throws Exception {
		try {
			if(id != null && id.longValue() >= 0) {
				List<Authority> auths = securityManager.getAllAuthority();
				for(Authority auth : auths) {
					for(Resource res : auth.getResources()) {
						if(res.getId().longValue() == id.longValue()) {
							auth.getResources().remove(res);
							securityManager.saveAuthority(auth);
							break;
						}
					}
				}
				securityManager.deleteResource(id);
				addActionMessage("删除资源成功");
			}
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage("删除资源失败");
		}
		return executeCommand();
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			entity = securityManager.getResource(id);
		} else {
			entity = new Resource();
		}
	}

	@Autowired
	public void setSecurityManager(SecurityManager securityManager) {
		this.securityManager = securityManager;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Page<Resource> getPage() {
		return page;
	}

	public Long getId() {
		return id;
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

}
