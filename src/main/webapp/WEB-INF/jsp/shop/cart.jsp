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
    <script src="https://code.jquery.com/jquery-3.0.0.min.js"></script>
    <title>我的购物车</title>

    <style type = “text/css”>
        a {font-size:16px}
        a:link {color: blue; text-decoration:none;}
        a:active:{color: red; }
        a:visited {color:purple;text-decoration:none;}
        a:hover {color: red; text-decoration:underline;}
    </style>

    <script type="text/javascript">

        $(document).ready(function(){
            $.ajax({
                type:"get",
                url:"/shop/ChangeCart",
                dataType:"json",
                success:function(data){
                    var dataObj = data.cartMap;
                    var totalFee = data.totalFee;
                    var con = "";
                    $.each(dataObj, function(i, jsonStr){
                        var n = eval('(' + jsonStr + ')');
                        con += "<tr>";
                        con += "<td>"+n.prName+"</td>";
                        con += "<td>"+n.price+"</td>";
                        con += "<td>"+n.saleNum+"</td>";
                        con += "<td>"+n.price * n.saleNum +"</td>";
                        con += "</tr>"
                    });
                    $("#con").html(con);
                    $("#fee").text("共计"+totalFee+"元");
                }
            });

        })

    </script>
</head>
<body>

<h2>购物车列表</h2>

<table>
    <thead>
    <td  width="100px">商品名称</td>
    <td  width="100px">商品价格</td>
    <td  width="100px">购买数量</td>
    <td  width="100px">小计</td>
    </thead>

    <tbody id="con">

    <%--<c:forEach  var="cart" items="${cartMap}">--%>
        <%--<tr>--%>
            <%--<td >${cart.value.prName}</td>--%>
            <%--<td >${cart.value.price}</td>--%>
            <%--<td >${cart.value.saleNum}</td>--%>
            <%--<td >${cart.value.price * cart.value.saleNum}</td>--%>
        <%--</tr>--%>
    <%--</c:forEach>--%>
    </tbody>
</table>
   <hr>
   <h4 id="fee"></h4>
   <br>
   <a href="list"><h2>继续购物</h2></a><br>
    <br>
   <a href="delCartAll"><h2>清空购物车</h2></a><br>

</body>
</html>

