package com.foogaro.data.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;

@Service
public class RedisStreamService {

    @Value("${stream.key}")
    private String streamKey;

    @Autowired
    private StreamMessageListenerContainer listenerContainer;
    @Autowired
    private JedisPool jedisPool;

    public List<Long> readEntireStream() {
        List<Long> entireStream = new ArrayList<>();
        listenerContainer.stop(() -> {
            try (Jedis jedis = jedisPool.getResource()) {
                jedis.xrange(streamKey, null, null)
                        .stream()
                        .flatMap(streamEntry -> streamEntry.getFields().values().stream())
                        .forEach(s -> entireStream.add(Long.valueOf(s)));
            }
        });

        if (!listenerContainer.isRunning()) {
            listenerContainer.start();
        }
        return entireStream;
    }
}