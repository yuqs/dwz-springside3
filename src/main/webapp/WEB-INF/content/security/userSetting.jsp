<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/includes/include.jsp"%>
<h2 class="contentTitle">个人设置</h2>
<form action="${pageContext.request.contextPath}/security/user!save.action?navTabId=UserSetting&callbackType=closeCurrent&ajax=1" method="post" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone)">
		<div class="pageFormContent" layoutH="97">
			<p>
				<label>密码：</label>
				<input type="password" name="password" size="30" class="required alphanumeric" alt="字母、数字、下划线" value="${password}" />
			</p>
			<p>
				<label>姓名：</label>
				<input type="text" name="fullname" size="30" class="required" value="${fullname}" />
			</p>
			<p>
				<label>邮箱：</label>
				<input type="text" name="email" size="30" class="email" value="${email}" />
			</p>
			<input type="hidden" name="id" value="${id}"/>
			<input type="hidden" name="username" value="${username}"/>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
</form>