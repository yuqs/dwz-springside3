<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/includes/include.jsp"%>

<form id="pagerForm" action="${pageContext.request.contextPath}/security/org.action" method="post">
	<input type="hidden" name="page.pageNo" value="${page.pageNo}" />
	<input type="hidden" name="lookup" value="${lookup}" />
</form>

<div class="pageHeader">
    <form rel="pagerForm" method="post" action="${pageContext.request.contextPath}/security/org.action?lookup=${lookup}" onsubmit="return dwzSearch(this, '${targetType}');">
    <input type="hidden" name="page.pageSize" value="${page.pageSize}" />
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					部门名称：<input type="text" name="filter_LIKES_name"
						value="${param['filter_LIKES_name']}"/>
				</td>
			</tr>
		</table>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
		<c:choose>
			<c:when test="${empty lookup}">
			<li><a class="add" href="${pageContext.request.contextPath}/security/org!input.action" target="navTab"><span>添加</span></a></li>
			</c:when>
			<c:otherwise>
				<li><a class="icon" href="javascript:$.bringBack({parentOrgId:'', parentOrgName:''})"><span><hi:text key="重置"/></span></a></li>
			</c:otherwise>
		</c:choose>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="orderIndexs" class="checkboxCtrl"></th>
				<th width="120">部门名称</th>
				<th width="100">上级部门</th>
				<th width="150">描述</th>
				<th width="90">
					<c:choose>
						<c:when test="${empty lookup}">操作</c:when>
						<c:otherwise>查找带回</c:otherwise>
					</c:choose>
				</th>	
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${page.result}" varStatus="s">
				<tr>
					<td><input name="orderIndexs" value="${item.id}" type="checkbox"></td>
				    <td>${item.name}</td>
				    <td>${item.parentOrg.name}</td>
				    <td>${item.description}</td>
				    <td>
				    <c:choose>
				    <c:when test="${empty lookup}">
				    	<a class="btnDel" href="${pageContext.request.contextPath}/security/org!delete.action?navTabId=OrgList&ajax=1&id=${item.id}" target="ajaxTodo" title="删除" parameterLanguageKeys="账号"/></a>
						<a class="btnEdit" href="${pageContext.request.contextPath}/security/org!input.action?id=${item.id}" title="编辑" target="navTab" >编辑</a>
						<a class="btnView" href="${pageContext.request.contextPath}/security/org!input.action?id=${item.id}" title="查看" target="navTab">查看</a>
					</c:when>
					<c:otherwise>
						<a class="btnSelect" href="javascript:$.bringBack({parentOrgId:'${item.id}', parentOrgName:'${item.name}'})" title="查找带回">选择</a>
					</c:otherwise>
					</c:choose>

					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>每页</span>
			<c:set var="pageSizeList" value="${fn:split('12|20|50|100', '|')}"/>  
			<select name="page.pageSize" onchange="navTabPageBreak({numPerPage:this.value})">
				<c:forEach var="item" items="${pageSizeList}">
					<option value="${item}" ${item eq page.pageSize ? 'selected="selected"' : ''}>${item}</option>
				</c:forEach>
			</select>
			<span>条，共${page.totalCount}条</span>
		</div>
		<div class="pagination" targetType="${targetType}" totalCount="${page.totalCount}" numPerPage="${page.pageSize}" pageNumShown="10" currentPage="${page.pageNo}"></div>
	</div>
</div>		