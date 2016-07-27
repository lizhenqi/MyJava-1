package com.kaishengit.pojo;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/27.
 */
public class Task implements Serializable{
    private String id;//此处为String（利用UUID）
    private String title;

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
}
