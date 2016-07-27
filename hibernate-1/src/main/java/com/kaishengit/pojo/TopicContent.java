package com.kaishengit.pojo;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/7/27.
 */

@Entity //标明为pojo类
@Table(name = "t_topic_content")  //标明对应数据库的表（误报错）
public class TopicContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String content;

//    一对一（唯一外键关联就是如下,topic不写 ）
    @ManyToOne
    @JoinColumn(name = "topicid")
    private Topic topic;//这个不设置最好（单向连接）（只是根据topic找topicCoontext）

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
