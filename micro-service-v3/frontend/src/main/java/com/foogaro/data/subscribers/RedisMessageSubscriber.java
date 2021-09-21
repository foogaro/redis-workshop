package com.foogaro.data.subscribers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisMessageSubscriber implements MessageListener {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired @Qualifier("WebSocketDestination")
    private String webSocketDestination;

    public void onMessage(Message message, byte[] pattern) {
        String wsMessage = new String(message.getBody());
        System.out.println("Received message \"" + wsMessage + "\" on channel \"" + new String(message.getChannel()) + "\"");
        template.convertAndSend(webSocketDestination, wsMessage);
        System.out.println("Sent back message \"" + wsMessage + "\" to WebSocket channel");
    }

}