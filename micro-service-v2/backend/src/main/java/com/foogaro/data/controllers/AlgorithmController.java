package com.foogaro.data.controllers;

import com.foogaro.data.TheAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.util.List;

@RestController
public class AlgorithmController {

	@Autowired
	private TheAlgorithm theAlgorithm;
	@Autowired
	private JedisPool jedisPool;
	@Autowired @Qualifier("RedisKeyspace")
	private String keyspace;

	@GetMapping("/results")
	public List<String> results(@RequestParam(value="maxValue", required = false) Long maxValue) {
		long result = theAlgorithm.result(maxValue);
		System.out.println("Generated " + result + ".");
		try (Jedis jedis = jedisPool.getResource()) {
			Pipeline pipeline = jedis.pipelined();
			pipeline.rpush(keyspace, Long.toString(result));
			pipeline.lrange(keyspace, 0, -1);
			List<String> results = (List<String>)pipeline.syncAndReturnAll().get(1);
			return results;
		}
	}
}
