package com.bee.auto.controller;

import com.bee.auto.bean.Answer;
import com.bee.auto.service.QueryService;
import com.bee.auto.util.WebUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@Slf4j
public class FrontController {

    @Resource
    private QueryService service;


    @PostMapping("/AutoReply")
    @ResponseBody
    public Answer AutoReply(@RequestParam("content") String content) {
        String s = service.queryByCommand(content);
        Answer answer = new Answer();
        answer.setText(s);
        if (answer.getText() == null) {
            answer = service.TULIApi(content);
        }
        return answer;
    }


    //生成二维码
    @RequestMapping("/toolCode")
    public void qrcode(@Param("text") String text, HttpServletResponse response) {

        String url = "https://cli.im/api/qrcode/code?text=" + text + "&mhid=sELPDFnok80gPHovKdI";
        String s = null;
        try {
            s = WebUtil.sendGet(url);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter   out = response.getWriter();
            out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
