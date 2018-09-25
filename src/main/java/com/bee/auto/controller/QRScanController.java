package com.bee.auto.controller;


import com.bee.auto.bean.SysUser;
import com.bee.auto.service.UserService;
import com.bee.auto.util.HttpClientUtils;
import com.bee.auto.util.qrcode.PoolCache;
import com.bee.auto.util.qrcode.ScanPool;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * Created by lu on 2018/7/5.
 */
@Controller
public class QRScanController {

    private static String APPID = "wxdaad37dc65e127b3";
    private static String APPSECERT = "39ffbc8840e5c97a7a25c9b87983a446";

    @Resource
    UserService userService;


    Long before = 1l;
    Long after = 1l;

    /**
     * 加载二维码页面
     *
     * @return
     */
    @RequestMapping("/index2")
    public String index(Model model, HttpServletRequest request) {
        String uuid = UUID.randomUUID().toString();
        PoolCache.cacheMap.put(uuid, new ScanPool());
        model.addAttribute("uuid", uuid);

        before = System.currentTimeMillis();
        return "index2";
    }

    @RequestMapping("/wx/login")
    public void logon(HttpServletResponse resp) {
        String callbackUrl = "http://180.76.246.249:8088/wx/callback";
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + APPID +
                "&redirect_uri=" + URLEncoder.encode(callbackUrl) +
                "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";

        try {
            resp.sendRedirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/wx/callback")
    public String wxcallback(HttpServletRequest request,Model model) {
        String code = request.getParameter("code");
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + APPID +
                "&secret=" + APPSECERT +
                "&code=" + code +
                "&grant_type=authorization_code";
        JSONObject jsonObject = HttpClientUtils.httpGet(url);
        String access_token = jsonObject.getString("access_token");
        String openid = jsonObject.getString("openid");
        String url2 = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token + "&openid=" + openid + "&lang=zh_CN";
        JSONObject result = HttpClientUtils.httpGet(url2);

        SysUser info = userService.getOpenId(result.getString("openid"));
        HttpSession session = request.getSession();
        session.getAttribute("user");
        if (info != null){
            if ( session.getAttribute("user") == null){
                return "bundling";
            }
            return "back/list";
        }else {
            model.addAttribute("openid",result.getString("openid"));
            model.addAttribute("city",result.getString("city"));
            model.addAttribute("nickname",result.getString("nickname"));
            model.addAttribute("headimgurl",result.getString("headimgurl"));
            model.addAttribute("country",result.getString("country"));
            model.addAttribute("province",result.getString("province"));
            model.addAttribute("sex",result.getInt("sex"));
            return "bundling";
        }
    }

    @PostMapping("wxLogin")
    public String wxLogin(String openid,String city,String nickname,String country,String headimgurl,String province,Integer sex,HttpServletRequest request) {
        SysUser sysUser = new SysUser();
        sysUser.setOpenid(openid);
        sysUser.setCity(city);
        sysUser.setNickname(nickname);
        sysUser.setCountry(country);
        sysUser.setHeadimgurl(headimgurl);
        sysUser.setProvince(province);
        sysUser.setSex(sex);
        userService.addWXInfo(sysUser);
        HttpSession session = request.getSession();
        session.setAttribute("user",sysUser);
        return "back/list";
    }

    @PostMapping("bundling")
    public String bundling(String openid,String city,String nickname,String country,String headimgurl,String province,Integer sex,HttpServletRequest request) {
        SysUser sysUser = new SysUser();
        sysUser.setOpenid(openid);
        sysUser.setCity(city);
        sysUser.setNickname(nickname);
        sysUser.setCountry(country);
        sysUser.setHeadimgurl(headimgurl);
        sysUser.setProvince(province);
        sysUser.setSex(sex);
        int num = userService.motifyWXInfo(sysUser);
        if (num != 1){
            return "error1";
        }
        HttpSession session = request.getSession();
        session.setAttribute("user",sysUser);
        return "back/list";
    }



    @RequestMapping("/scanLogin")
    public String scanLogin(String uuid, HttpServletRequest request) {
        ScanPool pool = null;
        String str = null;
        if (!(PoolCache.cacheMap == null || PoolCache.cacheMap.isEmpty())) {
            pool = PoolCache.cacheMap.get(uuid);
        }
        if (pool == null) {
            return "timeout";
        } else {
            pool.setSession("123123");
            pool.scanSuccess();
            return "success";
        }
    }

    @RequestMapping("/submitMobile")
    public void submitMobile(HttpServletRequest request) {

        Long auuid = System.currentTimeMillis();
        System.out.println("auuid=============" + auuid);
        after = auuid;
        System.out.println("after=============" + after);

    }


    @RequestMapping("/pool3")
    public String success(HttpServletRequest request) {
        String uuid = UUID.randomUUID().toString();
        HttpSession session = request.getSession();
        session.setAttribute("user", uuid);
        return "index2";
    }


    /**
     * 查询扫码状态
     *
     * @return
     */
    @RequestMapping("/pool")
    @ResponseBody
    public JSONObject pool(String uuid) {
        System.out.println("检测[   " + uuid + "   ]是否登录");
        JSONObject obj = new JSONObject();
        ScanPool pool = null;
        if (!(PoolCache.cacheMap == null || PoolCache.cacheMap.isEmpty())) {
            pool = PoolCache.cacheMap.get(uuid);
        }

        try {
            if (pool == null) {
                // 扫码超时,进线程休眠
                Thread.sleep(10 * 1000L);
                obj.put("successFlag", "0");
                obj.put("msg", "该二维码已经失效,请重新获取");
            } else {
                // 使用计时器，固定时间后不再等待扫描结果--防止页面访问超时
                new Thread(new ScanCounter(uuid, pool)).start();

                //这里得到的ScanPool(时间靠前)和用户使用手机扫码后得到的不是一个,用户扫码后又重新更新了ScanPool对象,并重新放入了redis中,,所以这里要等待上面的计时器走完,才能获得最新的ScanPool
                boolean scanFlag = pool.getScanStatus();
                if (scanFlag) {
                    // 根据uuid从redis中获取pool对象,得到对应的sessionId,返给页面,通过js存cookie中
                    obj.put("successFlag", "1");
                    obj.put("cname", "SESSIONKEY");
                    obj.put("cvalue", pool.getSession());
                } else {
                    obj.put("successFlag", "2");
                    obj.put("msg", "等待扫描");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return obj;
    }


    /**
     * 查询扫码状态
     *
     * @return
     */
    @RequestMapping("/pool2")
    @ResponseBody
    public JSONObject pool2() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("正在轮询是否授权登录=======================");
        JSONObject obj = new JSONObject();
        if (after - before > 0) {
            // 根据uuid从redis中获取pool对象,得到对应的sessionId,返给页面,通过js存cookie中
            obj.put("successFlag", "1");
            obj.put("cname", "SESSIONKEY");
        } else {
            obj.put("successFlag", "2");
            obj.put("msg", "等待扫描");
        }

        return obj;
    }


    @RequestMapping("/scan")
    @ResponseBody
    public String scan() {
        return "haha";
    }


}

class ScanCounter implements Runnable {

    public Long timeout = 30 * 1000L;

    // 传入的对象
    private String uuid;
    private ScanPool scanPool;

    public ScanCounter(String p, ScanPool scanPool) {
        uuid = p;
        this.scanPool = scanPool;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        notifyPool(uuid, scanPool);
    }

    public synchronized void notifyPool(String uuid, ScanPool scanPool) {
        if (scanPool != null) scanPool.notifyPool();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public ScanPool getScanPool() {
        return scanPool;
    }

    public void setScanPool(ScanPool scanPool) {
        this.scanPool = scanPool;
    }
}