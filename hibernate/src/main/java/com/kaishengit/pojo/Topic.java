package com.kaishengit.pojo;

/**
 * Created by Administrator on 2016/7/27.
 */
public class Topic {

    private Integer id;
    private String title;
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
