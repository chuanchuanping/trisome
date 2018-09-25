package com.bee.auto.util;

import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.util.*;

public class StrUtil {
    private static Logger logger = LoggerFactory.getLogger(StrUtil.class);

    public static String toStr(Object obj) {
        if (obj == null) {
            return "";
        } else {
            return obj.toString();
        }
    }

    public static boolean empty(String s) {
        boolean rtn = false;
        if (s == null || s.length() == 0 || (JSONNull.getInstance().equals(s) && !"null".equals(s))) {
            rtn = true;
        } else if (s.trim().length() == 0) {
            rtn = true;
        }
        return rtn;
    }

    public static boolean notEmpty(Map<String, Object> map, String key) {
        return map.containsKey(key) && !StrUtil.empty(map.get(key)) && !JSONNull.getInstance().equals(map.get(key));
    }

    public static boolean empty(Object s) {
        if (s == null || s.toString().trim().equals("") || (JSONNull.getInstance().equals(s) && !"null".equals(s))) {
            return true;
        }
        return false;
    }

    public static int parseInt(String s) {
        return parseInt(s, 0);
    }

    public static int parseIntObject(Object o) {
        if (null == o) {
            return 0;
        }
        return parseInt(o.toString());
    }

    public static int ceilDivide(long a, long b) {
        long c = a % b;
        return (int) (a / b + (c > 0 ? 1 : 0));
    }

    public static String join(Collection s, String delimiter) {
        StringBuffer buffer = new StringBuffer();
        Iterator iter = s.iterator();
        while (iter.hasNext()) {
            buffer.append(iter.next());
            if (iter.hasNext()) {
                buffer.append(delimiter);
            }
        }
        return buffer.toString();
    }

    public static int parseInt(String s, int iDefault) {
        if (s == null || s.equals("")) {
            return iDefault;
        }
        if (s.equals("true")) {
            return 1;
        }
        if (s.equals("false")) {
            return 0;
        }
        try {
            s = s.replaceAll(",", "");
            int l = s.indexOf(".");
            if (l > 0) {
                s = s.substring(0, l);
            }
            return Integer.parseInt(s);
        } catch (Exception e) {
            return iDefault;
        }
    }

    public static long parseLong(String s) {
        return parseLong(s, 0L);
    }

    public static long parseLongObject(Object o) {
        if (null == o) {
            return 0L;
        }
        return parseLong(o.toString());
    }

    public static long parseLong(String s, long iDefault) {
        if (s == null || s.equals("")) {
            return iDefault;
        }
        try {
            s = s.replaceAll(",", "");
            int l = s.indexOf(".");
            if (l > 0) {
                s = s.substring(0, l);
            }
            return Long.parseLong(s);
        } catch (Exception e) {
            return iDefault;
        }
    }

    public static int len(String src) {
        int len = 0;
        try {
            byte[] bb = src.getBytes("GBK");
            len = bb.length;
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            logger.error(e.getMessage(), e);
        }
        return len;
    }

    private static String hexString = "0123456789ABCDEF ";

    public static String encode(String str) {
        byte[] bytes = str.getBytes();
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
            sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
        }
        return sb.toString();
    }

    public static String decode(String bytes, String code) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length() / 2);
        for (int i = 0; i < bytes.length(); i += 2) {
            baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString.indexOf(bytes.charAt(i + 1))));
        }
        String s = "";
        try {
            s = new String(baos.toByteArray(), code);
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
        }
        return s;
    }

    public static String date2String(String pattern, Date date) {
        String retStr = "";
        java.text.SimpleDateFormat ff = new java.text.SimpleDateFormat();
        ff.applyPattern(pattern);
        retStr = ff.format(date);
        return retStr;
    }

    public static Date string2Date(String pattern, String str) {
        Date date = new Date();
        if (empty(str)) {
            return date;
        }
        java.text.SimpleDateFormat ff = new java.text.SimpleDateFormat();
        ff.applyPattern(pattern);
        try {
            date = ff.parse(str);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            logger.error(e.getMessage(), e);
        }
        return date;
    }

    public static boolean isWeekend(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dd = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (dd == 6 || dd == 0) {
            return true;
        }
        return false;
    }

    public static Float parseFloat(String s) {
        return parseFloat(s, 0.0f);
    }

    public static Float parseFloat(String s, Float defaultValue) {
        if (s == null || s == "") {
            return defaultValue;
        }
        try {
            return Float.parseFloat(s);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static long parseIpV4ToLong(String ip) {
        if (StrUtil.empty(ip)) {
            return 0;
        }
        String[] ips = ip.split("\\.");
        if (ips.length != 4) {
            return 0;
        }

        return (parseLong(ips[0]) << 24) + (parseLong(ips[1]) << 16) + (parseLong(ips[2]) << 8) + parseLong(ips[3]);
    }

    public static StringBuffer appendParam(StringBuffer returns, String paramId, String paramValue) {
        if (0 == returns.length()) {
            if (!StrUtil.empty(paramValue)) {
                returns.append(paramId + "=" + paramValue);
            }
        } else {
            if (!StrUtil.empty(paramValue)) {
                returns.append("&" + paramId + "=" + paramValue);
            }
        }
        return returns;
    }

    public static StringBuffer appendParamKqRefund(StringBuffer returns, String paramId, String paramValue) {
        if (0 == returns.length()) {
            if (!StrUtil.empty(paramValue)) {
                returns.append(paramId + "=" + paramValue);
            }
        } else {
            if (!StrUtil.empty(paramValue)) {
                returns.append(paramId + "=" + paramValue);
            }
        }
        return returns;
    }

    public static String genHtml(Map<String, Object> para, String url, String method) {
        List<String> keys = new ArrayList<String>(para.keySet());

        StringBuffer sbHtml = new StringBuffer();

        sbHtml.append("<form id=\"Form\"  action=\"" + url + "\" method=\"" + method + "\">");

        for (int i = 0; i < keys.size(); i++) {
            String name = keys.get(i);
            String value = StrUtil.toStr(para.get(name));

            sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>");
        }

        // submit按钮控件请不要含有name属性
        sbHtml.append("<input type=\"submit\" value=\"submit\" style=\"display:none;\"></form>");
        sbHtml.append("<script>document.forms['Form'].submit();</script>");
        return sbHtml.toString();
    }

    /**
     * get the nodes text array
     *
     * @param xml
     * @param nodeName
     * @return
     */
    public static String[] extract(String xml, String... nodeName) {
        String result[] = new String[nodeName.length];
        int i = 0;
        for (String node : nodeName) {
            String prefix = "<" + node + ">";
            String suffix = "</" + node + ">";
            int start = xml.indexOf(prefix) + prefix.length();
            int end = xml.indexOf(suffix, start);
            result[i++] = xml.substring(start, end);
        }
        return result;
    }

    /**
     * set node(named nodeName) text into the xml
     *
     * @param xml
     * @param nodeName
     * @param value
     * @return
     */
    public static String setNodeText(String xml, String nodeName, String value) {
        String prefix = "<" + nodeName + ">";
        String suffix = "</" + nodeName + ">";
        return xml.replaceAll(prefix + ".*" + suffix, prefix + value + suffix);
    }

    /**
     * append a new node <nodeName>value</nodeName> into parent node
     *
     * @param xml
     * @param parent
     * @param nodeName
     * @param value
     * @return
     */
    public static String appendNode(String xml, String parent, String nodeName, String value) {
        String node = "<" + nodeName + ">" + value + "</" + nodeName + ">";
        StringBuilder sb = new StringBuilder(xml);
        int index = sb.indexOf("</" + parent + ">");
        sb.insert(index, node);
        return sb.toString();
    }

    /**
     * 在字符串左侧按长度填充字符
     *
     * @param s
     * 原字符串
     * @param len
     * 填充后的总字符串长度
     * @param c
     * 填充字符
     * @return 填充完的字符串
     */
    public static String padleft(String s, int len, char c) {
        s = s.trim();
        if (s.length() > len) {
            throw new IllegalArgumentException("invalid len " + s.length() + "/" + len);
        }
        StringBuilder d = new StringBuilder(len);
        int fill = len - s.length();
        while (fill-- > 0) {
            d.append(c);
        }
        d.append(s);
        return d.toString();
    }

    public static Map<String, Object> param2Map(String string) throws Exception {
        if (null == string || 0 == string.length()) {
            throw new Exception("参数表为空");
        }
        Map<String, Object> map = new HashedMap();
        StringTokenizer tokenizer = new StringTokenizer(string, "&");
        while (tokenizer.hasMoreTokens()) {
            String tokenString = tokenizer.nextToken();
            int index = tokenString.indexOf("=");
            String key = tokenString.substring(0, index);
            String value = tokenString.substring(index + 1);
            map.put(key, value);
        }

        return map;
    }

    public static Object getValue(Map<String, Object> map, String key) {
        if (StrUtil.empty(map)) {
            return null;
        }
        if (map.containsKey(key) && !StrUtil.empty(map.get(key))) {
            return map.get(key);
        }
        return null;
    }

    public static Map<String, Object> parserJsonStrToMap(String s) {
        Map<String, Object> map = new HashMap();
        JSONObject json = JSONObject.fromObject(s);
        Iterator keys = json.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            String value = json.get(key).toString();
            map.put(key, value);
        }
        return map;
    }

    public static Map<String, Object> parserJsonToMap(JSONObject json) {
        Map<String, Object> map = new HashMap();
        Iterator keys = json.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            String value = json.get(key).toString();
            map.put(key, value);
        }
        return map;
    }

    public static Map<String, String> json2Map(JSONObject jsonObject) {
        HashMap<String, String> map = new HashMap<String, String>();
        Iterator iterator = jsonObject.keys();
        while (iterator.hasNext()) {
            String key = String.valueOf(iterator.next());
            String value = StrUtil.toStr(jsonObject.get(key));
            map.put(key, value);
        }
        return map;
    }

    public static Map<String, Object> urlencode2Map(String content) {
        Map<String, Object> params = new HashMap<>();
        StringTokenizer st = new StringTokenizer(content, "&", true);
        while (st.hasMoreElements()) {
            String str = st.nextToken();
            if (!StrUtil.empty(str) && str.contains("=")) {
                String[] entry = str.split("=");
                if (entry.length > 1 && !StrUtil.empty(entry[0]) && !StrUtil.empty(entry[1])) {
                    try {
                        if (!StrUtil.empty(StrUtil.empty(entry[1]))) {
                            params.put(entry[0], URLDecoder.decode(entry[1], "UTF-8"));
                        }
                    } catch (UnsupportedEncodingException e) {
                        logger.error("exception", e);
                    }
                }
            }
        }
        return params;
    }

    public static String parseUUIDFormat(String uuidWithoutMinus) {
        StringBuffer sb = new StringBuffer();
        sb.append(uuidWithoutMinus.substring(0, 8)).append("-").append(uuidWithoutMinus.substring(8, 12)).append("-")
                .append(uuidWithoutMinus.substring(12, 16)).append("-").append(uuidWithoutMinus.substring(16, 20))
                .append("-").append(uuidWithoutMinus.substring(20, 32));
        return sb.toString();
    }

    static public boolean ipCheck(String text) {
        if (text != null && !text.isEmpty()) {
            String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                    + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                    + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                    + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
            if (text.matches(regex)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(parseUUIDFormat("cb6432ba1b544001a1cdae92b005ab80"));
    }

    public static void buDanLog(String keySpace, String table, String objectId, String billNo, String type) {
        HashMap details = new HashMap();
        details.put("keyspace", keySpace);
        details.put("table", table);
        details.put("object_id", objectId);
        details.put("bill_no", billNo);
        details.put("type", type);
        logger.info("budan_details=" + JSONObject.fromObject(details).toString());
    }
}
