package com.kaishengit.pojo;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2016/7/26.
 */
//Card和Person一对一关系

@Entity
@Table(name = "t_card")
public class Card {

    @Id
    @GenericGenerator(name = "test",strategy = "foreign",
            parameters = @Parameter(name="property",value = "person")) //这是hibernate提供的
    @GeneratedValue(generator = "test") //这是JPA提供的（而它没有foreign策略故用hibernate提供的）
    private Integer id;
    private String cardname;

    @OneToOne(mappedBy = "card")//放弃关系维护
    @PrimaryKeyJoinColumn
    private Person person;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getCardname() {
        return cardname;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname;
    }
}
