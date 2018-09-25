<%@ page import="java.util.Date" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta name="viewport" content="width=device-width,height=device-height,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="X-UA-Compatible"content="IE=9; IE=8; IE=7; IE=EDGE" />
	<title>三体世界</title>
	<meta name="keywords" content="" />
	<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
	<link href="<%= basePath %>resources/css/style.css" type="text/css" rel="stylesheet" media="all">
	<link href="<%= basePath %>resources/css/jquerysctipttop.css" rel="stylesheet" type="text/css">
	<style type="text/css">
		body {background-image:url(<%= basePath %>resources/images/santi/1.jpg);}
	</style>
	<script src="https://code.jquery.com/jquery-3.0.0.min.js"></script>
	<script src="<%= basePath %>resources/js/back/lunbo.js"></script>

</head>

<body>
	<div class="banner">
		<a class="" href="####" ><img  src="<%= basePath %>resources/images/santi/lun1.jpg" width="110%" height="70%"></a>
		<a class="" href="####" ><img  src="<%= basePath %>resources/images/santi/lun2.jpg" width="110%" height="70%"></a>
		<a class="" href="####" ><img  src="<%= basePath %>resources/images/santi/lun3.jpg" width="110%" height="70%"></a>
		<a class="" href="####" ><img  src="<%= basePath %>resources/images/santi/lun4.jpg" width="110%" height="70%"></a>
		<a class="" href="####" ><img  src="<%= basePath %>resources/images/santi/lun5.jpg" width="110%" height="70%"></a>
		<a class="" href="####" ><img  src="<%= basePath %>resources/images/santi/lun6.jpg" width="110%" height="70%"></a>
		<a class="" href="####" ><img  src="<%= basePath %>resources/images/santi/lun7.jpg" width="110%" height="70%"></a>
	</div>
<h1>欢迎来到三体世界</h1>
		<section>
  <div class="controls w3l">
    <div class="cbcontrol" id="cbControlRight"><</div>
    <div class="cbcontrol" id="cbControlLeft">></div>
    <div class="clear"></div>
  </div>
  <div class="stage">
   
    <div class="cbImage w3">
		<h3>重置密码</h3>
		<form action="" method="post">
			<input type="text" class="email" name="email" placeholder="密码" required="">
			<input type="text" class="number" name="phone number" placeholder="手机号码" required="">
			<input type="submit" class="done" value="发送">
		</form>
		<a href="#" class="rterms">游戏服务条款</a>
	</div>
    <div class="cbImage active signin agileits">
		<h3>登录</h3>
		<form  method="post">
			<input type="text" id="user" name="Name" class="name" placeholder="账号" required=""/>
			<input type="password" id="password" name="password" class="password" placeholder="密码" required=""/>
			<span style="padding: 0px; width: 70px; height: 35px" >
			<img alt="验证码" class="mystyle" src="<%=basePath %>user/verifyCode" title="看不清可点击刷新验证码" style="cursor:pointer;"  onclick="this.src='<%=basePath %>user/verifyCode?id='+new Date();"></span>
			<input type="text"  id="code" name="code"
				   placeholder="输入验证码"/>
			<p id="inform" style="color: red" ></p>
			<input type="submit"  id="login" value="进入三体"/>

			<ul class="bottom">
				<li><a href="#" class="forgot">忘记密码？</a></li>
				<li><a href="#" class="terms">游戏服务条款</a></li>
			</ul>
		</form>
	</div>
    <div class="cbImage agileinfo">
		<h3>点我注册</h3>
		<form action="#" method="post">
			<input type="text" id="username" name="username" placeholder="帐号" required="">
			<input type="password" id="password1" name="password" placeholder="密码" required="">
			<input type="password" id="confirm_password" name="confirm password" placeholder="确认密码" required="">
			<input type="text" id="email" name="email" placeholder="邮箱" required="">
			<p id="inform1" style="color: red" ></p>
			<input type="submit" id="register" value="我要加入">
		</form>
	</div>
    <div class="clear"></div>
  </div>
  <div class="clear"></div>
  <div class="footer">
  <p>&copy; 2038 . All Rights Reserved | 未来三体计划</p>
  </div>
</section>
<!--<script src="js/jquery-2.1.4.min.js"></script>-->
<script src="<%= basePath %>resources/js/back/coverflow-slideshow.js"></script>
<div style="text-align:center;">
</div>

</body>
</html>
