package com.foogaro.data.configs;

import com.foogaro.util.Conf4J;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

@Configuration
public class RedisConfig {

    @Bean
    public JedisPool getJedisPool() {
        String host = Conf4J.get("redis.host", "localhost");
        String port = Conf4J.get("redis.port","6379");
        String uid = Conf4J.get("redis.auth.uid","");
        String pwd = Conf4J.get("redis.auth.pwd","");
        return (pwd.length() > 0) ? new JedisPool(host, Integer.valueOf(port), uid, pwd) : new JedisPool(host, Integer.valueOf(port));
    }

    @Bean(name = "RedisKeyspace")
    public String getKeyspace() { return Conf4J.get("redis.keyspace", "loremipsum"); }

}