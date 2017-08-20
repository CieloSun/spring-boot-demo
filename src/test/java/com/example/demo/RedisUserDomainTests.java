package com.example.demo;

import com.example.demo.Domain.RedisUser;
import com.example.demo.Domain.RedisUserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class RedisUserDomainTests {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisUserRepository redisUserRepository;
    @Test
    public void testStringRedis() throws Exception{
        stringRedisTemplate.opsForValue().set("Cielo","Good Boy");
        Assert.assertEquals("Good Boy",stringRedisTemplate.opsForValue().get("Cielo"));
    }
    @Test
    public void testObjectRedis() throws Exception{
        RedisUser redisUser =new RedisUser();
        redisUser.setId(1l);
        redisUser.setName("Cielo");
        redisUser.setAge(22);
        redisUserRepository.save(redisUser);
        Assert.assertEquals(redisUser.getName(), redisUserRepository.findOne(1l).getName());
    }
}
