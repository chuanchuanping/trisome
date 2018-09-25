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
    <title>用户中心</title>
    <script src="https://code.jquery.com/jquery-3.0.0.min.js"></script>
    <script src="<%= basePath %>resources/js/common/ajaxfileupload.js"></script>
    <script type="text/javascript">
    $(function () {

      $("#changImg").click(function () {

          if($("#partfile").val() != "") {
              $.ajaxFileUpload({
                  type: "POST",
                  url:"uploadImg",
                  dataType: "json",
                  fileElementId:"partfile",  // 文件的id
                  success: function(result){
                      if(result.result_code == 200) {
                          //alert("上传成功");
                          //图片显示
                          $("#partimg").attr("src","<%= basePath%>resources/userImg/"+result.result_msg);
                      }
                  },
                  error: function () {
                      alert("上传失败");
                  },
              });
          } else {
              alert("请先选择文件");
          }
      });

     $("#huan").click(function () {
         $.ajax({
             type:"post",
             url:"changeImg",
             dataType:"json",
             success:function(result){
                 if(result.result_code == 200) {
                     //alert("上传成功");
                     //图片显示
                     $("#partimg").attr("src","<%= basePath%>resources/userImg/"+result.result_msg);
                 }else if (result.result_code == 400){
                     alert(result.result_msg);
                 }else {
                     alert("图片更换失败请重试")
                 }
             }
         });
     })
    })

    </script>

</head>

<body background="<%= basePath%>resources/images/user/2.jpg">

<div id="header">
    <div class="page-container" id="nav">
       
        <ul class="nav-item">
            <li class="set-btn visible-xs-block js-header-avator"><a href="" target="_self"></a></li>

            <li class="visible-xs-block"><a href="#" target="_self">我的设置</a></li>
            <li class="visible-xs-block"><a href="<%= basePath %>user/logOff" target="_self">退出</a></li>

        </ul>
    </div>
</div>



<div id="main">

    <div class="page-settings">
        <div class="top">
            <div class="w960 mauto top_title">
                <p>个人设置</p>
            </div>
        </div>
        <div class="setting pb10">
            <div class="contentBox">
                <div class="formBox">
                    <div id="setting-profile" class="setting-wrap setting-profile">

                        <div class="wlfg-wrap clearfix ">
                            <label class="label-name" for="job">头像</label>
                            <div class="rlf-group" >
                                <div ><img class="fl avator-img" src="${headimgurl}"    id="partimg" data-portrait="58492fe600012e8e01800180" width="180" height="180"></div>
                                <div class="fl ml20 pr">
                                    <div><input type="button" id="huan" hidefocus="true" value="换一换" class="js-avator-try avator-try"></div>
                                    <div id="avator-btns" class="avator-btn-inner">
                                        <div class="avator-btn-wrap">
                                            <div>
                                                <form action="" method="post" id="partFrom" enctype="multipart/form-data" >
                                                    <input type="file"  id="partfile" name="uploadFile" />
                                                    <input type="button" id="changImg" value="上传头像">
                                                </form>
                                            </div>
                                            <iframe src="about:blank" id="uploadtarget" name="uploadtarget" frameborder="0" style="display:none;"></iframe>
                                        </div>
                                    </div>
                                </div>
                                <div class="cb"></div>
                                <p class="rlf-tip-wrap errorHint color-red" ></p>
                            </div>
                        </div>

                        <form id="profile" >

                            <div class="wlfg-wrap clearfix">
                                <label class="label-name" for="nick" >昵称：</label>
                                <div class="rlf-group">
                                    <input type="text" name="nickname" id="nick"  autocomplete="off"  data-validate="require-nick"  class="moco-form-control" value="${nickname}" placeholder="请输入昵称."/>
                                    <p class="rlf-tip-wrap errorHint color-red" ></p>
                                </div>
                            </div>


                            <label class="label-name h16 lh16" >所在地：</label>
                             国家：${country}    &nbsp; 省份：${province} &nbsp; 城市：${city}


                            <div class="wlfg-wrap clearfix">
                                <label class="label-name h16 lh16" >性别：</label>
                                <c:if test="${sex == 1}">
                                    <label  class="lh16"><input type="radio" hidefocus="true" value="1"  name="sex">男</label>
                                </c:if>
                                <c:if test="${sex == 0}">
                                    <label  class="lh16"><input type="radio" hidefocus="true" value="2" checked="checked" name="sex">女</label>
                                </c:if>
                            </div>
                            <div class="wlfg-wrap clearfix">
                                <label class="label-name" for="aboutme">个性签名：</label>
                                <div class="rlf-group">
                                    <div class="pr">
                                        <textarea name="aboutme"  id="aboutme"  rows="5" class="noresize js-sign moco-form-control"></textarea>
                                        <p class="numCanInput js-numCanInput ">还可以输入128个字符</p>
                                    </div>
                                </div>
                            </div>

                            <div class="wlfg-wrap clearfix">
                                <label class="label-name" for="profile-submit"></label>
                                <div class="rlf-group">
                                    <span id="profile-submit"  hidefocus="true"  aria-role="button" class="rlf-btn-green btn-block profile-btn">保存</span>
                                </div>
                            </div>
                        </form>

                    </div>
                </div>
            </div>
        </div>

    </div>

</div>


<div id="footer" >
    <div class="waper">
        <div class="footerwaper clearfix">
            <div class="followus r">
                <a class="followus-weixin" href="javascript:;"  target="_blank" title="微信">
                    <div class="flw-weixin-box"></div>
                </a>
                <a class="followus-weibo" href="http://weibo.com/u/3306361973"  target="_blank" title="新浪微博"></a>
                <a class="followus-qzone" href="http://user.qzone.qq.com/1059809142/" target="_blank" title="QQ空间"></a>
            </div>
        </div>
    </div>
</div>





<div style="display: none">
    <script type="text/javascript">
        var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
        document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3Ff0cfcccd7b1393990c78efdeebff3968' type='text/javascript'%3E%3C/script%3E"));
        (function (d) {
            window.bd_cpro_rtid="rHT4P1c";
            var s = d.createElement("script");s.type = "text/javascript";s.async = true;s.src = location.protocol + "//cpro.baidu.com/cpro/ui/rt.js";
            var s0 = d.getElementsByTagName("script")[0];s0.parentNode.insertBefore(s, s0);
        })(document);
    </script>
    <script>
        (function(){
            var bp = document.createElement('script');
            bp.src = '//push.zhanzhang.baidu.com/push.js';
            var s = document.getElementsByTagName("script")[0];
            s.parentNode.insertBefore(bp, s);
        })();
    </script>
</div>
</body>
</html>