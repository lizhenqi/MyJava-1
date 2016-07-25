import com.google.gson.Gson;
import com.kaishengit.pojo.User;
import io.protostuff.Tag;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2016/7/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JsonTestCase {

    @Autowired
    private RedisTemplate<String,User> redisTemplate;


//    假如不在applicationContext.xml中设置序列化可写如下
    @Before
    public void setUp(){
//        key的序列化方式
        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        value的序列化方式
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<User>(User.class));
    }

    @Test
    public void testSet(){
        User user=new User(110,"name",99.8F);
        redisTemplate.opsForValue().set("user:110",user);

        System.out.println(redisTemplate.opsForValue().get("user:110"));
    }



}
