package ru.itis.chat.repositories;

import ru.itis.chat.models.Chat;
import ru.itis.chat.models.Message;

import java.util.List;

public interface MessagesRepository extends CrudRepository<Message, Long> {
    List<Message> getAllByChat(Chat chat);
}
