package com.bee.auto.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebUtil {

    public static String sendGet(String url) throws IOException {
        String result = "";
        URL realUrl = new URL(url);
        // 打开和URL之间的连接
        HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
        // // 设置通用的请求属性
        // conn.setRequestProperty("Content-Type", "application/json");
        // conn.setRequestProperty("Authorization", accessToken );
        conn.setReadTimeout(5000);
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        // conn.setDoOutput(true);
        // conn.setDoInput(true);
        // 获取URLConnection对象对应的输出流
        // out = new PrintWriter(conn.getOutputStream());
        // // flush输出流的缓冲
        // out.flush();
        // // 定义BufferedReader输入流来读取URL的响应
        BufferedReader  in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) {
            result += line;
        }
        return result;

    }
}