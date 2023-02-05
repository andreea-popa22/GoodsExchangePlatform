package com.ge.exchange.chat;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.socket.config.annotation.*;

import java.security.Principal;
import java.util.ArrayList;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat").withSockJS();
    }
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app/post/{id}");
        registry.enableSimpleBroker("/topic/messages");
    }

//    @Override
//    public void configureClientInboundChannel(ChannelRegistration registration) {
//        registration.interceptors(new ChannelInterceptorAdapter() {
//            @Override
//            public Message<?> preSend(Message<?> message, MessageChannel channel) {
//                StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(message);
//                String username = headerAccessor.getNativeHeader("username").get(0);
//                if (!username.equals("miler@yahoo.com") && !username.equals("adorneanu@yahoo.com")) {
//                    throw new IllegalStateException("Only user1 and user2 are allowed to access the chat");
//                }
//                headerAccessor.setUser((Principal) new User(username, "", new ArrayList<>()));
//                return message;
//            }
//        });
//    }
}

