<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/includes/include.jsp"%>
<h2 class="contentTitle">部门编辑</h2>
<form action="${pageContext.request.contextPath}/security/org!save.action?navTabId=OrgList&callbackType=closeCurrent&ajax=1" method="post" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone)">
		<div class="pageFormContent" layoutH="97">
			<p>
				<label>部门名称：</label>
				<input type="text" name="name" size="30" maxlength="20" class="required" value="${name}" />
			</p>
			<p>
				<label>上级部门：</label>
				<input type="hidden" name="parentOrgId" value="${parentOrg.id}"/>
				<input type="text" class="textInput" name="parentOrgName" value="${parentOrg.name}" readonly="readonly" lookupGroup="parentOrg"/>
				<a class="btnLook" href="${pageContext.request.contextPath}/security/org.action?lookup=1" lookupGroup="" maxable="false" resizable="false">查找带回</a>				
			</p>
			<div class="divider"></div>
			<p>
			    <nobr>
				<label>描述：</label>
				<textarea cols="60" rows="2" name="description" style="height:70px;">${description}</textarea>
				</nobr>
			</p>
			<input type="hidden" name="id" value="${id}"/>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
</form>