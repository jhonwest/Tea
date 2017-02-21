package com.example.tea.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 自定义Http使用工具类
 */
public class HttpUtils {
    /**
     * 返回输入流
     */
    public static InputStream getInputSteam(String path) {
        try {
            URL url = new URL(path);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(3000);
            int num = httpURLConnection.getResponseCode();
            if (num == httpURLConnection.HTTP_OK) {
                final InputStream inputStream = httpURLConnection.getInputStream();
                return inputStream;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 返回字符串
     *
     * @param
     * @return
     */
    public static String getString(InputStream inputStream) {

        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        int len=0;
        byte[] buffer=new byte[1024];
        try {
            while((len=inputStream.read(buffer))!=-1){
                byteArrayOutputStream.write(buffer,0,len);
            }
            String json=byteArrayOutputStream.toString();
            byte[] data=json.getBytes();
            return json;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 解析json字符串
     *
     * @param json
     * @return
     */
    public static List<Map<String, Object>> getDrugStore(String json) {
        List<Map<String, Object>> maps = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            int len = jsonArray.length();
            for (int i = 0; i < len; i++) {
                jsonObject = jsonArray.getJSONObject(i);
                String wapthumb = jsonObject.getString("wap_thumb");
                String title = jsonObject.getString("title");
                String source = jsonObject.getString("source");
                String nickname = jsonObject.getString("nickname");
                String createtime = jsonObject.getString("create_time");
                String id=jsonObject.getString("id");
                Map<String, Object> map = new HashMap<>();
                map.put("wapthumb", wapthumb);
                map.put("title", title);
                map.put("source", source);
                map.put("nickname", nickname);
                map.put("createtime", createtime);
                map.put("id",id);
                maps.add(map);
            }
            return maps;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static List<String> getid(String json) {
        List<String> lists = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            int len = jsonArray.length();
            for (int i = 0; i < len; i++) {
                jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                lists.add(id);

            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
