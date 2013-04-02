<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ include file="/includes/javascripts.jsp"%>
<%@ include file="/includes/include.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>SpringSide-DWZ整合系统</title>

<link href="${pageContext.request.contextPath}/styles/themes/default/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/styles/themes/css/core.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/styles/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" />
<!--[if IE]>
<link href="themes/css/ieHack.css" rel="stylesheet" type="text/css" />
<![endif]-->

<script type="text/javascript">
$(function(){
	DWZ.init("${pageContext.request.contextPath}/styles/dwz.frag.xml", {
//		loginUrl:"loginsub.html", loginTitle:"登录",	// 弹出登录对话框
		loginUrl:"${pageContext.request.contextPath}/login.jsp",	// 跳到登录页面
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{pageNum:"page.pageNo", numPerPage:"page.pageSize", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"${pageContext.request.contextPath}/styles/themes"});
		}
	});
});
</script>
</head>

<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<a class="logo" href="${pageContext.request.contextPath}/styles/images/logo.png">标志</a>
				<ul class="nav">
					<li><a href="javascript:;">当前用户:<%=SpringSecurityUtils.getUserName() %></a></li>
					<li><a href="${pageContext.request.contextPath}/security/user!setting.action" target="navTab" rel="UserSetting">个人设置</a></li>
					<li><a href="${pageContext.request.contextPath}/j_spring_security_logout">退出</a></li>
				</ul>
				<ul class="themeList" id="themeList">
					<li theme="default"><div class="selected">蓝色</div></li>
					<li theme="green"><div>绿色</div></li>
					<li theme="purple"><div>紫色</div></li>
					<li theme="silver"><div>银色</div></li>
					<li theme="azure"><div>天蓝</div></li>
				</ul>
			</div>
		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2>SpringSide-DWZ整合</h2><div>收缩</div></div>

				<div class="accordion" fillSpace="sidebar">
					<div class="accordionHeader">
						<h2><span>Folder</span>我的应用</h2>
					</div>
					<div class="accordionContent">
						<work:display />
						<ul class="tree treeFolder">
							<li><a href="javascript:void(0)">系统管理</a>
								<ul>
									<li><a href="${pageContext.request.contextPath}/security/user.action" target="navTab" rel="UserList">用户管理</a></li>
									<li><a href="${pageContext.request.contextPath}/security/org.action" target="navTab" rel="OrgList">部门管理</a></li>
									<li><a href="${pageContext.request.contextPath}/security/org!tree.action" target="navTab" rel="OrgTree">部门用户管理</a></li>
									<li><a href="${pageContext.request.contextPath}/security/role.action" target="navTab" rel="RoleList">角色管理</a></li>
									<li><a href="${pageContext.request.contextPath}/security/authority.action" target="navTab" rel="AuthorityList">权限管理</a></li>
									<li><a href="${pageContext.request.contextPath}/security/resource.action" target="navTab" rel="ResourceList">资源管理</a></li>
								</ul>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent">
						<ul class="navTab-tab">
							<li tabid="main" class="main">
								<a href="javascript:;"><span><span class="home_icon">首页</span></span></a>
							</li>
						</ul>
					</div>
					<div class="tabsLeft">left</div>
					<div class="tabsRight">right</div>
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">首页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page">
						<div class="accountInfo">
						</div>
					</div>
					
				</div>
			</div>
		</div>

		<div id="taskbar" style="left:0px; display:none;">
			<div class="taskbarContent">
				<ul></ul>
			</div>
			<div class="taskbarLeft taskbarLeftDisabled" style="display:none;">taskbarLeft</div>
			<div class="taskbarRight" style="display:none;">taskbarRight</div>
		</div>
		<div id="splitBar"></div>
		<div id="splitBarProxy"></div>
	</div>

	<div id="footer">Copyright &copy; 2013 <a href="http://springside.org.cn" target="_blank">SpringSide-DWZ</a></div>

<!--拖动效果-->
	<div class="resizable"></div>
<!--阴影-->
	<div class="shadow" style="width:508px; top:148px; left:296px;">
		<div class="shadow_h">
			<div class="shadow_h_l"></div>
			<div class="shadow_h_r"></div>
			<div class="shadow_h_c"></div>
		</div>
		<div class="shadow_c">
			<div class="shadow_c_l" style="height:296px;"></div>
			<div class="shadow_c_r" style="height:296px;"></div>
			<div class="shadow_c_c" style="height:296px;"></div>
		</div>
		<div class="shadow_f">
			<div class="shadow_f_l"></div>
			<div class="shadow_f_r"></div>
			<div class="shadow_f_c"></div>
		</div>
	</div>
	<!--遮盖屏幕-->
	<div id="alertBackground" class="alertBackground"></div>
	<div id="dialogBackground" class="dialogBackground"></div>

	<div id='background' class='background'></div>
	<div id='progressBar' class='progressBar'>数据加载中，请稍等...</div>

<script type="text/javascript">
  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-16716654-1']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? ' https://ssl' : ' http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();
</script>

</body>
</html>
