<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/24
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">

    <!-- viewport meta to reset iPhone inital scale -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>扫码结果</title>

    <!-- css3-mediaqueries.js for IE8 or older -->
    <!--[if lt IE 9]>
    <script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
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

<body>

<div id="pagewrap">

    <div id="header">
        <h1>Header</h1>
        <p>Tutorial by <a href="http://webdesignerwall.com">Web Designer Wall</a> (read <a href="http://webdesignerwall.com/tutorials/responsive-design-in-3-steps">related article</a>)</p>
    </div>

    <div id="content">
        <h2>Content</h2>
        <p>该二维码已经失效,请重新获取</p>

    </div>

    <div id="sidebar">
        <h3>Sidebar</h3>
        <p>text</p>
        <p>text</p>
    </div>

    <div id="footer">
        <h4>Footer</h4>
    </div>

</div>

</body>
</html>