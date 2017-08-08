package com.example.demo;

import com.example.demo.Domain.User;
import com.example.demo.Domain.UserRedisRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisTests {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private UserRedisRepository userRedisRepository;
    @Test
    public void testStringRedis() throws Exception{
        stringRedisTemplate.opsForValue().set("Cielo","Good Boy");
        Assert.assertEquals("Good Boy",stringRedisTemplate.opsForValue().get("Cielo"));
    }
    @Test
    public void testObjectRedis() throws Exception{
        User user=new User("Cielo",20);
        user.setId(1l);
        userRedisRepository.save(user);
        Assert.assertEquals(user.getName(),userRedisRepository.findOne(1l).getName());
    }
}
