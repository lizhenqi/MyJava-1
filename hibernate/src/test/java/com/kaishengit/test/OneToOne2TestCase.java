package com.kaishengit.test;

import com.kaishengit.pojo.Topic;
import com.kaishengit.pojo.TopicContent;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

/**
 * Created by Administrator on 2016/7/27.
 */
public class OneToOne2TestCase {

    @Test
    public void testSave(){
        Session session= HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();

        Topic topic=new Topic();
        topic.setTitle("主题测试");

        TopicContent topicContent=new TopicContent();
        topicContent.setContent("正文");

        topic.setTopicContent(topicContent);
        topicContent.setTopic(topic);

        session.save(topic);
        session.save(topicContent);

        transaction.commit();
    }


    @Test
    public void testFind(){
        Session session= HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();

        Topic topic= (Topic) session.get(Topic.class,4);

//        System.out.println(topic.getTopicContent().getContent());

        transaction.commit();
//        System.out.println(topic.getTopicContent().getContent());
        //（假如这句放在这里不行，没有session(当然可以通过fetch，lazy，Hibernate.initialize,但这些都是赶在session关闭之前把他们加载到内存)）

    }

}
