import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2016/7/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpringRedisTestCase {

//  @Autowired是Spring自带的注入，功能和Inject一样
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testSet(){
        //注意和java中的区别
        ValueOperations valueOperations=redisTemplate.opsForValue();
        valueOperations.set("user:name","成龙");
        System.out.println("测试");
    }

    @Test
    public void testGet(){
        System.out.println(redisTemplate.opsForValue().get("user:name"));
    }

//    @Test
//    public void testIncr(){
//        redisTemplate.opsForValue().increment("user:num",1);
////        increment这个就包括整型和浮点
//        System.out.println(redisTemplate.opsForValue().get("user:num"));
//    }

}
