<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>系统登录</TITLE>
<LINK rel=stylesheet type=text/css href="styles/login/css/login.css">
<SCRIPT type=text/javascript src="styles/javascripts/jquery-1.4.4.min.js"></SCRIPT>

<SCRIPT language=javascript>
function sendForm(){
	var s,p;
	s=document.getElementById("j_username").value.trim();
	p=document.getElementById("j_password").value.trim();
	if(s==""||p==""){
		alert("请输入用户名口令");
		return false;
	}else{
		return true;
	}
}
</SCRIPT>

<BODY>
<FORM action="${pageContext.request.contextPath}/j_spring_security_check" method=post>
	<DIV id=content>
		<DIV class=dxsy></DIV>
		<BR/>
		<BR/>
		<DIV id=acc0 class=acc>
			<UL>
  				<LI>账&nbsp;&nbsp;号：
  					<INPUT onblur="this.style.borderWidth='1px' ;this.style.margin='1px';" id='j_username' name='j_username' class=input onfocus="this.style.borderWidth='2px';this.style.margin='0px';" maxLength=19 size=19> 
  				<LI>密&nbsp;&nbsp;码：
  					<INPUT onblur="this.style.borderWidth='1px' ;this.style.margin='1px';" id='j_password' name='j_password' class=input onfocus="this.style.borderWidth='2px';this.style.margin='0px';" maxLength=19 size=19 type='password'> 
  				<!-- 
  				<LI style="VERTICAL-ALIGN: middle">校验码：
  					<INPUT onblur="this.style.borderWidth='1px' ;this.style.margin='1px';" id=seccode0 class=input_d onfocus="this.style.borderWidth='2px';this.style.margin='0px';" maxLength=19 size=19 type=text name=verifycode>
  					<IMG style="VERTICAL-ALIGN: text-bottom" id=secimg0  src="vImage.jpg">&nbsp;&nbsp;
  					<A href="javascript:refreshImgt()">看不清楚？</A>
  				</LI>
   				-->
   				<LI>
   				<input id="_spring_security_remember_me" name="_spring_security_remember_me" type="checkbox" value="true"/>  
				<label for="_spring_security_remember_me">记住我?</label>
   				</LI>
  				<LI class=libuttom>
  					<INPUT type=submit name=submit id=searchinp class=button value="登 录" > 
  					<SPAN style="PADDING-LEFT: 10px; PADDING-TOP: 5px">&nbsp;&nbsp;</SPAN> 
  				</LI>
			</UL>
		</DIV>

		<DIV id=tipArea></DIV>
	
		<DIV style="LINE-HEIGHT: 24px; DISPLAY: none; FONT-FAMILY: 宋体" id=tipAuto>&nbsp;不区分大小写</DIV>

		<DIV class=userip></DIV>
		<DIV class=link></DIV>
		<DIV style="POSITION: absolute; BOTTOM: -10px; COLOR: #fff; LEFT: 20px" id=readcount></DIV>
	</DIV>
</FORM>
</BODY>
</HTML>
