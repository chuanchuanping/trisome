package com.bee.auto.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@Controller
public class DemoController {

   @RequestMapping("/talk")
   public String talk(){
      return "front/talk";
   }

   @RequestMapping("/index")
   public String index(){
      return "back/index";
   }

}
