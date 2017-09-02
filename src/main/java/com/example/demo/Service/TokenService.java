package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TokenService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    public String save(Long userId){
        String token= UUID.randomUUID().toString().replace("-","");
        stringRedisTemplate.opsForValue().set(token,userId.toString(),1, TimeUnit.HOURS);
        return token;
    }
    public Long get(String token){
        stringRedisTemplate.expire(token,1,TimeUnit.HOURS);
        return Long.parseLong(stringRedisTemplate.opsForValue().get(token));
    }
    public void delete(String token){
        stringRedisTemplate.delete(token);
    }
}
