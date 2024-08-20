package com.heartfoilo.demo.util;

import com.heartfoilo.demo.domain.webSocket.dto.StockSocketInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class RedisUtil {

    private final RedisTemplate<String, Object> redisTemplate;
    private final RedisTemplate<String, Object> redisBlackListTemplate;
    private final RedisTemplate<String, Object> stockInfoTemplate;

//    @Value("${jwt.expmin}")
    private int expMin;

    public void set(String key, Object o, int minutes) {
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer(o.getClass()));
        redisTemplate.opsForValue().set(key, o, minutes, TimeUnit.MINUTES);
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public boolean delete(String key) {
        return Boolean.TRUE.equals(redisTemplate.delete(key));
    }

    public boolean hasKey(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    public void setExcludeList(String key, Object o) {
        redisBlackListTemplate.setValueSerializer(new Jackson2JsonRedisSerializer(o.getClass()));
        redisBlackListTemplate.opsForValue().set(key, o, expMin, TimeUnit.MINUTES);
    }

    public Object getExcludeList(String key) {
        return redisBlackListTemplate.opsForValue().get(key);
    }

    public boolean deleteExcludeList(String key) {
        return Boolean.TRUE.equals(redisBlackListTemplate.delete(key));
    }

    public boolean hasKeyExcludeList(String key) {
        return Boolean.TRUE.equals(redisBlackListTemplate.hasKey(key));
    }

    public void setStockInfoTemplate(String key, Object o) {
        stockInfoTemplate.setValueSerializer(new Jackson2JsonRedisSerializer(o.getClass()));
        stockInfoTemplate.opsForValue().set(key, o, 1440, TimeUnit.MINUTES);
    }

    public StockSocketInfoDto getStockInfoTemplate(String key) {
        stockInfoTemplate.setValueSerializer(new Jackson2JsonRedisSerializer(StockSocketInfoDto.class));
        return (StockSocketInfoDto) stockInfoTemplate.opsForValue().get(key);
    }

    public boolean hasKeyEmail(String key) {
        return Boolean.TRUE.equals(stockInfoTemplate.hasKey(key));
    }



}