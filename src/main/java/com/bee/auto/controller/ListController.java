package com.bee.auto.controller;


import com.bee.auto.bean.Message;
import com.bee.auto.service.MaintainService;
import com.bee.auto.service.QueryService;
import com.bee.auto.util.ExportPOIUtils;
import com.bee.auto.util.Result;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/backed")
public class ListController {


    public final static Logger logger = LoggerFactory.getLogger(ListController.class);

    @Resource
    private QueryService listService;

    @Resource
    private MaintainService maintainService;



//    @RequestMapping(value = "/list")
//    public String showList(@Param("command") String command, @Param("description") String description, @Param("currentPage") String currentPage, Model model) {
//        // 创建分页对象
//        Page page = new Page();
//
//        Pattern pattern = Pattern.compile("[0-9]{1,9}");
//        if (currentPage == null || !pattern.matcher(currentPage).matches()) {
//            page.setCurrentPage(1);
//        } else {
//            page.setCurrentPage(Integer.valueOf(currentPage));
//        }
//        // 查询消息列表并传给页面
////        req.setAttribute("messageList", listService.queryMessageListByPage(command, description,page));
////        // 向页面传值
////        req.setAttribute("command", command);
////        req.setAttribute("description", description);
////        req.setAttribute("page", page);
//        model.addAttribute("messageList", listService.queryMessageListByPage(command, description, page));
//        model.addAttribute("command", command);
//        model.addAttribute("description", description);
//        model.addAttribute("page", page);
//        return "back/list";
//    }

    @RequestMapping(value = "/list")
    public String showList2(@Param("command") String command, @Param("description") String description, @Param("pageNum") String pageNum, Model model) {
        int page = 0;
        Pattern pattern = Pattern.compile("[0-9]{1,9}");
        if (pageNum == null || !pattern.matcher(pageNum).matches()) {
            page = 1;
        } else {
            page = Integer.valueOf(pageNum);
        }

        PageInfo<Message> pageInfo = listService.queryMessageList(command, description, page);
        model.addAttribute("messageList",pageInfo.getList());
        model.addAttribute("command", command);
        model.addAttribute("description", description);
        model.addAttribute("page", pageInfo);
        return "back/list";
    }

    @RequestMapping(value = "/queryMessageById")
    @ResponseBody
    public JSONObject queryMessageById(@RequestParam("id") Integer id) {
        Message message = listService.queryMessageById(id);
        JSONObject  jsonObject = JSONObject.fromObject(message);
        return jsonObject;
    }


    @GetMapping(value = "/deleteOne")
    public String delectOne(@RequestParam String id, @Param("command") String command, @Param("description") String description, @Param("currentPage") String currentPage) {
         logger.info("--deleteOne--id:"+id+",-->command:"+command+",--->description:"+description+",-->currentPage:"+currentPage);
        maintainService.deleteOne(id);
        //页面重定向
        try {
            if (command != null && !"".equals(command.trim())) {
                command = new String(command.getBytes("utf-8"), "iso-8859-1");
            }
            if (description != null && !"".equals(description.trim())) {
                description = new String(description.getBytes("utf-8"), "iso-8859-1");
            }
            return "redirect:list?command=" + command + "&description=" + description + "&currentPage=" + currentPage;
        } catch (UnsupportedEncodingException e) {
            logger.info("--deleteOne--id:"+id+",-->command:"+command+",--->description:"+description+",-->currentPage:"+currentPage+e);
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/deleteBatch")
    @ResponseBody
    public Result deleteUser(@RequestParam("ids[]") String ids[]) {
        logger.info("--deleteBatch--ids:"+ids);
        maintainService.deleteBatch(ids);
        return new Result(200);
    }

    @PostMapping(value = "/addOne")
    @ResponseBody
    public Result addOne(@Param("command1") String command1, @Param("description1") String description1, @Param("content1") String content1) {

        Message message = new Message();
        message.setCommand(command1);
        message.setDescription(description1);
        message.setContent(content1);
        maintainService.addOne(message);
        return new Result(200);
    }

    @PostMapping(value = "/updateOne")
    @ResponseBody
    public Result updateOne(@RequestParam String id,@Param("command2") String command2, @Param("description2") String description2, @Param("content2") String content2) {

        Message message = new Message();
        try {
            message.setId(id);
            message.setCommand(command2);
            message.setDescription(description2);
            message.setContent(content2);
            logger.info("---updateOne "+message +"---success");
           // int a = 1/0;
            maintainService.updateOne(message);
            return new Result(200);
        }catch (Exception e){
            logger.info("---updateOne "+message +"---fail" );
            logger.info("---updateOne "+e);
            return new Result(500);
        }

    }


    @RequestMapping("/downloadExcel")
    public void downloadExcel(@Param("command") String command, @Param("description") String description, HttpServletResponse response){

        String fileName = "列表"+System.currentTimeMillis();

        List<Message> users = listService.queryMessageList2(command, description );
//        System.out.println(users);

        // 列名
        String columnNames[] = { "序号", "指令名称", "描述", "操作"};
        // map中的key
        String keys[] = { "id", "command", "description", "content"};
        try {
            ExportPOIUtils.start_download(response, fileName, users,columnNames, keys);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}



