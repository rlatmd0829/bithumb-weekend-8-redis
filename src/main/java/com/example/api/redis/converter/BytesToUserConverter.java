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
public class BytesToUserConverter implements Converter<byte[], User> {

    private final Jackson2JsonRedisSerializer<User> serializer;

    public BytesToUserConverter() { // 직렬화는 사람이 읽는 값을 컴퓨터가 읽을 수 있는 값 기계어로 바꿈
        this.serializer = new Jackson2JsonRedisSerializer<User>(User.class);
        serializer.setObjectMapper(new ObjectMapper());
    }


    @Override
    public User convert(byte[] source) {
        return serializer.deserialize(source); // 역직렬화는 바이트값을 인간이 인지하는 자연어 값으로 바꾼다
    }
}
