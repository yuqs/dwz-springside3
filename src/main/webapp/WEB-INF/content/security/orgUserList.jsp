<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/includes/include.jsp"%>

	<form id="pagerForm" onsubmit="return divSearch(this, 'usersBox');" action="${pageContext.request.contextPath}/security/org!oulist.action" method="post">
    <input type="hidden" name="page.pageNo" value="${page.pageNo}" />
    <input type="hidden" name="page.pageSize" value="${page.pageSize}" />
    <input type="hidden" name="parentOrgId" id="parentOrgId" value="${parentOrgId}"/>
	</form>

<div class="pageContent" style="border-left:1px #B8D0D6 solid;border-right:1px #B8D0D6 solid">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${pageContext.request.contextPath}/security/org!ouedit.action?parentOrgId=${parentOrgId}" target="dialog" mask="true"><span>添加</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="97" rel="usersBox">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="orderIndexs" class="checkboxCtrl"></th>
				<th width="120">账号</th>
				<th width="100">姓名</th>
				<th width="150">邮箱</th>
				<th width="90">操作</th>	
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${userPage.result}" varStatus="s">
				<tr>
					<td><input name="orderIndexs" value="${item.id}" type="checkbox"></td>
				    <td>${item.username}</td>
				    <td>${item.fullname}</td>
				    <td>${item.email}</td>
				    <td>
				    	<a class="btnDel" href="${pageContext.request.contextPath}/security/user!delete.action?ajax=1&rel=usersBox&id=${item.id}" target="ajaxTodo" title="删除" parameterLanguageKeys="账号"/></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<div class="panelBar">
		<div class="pages">
			<span>每页</span>
			<c:set var="pageSizeList" value="${fn:split('12|20|50|100', '|')}"/>  
			<select name="page.pageSize" onchange="navTabPageBreak({numPerPage:this.value}, 'usersBox')">
				<c:forEach var="item" items="${pageSizeList}">
					<option value="${item}" ${item eq page.pageSize ? 'selected="selected"' : ''}>${item}</option>
				</c:forEach>
			</select>
			<span>条，共${page.totalCount}条</span>
		</div>
		<div class="pagination" rel="usersBox" targetType="${targetType}" totalCount="${page.totalCount}" numPerPage="${page.pageSize}" pageNumShown="10" currentPage="${page.pageNo}"></div>
	</div>
</div>		