package org.springside.modules.web;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.springside.modules.security.entity.Authority;
import org.springside.modules.security.entity.Resource;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.security.springsecurity.UserContext;

public class MenuTreeTag extends TagSupport 
{
	private static final long serialVersionUID = -3113033467941314801L;
	
	ServletContext sc = null;

	@Override
	public int doEndTag() throws JspException 
	{
		try 
		{
			//获取ServletContext
			sc = pageContext.getServletContext();
			//获取菜单Tree的map数据
			Map<Long, List<Resource>> menuMap = buildMenuTreeMap();
			
			StringBuffer buffer = new StringBuffer();
			//构造树型html语句
			buildMenuTreeFolder(buffer, menuMap, Resource.ROOT_MENU);
			//将html语句输出到页面标签<tree:display/>处
			pageContext.getOut().write(buffer.toString());
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return super.doEndTag();
	}

	@Override
	public int doStartTag() throws JspException
	{
		return super.doStartTag();
	}
	
	/**
	 * 该方法根据当前用户拥有的菜单权限列表构造菜单树所需要的菜单数据
	 * @return 返回map映射数据，其中key：菜单节点的Id，value：菜单节点对应的子菜单列表
	 */
	private Map<Long, List<Resource>> buildMenuTreeMap()
	{
		Map<Long, List<Resource>> menuMap = new TreeMap<Long, List<Resource>>();
		
		/**
		 * 从上下文环境获取已经认证的用户实体
		 */
		UserContext uc = (UserContext)SpringSecurityUtils.getUserContext();
		if(uc == null)
		{
			return menuMap;
		}
		/**
		 * 获取用户实体的已授权的权限列表
		 */
		List<Authority> grantedAuthorities = uc.getGrantedAuthorities();
		for(Authority authority : grantedAuthorities)
		{
			/**
			 * 通过权限获取该权限拥有的资源
			 */
			Set<Resource> ress = authority.getResources();
			Iterator<Resource> resIt = ress.iterator();
			while(resIt.hasNext())
			{
				Resource resource = resIt.next();
				/**
				 * 如果资源不是菜单类型，则不会添加到菜单树的Map中去
				 */
				if(resource.getType() == null || resource.getType().intValue() != Resource.TYPE_MENU)
				{
					continue;
				}
				
				/**
				 * 如果是菜单类型的资源，判断是否有上一级菜单，如果有，则添加到上一级菜单的Map中去
				 * 如果没有上一级菜单，把该菜单作为根节点
				 */
				Long parentMenuId = resource.getMenu() == null ? Resource.ROOT_MENU : resource.getMenu().getId();
				if(!menuMap.containsKey(parentMenuId))
				{
					List<Resource> subMenus = new ArrayList<Resource>();
					subMenus.add(resource);
					menuMap.put(parentMenuId, subMenus);
				}
				else
				{
					List<Resource> subMenus = menuMap.get(parentMenuId);
					subMenus.add(resource);
					menuMap.put(parentMenuId, subMenus);
				}
			}
		}
		
//		Map<Long, List<Resource>> treeMap = new TreeMap<Long, List<Resource>>();
//		List list = new ArrayList();
//		Set<Long> set = menuMap.keySet();
//		Iterator<Long> it = set.iterator();
//		while(it.hasNext())
//		{
//			Long key = it.next();
//			List<Resource> resources = menuMap.get(key);
//			if(!list.contains(key.longValue()))
//			{
//				treeMap.put(key, resources);
//				list.add(key.longValue());
//			}
//		}
//		System.out.println(list);
		return menuMap;
	}
	
	/**
	 * 构建菜单目录
	 * @param buffer html信息
	 * @param menuMap
	 * @param menuId
	 */
	private void buildMenuTreeFolder(StringBuffer buffer, Map<Long, List<Resource>> menuMap, Long menuId)
	{
		List<Resource> treeFolders = menuMap.get(menuId);
		if(treeFolders == null)
		{
			return;
		}
		List list = new ArrayList();
		/**
		 * 循环根菜单资源，并构造dwz框架所需要的tree型html语句
		 */
		for(Resource resource : treeFolders)
		{
			if(list.contains(resource.getId().longValue()))
			{
				continue;
			}
			buffer.append("<ul class='tree treeFolder'>");
			buffer.append("<li><a href='javascript:void(0)'>");
			buffer.append(resource.getName());
			buffer.append("</a><ul>");
			
			List<Resource> treeNodes = menuMap.get(resource.getId());
			/**
			 * 有子菜单时，将子菜单添加到当前节点上
			 */
			buildMenuTreeNode(buffer, treeNodes);
			
			buffer.append("</ul></li></ul>");
			list.add(resource.getId().longValue());
		}
	}
	
	/**
	 * 循环子菜单资源，并构造dwz框架的tree型html语句
	 * @param buffer
	 * @param treeNodes
	 */
	private void buildMenuTreeNode(StringBuffer buffer, List<Resource> treeNodes)
	{
		if(treeNodes == null)
		{
			return;
		}
		for(Resource resource : treeNodes)
		{
			buffer.append("<li><a href='");
			buffer.append(sc.getContextPath());
			buffer.append(resource.getSource());
			buffer.append("' target='navTab' ");
			buffer.append(" rel='");
			buffer.append(resource.getRel() == null ? "" : resource.getRel());
			buffer.append("'>");
			buffer.append(resource.getName());
			buffer.append("</a></li>");
		}
	}
	
}
