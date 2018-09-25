package com.bee.auto.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 共通的工具类
 */
public class CommonUtil {

    public static Map<String, Object> errorResult(Integer code, String resultMsg, Object errDetail, String errMsg) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result_code", code);
        map.put("result_msg",resultMsg);

        if (StrUtil.empty(errDetail)) {
            errDetail = "";
        }
        map.put("err_detail", errDetail);

        map.put("errMsg", errMsg + ":" + errDetail);
        return map;
    }

    public static Map<String, Object> errorResult(String resultMsg) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result_code", 200);
        map.put("result_msg",resultMsg);
        map.put("err_detail", "");
        map.put("errMsg", "ok" + ":" + "");
        return map;
    }


    public static Map<String, Object> errorResult(Integer code,String resultMsg) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result_code", code);
        map.put("result_msg",resultMsg);
        map.put("err_detail", "");
        map.put("errMsg", "ok" + ":" + "");
        return map;
    }
}
