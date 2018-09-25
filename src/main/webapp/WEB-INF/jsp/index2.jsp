<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>首页</title>

    <link rel="stylesheet" type="text/css" href="<%= basePath %>resources/css/base.css">
    <script type="text/javascript" src="<%= basePath %>resources/js/jquery-2.1.3.min.js"></script>
    <script type="text/javascript" src="<%= basePath %>resources/js/qrcode/jquery.qrcode.js"></script>
    <script type="text/javascript" src="<%= basePath %>resources/js/qrcode/utf.js"></script>
</head>
<body>
<div class="pc_sign_in_box">
    <div class="pc_sign_in_title">
        <ul>
            <li class="title_selected" data-id="1">扫码登录</li>
            <li data-id="2">账号密码登录</li>
        </ul>
    </div>
    <div class="pc_sign_in_cont">
        <div class="pc_qr_code">
            <input type="hidden" id="uuid" value="${uuid }"/>
        </div>
        <div id="result">请使用手机扫码</div>
    </div>
    <div class="pc_sign_in_input">
        <div class="pc_sign_in_input_w">
            <input type="Number" placeholder="手机号码" maxlength="11" id="username" oninput="if(value.length>11)value=value.slice(0,11)" />
            <input type="password" placeholder="密码" maxlength="15" id="password" />
            <input type="text"  id="code" name="code"
                         placeholder="输入验证码" style="width:120px"/>
            &nbsp;&nbsp;&nbsp;
            <span style="padding: 0px; width: 70px; height: 70px" >
			<img alt="验证码" class="mystyle" src="<%=basePath %>user/verifyCode" title="看不清可点击刷新验证码" style="cursor:pointer;"  onclick="this.src='<%=basePath %>user/verifyCode?id='+new Date();"></span>

        </div>
        <p style="color:red;" class="test_sign_in">手机号输入有误，请重新输入</p>
        <div class="sign_btn">
            <button type="button" id="sign_btn">登 录</button>
        </div>
    </div>
    <div class="footer_cont">
        <ul>
            <li><a href="<%=basePath %>/wx/login">微信登录(第三方)</a></li>
            <li>立即注册</li>
        </ul>
    </div>
</div>
</body>
<script type="text/javascript">
    $(window).load(function(){
        $(".pc_sign_in_box").addClass("bounce-in-down");
    })

    $(".pc_sign_in_title ul li").on("click",function(){
        $(this).parents(".pc_sign_in_title").find("ul li").removeClass("title_selected");
        $(this).addClass("title_selected");
        if($(this).attr("data-id") == "1"){
            $(".pc_sign_in_cont").show();
            $(".pc_sign_in_input").hide();
        }else{
            $(".pc_sign_in_cont").hide();
            $(".pc_sign_in_input").show();
        }
    })
    function testSign(showText){
        $(".test_sign_in").css({
            "visibility":"visible"
        }).html(showText);
    }

    //账号密码登录
    $(".sign_btn").on("click",function(){
        if($("#username").val() == ""){
            testSign("手机号不能为空");
            return;
        }
        if($("#password").val() == ""){
            testSign("密码不能为空");
            return;
        }
        if ($("#code").val().length == 0) {
            testSign("验证码不能为空");
        }
        if(!(/^1[34578]\d{9}$/.test($("#username").val()))){
            testSign("手机号输入有误，请重新输入");
            return;
        }
        $.post("/user/login",
            {username:$("#username").val(),
                password:$("#password").val(),
                code: $("#code").val()
            },
            function(result){
                // alert(result);
                // var logindata = JSON.parse(result);
                if (result.checkResult == "none") {
                    testSign("该用户不存在");
                } else if (result.checkResult == "true") {
                    window.location.href = "backed/list";
                    //alert("done");
                } else if (result.checkResult == "false") {
                    testSign("账号或密码错误");
                }else if (result.checkResult == "errorCode"){
                    testSign("验证码错误");
                }
            })
    })


    //生成二维码
    !function(){
        var uuid = $("#uuid").val();
        console.log(uuid);
        var content =  "http://"+"${pageContext.request.serverName}"+":"+"${pageContext.request.serverPort}"+"/scanLogin?uuid="+uuid;
        console.dir("扫码url: "+content);
        var contextRootPath = "${ctx}";
        console.log("项目根路径: "+"${pageContext.request}");
        $('.pc_qr_code').qrcode({
            render:"canvas",
            width:200,
            height:200,
            correctLevel:0,
            text:content,
            background:"#ffffff",
            foreground:"black",
            src:"resources/img/haibao.png"
        });

        setCookie("sid", 123, -1*60*60*1000);
        keepPool();//自动循环调用
        keepPool2();
    }();

       //轮询
    function keepPool(){
        var uuid = $("#uuid").val();

        $.get("/pool",{"uuid":uuid},function(msg){//如果放入一个不存在的网址怎么办?
            if(msg.successFlag == '1'){
                console.log("扫码成功.....")
                $("#result").html("<font color='red'>扫码成功</font>");
                setCookie(msg.cname, msg.cvalue, 3*60*60*1000);
            }else if(msg.successFlag == '0'){
                $("#result").html(msg.msg);
                $("#result").css({
                    "color":"red"
                })
            }else{
                keepPool();
            }

        });
    }

    //轮询
    function keepPool2(){

        $.get("/pool2",function(msg){//如果放入一个不存在的网址怎么办?
            if(msg.successFlag == '1'){
                console.log("扫码成功.....")
                $.get("/pool3",function(msg){//如果放入一个不存在的网址怎么办?

                });
                $("#result").html("<font color='red'>扫码成功</font>");
                function st(){
                    window.location.href = "backed/list";
                }
                setTimeout(st, 2000);

            }else if(msg.successFlag == '0'){
                $("#result").html(msg.msg);
                $("#result").css({
                    "color":"red"
                })
            }else{
                keepPool2();
            }

        });
    }




    //设置cookie
    function setCookie(cname, cvalue, expireTime) {
        var d = new Date();
        d.setTime(d.getTime() + expireTime);//设置过期时间
        var expires = "expires="+d.toUTCString();
        var path = "path=/"
        document.cookie = cname + "=" + cvalue + "; " + expires + "; " + path;
    }



</script>
</html>