$(function () {
    var pics = $(".banner a img");
    var len = pics.length;
    pics.eq(0).show();
    for (var i = 1; i < len; i++) {
        pics.eq(i).hide();
    }

    function turn() {

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
    setInterval(turn, 5000);
});


(function () {
    var OriginTitile = document.title, titleTime;
    document.addEventListener('visibilitychange', function () {
        if (document.hidden) {
            document.title = '死鬼去哪里了！';
            clearTimeout(titleTime);
        } else {
            document.title = '(つェ⊂)咦!又好了!';
            titleTime = setTimeout(function () {
                document.title = OriginTitile;
            }, 2000);
        }
    });
})();


/**
 * 登录
 */
$(function () {
    $("#login").click(function (event) {
        event.preventDefault();
        if ($("#user").val().length == 0) {
            $("#inform").text("用户名不能为空");
        } else if ($("#password").val().length == 0) {
            $("#inform").text("密码不能为空");
        } else if ($("#code").val().length == 0) {
            $("#inform").text("验证码不能为空");
        } else if ($("#user").val().length != 0 && $("#password").val().length != 0) {
            if (!(/(^[a-zA-Z0-9_-]{4,16}$)/.test($("#user").val()))) {
                $("#inform").text("用户名含有非法字符");//有其他字母或者符号型字符的存在
            } else if ((/(^[a-zA-Z0-9_-]{4,16}$)/.test($("#user").val()))) {
                $.ajax({
                    type: "post",
                    url: "user/login",
                    data: {username: $("#user").val(), password: $("#password").val(),code: $("#code").val()},
                    success: function (result) {
                        if (result.checkResult == "none") {
                            $("#inform").text("该用户不存在");
                        } else if (result.checkResult == "true") {
                            window.location.href = "backed/list";
                            //alert("done");
                        } else if (result.checkResult == "false") {
                            $("#inform").text("账号或密码错误");
                        }else if (result.checkResult == "errorCode"){
                            $("#inform").text("验证码错误");
                        }
                    }
                });
            }
        }
    });
});


/**
 * 注册新用户
 */
$(function () {
    $("#register").click(function (event) {
        event.preventDefault();
        if ($("#username").val().length == 0) {
            $("#inform1").text("用户名不能为空");
        } else if ($("#password1").val().length == 0) {
            $("#inform1").text("密码不能为空");
        } else if ($("#username").val().length != 0 && $("#password1").val().length != 0) {
            if (!(/(^[a-zA-Z0-9_-]{4,16}$)/.test($("#username").val()))) {
                $("#inform1").text("用户名含有非法字符");//有其他字母或者符号型字符的存在
                if (!(/(^[1-9]\d[\w]*$)/.test($("#password1").val()))) {
                    $("#inform1").text("密码必须以数字开头或含有非法字符");//有其他字母或者符号型字符的存在
                    return false
                }
            } else if ($("#password1").val() != $("#confirm_password").val() || $("#confirm_password").val().length == 0) {
                // alert($("#password1").val())
                // alert($("#confirm_password").val())
                $("#inform1").text("密码前后输入不一致");
                return false;
            }
            var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$"); //正则表达式
            if ($("#email") === "") { //输入不能为空
                $("#inform1").text("邮箱不能为空");
                return false;
            } else if (!reg.test($("#email").val())) { //正则验证不通过，格式不对
                $("#inform1").text("邮箱格式不对");
                return false;
            }
            $.ajax({
                type: "post",
                url: "user/register",
                data: {username: $("#username").val(), password: $("#password1").val(), email: $("#email").val()},
                success: function (result) {
                    switch (result.checkResult) {
                        case "none":
                            $("#inform1").text("用户名密码不能为空");
                            break;
                        case "true":
                            $("#inform1").text("恭喜您注册成功");
                            window.location.href = "backed/list";
                            break;
                        case "false":
                            $("#inform1").text("注册失败请重试");
                            break;
                        case "have":
                            $("#inform1").text("用户名已经被占用");
                            break;
                        default:
                            break;
                    }
                }
            });
        }
    });
});


function validateCode(event) {
    event.preventDefault();
    if ($("#code").val().length == 0) {
        $("#inform").text("用户名不能为空");
    }

    $.ajax({
        type: "post",
        url: "user/verifyCode",
        data: {code: $("#code").val()},
        success: function (result) {
            alert(result);
            // var logindata = JSON.parse(result);
            switch (result.checkResult) {
                case "none":
                    $("#inform1").text("用户名密码不能为空");
                    break;
                case "true":
                    $("#inform1").text("恭喜您注册成功");
                    window.location.href = "backed/list";
                    break;
                case "false":
                    $("#inform1").text("注册失败请重试");
                    break;
                case "have":
                    $("#inform1").text("用户名已经被占用");
                    break;
                default:
                    break;
            }
        }
    });
}
