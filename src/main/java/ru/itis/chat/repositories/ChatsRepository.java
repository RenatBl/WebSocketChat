package ru.itis.chat.repositories;

import org.springframework.stereotype.Repository;
import ru.itis.chat.models.Chat;
import ru.itis.chat.models.User;

import java.util.List;

@Repository
public interface ChatsRepository extends CrudRepository<Chat, Long> {
    List<Chat> findAllByUser(User user);
}
