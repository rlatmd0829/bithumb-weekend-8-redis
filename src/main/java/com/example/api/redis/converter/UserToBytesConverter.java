package com.example.api.redis.converter;

import com.example.api.redis.domain.User;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;

@Component @ReadingConverter
public class UserToBytesConverter implements Converter<User, byte[]>{

    private final Jackson2JsonRedisSerializer<User> serializer;

    public UserToBytesConverter() {
        this.serializer = new Jackson2JsonRedisSerializer<User>(User.class);
        serializer.setObjectMapper(new ObjectMapper());
    }


    @Override
    public byte[] convert(User source) {
        return serializer.serialize(source);
    }
}
