package com.heartfoilo.demo.config;

import com.heartfoilo.demo.domain.socket.handler.WebSocketClientHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketClientConfig implements WebSocketConfigurer {
    private static final String URL = "ws://ops.koreainvestment.com:21000";
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        WebSocketClient webSocketClient = new StandardWebSocketClient();

        WebSocketConnectionManager connectionManager = new WebSocketConnectionManager(webSocketClient,
            new WebSocketClientHandler(), URL);

        connectionManager.setAutoStartup(true);
        connectionManager.start();
    }
}
