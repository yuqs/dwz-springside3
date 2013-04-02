package org.springside.modules.security.web;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import org.springside.modules.security.entity.Org;
import org.springside.modules.security.entity.User;
import org.springside.modules.security.service.SecurityManager;
import org.springside.modules.service.ServiceException;
import org.springside.modules.web.CrudActionSupport;

@Namespace("/security")
@Results( { @Result(name = OrgAction.RESULT_TREE, location = "/WEB-INF/content/security/orgTree.jsp"),
			@Result(name = OrgAction.RESULT_ORGUSERLIST, location = "/WEB-INF/content/security/orgUserList.jsp"),
			@Result(name = OrgAction.RESULT_ORGUSEREDIT, location = "/WEB-INF/content/security/orgUserEdit.jsp"),
			@Result(name = OrgAction.RESULT_ORGUSERSAVE, location = "org!oulist.action", type = "redirect")})
public class OrgAction extends CrudActionSupport<Org> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7831900786247842095L;
	public static final String RESULT_TREE = "tree";
	public static final String RESULT_ORGUSERLIST = "orguserlist";
	public static final String RESULT_ORGUSEREDIT = "orguseredit";
	public static final String RESULT_ORGUSERSAVE = "orgusersave";
	private SecurityManager securityManager;
	//-- 页面属性 --//
	private Long id;
	private Org entity;
	private User user;
	private Page<Org> page = new Page<Org>();
	private Page<User> userPage = new Page<User>();
	private Long parentOrgId;
	@Override
	public Org getModel() {
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
		page = securityManager.searchOrg(page, filters);
		return SUCCESS;
	}
	
	public String tree() throws Exception {
		return RESULT_TREE;
	}
	
	public String oulist() throws Exception {
		if(parentOrgId != null && parentOrgId.longValue() > 0) {
			userPage = securityManager.searchUser(userPage, parentOrgId);
		}
		return RESULT_ORGUSERLIST;
	}
	
	public String ouedit() throws Exception {
		return RESULT_ORGUSEREDIT;
	}
	
	public String ousave() throws Exception {
		if(parentOrgId != null && parentOrgId.longValue() > 0) {
			Org org = new Org();
			org.setId(parentOrgId);
			user.setOrg(org);
		}

		securityManager.saveUser(user);
		return executeCommand();
	}

	@Override
	public String input() throws Exception {
		return INPUT;
	}

	@Override
	public String save() throws Exception {
		if(parentOrgId != null && parentOrgId.longValue() > 0) {
			Org org = new Org();
			org.setId(parentOrgId);
			entity.setParentOrg(org);
		}
		securityManager.saveOrg(entity);
		addActionMessage("保存部门成功");
		return executeCommand();
	}

	@Override
	public String delete() throws Exception {
		try {
			securityManager.deleteOrg(id);
			addActionMessage("删除部门成功");
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage("删除部门失败");
		}
		return executeCommand();
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			entity = securityManager.getOrg(id);
		} else {
			entity = new Org();
		}
	}

	@Autowired
	public void setSecurityManager(SecurityManager securityManager) {
		this.securityManager = securityManager;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Page<Org> getPage() {
		return page;
	}

	public Long getId() {
		return id;
	}

	public Long getParentOrgId() {
		return parentOrgId;
	}

	public void setParentOrgId(Long parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	public Page<User> getUserPage() {
		return userPage;
	}

	public void setUserPage(Page<User> userPage) {
		this.userPage = userPage;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
