<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/includes/include.jsp"%>
<h2 class="contentTitle">用户角色分配</h2>
<form action="RoleAssignSave.do?navTabId=roleList&callbackType=closeCurrent&ajax=1" method="post" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone)">
	<input type="hidden" name="role.id" value="${role.id}"/>
	<div class="pageContent">
		<div class="pageFormContent" layoutH="97">
			<p>
				<label>角色名称：</label>
				<input type="text" name="role.name" size="30" class="required" readonly="readonly" value="${role.name}" />
			</p>
			<div class="divider"></div>
			
			<div style="overflow: auto">
				<table class="list nowrap" width="98%">
					<thead>
						<tr>
							<th width="22"><input type="checkbox" group="orderIndexs" class="checkboxCtrl"></th>
							<th width="120">用户账号</th>
							<th width="100">用户姓名</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${users}" varStatus="s">
							<tr>
								<td><input name="orderIndexs" value="${item.id}" type="checkbox" ${item.isSelected == 1 ? 'checked=true' : '' }></td>
							    <td>${item.username}</td>
							    <td>${item.fullname}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

		<div class="formBar">
			<ul>
			    <li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
			</ul>
		</div>
</form>