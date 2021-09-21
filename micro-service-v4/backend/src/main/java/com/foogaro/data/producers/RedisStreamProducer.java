package com.foogaro.data.producers;

import com.foogaro.data.TheAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class RedisStreamProducer {

	@Autowired
	private TheAlgorithm theAlgorithm;
	@Autowired
	private ReactiveRedisTemplate<String,String> reactiveRedisTemplate;

	private final AtomicInteger publishedEventsCounter = new AtomicInteger(0);

	@Value("${stream.key}")
	private String streamKey;

	@Scheduled(fixedRateString= "${publish.rate}")
	public void publish() {
		long number = this.theAlgorithm.result();
		ObjectRecord<String, Long> record = StreamRecords
				.newRecord()
				.ofObject(number)
				.withStreamKey(streamKey);
		this.reactiveRedisTemplate
				.opsForStream()
				.add(record)
				.subscribe();
		System.out.println("Event " + number + " published in stream " + streamKey);
		publishedEventsCounter.incrementAndGet();
	}

	@Scheduled(fixedRate = 10000)
	public void publishedEvents(){
		System.out.println("Total published events :: " + publishedEventsCounter.get());
	}
}
