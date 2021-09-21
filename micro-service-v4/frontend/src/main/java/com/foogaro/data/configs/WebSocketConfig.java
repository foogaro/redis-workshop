package com.foogaro.data.configs;

import com.foogaro.util.Conf4J;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Bean(name = "WebSocketDestination")
    public String getWebSocketDestination() { return Conf4J.get("websocket.destination", "/loremipsum-ai-stream"); }
    @Bean(name = "StompEndpoint")
    public String getStompEndpoint() { return Conf4J.get("stomp.endpoint", "/numbers-websocket"); }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker(getWebSocketDestination());
        config.setApplicationDestinationPrefixes("/");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
         registry.addEndpoint(getStompEndpoint());
         registry.addEndpoint(getStompEndpoint()).withSockJS();
    }

}
