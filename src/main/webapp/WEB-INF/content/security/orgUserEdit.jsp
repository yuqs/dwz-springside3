<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/includes/include.jsp"%>
<div class="pageContent">
<h2 class="contentTitle">账号编辑</h2>
<form method="post" action="${pageContext.request.contextPath}/security/org!ousave.action?ajax=1&rel=usersBox&callbackType=closeCurrent" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)">
		<div class="pageFormContent" layoutH="97">
			<input type="hidden" name="parentOrgId" id="parentOrgId" value="${parentOrgId}"/>
			<input type="hidden" name="user.id" value="${user.id}"/>
			<p>
				<label>账号：</label>
				<input type="text" name="user.username" size="30" maxlength="20" class="required" value="${user.username}" />
			</p>
			<p>
				<label>密码：</label>
				<input type="password" name="user.password" size="30" class="required alphanumeric" alt="字母、数字、下划线" value="${user.password}" />
			</p>
			<p>
				<label>姓名：</label>
				<input type="text" name="user.fullname" size="30" class="required" value="${user.fullname}" />
			</p>
			<p>
				<label>邮箱：</label>
				<input type="text" name="user.email" size="30" value="${user.email}" />
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
</form>
</div>