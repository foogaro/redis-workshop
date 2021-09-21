package com.foogaro.data.listeners;

import com.foogaro.data.services.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class RedisStreamListener implements StreamListener<String, ObjectRecord<String, Long>> {

    @Autowired
    private WebSocketService webSocketService;

    private final AtomicInteger consumedEventsCounter = new AtomicInteger(0);

    @Value("${stream.key}")
    private String streamKey;

    @Override
    public void onMessage(ObjectRecord<String, Long> message) {
        String stream = message.getStream();
        Long value = message.getValue();
        webSocketService.pushBack(value);
        System.out.println("Event " + value + " consumed from stream " + streamKey);
        consumedEventsCounter.incrementAndGet();
    }

    @Scheduled(fixedRate = 10000)
    public void publishedEvents(){
        System.out.println("Total consumed events :: " + consumedEventsCounter.get());
    }

}