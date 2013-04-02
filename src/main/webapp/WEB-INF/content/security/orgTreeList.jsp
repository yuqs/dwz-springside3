<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<html>
<%@ include file="/includes/include.jsp"%>
<head>
<link href="${pageContext.request.contextPath}/styles/css/zTreeStyle.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/styles/js/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/styles/js/jquery.ztree.core-3.4.min.js" type="text/javascript"></script>
</head>
	<SCRIPT type="text/javascript">
		var zTreeNodes = [];
		var setting = {
		};
		$(document).ready(function(){
			$.ajax({
			    type:"GET",       
			    url:"orgTreeData.do",
			    success:function(msg)
			    {
					var datas = eval(msg);
					zTreeNodes = datas;
					$.fn.zTree.init($("#orgTree"), setting, zTreeNodes);
			    }
			});
		});
	</SCRIPT>
<div id="orgTree" class="ztree" style="width:160px;height:400px"></div>
</html>