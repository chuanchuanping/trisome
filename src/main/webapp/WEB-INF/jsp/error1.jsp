<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<html lang="en">
<head>
    <meta charset="utf-8">


    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>扫码成功页面</title>


    <script src="https://cdn.bootcss.com/livingston-css3-mediaqueries-js/1.0.0/css3-mediaqueries.js"></script>
    <![endif]-->

    <style type="text/css">

        body {
            font: 1em/150% Arial, Helvetica, sans-serif;
        }
        a {
            color: #669;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
        h1 {
            font: bold 36px/100% Arial, Helvetica, sans-serif;
        }

        /************************************************************************************
        STRUCTURE
        *************************************************************************************/
        #pagewrap {
            padding: 5px;
            width: 960px;
            margin: 20px auto;
        }
        #header {
            height: 180px;
        }
        #content {
            width: 600px;
            float: left;
        }
        #sidebar {
            width: 300px;
            float: right;
        }
        #footer {
            clear: both;
        }

        /************************************************************************************
        MEDIA QUERIES
        *************************************************************************************/
        /* for 980px or less */
        @media screen and (max-width: 980px) {

            #pagewrap {
                width: 94%;
            }
            #content {
                width: 65%;
            }
            #sidebar {
                width: 30%;
            }

        }

        /* for 700px or less */
        @media screen and (max-width: 700px) {

            #content {
                width: auto;
                float: none;
            }
            #sidebar {
                width: auto;
                float: none;
            }

        }

        /* for 480px or less */
        @media screen and (max-width: 480px) {

            #header {
                height: auto;
            }
            h1 {
                font-size: 24px;
            }
            #sidebar {
                display: none;
            }

        }

        /* border & guideline (you can ignore these) */
        #content {
            background: #f8f8f8;
        }
        #sidebar {
            background: #f0efef;
        }
        #header, #content, #sidebar {
            margin-bottom: 5px;
        }
        #pagewrap, #header, #content, #sidebar, #footer {
            border: solid 1px #ccc;
        }

    </style>
</head>

<body bgcolor="#ffe4c4">

<h2 style="color: #ba2636">绑定失败请检查帐号密码是否存在</h2>
<a href="<%= basePath%>/index2">返回首页</a>

</body>

</html>