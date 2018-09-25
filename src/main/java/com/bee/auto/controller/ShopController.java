package com.bee.auto.controller;

import com.alibaba.fastjson.JSONObject;
import com.bee.auto.bean.Product;
import com.bee.auto.bean.SysUser;
import com.bee.auto.service.ShopService;
import com.bee.auto.service.cache.RedisUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/shop")
public class ShopController {
    public final static Logger logger = LoggerFactory.getLogger(ShopController.class);

    @Autowired
    private ShopService shopService;

    @Resource
    private RedisUtils redisCache;//这里我们用HASH  (MAP)

    @RequestMapping("list")
    public String list(Model model){
        List<Product> proList = shopService.getProList();
        //设置模型数据
        model.addAttribute("proList" , proList);

        return "shop/list";
    }


    //添加商品到购物车， 现在拿到的是商品的索引
    @RequestMapping("addToCart")
    @ResponseBody
    public String addToCart(HttpServletRequest request , @Param("index") String index, @Param("time") Long time){
        logger.info("要添加商品到购物车了~");
        HttpSession session = request.getSession();
        SysUser user = (SysUser) session.getAttribute("user");
        List<Product> proList = shopService.getProList();
        //1. 根据索引，获取到要添加到购物车的商品
        Product product  = proList.get(Integer.parseInt(index));
        //判断购物车里面是否有该商品 ，如果有  数量 + 1  ，如果没有，数量-1
        //从缓存中判断是否存在key
        if(redisCache.hHasKey(user.getUsername(),product.getPrName())){
            String pro = (String) redisCache.hget(user.getUsername(), product.getPrName());
            JSONObject parse = (JSONObject) JSONObject.parse(pro);
            product.setSaleNum(parse.getInteger("saleNum")+1);
            product.setStockNum(parse.getInteger("stockNum") - 1);
        }else{
           product.setSaleNum(1);
           product.setStockNum(100 - 1);
        }
        redisCache.hset(user.getUsername(),product.getPrName(),JSONObject.toJSONString(product),0);
        //计算总价
        Double totalFee = 0d;
        if (redisCache.hasKey(user.getUsername())){
            Map<Object, Object> hmget = redisCache.hmget(user.getUsername());
            for(Object s:hmget.keySet()){
                String pro = (String)  hmget.get(s);
                JSONObject parse = (JSONObject) JSONObject.parse(pro);
                double Fee = parse.getDouble("price") * parse.getInteger("saleNum");
                totalFee +=Fee;
            }
        }

        redisCache.set(user.getUsername()+"_totalFee" , totalFee.toString(), 0);
        //3. 存储到session中。
//        request.getSession().setAttribute("cartMap" , map);
//        request.getSession().setAttribute("totalFee" , totalFee);
        //当前的跳转是基于template/list.html ，所以需要重定向跳转到中转页面
        String str ="success";
        return str;
    }

    @RequestMapping("ChangeCart")
    @ResponseBody
    public Map<String,Object> ChangeCart(HttpServletRequest request){
        HttpSession session = request.getSession();
        SysUser user = (SysUser) session.getAttribute("user");
        //从缓存中取出购物车信息
        Map<Object, Object> hmget =null;
        String totalFee =null;
        if (redisCache.hasKey(user.getUsername())){
             hmget = redisCache.hmget(user.getUsername());
        }
        if (redisCache.hasKey(user.getUsername()+"_totalFee")){
            totalFee = (String) redisCache.get(user.getUsername()+"_totalFee");
        }
        List<String> cartMap = new ArrayList<>();
        if (hmget!=null){
            for(Object s:hmget.keySet()){
                String pro = (String)  hmget.get(s);
                cartMap.add(pro);
            }
        }
        Map<String,Object> data = new HashMap<>();
        System.out.println(cartMap);
        data.put("cartMap",cartMap);
        data.put("totalFee",totalFee);
        return data;
    }


    @RequestMapping("toCart")
    public String toCart(){

        return "shop/cart";
    }
    @RequestMapping("delCartAll")
    public String delCart(HttpServletRequest request){
        //从缓存中取出购物车信息
        HttpSession session = request.getSession();
        SysUser user = (SysUser) session.getAttribute("user");
        redisCache.del(user.getUsername());
        redisCache.del(user.getUsername()+"_totalFee");
        return "shop/cart";
    }

}
