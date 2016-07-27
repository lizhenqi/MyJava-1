package com.kaishengit.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/27.
 */
@Entity
@Table(name = "t_task")
public class Task implements Serializable{

    @Id
    @GenericGenerator(name="test",strategy = "uuid")
    @GeneratedValue(generator = "test")

    private String id;//此处为String（利用UUID）
    private String title;

//    @Version
//    private Integer version;
//
//
//
//    public Integer getVersion() {
//        return version;
//    }
//
//    public void setVersion(Integer version) {
//        this.version = version;
//    }

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
