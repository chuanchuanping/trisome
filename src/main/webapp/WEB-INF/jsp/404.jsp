<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />--%>
    <meta http-equiv="X-UA-Compatible"content="IE=9; IE=8; IE=7; IE=EDGE" />
        <meta name="viewport" content="width=device-width,height=device-height,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>未知世界</title>

    <style>
        html{height: 100%;min-height: 460px;}
        body{height: 100%;position: relative;}
        body,p,ul{margin:0;font-family: "PingFang SC", Helvetica, "Microsoft Yahei", Arial, sans-serif;font-weight:200;}
        .main{width: 980px;margin:0 auto;text-align: center;position: absolute;top:50%;left:50%; margin:-250px 0 0 -490px;}
        .top{ min-height:221px;background: url("<%= basePath %>resources/images/error/404-bg.png") center bottom no-repeat;border-bottom: 1px solid #ccc;position: relative;font-size: 39px;color: #000;padding-bottom: 20px;}
        .top .bee{width: 212px;display: block;margin:0 auto;}
        .result_main{overflow: hidden; display: inline-block; margin-top: 30px;color: #888888;font-size: 16px;letter-spacing: 1px;}
        .result_main ul{list-style:none;padding:0;}
        .result_main ul li{ margin-bottom: 14px; line-height:20px;}
        .result_main ul li i, .result_main ul li span{ display: inline-block; vertical-align: middle;}
        .result_main ul li i{ width: 6px; height: 6px;background: #D8D8D8; border-radius: 50%; margin-right: 13px;}
        .p1 *{vertical-align: middle;}
        .p1 span{margin-left: 8px;}
        a{text-decoration: none;color: #888888}
        @media screen and (max-width:980px){
            .main{width:88%;margin-left: -44%;}
            .top{background-size:100% auto;}
        }
        @media screen and (max-width:540px){
            .top{font-size:30px;background-size:80% auto;}
            .top .bee{width:180px;}
            .result_main{font-size: 14px;}
        }
    </style>
</head>
<body background="">
<div class="main">
    <div class="top">
        <img src="<%= basePath %>resources/images/error/img_gif.gif" alt="" class="bee">
        <p style="font-weight: bold;">哎呀！出错了！</p>
    </div>
    <div class="result_main">
        <p style="float:left;margin-left: 10px;">可能原因：</p>
        <div style="float:left;text-align:left;">
            <ul>
                <li><i></i><span>你所查看的页面无法浏览或已经不存在</span></li>
                <li><i></i><span>输入的地址不正确</span></li>
                <li><i></i><span>页面重定义或程序出错</span></li>
            </ul>
            <p class="p1">
                <a href="javascript:history.back(-1);" style="margin-right: 70px;color:#FF6C2B;"><img src="<%= basePath %>resources/images/error/refresh.png" alt=""><span>刷新</span></a>
                <a href="javascript:history.back(-1);"><img src="<%= basePath %>resources/images/error/back.png" alt=""><span>返回</span></a>
            </p>
        </div>
    </div>
</div>
<script type="text/javascript">
    setTimeout(function(){
        window.location.href = "/index";
    },5000);
</script>
</body>
</html>