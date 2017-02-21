package com.example.tea.domain;

/**
 * Created by lenovo on 2016/10/8.
 */
import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 声明网络数据中Json字符串对应的数据实体类
 *
 * @author dell
 *
 */
public class Data implements Serializable {

    /**
     * 声明id
     */
    private String id;

    /**
     * 声明标题
     */
    private String title;

    /**
     * 出处
     */
    private String source;
    /**
     * 描述
     */
    private String description;
    /**
     * 图片
     */
    private String wapThumb;
    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 昵称
     */
    private String nickName;

    public Data() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Data(String id, String title, String source, String description,
                String wapThumb, String createTime, String nickName) {
        super();
        this.id = id;
        this.title = title;
        this.source = source;
        this.description = description;
        this.wapThumb = wapThumb;
        this.createTime = createTime;
        this.nickName = nickName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWapThumb() {
        return wapThumb;
    }

    public void setWapThumb(String wapThumb) {
        this.wapThumb = wapThumb;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 根据json对象返回Data对象
     * @param json json对象
     * @return Data对象
     */
    public static Data fromJson(JSONObject json){
        try {
            String id=json.getString("id");
            String title=json.getString("title");
            String source=json.getString("source");
            String description=json.getString("description");
            String wap_thumb=json.getString("wap_thumb");
            String createTime=json.getString("create_time");
            String nickName=json.getString("nickname");
            return new Data(id,title,source,description,wap_thumb,createTime,nickName);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return null;

    }

}

