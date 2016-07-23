import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Created by Administrator on 2016/7/23.
 */
public class JedisTestStringCase {


    private Jedis jedis = null;


//    开始之前运行ip，结束后运行close。这样后面的就不用重复写了
    @Before
    public void before() {
        jedis = new Jedis("192.168.107.128");//里面写ip地址
    }
    @After
    public void after() {
        if(jedis!=null){
            jedis.close();
        }
    }

//自增
    @Test
    public void testIncr() {
        jedis.incr("num:1");
        System.out.println(jedis.get("num:1"));
    }

    //指定递增数（Integer型）
    @Test
    public void testIncrBy() {
        String key="num:1";
        Integer num=100;
        jedis.incrBy(key,num);
        System.out.println(jedis.get(key));
    }
    //指定递增数（Float型）
    @Test
    public void testIncrByFloat() {
        String key="num:1";
        Float num=1.5F;
        jedis.incrByFloat(key,num);
        System.out.println(jedis.get(key));
    }


    //递减1(要都是Integer型)
    @Test
    public void testDecr() {
        String key="num:1";
        jedis.decr(key);
        System.out.println(jedis.get(key));
    }

//指定递减数
    @Test
    public void testDecrBy() {
        String key="num:1";
        Integer num=100;
        jedis.decrBy(key,num);
        System.out.println(jedis.get(key));
    }







//测Exists
    @Test
    public void testExists(){
        if(!jedis.exists("name:1")){
            jedis.set("name:1","尼古拉斯");
        }
        System.out.println(jedis.get("name:1"));
    }
    //测append
    @Test
    public void testAppend(){
        jedis.append("name:1","凯奇");
        System.out.println(jedis.get("name:1"));
    }
    //测strlen
    @Test
    public void testStrlen(){
        Long length=jedis.strlen("name:1");
        System.out.println(length);
    }

//    mset和mget
    @Test
    public void testMset(){
        jedis.mset("k1","v1","k2","v2","k3","v3");

        List<String> list=jedis.mget("k1","k2","k3");
        for(String msg:list){
            System.out.println(msg);
        }

    }







//    这是没写befor和after时候的
    @Test
    public void testSet() {
        jedis = new Jedis("192.168.107.128");//里面写ip地址
        jedis.set("email", "email");//可在Cmder上面查看
        jedis.close();
    }

    @Test
    public void testGet() {
        jedis = new Jedis("192.168.107.128");//里面写ip地址
        jedis.set("email", "email");
        String msg = jedis.get("email");
        Assert.assertEquals("email", msg);
        jedis.close();
    }

}
