package ru.itis.chat.services;

import ru.itis.chat.dto.MessageDto;
import ru.itis.chat.models.Chat;
import ru.itis.chat.models.Message;

import java.util.List;

public interface MessagesService {
    List<Message> getAllMessagesByChat(Chat chat);
    void saveMessage(MessageDto messageDto);
}
