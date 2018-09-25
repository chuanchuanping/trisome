<%@ page import="java.util.Date" %>
<%@ page import="java.util.Random" %>
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.0.0.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

    <!--讨论区滚动条begin-->
    <link rel="stylesheet" type="text/css" href="<%= basePath %>resources/css/jscrollpane1.css" />
    <script src="<%= basePath %>resources/js/common/jquery-1.8.0.min.js" type="text/javascript"></script>
    <!-- the mousewheel plugin -->
    <script type="text/javascript" src="<%= basePath %>resources/js/common/jquery.mousewheel.js"></script>
    <!-- the jScrollPane script -->
    <script type="text/javascript" src="<%= basePath %>resources/js/common/jquery.jscrollpane.min.js"></script>
    <script type="text/javascript" src="<%= basePath %>resources/js/common/scroll-startstop.events.jquery.js"></script>
    <!--讨论区滚动条end-->
    <script type="text/javascript" src="<%= basePath %>resources/js/front/talk.js"></script>
    <title>商品列表页面</title>
    <style type = “text/css”>
        a {font-size:16px}
        a:link {color: blue; text-decoration:none;}
        a:active:{color: red; }
        a:visited {color:purple;text-decoration:none;}
        a:hover {color: red; text-decoration:underline;}
    </style>



</head>
<body>

<div align="right"> <a class="btn03" ><button type="button" class="btn03"  data-toggle="modal" data-target="#myModal">在线MI客服</button></a></div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">

            <input type="hidden" value="<%= basePath %>" id="basePath"/>
            <br/>
            <div class="talk">
                <div class="talk_title"><span>正在与秀念对话</span></div>
                <div class="talk_record">
                    <div id="jp-container" class="jp-container">

                    </div>
                </div>

                <div class="talk_word">
                    &nbsp;
                    <input class="add_face" id="facial" type="button" title="添加表情" value="" />
                    <input id="content" class="messages emotion"   />
                    <input class="talk_send" id="talkSubmit" onclick="send()" type="button" title="发送" value="发送" />
                </div>
            </div>
            <div style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';"></div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<br/>
<div align="center">
    <h2>商品列表</h2>
<table>

    <thead>
    <%--<th><input type="hidden" id="all" checked="checked" onclick="changeStatus()"/></th>--%>
    <th width="100px">编号</th>
    <th width="100px">商品名称</th>
    <th width="200px">商品详情</th>
    <th width="100px">价格</th>
    <th width="200px">操作</th>
    </thead>

    <c:forEach items="${proList}" var="product" varStatus="status">
        <tr  <c:if test="${status.index % 2 != 0}">style='background-color:#ECF6EE;'</c:if>>
            <td>${status.index + 1}</td>
            <td>${product.prName}</td>
            <td>${product.prContent}</td>
            <td>${product.price}</td>
            <td ><a name="${status.index+1}"   onclick="addCart(this.name,this)">加入购物车</a></td>
        </tr>
    </c:forEach>
</table>
    <hr>
    <div align="center">
        <a href="toCart"><h2>去购物车查看</h2></a><br>
    </div>
</div>

</body>
</html>
<script type="text/javascript">
    function addCart(name){
        var sta = name;
        $.ajax({
            type:"get",
            url:"/shop/addToCart",
            data:{
                index:sta,
                time:<%= new Random().nextInt(10000)%>
            },
            dataType:"json",
            success:function(str){
                if (str == 'success'){
                    this.append("添加成功``");
                }else {
                    this.append("添加失败~~");
                }
            }
        });
    }

</script>