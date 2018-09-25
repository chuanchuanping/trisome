package com.bee.auto.controller;

import com.bee.auto.bean.SysUser;
import com.bee.auto.service.UserService;
import com.bee.auto.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping("/user")
public class UserController {

    public final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    public UserService userService;

    @Resource
    public VerifyCode verifyCode;

    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("code") String code, HttpServletRequest request) {
        logger.info("----用户账户：" + username + ",密码：" + password);
        Result result = new Result();
        if (username == null || "".equals(username.trim())) {
            result.setCheckResult(null);
            return result;
        }
        if (password == null || "".equals(password.trim())) {
            result.setCheckResult("none");
            return result;
        }
        if (!verifyCode.getText().toLowerCase().equals(code.toLowerCase())) {
            result.setCheckResult("errorCode");
            return result;
        }
        HttpSession session = request.getSession();
        SysUser sysUser = userService.getSysUser(username, password);
        if (sysUser == null) {
            result.setCheckResult("false");
            return result;
        }
        session.setAttribute("user", sysUser);

        result.setCheckResult("true");
        return result;
    }

    //生成图片验证码
    @RequestMapping("/verifyCode")
    @ResponseBody
    public void verifyCode(HttpServletResponse response) throws IOException {

        BufferedImage image = verifyCode.createImage();
        ImageIO.write(image, "jpg", response.getOutputStream());
    }

    //加入一个事务
    @PostMapping("/register")
    @ResponseBody
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    public Result register(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("email") String email) {
        logger.info("----用户账户：" + username + ",密码：" + password + ", 邮箱是：" + email);
        Result result = new Result();
        if (username == null || "".equals(username.trim())) {
            result.setCheckResult(null);
            return result;
        }
        if (password == null || "".equals(password.trim())) {
            result.setCheckResult("none");
            return result;
        }
        if (email == null || "".equals(email.trim())) {
            result.setCheckResult("none");
            return result;
        }
        SysUser sysUser = userService.getSysUser(username, password);
        if (sysUser != null) {
            result.setCheckResult("have");
            return result;
        }
        try {
            userService.addUser(username, password, email);
        } catch (Exception e) {
            logger.info("运行时异常：" + e);
            result.setCheckResult("false");
            return result;
        }

        result.setCheckResult("true");
        return result;
    }

    @RequestMapping("/logOff")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            session.removeAttribute("user");
        }

        return "redirect:/index";
    }

    //上传图片
    @PostMapping("/uploadImg")
    @ResponseBody
    public Map<String, Object> uploadImg(@RequestParam("uploadFile") MultipartFile uploadFile, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        String fileName = null;
        if (!uploadFile.isEmpty()) {
            try {
                System.out.println(uploadFile.getOriginalFilename());
                String str = uploadFile.getOriginalFilename();
                String end = str.substring(str.lastIndexOf("."));
                fileName = System.currentTimeMillis() + end;
                String path = request.getSession().getServletContext().getRealPath("/resources/userImg") + "\\" + fileName;
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(path)));
                try {
                    //一段代码可以复制到path
//                    uploadFile.transferTo(new File(path));
                    out.write(uploadFile.getBytes());
                    out.flush();
                    out.close();
                    result = CommonUtil.errorResult(200, fileName);

                } catch (IOException e) {
                    e.printStackTrace();
                    result = CommonUtil.errorResult(500, "上传失败," + e.getMessage());
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                result = CommonUtil.errorResult(500, "上传失败" + e.getMessage());
            }
        } else {
            result = CommonUtil.errorResult(500, "上传失败，文件为空");

        }

        return result;
    }

    @GetMapping("/userInfo")
    public String showImg(HttpServletRequest request,Model model) {
        HttpSession session = request.getSession();
        SysUser sysUser = (SysUser) session.getAttribute("user");
        System.out.println(sysUser);
        SysUser result = userService.getOpenId(sysUser.getOpenid());
        model.addAttribute("headimgurl",result.getHeadimgurl());
        model.addAttribute("nickname",result.getNickname());
        model.addAttribute("city",result.getCity());
        model.addAttribute("province",result.getProvince());
        model.addAttribute("country",result.getCountry());
        model.addAttribute("sex",result.getSex());
        return "back/userInfo";
    }

    //随机换一个图片
    @PostMapping("/changeImg")
    @ResponseBody
    public Map<String, Object> changeImg(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        String fileName = null;
        String path = request.getSession().getServletContext().getRealPath("/resources/userImg");
        File file = new File(path);
        File[] listFiles = file.listFiles();
        if (listFiles.length > 1) {
            //文件夹下有文件
            int size = new Random().nextInt(listFiles.length);
            if (listFiles[size].isFile()){
                 fileName = listFiles[size].getName();
                result = CommonUtil.errorResult(fileName);
            }
        } else {
            //文件夹下没有文件
            result = CommonUtil.errorResult(400,"请先上传一个图片");
        }

        return result;
    }



}
