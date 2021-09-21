package com.foogaro.data.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired @Qualifier("WebSocketDestination")
    private String destination;

    public void pushBack(Long value) {
        template.convertAndSend(destination, value);
    }

}
