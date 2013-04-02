<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/includes/include.jsp"%>

<form id="pagerForm" action="${pageContext.request.contextPath}/security/authority.action" method="post">
	<input type="hidden" name="page.pageNo" value="${page.pageNo}" />
	<input type="hidden" name="lookup" value="${lookup}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" method="post" action="${pageContext.request.contextPath}/security/authority.action?lookup=${lookup}" onsubmit="return dwzSearch(this, '${targetType}');">
    <input type="hidden" name="page.pageSize" value="${page.pageSize}" />
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					权限名称：<input type="text" name="filter_LIKES_name"
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
			<li><a class="add" href="${pageContext.request.contextPath}/security/authority!input.action" target="navTab"><span>添加</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="orderIndexs" class="checkboxCtrl"></th>
				<th width="120">权限名称</th>
				<th width="100">显示信息</th>
				<th width="150">权限描述</th>
				<th width="90">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${page.result}" varStatus="s">
				<tr>
					<td><input name="orderIndexs" value="${item.id}" type="checkbox"></td>
				    <td>${item.name}</td>
				    <td>${item.display}</td>
				    <td>${item.description}</td>
				    <td>
				    	<a class="btnDel" href="${pageContext.request.contextPath}/security/authority!delete.action?navTabId=AuthorityList&ajax=1&id=${item.id}" target="ajaxTodo" title="删除"/></a>
						<a class="btnEdit" href="${pageContext.request.contextPath}/security/authority!input.action?id=${item.id}" title="编辑" target="navTab" >编辑</a>
						<a class="btnView" href="${pageContext.request.contextPath}/security/authority!input.action?id=${item.id}" title="查看" target="navTab">查看</a>
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