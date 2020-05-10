package ru.itis.chat.services;

import ru.itis.chat.dto.ChatDto;
import ru.itis.chat.models.Chat;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ChatsService {
    List<Chat> getAllChats(HttpServletRequest req);
    Chat getChatById(Long id);
    void newChat(HttpServletRequest req, ChatDto dto);
}
