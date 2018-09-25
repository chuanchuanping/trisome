$(function(){
    $(".deleteOne").click(function() {
        var c = window.confirm("确定删除吗");
        if(!c){
        return;
        }
      });
    });

function changeStatus(){
    //获取第一行 (选择两字旁边 的那个复选框的状态)
    var flag = $("#all").is(":checked");
    $("input").attr("checked",flag);
}
$(function(){
    $("#click1").click(function(){
        var checkedNum  = $("input:checked").length;
        if(checkedNum==0){ alert("请至少选择一项!"); return false; }
        if(confirm("确定删除所选项？")){
            var userIds = new Array();
            var command = $('input[name="command"]').val();
            var description = $('input[name="description"]').val();
            var pageNum = $("#currentPageText").val();
            $("input:checked").each(function(){
                //将标签的值放入数组中
                userIds.push($(this).val());//此处添加不能使用add  add不是一个function
            });
            /*    也可以直接使用用jQuery发送异步请求
                $.post("user/reduceUser",{userIds:userIds},function(data){
                if(data.status==200){
                    //删除成功
                    if(confirm("恭喜你删除成功！点击确认刷新页面")){
                        //删除成功直接从新发送请求，加载页面
                        $(location).attr("href","user/showUser");
                    }
                }
                },"json");
            */
            $.ajax({
                type:"post",
                url:"deleteBatch",
                data:{"ids":userIds},
                dataType:"json",
                success:function(data){
                    if(data.status==200){
                        if(confirm("恭喜你删除成功！点击确认刷新页面")){
                            // 删除成功后发送请求，刷新页面
                          $(location).attr("href","list?command="+command+"&description="+description+"&pageNum="+pageNum);
                        }
                    }
                }
            });
        }
    });
});



/**
 * 修改当前页码，调用后台重新查询
 */
function changeCurrentPage(pageNum) {
    $("#pageNum").val(pageNum);
    $("#mainForm").submit();
}

/**
 * 添加一个Message
 */
$(function () {
    $("#saveChanges").click(function(){
        // alert("点击进来了。。。。。")
        var command1 = $('input[name="command1"]').val();
        var description1 = $('input[name="description1"]').val();
        var content1 = $('input[name="content1"]').val();
        $.ajax({
            type:"post",
            url:"addOne",
            data:{"command1":command1,
                "description1":description1,
                "content1":content1
            },
            dataType:"json",
            success:function(data){
                if(data.status==200){
                    if(confirm("恭喜你添加成功！点击确认刷新页面")){
                        // 删除成功后发送请求，刷新页面
                        $(location).attr("href","list");
                    }
                }
            }
        });
    });
});

// function downloadExcel() {
//     var command = $('input[name="command"]').val();
//     var description = $('input[name="description"]').val();
//     location.href="downloadExcel?command="+command+"&description="+description;
//     location.href="www.baidu.com";
// }
/**
 * 修改一个Message
 */
$(document).ready(function(){
    $("#myModalLabel2").click(function(){
        var checkedNum  = $("input:checked").length;
        //alert(checkedNum);
        if(checkedNum==0){ alert("请选择一项!"); return false; }
        if(checkedNum > 1){ alert("只能选择一项!"); return false; }

        var id = $("input:checked").val();
        $.ajax({
            type:"post",
            url:"queryMessageById",
            data:{"id":id},
            dataType:"json",
            success:function(data){
                //如何显示一个input的值
                $("#command2").attr("value",data.command);
                $("#description2").attr("value",data.description);
                $("#content2").attr("value",data.content);
            }
        });
    });

    $("#Changes").click(function(){
        var checkedNum  = $("input:checked").length;
        if(checkedNum==0){ alert("请选择一项!"); return false; }
        if(checkedNum > 1){ alert("只能选择一项!"); return false; }
        var id = $("input:checked").val();
        var command2 = $('input[name="command2"]').val();
        var description2 = $('input[name="description2"]').val();
        var content2 = $('input[name="content2"]').val();
        $.ajax({
            type:"post",
            url:"updateOne",
            data:{"id":id,
                "command2":command2,
                "description2":description2,
                "content2":content2
            },
            dataType:"json",
            success:function(data) {
                if (data.status == 200) {
                    if (confirm("恭喜你更新成功！点击确认刷新页面，关闭该窗口查看")) {
                        // 更新成功，刷新页面
                        $(location).attr("href", "list");
                    }
                }
                if (data.status == 500) {
                    if (confirm("更新失败！请重试")) {
                        $(location).attr("href", "list");
                    }
                }
            }
        });
    });

});

