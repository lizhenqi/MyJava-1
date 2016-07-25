import com.google.gson.Gson;
import com.kaishengit.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2016/7/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpringRedisTemplateTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

//    相当于spring类型
    @Test
    public void testSet(){
        stringRedisTemplate.opsForValue().set("user:1","lizhenqi");
    }

//    相当于set类型
    @Test
    public void testSave(){
        stringRedisTemplate.opsForSet().add("user:name","1","2","3");
    }

    @Test
    public void testGetFromSet(){
        Set<String> integers=stringRedisTemplate.opsForSet().members("user:name");
        for(String stringList:integers){
            System.out.println(stringList);
        }
    }

//    相当于hash里面的hset
    @Test
    public void testHset(){
//        hset
        stringRedisTemplate.opsForHash().put("test","name","lizhenqi");
//hget
        System.out.println(stringRedisTemplate.opsForHash().get("test","name"));
    }



    //    相当于hash里面的hmset
    @Test
    public void testHMset(){
//        hmset
        Map<String,String> map=new HashMap<>();
        map.put("name","李振起");
        map.put("address","焦作");
        stringRedisTemplate.opsForHash().putAll("user:110",map);
//hgetall
        System.out.println(stringRedisTemplate.opsForHash().entries("user:110"));
    }


    //    用User对象的方法(spring类型)
    @Test
    public void testSaveUser(){
        User user=new User(110,"tom",99.9F);
        stringRedisTemplate.opsForValue().set("user:110",new Gson().toJson(user));

        System.out.println(stringRedisTemplate.opsForValue().get("user:110"));
    }


}
