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
    <title>上传图片</title>
</head>
<body background="">
<div>
    <p>上传图片demo</p>
       <form action="<%= basePath%>uploadImg" method="post" enctype="multipart/form-data" >
           <input type="file" name="uploadFile" />
            <input type="submit" value="上传头像"/>
       </form>

</div>
</body>
</html>