package com.kaishengit.pojo;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/7/27.
 */

@Entity //标明为pojo类
@Table(name = "t_topic")  //标明对应数据库的表（误报错）
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String title;
    @ManyToOne(fetch = FetchType.LAZY) //对于这种一对一的就是要延迟加载
    @JoinColumn(name = "contentid",unique = true)

    private TopicContent topicContent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TopicContent getTopicContent() {
        return topicContent;
    }

    public void setTopicContent(TopicContent topicContent) {
        this.topicContent = topicContent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
