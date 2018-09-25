package com.bee.auto.util;

import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * 通过这个对象来搞定发送数据
 * @author yellowcong
 * @date 2016年3月26日
 *
 */
public class TulingClient {

    //访问的key,是每一个用户自己的
    private static String apikey = "381ed387b2ba4f9895bd2d381c3e2d06" ;

    //接口地址
    private  static String url = "http://openapi.tuling123.com/openapi/api/v2";

    /**
     * 创建日期:2018年1月14日<br/>
     * 创建时间:下午12:09:56<br/>
     * 创建用户:yellowcong<br/>
     * 机能概要:发送数据到图灵
     *
     * "reqType":0,
     "perception": {
     "inputText": {
     "text": "附近的酒店"
     },
     "inputImage": {
     "url": "imageUrl"
     },
     */
    public static JSONObject sendMessage(String args) {
        Map<String, Object> params = new HashMap<String, Object>();
        //0-文本(默认)、1-图片、2-音频
        params.put("reqType","0");
        Map<String, Object> perception = new HashMap<String, Object>();
        Map<String, String> text = new HashMap<String, String>();
        text.put("text", args);
        perception.put("inputText",text);
        Map<String, Object> userInfo = new HashMap<String, Object>();
        userInfo.put("apiKey", apikey);
        params.put("perception",perception);
        params.put("userInfo",userInfo);
        userInfo.put("userId", "chuan"+System.currentTimeMillis());
        JSONObject jsonObject = JSONObject.fromObject(params);
        //发送数据
        JSONObject json = HttpClientUtils.httpPost(url, jsonObject);
        return json;
    }

    /**
     * 创建日期:2018年1月14日<br/>
     * 创建时间:下午12:15:58<br/>
     * 创建用户:yellowcong<br/>
     * 机能概要:直接传递Map 集合,直接自己写一个集合，然后传递给图灵机器人
     *
     * @param params
     * @return 返回的是一个消息对象
     */
    public static JSONObject sendImage(String imageUrl) {
        Map<String, Object> params = new HashMap<String, Object>();
        //0-文本(默认)、1-图片、2-音频
        params.put("reqType","1");
        Map<String, Object> perception = new HashMap<String, Object>();
        Map<String, Object> urls  = new HashMap<String, Object>();
        urls.put("url", imageUrl);
        perception.put("inputImage",urls);
        Map<String, Object> userInfo = new HashMap<String, Object>();
        userInfo.put("apiKey", apikey);
        params.put("perception",perception);
        params.put("userInfo",userInfo);
        userInfo.put("userId", "chuan"+System.currentTimeMillis());
        JSONObject jsonObject = JSONObject.fromObject(params);
        //发送数据
        JSONObject json = HttpClientUtils.httpPost(url, jsonObject);
        return json;
    }
}