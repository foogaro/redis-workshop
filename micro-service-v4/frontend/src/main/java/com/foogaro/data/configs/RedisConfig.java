package com.foogaro.data.configs;

import com.foogaro.util.Conf4J;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.data.redis.stream.Subscription;
import redis.clients.jedis.JedisPool;

import java.time.Duration;

@Configuration
public class RedisConfig {

    @Value("${stream.key}")
    private String streamKey;

    @Autowired
    private StreamListener<String, ObjectRecord<String, Long>> streamListener;

    @Bean
    public StreamMessageListenerContainer listenerContainer(RedisConnectionFactory redisConnectionFactory) {
        var options = StreamMessageListenerContainer
                .StreamMessageListenerContainerOptions
                .builder()
                .pollTimeout(Duration.ofSeconds(1))
                .targetType(Long.class)
                .build();
        var listenerContainer = StreamMessageListenerContainer
                .create(redisConnectionFactory, options);
        return listenerContainer;
    }

    @Bean
    public JedisPool getJedisPool() {
        String host = Conf4J.get("spring.redis.host", "localhost");
        String port = Conf4J.get("spring.redis.port","6379");
        String uid = Conf4J.get("spring.redis.auth.uid","");
        String pwd = Conf4J.get("spring.redis.auth.pwd","");
        return (pwd.length() > 0) ? new JedisPool(host, Integer.valueOf(port), uid, pwd) : new JedisPool(host, Integer.valueOf(port));
    }

    @Bean
    public Subscription subscription(StreamMessageListenerContainer listenerContainer) {
        var subscription = listenerContainer.receive(
                StreamOffset.create(streamKey, ReadOffset.lastConsumed()),
                streamListener);
        return subscription;
    }
}