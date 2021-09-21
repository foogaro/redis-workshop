package com.foogaro.data.publishers;

import com.foogaro.data.TheAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class AlgorithmPublisher {

	@Autowired
	private TheAlgorithm theAlgorithm;
	@Autowired
	private JedisPool jedisPool;
	@Autowired @Qualifier("RedisKeyspace")
	private String keyspace;

	@Scheduled(fixedDelay = 5000l)
	public void result() {
		long number = theAlgorithm.result();
		try (Jedis jedis = jedisPool.getResource()) {
			jedis.publish(keyspace, String.valueOf(number));
			System.out.println("["+System.currentTimeMillis()+"] Published message \""+number+"\" to channel \"" + keyspace + "\"");
		}
	}
}
