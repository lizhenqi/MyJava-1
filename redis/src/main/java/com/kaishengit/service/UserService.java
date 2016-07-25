package com.kaishengit.service;


import com.google.gson.Gson;
import com.kaishengit.pojo.User;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class UserService {

    @Autowired
    private JedisPool jedisPool;

    public Jedis getJedis(){
        return jedisPool.getResource();
    }

    public User findUserById(Integer id){

//    利用protostuff序列化的方法获取User
        Jedis jedis=getJedis();
        byte[] bytes=jedis.get(KeyUtil.userKey(id).getBytes());
        User user=new User();
        Schema<User> userSchema= RuntimeSchema.createFrom(User.class);
        ProtobufIOUtil.mergeFrom(bytes,user,userSchema);
        jedis.close();
        return user;


//        这个写法也行（json），上面的是利用protostuff序列化的方法
//        Jedis jedis=getJedis();
//        String  json=jedis.get(KeyUtil.userKey(id));
//        User user=new Gson().fromJson(json,User.class);
//        if(user==null){
//        }
//        jedis.close();
//        return user;
    }

    //利用protostuff序列化的方法
    public void testSaveUser(User user){
        Schema schema= RuntimeSchema.getSchema(User.class);
        byte[] bytes= ProtostuffIOUtil.toByteArray(user,schema, LinkedBuffer.allocate());

        Jedis jedis=getJedis();
        jedis.set(KeyUtil.userKey(user.getId()).getBytes(),bytes);
        jedis.close();

    }



//    方法一
//    private RedisTemplate<String, User> redisTemplate;
//
//    @Autowired
//    public UserService(RedisTemplate<String, User> redisTemplate) {
//        this.redisTemplate=redisTemplate;
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<User>(User.class));
//    }
//
//    public User findUserById(Integer id){
//        User user=redisTemplate.opsForValue().get(KeyUtil.userKey(id));
//        if(user==null){
//
//        }
//        return user;
//    }

}
