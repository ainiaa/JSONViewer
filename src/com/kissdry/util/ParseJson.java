package com.kissdry.util;

import com.alibaba.fastjson.*;
import java.util.Set;

/**
 *
 * @author Administrator
 */
public class ParseJson {

    /**
     * 将字符串转化为标准的json字符串
     *
     * @author Jeff Liu<jeff.liu.guo@gmail.com>
     * @param jsonStr
     * @param prettyFormat
     * @return
     */
    public static String parseJsonStr(String jsonStr, boolean prettyFormat) {
        Object obj = JSON.parseArray(jsonStr);
        return JSON.toJSONString(obj, true);
    }

    /**
     * 将字符串转化为JSONArray
     *
     * @author Jeff Liu<jeff.liu.guo@gmail.com>
     * @param jsonStr
     * @return
     */
    public static JSONArray parseJsonArray(String jsonStr) {
        return JSON.parseArray(jsonStr);
    }

    /**
     * 测试用的 
     * @author Jeff Liu<jeff.liu.guo@gmail.com>
     * @param jsonObj 
     */
    private void getTypes(Object jsonObj) {
        int size = 0;
        if (jsonObj instanceof JSONObject) {
            JSONObject obj = (JSONObject) jsonObj;
            Set<String> keySet = obj.keySet();
            keySet.stream().map((k) -> obj.get(k)).forEach((v) -> {
                String name = v.getClass().getName();
                System.out.println(v.getClass().getName() + ":" + v);
                if (name.equals("com.alibaba.fastjson.JSONObject") || name.equals("com.alibaba.fastjson.JSONArray")) {
                    getTypes(v);
                }
            });
        } else if (jsonObj instanceof JSONArray) {
            JSONArray obj = (JSONArray) jsonObj;
            size = ((JSONArray) jsonObj).size();
            for (int i = 0; i < size; i++) {
                Object v = obj.get(i);
                String name = v.getClass().getName();
                System.out.println(name);
                if (name.equals("com.alibaba.fastjson.JSONObject") || name.equals("com.alibaba.fastjson.JSONArray")) {
                    getTypes(v);
                }
            }
        } else {
            System.out.println("other:" + jsonObj.getClass().getName());
        }
    }
}
