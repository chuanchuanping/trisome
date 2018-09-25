<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,height=device-height,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.0.0.min.js"></script>
    <script type="text/javascript" >

        $(function () {
            var pics = $(".banner a img");
            var len = pics.length;
            pics.eq(0).show();
            for (var i = 1; i < len; i++) {
                pics.eq(i).hide();
            }

            function turn(){

                if (i < 5) {
                    if (pics.eq(i).not('hidden')) {
                        pics.eq(i).hide();
                        pics.eq(i + 1).show();
                    }
                    i++;
                }
                if (i == 5) {
                    pics.eq(5).hide();
                    pics.eq(0).show();
                    i = 0;
                }
            }
            i = 0;
            setInterval(turn, 2000);
        });

    </script>
</head>
<body>
        <div class="banner">

            <a class="" href="####" target="_blank"><img src="<%= basePath %>resources/images/santi/1.jpg"></a>
            <a class="" href="####" target="_blank"><img src="<%= basePath %>resources/images/santi/2.jpg"></a>
            <a class="" href="####" target="_blank"><img src="<%= basePath %>resources/images/santi/3.jpg"></a>
            <a class="" href="####" target="_blank"><img src="<%= basePath %>resources/images/santi/4.jpg"></a>
            <a class="" href="####" target="_blank"><img src="<%= basePath %>resources/images/santi/5.jpg"></a>
            <a class="" href="####" target="_blank"><img src="<%= basePath %>resources/images/santi/6.jpg"></a>
            <a class="" href="####" target="_blank"><img src="<%= basePath %>resources/images/santi/7.jpg"></a>
            <a class="" href="####" target="_blank"><img src="<%= basePath %>resources/images/santi/8.jpg"></a>
        </div>
</body>
</html>