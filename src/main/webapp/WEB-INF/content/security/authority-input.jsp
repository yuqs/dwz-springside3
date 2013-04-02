<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/includes/include.jsp"%>
<h2 class="contentTitle">权限编辑</h2>
<form action="${pageContext.request.contextPath}/security/authority!save.action?navTabId=AuthorityList&callbackType=closeCurrent&ajax=1" method="post" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone)">
		<div class="pageFormContent" layoutH="97">

			<p>
				<label>权限名称：</label>
				<input type="text" name="name" size="30" maxlength="20" class="required" value="${name}" />
			</p>
			<p>
				<label>显示信息：</label>
				<input type="text" name="display" size="30" class="required" value="${display}" />
			</p>
			<p>
				<label>权限描述：</label>
				<input type="text" name="description" size="30" value="${description}" />
			</p>
			<div class="divider"></div>
			
			<div style="overflow: auto">
				<table class="list nowrap" width="98%">
					<thead>
						<tr>
							<th width="22"><input type="checkbox" group="orderIndexs" class="checkboxCtrl"></th>
							<th width="120">资源名称</th>
							<th width="100">资源类型</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${resPage.result}" varStatus="s">
							<tr>
								<td><input name="orderIndexs" value="${item.id}" type="checkbox" ${item.selected == 1 ? 'checked=true' : '' }></td>
							    <td>${item.source}</td>
							    <td>${item.type == 0 ? '菜单资源' : (item.type == 1 ? 'URL资源' : '文件目录')}</td>
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