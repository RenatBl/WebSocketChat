package ru.itis.chat.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import ru.itis.chat.dto.MessageDto;
import ru.itis.chat.services.MessagesService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@EnableWebSocket
public class WebSocketMessagesHandler extends TextWebSocketHandler {

    private static final Map<Long, List<WebSocketSession>> sessions = new HashMap<>();

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MessagesService messagesService;

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> webSocketMessage) throws Exception {
        String messageText = (String) webSocketMessage.getPayload();
        MessageDto messageDto = objectMapper.readValue(messageText, MessageDto.class);

        messageDto.setDate(LocalDateTime.now().toString());
        messagesService.saveMessage(messageDto);

        messageDto.setCreationDate(LocalDateTime.now());
        String textMessage = objectMapper.writeValueAsString(messageDto);

        Long id = messageDto.getChatId();
        loadSession(id, session);

        sessions.get(id).forEach(webSocketSession -> {
            try {
                webSocketSession.sendMessage(new TextMessage(textMessage));
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        });
    }

    private void loadSession(Long id, WebSocketSession session) {
        List<WebSocketSession> currentSession;
        if ((currentSession = sessions.get(id)) != null) {
            currentSession.add(session);
        } else {
            currentSession = new ArrayList<>();
            currentSession.add(session);
            sessions.put(id, currentSession);
        }
    }
}
