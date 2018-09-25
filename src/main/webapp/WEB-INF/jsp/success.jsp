<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    Long date  = new Date().getTime();
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

<div id="pagewrap" >

    <div id="header">
        <h1>欢迎来到三体</h1>
            <p>《三体》是<a href="https://baike.baidu.com/item/%E5%88%98%E6%85%88%E6%AC%A3/142084">刘慈欣</a>创作的系列长篇科幻小说，《三体Ⅱ·黑暗森林》、《三体Ⅲ·死神永生》组成，第一部于2006年5月起在《科幻世界》杂志上连载，第二部于2008年5月首次出版，第三部则于2010年11月出版。
            作品讲述了地球人类文明和三体文明的信息交流、生死搏杀及两个文明在宇宙中的兴衰历程。其第一部经过刘宇昆翻译后获得了第73届雨果奖最佳长篇小说</p>
    </div>

    <div id="content">
        <h2> <a href="<%= basePath%>/submitMobile" > 授权登录</a> <h2>

    </div>

    <div id="sidebar">
        <h3>Sidebar</h3>
        <p>text</p>
        <p>text</p>
    </div>


</div>

</body>

</html>