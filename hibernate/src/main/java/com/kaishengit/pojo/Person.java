package com.kaishengit.pojo;

/**
 * Created by Administrator on 2016/7/26.
 */
public class Person {
    private Integer id;
    private String name;
    private Card card;

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
