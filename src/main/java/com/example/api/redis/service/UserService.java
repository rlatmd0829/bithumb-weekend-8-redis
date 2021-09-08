package com.example.api.redis.service;

import com.example.api.redis.domain.User;
import lombok.RequiredArgsConstructor;
import com.example.api.redis.converter.BytesToUserConverter;
import com.example.api.redis.converter.UserToBytesConverter;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class UserService {
    private final BytesToUserConverter bytesToUserConverter;
    private final UserToBytesConverter userToBytesConverter;
    private final RedisTemplate redisTemplate;

    public void save(User user){
        HashOperations operations = redisTemplate.opsForHash();
        operations.put("user", user.getId(), userToBytesConverter.convert(user));
    }

    public User findById(String id){
        HashOperations operations = redisTemplate.opsForHash();
        return bytesToUserConverter.convert((byte[]) operations.get("user",id));
    }
}
