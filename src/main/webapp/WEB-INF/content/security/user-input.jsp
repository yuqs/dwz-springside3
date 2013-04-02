<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/includes/include.jsp"%>
<h2 class="contentTitle">账号编辑</h2>
<form action="${pageContext.request.contextPath}/security/user!save.action?navTabId=UserList&callbackType=closeCurrent&ajax=1" method="post" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone)">
		<div class="pageFormContent" layoutH="97">

			<p>
				<label>账号：</label>
				<input type="text" name="username" size="30" maxlength="20" class="required" value="${username}" />
			</p>
			<p>
				<label>姓名：</label>
				<input type="text" name="fullname" size="30" class="required" value="${fullname}" />
			</p>
			<p>
				<label>年龄：</label>
				<input type="text" name="age" size="30" value="${age}" />
			</p>
			<p>
				<label>邮箱：</label>
				<input type="text" name="email" size="30" value="${email}" />
			</p>
		<div class="divider"></div>
		<div style="overflow: auto">
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="orderIndexs" class="checkboxCtrl"></th>
				<th width="120">角色名称</th>
				<th width="100">显示信息</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${rolePage.result}" varStatus="s">
				<tr>
					<td><input name="orderIndexs" value="${item.id}" type="checkbox" ${item.selected == 1 ? 'checked=true' : '' }></td>
				    <td>${item.name}</td>
				    <td>${item.fullname}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>	
	</div>		
			<input type="hidden" name="id" value="${id}"/>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
</form>