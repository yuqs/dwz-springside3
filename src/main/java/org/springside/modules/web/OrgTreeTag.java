package org.springside.modules.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springside.modules.security.entity.Org;
import org.springside.modules.security.service.SecurityManager;

public class OrgTreeTag extends TagSupport
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8236178120161088070L;

	WebApplicationContext context;
	
	ServletContext sc = null;

	@Override
	public int doEndTag() throws JspException 
	{
		try 
		{
			sc = pageContext.getServletContext();
			context = WebApplicationContextUtils.getWebApplicationContext(sc);
			
			SecurityManager securityManager = context.getBean(SecurityManager.class);
			List<Org> orgs = securityManager.getAllOrg();
			
			Map<Long, List<Org>> orgMap = buildOrgTreeMap(orgs);
			
			StringBuffer buffer = new StringBuffer();
			buildOrgTreeFolder(buffer, orgMap, Org.ROOT_ORG_ID);
			pageContext.getOut().write(buffer.toString());
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return super.doEndTag();
	}
	
	private Map<Long, List<Org>> buildOrgTreeMap(List<Org> orgs)
	{
		Map<Long, List<Org>> menuMap = new TreeMap<Long, List<Org>>();
		
		for(Org org : orgs)
		{
			Long pId = org.getParentOrg() == null ? Org.ROOT_ORG_ID : org.getParentOrg().getId();
			if(!menuMap.containsKey(pId))
			{
				List<Org> subOrgs = new ArrayList<Org>();
				subOrgs.add(org);
				menuMap.put(pId, subOrgs);
			}
			else
			{
				List<Org> subOrgs = menuMap.get(pId);
				subOrgs.add(org);
				menuMap.put(pId, subOrgs);
			}
		}
		return menuMap;
	}
	
	/**
	 * 构建菜单目录
	 * @param buffer html信息
	 * @param menuMap
	 * @param menuId
	 */
	private void buildOrgTreeFolder(StringBuffer buffer, Map<Long, List<Org>> orgMap, Long orgId)
	{
		List<Org> treeFolders = orgMap.get(orgId);
		if(treeFolders == null)
		{
			return;
		}
		for(Org org : treeFolders)
		{
			buffer.append("<ul class='tree treeFolder'>");
			buffer.append("<li><a href='");
			buffer.append(sc.getContextPath());
			buffer.append("/security/org!oulist.action?parentOrgId=").append(org.getId());
			buffer.append("' target='ajax' ");
			buffer.append(" rel='usersBox'>");
			buffer.append(org.getName());
			buffer.append("</a><ul>");
			
			List<Org> treeNodes = orgMap.get(org.getId());
			buildOrgTreeNode(orgMap, buffer, treeNodes);
			
			buffer.append("</ul></li></ul>");
		}
	}
	
	private void buildOrgTreeNode(Map<Long, List<Org>> orgMap, StringBuffer buffer, List<Org> treeNodes)
	{
		if(treeNodes == null)
		{
			return;
		}
		for(Org org : treeNodes)
		{
			buffer.append("<li><a href='");
			buffer.append(sc.getContextPath());
			buffer.append("/security/org!oulist.action?parentOrgId=").append(org.getId());
			buffer.append("' target='ajax' ");
			buffer.append(" rel='usersBox'>");
			buffer.append(org.getName());
			buffer.append("</a>");
			
			if(orgMap.containsKey(org.getId()))
			{
				buffer.append("<ul>");
				buildOrgTreeNode(orgMap, buffer, orgMap.get(org.getId()));
				buffer.append("</ul>");
			}
			buffer.append("</li>");
		}
	}
}
