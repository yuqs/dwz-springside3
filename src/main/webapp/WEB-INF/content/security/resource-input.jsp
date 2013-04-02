<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/includes/include.jsp"%>
<h2 class="contentTitle">资源编辑</h2>
<form action="${pageContext.request.contextPath}/security/resource!save.action?navTabId=ResourceList&callbackType=closeCurrent&ajax=1" method="post" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone)">
	<div class="pageContent">
		<div class="pageFormContent" layoutH="97">
			<p>
				<label>资源名称：</label>
				<input type="text" name="name" size="30" class="required" value="${name}" />
			</p>
			<p>
				<label>资源：</label>
				<input type="text" name="source" size="30" class="required" value="${source}" />
			</p>
			<div class="divider"></div>
			<p>
				<label>资源类型：</label>
				<input type="radio" name="type" onclick="showMenuSelect(this.value)" value="1" ${empty type ? 'checked=true' : (type == 1 ? 'checked=true' : '')}/>URL资源
				<input type="radio" name="type" onclick="showMenuSelect(this.value)" value="0" ${type == 0 ? 'checked=true' : ''}/>菜单资源
				<input type="radio" name="type" onclick="showMenuSelect(this.value)" value="2" ${type == 2 ? 'checked=true' : ''}/>文件目录
			</p>
			<div id="cascadeMenu" style="display:${type == 0 ? 'block' : 'none'}">
			<p>
			    <label>上级菜单：</label>
				<input type="hidden" name="menuId" value="${menu.id}"/>
				<input type="text" class="textInput" name="menuName" value="${menu.name}" readonly="readonly" lookupGroup="menu" />
				<a class="btnLook" href="${pageContext.request.contextPath}/security/resource.action?lookup=1" lookupGroup="">查找带回</a>				
			</p>
			<p>
				<label>rel：</label>
				<input type="text" name="rel" size="30" value="${rel}" />
			</p>
			</div>
			<div class="divider"></div>
			<p>
			    <nobr>
				<label>资源描述：</label>
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
	</div>
<script>
function showMenuSelect(value)
{
	switch (value) {
    case "0" :
    	document.getElementById("cascadeMenu").style.display='block';
    	break;
    default:
    	document.getElementById("cascadeMenu").style.display='none';
    	break;
	}
}
</script>
</form>