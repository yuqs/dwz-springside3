<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/includes/include.jsp"%>
<h2 class="contentTitle">角色编辑</h2>
<form action="${pageContext.request.contextPath}/security/role!save.action?navTabId=RoleList&callbackType=closeCurrent&ajax=1" method="post" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone)">
		<div class="pageFormContent" layoutH="97">
			<p>
				<label>角色名称：</label>
				<input type="text" name="name" size="30" class="required" value="${name}" />
			</p>
			<p>
				<label>显示信息：</label>
				<input type="text" name="fullname" size="30" class="required" value="${fullname}" />
			</p>
			<p>
				<label>描述信息：</label>
				<input type="text" name="description" size="30" value="${description}" />
			</p>
			<div class="divider"></div>
			
			<div style="overflow: auto">
				<table class="list nowrap" width="98%">
					<thead>
						<tr>
							<th width="22"><input type="checkbox" group="orderIndexs" class="checkboxCtrl"></th>
							<th width="120">权限名称</th>
							<th width="100">显示信息</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${authPage.result}" varStatus="s">
							<tr>
								<td><input name="orderIndexs" value="${item.id}" type="checkbox" ${item.selected == 1 ? 'checked=true' : '' }></td>
							    <td>${item.name}</td>
							    <td>${item.display}</td>
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